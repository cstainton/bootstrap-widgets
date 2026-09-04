#!/usr/bin/env node

import assert from "node:assert/strict";
import { spawn, spawnSync } from "node:child_process";
import { createServer } from "node:http";
import { createServer as createNetServer } from "node:net";
import { existsSync, mkdtempSync, readFileSync, rmSync, statSync } from "node:fs";
import { tmpdir } from "node:os";
import { extname, join, resolve, sep } from "node:path";
import { fileURLToPath } from "node:url";

const root = resolve(fileURLToPath(new URL("..", import.meta.url)));
const pages = resolve(process.env.PAGES_OUTPUT_DIR || join(root, "target/pages"));
const fixtureTargets = [
  { name: "GWT3", generation: 3, path: "/fixtures/gwt-bootstrap3/index.html" },
  { name: "GWT5", generation: 5, path: "/fixtures/gwt-bootstrap5/index.html" },
].filter((target) => !process.env.BROWSER_TEST_TARGET || target.name === process.env.BROWSER_TEST_TARGET);
const timeoutMs = Number(process.env.BROWSER_TEST_TIMEOUT_MS || 15000);
const caseFilter = process.env.BROWSER_TEST_CASE;

function findChrome() {
  const candidates = [
    process.env.CHROME_BIN,
    "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome",
    "google-chrome",
    "google-chrome-stable",
    "chromium",
    "chromium-browser",
  ].filter(Boolean);
  for (const candidate of candidates) {
    if (candidate.includes(sep) && existsSync(candidate)) {
      return candidate;
    }
    const located = spawnSync("sh", ["-c", `command -v ${candidate}`], { encoding: "utf8" });
    if (located.status === 0 && located.stdout.trim()) {
      return located.stdout.trim();
    }
  }
  throw new Error("Chrome or Chromium is required for browser behaviour tests");
}

function mimeType(path) {
  return {
    ".css": "text/css",
    ".html": "text/html",
    ".js": "text/javascript",
    ".json": "application/json",
    ".map": "application/json",
    ".png": "image/png",
    ".svg": "image/svg+xml",
    ".woff": "font/woff",
    ".woff2": "font/woff2",
  }[extname(path)] || "application/octet-stream";
}

function startStaticServer() {
  const server = createServer((request, response) => {
    try {
      const pathname = decodeURIComponent(new URL(request.url, "http://127.0.0.1").pathname);
      let path = resolve(pages, `.${pathname}`);
      if (path !== pages && !path.startsWith(`${pages}${sep}`)) {
        throw new Error("Path escaped the Pages root");
      }
      if (statSync(path).isDirectory()) {
        path = join(path, "index.html");
      }
      response.writeHead(200, { "Content-Type": mimeType(path), "Cache-Control": "no-store" });
      response.end(readFileSync(path));
    } catch (error) {
      response.writeHead(404, { "Content-Type": "text/plain" });
      response.end(String(error));
    }
  });
  return new Promise((resolveServer) => {
    server.listen(0, "127.0.0.1", () => resolveServer(server));
  });
}

function reservePort() {
  return new Promise((resolvePort, reject) => {
    const server = createNetServer();
    server.once("error", reject);
    server.listen(0, "127.0.0.1", () => {
      const { port } = server.address();
      server.close((error) => error ? reject(error) : resolvePort(port));
    });
  });
}

async function poll(operation, description, timeout = timeoutMs) {
  const deadline = Date.now() + timeout;
  let lastError;
  while (Date.now() < deadline) {
    try {
      const value = await operation();
      if (value) {
        return value;
      }
    } catch (error) {
      lastError = error;
    }
    await new Promise((resolveWait) => setTimeout(resolveWait, 50));
  }
  throw new Error(`Timed out waiting for ${description}${lastError ? `: ${lastError.message}` : ""}`);
}

class CdpClient {
  constructor(url) {
    this.url = url;
    this.nextId = 1;
    this.pending = new Map();
    this.handlers = new Map();
  }

  async connect() {
    this.socket = new WebSocket(this.url);
    await new Promise((resolveOpen, reject) => {
      this.socket.addEventListener("open", resolveOpen, { once: true });
      this.socket.addEventListener("error", reject, { once: true });
    });
    this.socket.addEventListener("message", (event) => this.receive(JSON.parse(event.data)));
  }

  receive(message) {
    if (message.id) {
      const pending = this.pending.get(message.id);
      if (!pending) return;
      this.pending.delete(message.id);
      if (message.error) pending.reject(new Error(message.error.message));
      else pending.resolve(message.result);
      return;
    }
    for (const handler of this.handlers.get(message.method) || []) {
      handler(message.params || {});
    }
  }

  on(method, handler) {
    const handlers = this.handlers.get(method) || [];
    handlers.push(handler);
    this.handlers.set(method, handlers);
  }

  send(method, params = {}) {
    const id = this.nextId++;
    return new Promise((resolveCommand, reject) => {
      this.pending.set(id, { resolve: resolveCommand, reject });
      this.socket.send(JSON.stringify({ id, method, params }));
    });
  }

  close() {
    this.socket.close();
  }
}

