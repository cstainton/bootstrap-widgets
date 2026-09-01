(function (global) {
  'use strict';

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

  document.addEventListener('click', function (event) {
    var trigger = closest(event.target, '[data-toggle]');
    if (!trigger) {
      return;
    }

    var toggle = trigger.getAttribute('data-toggle');
    if (toggle === 'dropdown' && bootstrap.Dropdown) {
      event.preventDefault();
      bootstrap.Dropdown.getOrCreateInstance(trigger).toggle();
      return;
    }

    if (toggle === 'collapse' && bootstrap.Collapse) {
      var selector = selectorFor(trigger);
      if (!selector) {
        return;
      }
      var target = document.querySelector(selector);
      if (target) {
        event.preventDefault();
        bootstrap.Collapse.getOrCreateInstance(target, { toggle: false }).toggle();
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
