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