async function main() {
  for (const target of fixtureTargets) {
    if (!existsSync(join(pages, target.path))) {
      throw new Error(`Missing assembled ${target.name} browser fixtures under ${pages}`);
    }
  }

  const server = await startStaticServer();
  const appPort = server.address().port;
  const debugPort = await reservePort();
  const initialUrl = `http://127.0.0.1:${appPort}${fixtureTargets[0].path}`;
  const profile = mkdtempSync(join(tmpdir(), "bootstrap-widget-browser-"));
  const browser = spawn(findChrome(), [
    "--headless=new",
    "--no-sandbox",
    "--disable-dev-shm-usage",
    "--disable-gpu",
    "--disable-background-networking",
    `--remote-debugging-port=${debugPort}`,
    `--user-data-dir=${profile}`,
    initialUrl,
  ], { stdio: ["ignore", "ignore", "pipe"] });
  let browserStderr = "";
  browser.stderr.on("data", (chunk) => { browserStderr += chunk.toString(); });

  let cdp;
  try {
    const target = await poll(async () => {
      const response = await fetch(`http://127.0.0.1:${debugPort}/json/list`);
      if (!response.ok) return null;
      const targets = await response.json();
      return targets.find((candidate) => candidate.type === "page" && candidate.url.startsWith(initialUrl));
    }, "the Chrome DevTools target");

    cdp = new CdpClient(target.webSocketDebuggerUrl);
    await cdp.connect();
    const diagnostics = [];
    cdp.on("Runtime.exceptionThrown", ({ exceptionDetails }) => {
      diagnostics.push(`Uncaught exception: ${exceptionDetails?.text || "unknown"}`);
    });
    cdp.on("Log.entryAdded", ({ entry }) => {
      if (entry?.level === "error") diagnostics.push(`Browser log: ${entry.text}`);
    });
    cdp.on("Network.responseReceived", ({ response }) => {
      if (response?.status >= 400) diagnostics.push(`HTTP ${response.status}: ${response.url}`);
    });
    cdp.on("Network.loadingFailed", ({ errorText, type }) => {
      diagnostics.push(`Network failure (${type}): ${errorText}`);
    });

    await Promise.all([
      cdp.send("Page.enable"),
      cdp.send("Runtime.enable"),
      cdp.send("Log.enable"),
      cdp.send("Network.enable"),
    ]);
    await cdp.send("Emulation.setDeviceMetricsOverride", {
      width: 390,
      height: 1200,
      deviceScaleFactor: 3,
      mobile: true,
    });
    await cdp.send("Emulation.setTouchEmulationEnabled", { enabled: true, maxTouchPoints: 5 });

    async function evaluate(expression) {
      const response = await cdp.send("Runtime.evaluate", {
        expression,
        awaitPromise: true,
        returnByValue: true,
      });
      if (response.exceptionDetails) {
        throw new Error(response.exceptionDetails.exception?.description || response.exceptionDetails.text);
      }
      return response.result.value;
    }

    async function waitFor(expression, description, timeout) {
      return poll(async () => {
        try {
          return await evaluate(expression);
        } catch {
          return false;
        }
      }, description, timeout);
    }

    async function freshPage(fixtureUrl, name) {
      diagnostics.length = 0;
      await cdp.send("Page.navigate", { url: `${fixtureUrl}?case=${encodeURIComponent(name)}&t=${Date.now()}` });
      await waitFor(
        "document.body && document.body.dataset.fixturesReady === 'true' && window.__bootstrapWidgetFixturesReady === true",
        "the GWT fixtures to become ready",
      );
      assert.ok(await evaluate("navigator.maxTouchPoints > 0"), "Chrome touch emulation was not active");
      // Keep independent cases outside Chrome's double-tap recognition window.
      await new Promise((resolveWait) => setTimeout(resolveWait, 400));
    }

    async function tap(testId, allowPointerPassThrough = false) {
      const selector = `[data-testid=${JSON.stringify(testId)}]`;
      await evaluate(`new Promise((resolve) => {
        const element = document.querySelector(${JSON.stringify(selector)});
        if (!element) throw new Error('Missing fixture ${testId}');
        element.scrollIntoView({block: 'center', inline: 'center', behavior: 'instant'});
        requestAnimationFrame(() => {
          const rect = element.getBoundingClientRect();
          if (rect.top < 0 || rect.bottom > window.innerHeight) {
            window.scrollBy(0, rect.top + rect.height / 2 - window.innerHeight / 2);
          }
          requestAnimationFrame(() => resolve(true));
        });
      })`);
      const point = await evaluate(`(() => {
        const element = document.querySelector(${JSON.stringify(selector)});
        const hitTarget = element.matches('button, a, input, label')
          ? element
          : element.querySelector('label, button, a, input') || element;
        const rect = hitTarget.getBoundingClientRect();
        const x = rect.left + rect.width / 2;
        const y = rect.top + rect.height / 2;
        const hit = document.elementFromPoint(x, y);
        return {
          x,
          y,
          rect: {left: rect.left, top: rect.top, width: rect.width, height: rect.height},
          viewport: {width: window.innerWidth, height: window.innerHeight},
          scroll: {x: window.scrollX, y: window.scrollY},
          hitStack: document.elementsFromPoint(x, y).slice(0, 5).map((candidate) => ({
            tag: candidate.tagName,
            className: candidate.className,
            testId: candidate.getAttribute && candidate.getAttribute('data-testid')
          })),
          hitTestId: hit && hit.closest('[data-testid]')
            ? hit.closest('[data-testid]').getAttribute('data-testid') : null
        };
      })()`);
      if (!allowPointerPassThrough) {
        assert.equal(point.hitTestId, testId,
          `Touch point for ${testId} was obscured: ${JSON.stringify(point)}`);
      }
      await cdp.send("Input.synthesizeTapGesture", {
        x: point.x,
        y: point.y,
        duration: 50,
        gestureSourceType: "touch",
      });
      await new Promise((resolveWait) => setTimeout(resolveWait, 100));
    }

    async function tapOutside() {
      const point = await evaluate("({x: document.documentElement.clientWidth - 8, y: window.innerHeight - 8})");
      await cdp.send("Input.synthesizeTapGesture", {
        x: point.x,
        y: point.y,
        duration: 50,
        gestureSourceType: "touch",
      });
      await new Promise((resolveWait) => setTimeout(resolveWait, 100));
    }

    async function pressKey(key, code, keyCode) {
      const keyEvent = {
        key,
        code,
        windowsVirtualKeyCode: keyCode,
        nativeVirtualKeyCode: keyCode,
      };
      await cdp.send("Input.dispatchKeyEvent", { type: "rawKeyDown", ...keyEvent });
      await cdp.send("Input.dispatchKeyEvent", { type: "keyUp", ...keyEvent });
      await new Promise((resolveWait) => setTimeout(resolveWait, 100));
    }

    async function replaceText(testId, value) {
      await tap(testId);
      await evaluate(`document.querySelector('[data-testid=' +
        ${JSON.stringify(JSON.stringify(testId))} + ']').select()`);
      await cdp.send("Input.insertText", { text: value });
      await pressKey("Tab", "Tab", 9);
    }

    async function state(testId) {
      return evaluate(`(() => {
        const element = document.querySelector('[data-testid=' + ${JSON.stringify(JSON.stringify(testId))} + ']');
        const input = element && element.querySelector('input');
        return element && {
          active: element.classList.contains('active') || Boolean(element.querySelector('label.active')),
          open: element.classList.contains('open') || element.classList.contains('show')
            || Boolean(element.querySelector('.dropdown-toggle.show, .dropdown-menu.show')),
          shown: element.classList.contains('in') || element.classList.contains('show'),
          disabled: element.hasAttribute('disabled'),
          ariaPressed: element.getAttribute('aria-pressed'),
          ariaExpanded: element.getAttribute('aria-expanded'),
          ariaBusy: element.getAttribute('aria-busy'),
          clickCount: Number(element.dataset.clickCount || 0),
          changeCount: Number(element.dataset.changeCount || 0),
          value: element.dataset.value,
          checked: input ? input.checked : undefined,
          text: element.textContent.trim(),
          events: element.dataset.eventOrder || ''
        };
      })()`);
    }

    function testsFor(target) {
      const tests = [];
      const test = (id, name, body) => tests.push({ name: `${target.name}-TOUCH-${id} ${name}`, body });

      test("BTN-001/002", "toggle receives one click per activation and toggles twice", async () => {
        await tap("behaviour/toggle-button/basic");
        let result = await state("behaviour/toggle-button/basic");
        assert.equal(result.active, true);
        assert.equal(result.ariaPressed, "true");
        assert.equal(result.clickCount, 1);
        await tap("behaviour/toggle-button/basic");
        result = await state("behaviour/toggle-button/basic");
        assert.equal(result.active, false);
        assert.equal(result.ariaPressed, "false");
        assert.equal(result.clickCount, 2);
      });

      test("BTN-003", "checkbox value assignment honours the fire-events flag", async () => {
        await tap("behaviour/check-box-button/set-silent");
        let result = await state("behaviour/check-box-button/basic");
        assert.equal(result.checked, true, JSON.stringify(result));
        assert.equal(result.value, "true");
        assert.equal(result.changeCount, 0);

        await tap("behaviour/check-box-button/set-firing");
        result = await state("behaviour/check-box-button/basic");
        assert.equal(result.checked, false);
        assert.equal(result.value, "false");
        assert.equal(result.changeCount, 1);
      });

      test("BTN-004", "disabled toggle suppresses touch activation", async () => {
      await tap("behaviour/toggle-button/disabled", true);
      const result = await state("behaviour/toggle-button/disabled");
      assert.equal(result.active, false);
      assert.equal(result.clickCount, 0);
    });

      test("BTN-005", "checkbox buttons retain independent touch selections", async () => {
      await tap("behaviour/check-box-button/first");
      await tap("behaviour/check-box-button/third");
      const first = await state("behaviour/check-box-button/first");
      const second = await state("behaviour/check-box-button/second");
      const third = await state("behaviour/check-box-button/third");
      assert.equal(first.active, true);
      assert.equal(first.checked, true);
      assert.equal(first.value, "true");
      assert.equal(first.changeCount, 1);
      assert.equal(first.clickCount, 1);
      assert.equal(second.active, false);
      assert.equal(second.checked, false);
      assert.equal(second.value, "false");
      assert.equal(second.changeCount, 0);
      assert.equal(second.clickCount, 0);
      assert.equal(third.active, true);
      assert.equal(third.checked, true);
      assert.equal(third.value, "true");
      assert.equal(third.changeCount, 1);
      assert.equal(third.clickCount, 1);
    });

      test("BTN-006", "radio button remains exclusive and reports once", async () => {
      await tap("behaviour/radio-button/second");
      await waitFor(
        "document.querySelector('[data-testid=\"behaviour/radio-button/second\"]').dataset.value === 'true'",
        "the deferred GWT radio value handler",
      );
      const first = await state("behaviour/radio-button/first");
      const second = await state("behaviour/radio-button/second");
      assert.equal(first.active, false);
      assert.equal(first.checked, false);
      assert.equal(first.value, "false");
      assert.equal(second.active, true);
      assert.equal(second.checked, true);
      assert.equal(second.value, "true");
      assert.equal(second.changeCount, 1);
      assert.equal(second.clickCount, 1);
    });

      test("DRP-001/002", "dropdown opens and closes from touch", async () => {
        await evaluate(`(() => {
        window.__dropdownEvents = [];
        const dropdown = document.querySelector('[data-testid="behaviour/dropdown/basic"]');
        const events = ['show', 'shown', 'hide', 'hidden'];
        if (${target.generation} === 3) {
          for (const event of events) {
            window.jQuery(dropdown).on(event + '.bs.dropdown', () => window.__dropdownEvents.push(event));
          }
        } else {
          for (const event of events) {
            dropdown.addEventListener(event + '.bs.dropdown', () => window.__dropdownEvents.push(event));
          }
        }
      })()`);
      await tap("behaviour/dropdown/toggle");
      let dropdown = await state("behaviour/dropdown/basic");
      let toggle = await state("behaviour/dropdown/toggle");
      assert.equal(dropdown.open, true);
      assert.equal(toggle.ariaExpanded, "true");
      assert.deepEqual(await evaluate("window.__dropdownEvents"), ["show", "shown"]);
      await tapOutside();
      dropdown = await state("behaviour/dropdown/basic");
      toggle = await state("behaviour/dropdown/toggle");
      assert.equal(dropdown.open, false);
      assert.equal(toggle.ariaExpanded, "false");
      assert.deepEqual(await evaluate("window.__dropdownEvents"), ["show", "shown", "hide", "hidden"]);
    });

      test("DRP-003", "Escape closes the dropdown and restores toggle focus", async () => {
        await tap("behaviour/dropdown/toggle");
        await waitFor(
          `document.querySelector('[data-testid="behaviour/dropdown/basic"]').classList.contains('open')
            || document.querySelector('[data-testid="behaviour/dropdown/basic"]').classList.contains('show')
            || Boolean(document.querySelector('[data-testid="behaviour/dropdown/basic"] .dropdown-menu.show'))`,
          "the dropdown to open",
        );
        await evaluate(`(() => {
          const item = document.querySelector('[data-testid="behaviour/dropdown/action"]');
          const focusTarget = item.matches('a, button, input') ? item : item.querySelector('a, button, input');
          focusTarget.focus();
        })()`);
        await pressKey("Escape", "Escape", 27);
        const dropdown = await state("behaviour/dropdown/basic");
        const focusedFixture = await evaluate(`document.activeElement.closest('[data-testid]')
          && document.activeElement.closest('[data-testid]').getAttribute('data-testid')`);
        assert.equal(dropdown.open, false);
        assert.equal(focusedFixture, "behaviour/dropdown/toggle");
      });

      test("DRP-004", "disabled dropdown item suppresses action and navigation", async () => {
        const locationBefore = await evaluate("window.location.href");
        await tap("behaviour/dropdown/toggle");
        await tap("behaviour/dropdown/disabled-item");
        const disabled = await state("behaviour/dropdown/disabled-item");
        assert.equal(disabled.clickCount, 0);
        assert.equal(await evaluate("window.location.href"), locationBefore);
      });

      test("DRP-005", "split dropdown keeps its primary action independent", async () => {
        await tap("behaviour/dropdown/split-primary");
        const primary = await state("behaviour/dropdown/split-primary");
        assert.equal(primary.clickCount, 1);
        assert.equal(await evaluate(`getComputedStyle(document.querySelector(
          '[data-testid="behaviour/dropdown/split-menu"]'
        )).display`), "none");

        await tap("behaviour/dropdown/split-toggle");
        await waitFor(
          `getComputedStyle(document.querySelector(
            '[data-testid="behaviour/dropdown/split-menu"]'
          )).display !== 'none'`,
          "the split dropdown menu to open",
        );
        assert.equal((await state("behaviour/dropdown/split-primary")).clickCount, 1);
      });

      test("DRP-006", "dropup menu is positioned above its toggle", async () => {
        await tap("behaviour/dropdown/dropup-toggle");
        await waitFor(
          `getComputedStyle(document.querySelector(
            '[data-testid="behaviour/dropdown/dropup-menu"]'
          )).display !== 'none'`,
          "the dropup menu to open",
        );
        const geometry = await evaluate(`(() => {
          const toggle = document.querySelector('[data-testid="behaviour/dropdown/dropup-toggle"]')
            .getBoundingClientRect();
          const menu = document.querySelector('[data-testid="behaviour/dropdown/dropup-menu"]')
            .getBoundingClientRect();
          return {menuBottom: menu.bottom, toggleTop: toggle.top};
        })()`);
        assert.ok(geometry.menuBottom <= geometry.toggleTop + 1, JSON.stringify(geometry));
      });

      test("DRP-007", "constrained dropdown menu matches its owning group", async () => {
        await tap("behaviour/dropdown/aligned-width-toggle");
        await waitFor(
          `getComputedStyle(document.querySelector(
            '[data-testid="behaviour/dropdown/aligned-width-menu"]'
          )).display !== 'none'`,
          "the constrained dropdown menu to open",
        );
        const geometry = await evaluate(`(() => {
          const group = document.querySelector('[data-testid="behaviour/dropdown/aligned-width"]')
            .getBoundingClientRect();
          const menuElement = document.querySelector(
            '[data-testid="behaviour/dropdown/aligned-width-menu"]'
          );
          const menu = menuElement.getBoundingClientRect();
          return {
            groupWidth: group.width,
            menuWidth: menu.width,
            overflow: Array.from(menuElement.children).some(
              (item) => item.scrollWidth > item.clientWidth + 1
            )
          };
        })()`);
        assert.ok(Math.abs(geometry.menuWidth - geometry.groupWidth) <= 1, JSON.stringify(geometry));
        assert.equal(geometry.overflow, false);
      });

      test("FRM-001", "checkbox label toggles its input once", async () => {
        await tap("behaviour/form/checkbox-label");
        const result = await state("behaviour/form/checkbox-label");
        assert.equal(result.checked, true);
        assert.equal(result.value, "true");
        assert.equal(result.changeCount, 1);
        const semantics = await evaluate(`(() => {
          const fixture = document.querySelector('[data-testid="behaviour/form/checkbox-label"]');
          const input = fixture.querySelector('input');
          const label = fixture.querySelector('label');
          return {
            associated: label.contains(input) || (Boolean(input.id) && label.htmlFor === input.id),
            sourceMatch: fixture.dataset.sourceMatch
          };
        })()`);
        assert.equal(semantics.associated, true);
        assert.equal(semantics.sourceMatch, "true");
      });

      test("FRM-002", "radio label selects its input once", async () => {
        await tap("behaviour/form/radio-label");
        const result = await state("behaviour/form/radio-label");
        assert.equal(result.checked, true);
        assert.equal(result.value, "true");
        assert.equal(result.changeCount, 1);
        const semantics = await evaluate(`(() => {
          const fixture = document.querySelector('[data-testid="behaviour/form/radio-label"]');
          const input = fixture.querySelector('input');
          const label = fixture.querySelector('label');
          return {
            associated: label.contains(input) || (Boolean(input.id) && label.htmlFor === input.id),
            sourceMatch: fixture.dataset.sourceMatch
          };
        })()`);
        assert.equal(semantics.associated, true);
        assert.equal(semantics.sourceMatch, "true");
      });

      test("FRM-003", "text field label transfers focus to its control", async () => {
        await tap("behaviour/form/text-label/label");
        const result = await evaluate(`(() => {
          const label = document.querySelector('[data-testid="behaviour/form/text-label/label"]');
          const control = document.querySelector('[data-testid="behaviour/form/text-label/control"]');
          return {
            focused: document.activeElement === control,
            labelFor: label.htmlFor,
            controlId: control.id
          };
        })()`);
        assert.equal(result.focused, true);
        assert.ok(result.controlId);
        assert.equal(result.labelFor, result.controlId);
      });

      test("FRM-004", "committed user text reports one widget value event", async () => {
        await replaceText("behaviour/form/text-value", "updated");
        await waitFor(
          `document.querySelector('[data-testid="behaviour/form/text-value"]')
            .dataset.changeCount === '1'`,
          "the text value-change event",
        );
        const result = await evaluate(`(() => {
          const input = document.querySelector('[data-testid="behaviour/form/text-value"]');
          return {
            inputValue: input.value,
            reportedValue: input.dataset.value,
            changeCount: input.dataset.changeCount,
            sourceMatch: input.dataset.sourceMatch
          };
        })()`);
        assert.equal(result.inputValue, "updated");
        assert.equal(result.reportedValue, "updated");
        assert.equal(result.changeCount, "1");
        assert.equal(result.sourceMatch, "true");
      });

      test("FRM-005", "programmatic text assignment honours the fire-events flag", async () => {
        await tap("behaviour/form/values/set-silent");
        let result = await state("behaviour/form/values");
        assert.equal(result.value, "silent");
        assert.equal(result.changeCount, 0);

        await tap("behaviour/form/values/set-firing");
        result = await state("behaviour/form/values");
        assert.equal(result.value, "firing");
        assert.equal(result.changeCount, 1);
        assert.equal(await evaluate(`document.querySelector(
          '[data-testid="behaviour/form/values"]'
        ).dataset.sourceMatch`), "true");
      });

      test("FRM-006", "validation state message and ARIA state agree", async () => {
        await tap("behaviour/form/validation/action");
        await waitFor(
          `document.querySelector('[data-testid="behaviour/form/validation/message"]')
            .textContent.trim() === 'Required'`,
          "the validation message",
        );
        const result = await evaluate(`(() => {
          const group = document.querySelector('[data-testid="behaviour/form/validation"]');
          const control = document.querySelector('[data-testid="behaviour/form/validation/control"]');
          const message = document.querySelector('[data-testid="behaviour/form/validation/message"]');
          return {
            invalidClass: group.classList.contains('has-error') || control.classList.contains('is-invalid'),
            ariaInvalid: control.getAttribute('aria-invalid'),
            describedBy: (control.getAttribute('aria-describedby') || '').split(/\\s+/).filter(Boolean),
            messageId: message.id,
            messageVisible: message.getClientRects().length > 0,
            valid: group.dataset.validationResult
          };
        })()`);
        assert.equal(result.invalidClass, true);
        assert.equal(result.ariaInvalid, "true");
        assert.ok(result.messageId);
        assert.ok(result.describedBy.includes(result.messageId), JSON.stringify(result));
        assert.equal(result.messageVisible, true);
        assert.equal(result.valid, "false");

        await tap("behaviour/form/validation/clear");
        const cleared = await evaluate(`(() => {
          const group = document.querySelector('[data-testid="behaviour/form/validation"]');
          const control = document.querySelector('[data-testid="behaviour/form/validation/control"]');
          const message = document.querySelector('[data-testid="behaviour/form/validation/message"]');
          return {
            invalidClass: group.classList.contains('has-error') || control.classList.contains('is-invalid'),
            ariaInvalid: control.getAttribute('aria-invalid'),
            describedBy: control.getAttribute('aria-describedby'),
            message: message.textContent.trim(),
            valid: group.dataset.validationResult
          };
        })()`);
        assert.equal(cleared.invalidClass, false);
        assert.equal(cleared.ariaInvalid, null);
        assert.equal(cleared.describedBy, null);
        assert.equal(cleared.message, "");
        assert.equal(cleared.valid, "true");
      });

      test("FRM-007", "list selection exposes one selected value", async () => {
        await evaluate(`(() => {
          const select = document.querySelector('[data-testid="behaviour/form/list-selection"]');
          select.selectedIndex = 1;
          select.dispatchEvent(new Event('change', {bubbles: true}));
        })()`);
        const result = await evaluate(`(() => {
          const select = document.querySelector('[data-testid="behaviour/form/list-selection"]');
          return {
            value: select.dataset.value,
            selected: Array.from(select.options).filter((option) => option.selected).length,
            changeCount: select.dataset.changeCount,
            sourceMatch: select.dataset.sourceMatch
          };
        })()`);
        assert.equal(result.value, "Germany");
        assert.equal(result.selected, 1);
        assert.equal(result.changeCount, "1");
        assert.equal(result.sourceMatch, "true");
      });

      test("FRM-008", "radio controls with one name remain exclusive", async () => {
        await tap("behaviour/form/radio-group/first");
        await tap("behaviour/form/radio-group/second");
        const first = await state("behaviour/form/radio-group/first");
        const second = await state("behaviour/form/radio-group/second");
        const group = await state("behaviour/form/radio-group");
        assert.equal(first.checked, false);
        assert.equal(second.checked, true);
        assert.equal(group.value, "second");
      });

      test("FRM-009", "cancelled form submission reports once without navigating", async () => {
        const before = await evaluate(`(() => {
          const form = document.querySelector('[data-testid="behaviour/form/submission"]');
          const frameName = form.dataset.frameName;
          return {
            url: window.location.href,
            frameName,
            frameAttached: Array.from(document.querySelectorAll('iframe'))
              .some((frame) => frame.name === frameName)
          };
        })()`);
        assert.ok(before.frameName);
        assert.equal(before.frameAttached, true);
        await tap("behaviour/form/submission/action");
        const after = await evaluate(`(() => {
          const form = document.querySelector('[data-testid="behaviour/form/submission"]');
          return {
            url: window.location.href,
            submitCount: form.dataset.submitCount,
            sourceMatch: form.dataset.sourceMatch,
            frameAttached: Array.from(document.querySelectorAll('iframe'))
              .some((frame) => frame.name === form.dataset.frameName)
          };
        })()`);
        assert.equal(after.url, before.url);
        assert.equal(after.submitCount, "1");
        assert.equal(after.sourceMatch, "true");
        assert.equal(after.frameAttached, true);
      });

      test("COL-001/002/003", "collapse reports one ordered transition each way", async () => {
      await evaluate(`(() => {
        window.__collapseInputEvents = [];
        const toggle = document.querySelector('[data-testid="behaviour/collapse/toggle"]');
        for (const event of ['touchstart', 'touchend', 'pointerdown', 'pointerup', 'click']) {
          toggle.addEventListener(event, () => window.__collapseInputEvents.push(event), true);
        }
      })()`);
      await tap("behaviour/collapse/toggle");
      await waitFor(
        "document.querySelector('[data-testid=\"behaviour/collapse/basic\"]').classList.contains('in') || document.querySelector('[data-testid=\"behaviour/collapse/basic\"]').classList.contains('show')",
        "the collapse show transition",
      );
      let collapse = await state("behaviour/collapse/basic");
      let toggle = await state("behaviour/collapse/toggle");
      assert.equal(collapse.events, "show,shown");
      assert.equal(toggle.ariaExpanded, "true");
      assert.equal(toggle.clickCount, 1);
      await new Promise((resolveWait) => setTimeout(resolveWait, 500));
      await tap("behaviour/collapse/toggle");
      try {
        await waitFor(
          "!document.querySelector('[data-testid=\"behaviour/collapse/basic\"]').classList.contains('in') && !document.querySelector('[data-testid=\"behaviour/collapse/basic\"]').classList.contains('show') && !document.querySelector('[data-testid=\"behaviour/collapse/basic\"]').classList.contains('collapsing')",
          "the collapse hide transition",
        );
      } catch (error) {
        const failedCollapse = await state("behaviour/collapse/basic");
        const failedToggle = await state("behaviour/collapse/toggle");
        const inputEvents = await evaluate("window.__collapseInputEvents");
        const toggleHtml = await evaluate("document.querySelector('[data-testid=\"behaviour/collapse/toggle\"]').outerHTML");
        throw new Error(`${error.message}\nCollapse: ${JSON.stringify(failedCollapse)}\nToggle: ${JSON.stringify(failedToggle)}\nInput events: ${JSON.stringify(inputEvents)}\nToggle HTML: ${toggleHtml}`);
      }
      collapse = await state("behaviour/collapse/basic");
      toggle = await state("behaviour/collapse/toggle");
      assert.equal(collapse.events, "show,shown,hide,hidden");
      assert.equal(toggle.ariaExpanded, "false");
      assert.equal(toggle.clickCount, 2);
    });

      test("BTN-007", "loading state starts and restores after one touch", async () => {
        await tap("behaviour/button/loading");
        await waitFor(
          "document.querySelector('[data-testid=\"behaviour/button/loading\"]').textContent.trim() === 'Saving...'",
          "the deferred loading state",
        );
        let result = await state("behaviour/button/loading");
        assert.equal(result.text, "Saving...");
        assert.equal(result.disabled, true);
        assert.equal(result.ariaBusy, "true");
        assert.equal(result.clickCount, 1);
        await waitFor(
          "document.querySelector('[data-testid=\"behaviour/button/loading\"]').textContent.trim() === 'Save'",
          "the loading state reset",
        );
        result = await state("behaviour/button/loading");
        assert.equal(result.text, "Save");
        assert.equal(result.disabled, false);
        assert.equal(result.ariaBusy, null);
        assert.equal(result.clickCount, 1);
      });

      test("BTN-008", "button types round-trip without framework-class fallthrough", async () => {
        const buttons = await evaluate(`Array.from(
          document.querySelector('[data-testid="behaviour/button/types"]').children
        ).map((button) => ({
          assigned: button.dataset.assignedType,
          reported: button.dataset.reportedType,
          expectedClass: button.dataset.expectedClass,
          typeClasses: Array.from(button.classList).filter((name) => name.startsWith('btn-'))
        }))`);
        assert.ok(buttons.length >= 7);
        for (const button of buttons) {
          assert.equal(button.reported, button.assigned, `${button.assigned} did not round-trip`);
          assert.deepEqual(button.typeClasses, [button.expectedClass],
            `${button.assigned} did not map exclusively to ${button.expectedClass}`);
        }
      });

      test("BTN-009", "button size replacement removes the previous size", async () => {
        let result = await state("behaviour/button/sizes");
        assert.equal(result.text, "Sized");
        assert.equal(await evaluate(`document.querySelector(
          '[data-testid="behaviour/button/sizes"]'
        ).dataset.reportedSize`), "LARGE");
        assert.equal(await evaluate(`document.querySelector(
          '[data-testid="behaviour/button/sizes"]'
        ).classList.contains('btn-lg')`), true);

        await tap("behaviour/button/sizes/change");
        assert.equal(await evaluate(`document.querySelector(
          '[data-testid="behaviour/button/sizes"]'
        ).dataset.reportedSize`), "SMALL");
        assert.equal(await evaluate(`document.querySelector(
          '[data-testid="behaviour/button/sizes"]'
        ).classList.contains('btn-sm')`), true);
        assert.equal(await evaluate(`document.querySelector(
          '[data-testid="behaviour/button/sizes"]'
        ).classList.contains('btn-lg')`), false);
      });

      test("BGR-001", "button group preserves rendered insertion order", async () => {
        const labels = await evaluate(`Array.from(document.querySelector(
          '[data-testid="behaviour/button-group/basic"]'
        ).children).map((button) => button.textContent.trim())`);
        assert.deepEqual(labels, ["First", "Second", "Third"]);
      });

      test("BGR-002", "group size changes without changing child values", async () => {
        const selector = '[data-testid="behaviour/button-group/sizes"]';
        const before = await evaluate(`(() => {
          const group = document.querySelector(${JSON.stringify(selector)});
          return {
            size: group.dataset.reportedSize,
            large: group.classList.contains('btn-group-lg'),
            values: Array.from(group.children).map((child) => child.dataset.value)
          };
        })()`);
        assert.equal(before.size, "LARGE");
        assert.equal(before.large, true);
        assert.deepEqual(before.values, ["true", "false", "true"]);

        await tap("behaviour/button-group/sizes/change");
        const after = await evaluate(`(() => {
          const group = document.querySelector(${JSON.stringify(selector)});
          return {
            size: group.dataset.reportedSize,
            large: group.classList.contains('btn-group-lg'),
            small: group.classList.contains('btn-group-sm'),
            values: Array.from(group.children).map((child) => child.dataset.value)
          };
        })()`);
        assert.equal(after.size, "SMALL");
        assert.equal(after.large, false);
        assert.equal(after.small, true);
        assert.deepEqual(after.values, ["true", "false", "true"]);
      });

      test("BGR-003", "vertical button group stacks each child", async () => {
        const result = await evaluate(`(() => {
          const group = document.querySelector('[data-testid="behaviour/button-group/vertical"]');
          const tops = Array.from(group.children).map((child) => child.getBoundingClientRect().top);
          return {
            vertical: group.classList.contains('btn-group-vertical'),
            ordered: tops.every((top, index) => index === 0 || top > tops[index - 1])
          };
        })()`);
        assert.equal(result.vertical, true);
        assert.equal(result.ordered, true);
      });

      test("BGR-004", "nested dropdown remains owned and leaves siblings actionable", async () => {
        await tap("behaviour/button-group/nested-dropdown/toggle");
        await waitFor(
          `getComputedStyle(document.querySelector(
            '[data-testid="behaviour/button-group/nested-dropdown/menu"]'
          )).display !== 'none'`,
          "the nested dropdown menu to open",
        );
        const composition = await evaluate(`(() => {
          const group = document.querySelector('[data-testid="behaviour/button-group/nested-dropdown"]');
          const menu = document.querySelector('[data-testid="behaviour/button-group/nested-dropdown/menu"]');
          return {
            ownsMenu: menu.parentElement === group,
            menuVisible: getComputedStyle(menu).display !== 'none'
          };
        })()`);
        assert.equal(composition.ownsMenu, true);
        assert.equal(composition.menuVisible, true);

        if (target.generation === 3) {
          // Bootstrap 3 inserts a mobile backdrop; the first touch dismisses it.
          await tap("behaviour/button-group/nested-dropdown/sibling", true);
          await new Promise((resolveWait) => setTimeout(resolveWait, 500));
        }
        await tap("behaviour/button-group/nested-dropdown/sibling");
        const sibling = await state("behaviour/button-group/nested-dropdown/sibling");
        assert.equal(sibling.clickCount, 1);
      });

      test("BGR-005", "removing a grouped button clears DOM and widget ownership", async () => {
        await tap("behaviour/button-group/removal/action");
        const result = await evaluate(`(() => {
          const group = document.querySelector('[data-testid="behaviour/button-group/removal"]');
          return {
            childCount: group.children.length,
            removedPresent: Boolean(document.querySelector(
              '[data-testid="behaviour/button-group/removal/middle"]'
            )),
            parentNull: group.dataset.removedParentNull
          };
        })()`);
        assert.equal(result.childCount, 2);
        assert.equal(result.removedPresent, false);
        assert.equal(result.parentNull, "true");
      });

      return tests;
    }

    let total = 0;
    let failures = 0;
    for (const fixtureTarget of fixtureTargets) {
      const fixtureUrl = `http://127.0.0.1:${appPort}${fixtureTarget.path}`;
      const tests = testsFor(fixtureTarget);
      const selectedTests = caseFilter ? tests.filter((test) => test.name.includes(caseFilter)) : tests;
      const failuresBeforeTarget = failures;
      for (const current of selectedTests) {
        total++;
        await freshPage(fixtureUrl, current.name);
        try {
          await current.body();
          if (diagnostics.length) throw new Error(diagnostics.join("\n"));
          console.log(`ok - ${current.name}`);
        } catch (error) {
          failures++;
          console.error(`not ok - ${current.name}`);
          console.error(error.stack || error);
          if (diagnostics.length) console.error(diagnostics.join("\n"));
        }
      }
      const targetFailures = failures - failuresBeforeTarget;
      console.log(`${selectedTests.length - targetFailures}/${selectedTests.length} compiled ${fixtureTarget.name} touch tests passed`);
    }
    console.log(`${total - failures}/${total} compiled GWT mobile touch tests passed`);
    if (failures) process.exitCode = 1;
  } finally {
    cdp?.close();
    browser.kill("SIGTERM");
    await new Promise((resolveExit) => browser.once("exit", resolveExit));
    server.close();
    rmSync(profile, { recursive: true, force: true });
    if (process.exitCode && browserStderr) {
      console.error(browserStderr);
    }
  }
}

main().catch((error) => {
  console.error(error.stack || error);
  process.exitCode = 1;
});
