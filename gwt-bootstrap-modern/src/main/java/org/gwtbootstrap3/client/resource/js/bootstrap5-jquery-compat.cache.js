(function (global) {
  'use strict';

  global.gwtBootstrap3CompatibilityLoaded = true;

  var $ = global.jQuery;
  var bootstrap = global.bootstrap;

  if (!$ || !bootstrap) {
    return;
  }

  function eachElement(collection, callback) {
    return collection.each(function () {
      callback(this);
    });
  }

  function installPlugin(name, Component, defaults) {
    if (!Component || $.fn[name]) {
      return;
    }

    $.fn[name] = function (option) {
      var args = Array.prototype.slice.call(arguments, 1);
      var result = this;

      eachElement(this, function (element) {
        var config = typeof option === 'object' && option !== null ? option : (defaults || {});
        var instance = Component.getOrCreateInstance(element, config);

        if (typeof option === 'string') {
          if (option === 'destroy') {
            instance.dispose();
          } else if (option === 'fixTitle') {
            return;
          } else if (typeof instance[option] === 'function') {
            var value = instance[option].apply(instance, args);
            if (value !== undefined) {
              result = value;
            }
          }
        }
      });

      return result;
    };

    $.fn[name].Constructor = Component;
    $.fn[name].noConflict = function () {
      return $.fn[name];
    };
  }

  installPlugin('alert', bootstrap.Alert);
  installPlugin('button', bootstrap.Button);
  installPlugin('carousel', bootstrap.Carousel);
  installPlugin('collapse', bootstrap.Collapse, { toggle: false });
  installPlugin('dropdown', bootstrap.Dropdown);
  installPlugin('modal', bootstrap.Modal);
  installPlugin('popover', bootstrap.Popover);
  installPlugin('scrollspy', bootstrap.ScrollSpy);
  installPlugin('tab', bootstrap.Tab);
  installPlugin('tooltip', bootstrap.Tooltip);

  function selectorFor(element) {
    var selector = element.getAttribute('data-target') || element.getAttribute('href');
    if (!selector || selector === '#') {
      return null;
    }
    return selector;
  }

  function matches(element, selector) {
    if (!element || element.nodeType !== 1) {
      return false;
    }

    var matcher = element.matches || element.webkitMatchesSelector || element.msMatchesSelector;
    return matcher ? matcher.call(element, selector) : false;
  }

  function closest(element, selector) {
    while (element && element !== document) {
      if (matches(element, selector)) {
        return element;
      }
      element = element.parentElement;
    }
    return null;
  }

  function triggerJQueryEvent(element, name) {
    if (global.jQuery) {
      global.jQuery(element).trigger(name);
    }
  }

  function toggleLegacyCollapse(trigger, target) {
    var willShow = !target.classList.contains('show');

    target.classList.add('collapse');
    target.classList.remove('collapsing');
    target.classList.toggle('show', willShow);
    target.style.height = '';
    target.setAttribute('aria-expanded', String(willShow));
    trigger.setAttribute('aria-expanded', String(willShow));
    trigger.classList.toggle('collapsed', !willShow);

    triggerJQueryEvent(target, willShow ? 'shown.bs.collapse' : 'hidden.bs.collapse');
  }

  function toggleLegacyDropdown(trigger) {
    var parent = closest(trigger, '.dropdown') || trigger.parentElement;
    if (!parent) {
      return;
    }

    var menu = parent.querySelector('.dropdown-menu');
    var willShow = !parent.classList.contains('open') && !(menu && menu.classList.contains('show'));

    eachElement($('.dropdown.open, .dropdown.show'), function (element) {
      if (element !== parent) {
        element.classList.remove('open', 'show');
        var openMenu = element.querySelector('.dropdown-menu');
        if (openMenu) {
          openMenu.classList.remove('show');
        }
      }
    });

    parent.classList.toggle('open', willShow);
    parent.classList.toggle('show', willShow);
    trigger.setAttribute('aria-expanded', String(willShow));

    if (menu) {
      menu.classList.toggle('show', willShow);
    }

    triggerJQueryEvent(parent, willShow ? 'shown.bs.dropdown' : 'hidden.bs.dropdown');
  }

  document.addEventListener('click', function (event) {
    var trigger = closest(event.target, '[data-toggle]');
    if (!trigger) {
      return;
    }

    var toggle = trigger.getAttribute('data-toggle');
    if (toggle === 'dropdown') {
      event.preventDefault();
      toggleLegacyDropdown(trigger);
      return;
    }

    if (toggle === 'collapse') {
      var selector = selectorFor(trigger);
      if (!selector) {
        return;
      }
      var target = document.querySelector(selector);
      if (target) {
        event.preventDefault();
        toggleLegacyCollapse(trigger, target);
      }
    }
  });

  $.fn.emulateTransitionEnd = $.fn.emulateTransitionEnd || function (duration) {
    var called = false;
    var collection = this;
    this.one('transitionend', function () {
      called = true;
    });
    global.setTimeout(function () {
      if (!called) {
        collection.trigger('transitionend');
      }
    }, duration || 0);
    return this;
  };
})(window);
