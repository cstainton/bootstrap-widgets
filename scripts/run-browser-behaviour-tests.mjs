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
];
const timeoutMs = Number(process.env.BROWSER_TEST_TIMEOUT_MS || 15000);

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
      height: 844,
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
    }

    async function tap(testId) {
      const selector = `[data-testid=${JSON.stringify(testId)}]`;
      const point = await evaluate(`(() => {
        const element = document.querySelector(${JSON.stringify(selector)});
        if (!element) throw new Error('Missing fixture ${testId}');
        element.scrollIntoView({block: 'center', inline: 'center'});
        const hitTarget = element.matches('button, a, input, label')
          ? element
          : element.querySelector('label, button, a, input') || element;
        const rect = hitTarget.getBoundingClientRect();
        return {x: rect.left + rect.width / 2, y: rect.top + rect.height / 2};
      })()`);
      await cdp.send("Input.synthesizeTapGesture", {
        x: point.x,
        y: point.y,
        duration: 50,
        gestureSourceType: "touch",
      });
      await new Promise((resolveWait) => setTimeout(resolveWait, 100));
    }

    async function state(testId) {
      return evaluate(`(() => {
        const element = document.querySelector('[data-testid=' + ${JSON.stringify(JSON.stringify(testId))} + ']');
        const input = element && element.querySelector('input');
        return element && {
          active: element.classList.contains('active') || Boolean(element.querySelector('label.active')),
          open: element.classList.contains('open') || element.classList.contains('show'),
          shown: element.classList.contains('in') || element.classList.contains('show'),
          disabled: element.hasAttribute('disabled'),
          ariaPressed: element.getAttribute('aria-pressed'),
          ariaExpanded: element.getAttribute('aria-expanded'),
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

      test("001", "toggle receives one click and toggles twice", async () => {
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

      test("002", "disabled toggle suppresses touch activation", async () => {
      await tap("behaviour/toggle-button/disabled");
      const result = await state("behaviour/toggle-button/disabled");
      assert.equal(result.active, false);
      assert.equal(result.clickCount, 0);
    });

      test("003", "checkbox button changes exactly once", async () => {
      await tap("behaviour/check-box-button/touch");
      const result = await state("behaviour/check-box-button/touch");
      assert.equal(result.active, true);
      assert.equal(result.checked, true);
      assert.equal(result.value, "true");
      assert.equal(result.changeCount, 1);
      assert.equal(result.clickCount, 1);
    });

      test("004", "radio button remains exclusive and reports once", async () => {
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

      test("005", "dropdown opens and closes from touch", async () => {
        await evaluate(`(() => {
        window.__dropdownEvents = [];
        const dropdown = document.querySelector('[data-testid="behaviour/dropdown/touch"]');
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
      let dropdown = await state("behaviour/dropdown/touch");
      let toggle = await state("behaviour/dropdown/toggle");
      assert.equal(dropdown.open, true);
      assert.equal(toggle.ariaExpanded, "true");
      assert.deepEqual(await evaluate("window.__dropdownEvents"), ["show", "shown"]);
      await tap("behaviour/dropdown/outside");
      dropdown = await state("behaviour/dropdown/touch");
      toggle = await state("behaviour/dropdown/toggle");
      assert.equal(dropdown.open, false);
      assert.equal(toggle.ariaExpanded, "false");
      assert.deepEqual(await evaluate("window.__dropdownEvents"), ["show", "shown", "hide", "hidden"]);
    });

      test("006", "collapse reports one ordered transition each way", async () => {
      await tap("behaviour/collapse/toggle");
      await waitFor(
        "document.querySelector('[data-testid=\"behaviour/collapse/touch\"]').classList.contains('in') || document.querySelector('[data-testid=\"behaviour/collapse/touch\"]').classList.contains('show')",
        "the collapse show transition",
      );
      let collapse = await state("behaviour/collapse/touch");
      let toggle = await state("behaviour/collapse/toggle");
      assert.equal(collapse.events, "show,shown");
      assert.equal(toggle.ariaExpanded, "true");
      assert.equal(toggle.clickCount, 1);
      await tap("behaviour/collapse/toggle");
      await waitFor(
        "!document.querySelector('[data-testid=\"behaviour/collapse/touch\"]').classList.contains('in') && !document.querySelector('[data-testid=\"behaviour/collapse/touch\"]').classList.contains('show') && !document.querySelector('[data-testid=\"behaviour/collapse/touch\"]').classList.contains('collapsing')",
        "the collapse hide transition",
      );
      collapse = await state("behaviour/collapse/touch");
      toggle = await state("behaviour/collapse/toggle");
      assert.equal(collapse.events, "show,shown,hide,hidden");
      assert.equal(toggle.ariaExpanded, "false");
      assert.equal(toggle.clickCount, 2);
    });

      test("007", "loading state reacts to one touch", async () => {
      await tap("behaviour/button/loading-touch");
      await waitFor(
        "document.querySelector('[data-testid=\"behaviour/button/loading-touch\"]').textContent.trim() === 'Saving...'",
        "the deferred loading state",
      );
      const result = await state("behaviour/button/loading-touch");
      assert.equal(result.text, "Saving...");
      assert.equal(result.disabled, true);
      assert.equal(result.clickCount, 1);
    });

      return tests;
    }

    let total = 0;
    let failures = 0;
    for (const fixtureTarget of fixtureTargets) {
      const fixtureUrl = `http://127.0.0.1:${appPort}${fixtureTarget.path}`;
      const tests = testsFor(fixtureTarget);
      for (const current of tests) {
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
      console.log(`${tests.length - failures}/${tests.length} compiled ${fixtureTarget.name} touch tests passed`);
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
