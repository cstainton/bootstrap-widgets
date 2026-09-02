package com.google.gwt.user.client;

import org.teavm.jso.browser.Window;

/** One-shot and repeating timer, matching GWT's Timer surface. */
public abstract class Timer {

    private int handle = -1;
    private boolean repeating;

    public abstract void run();

    public void schedule(final int delayMillis) {
        cancel();
        repeating = false;
        handle = Window.setTimeout(this::fire, delayMillis);
    }

    public void scheduleRepeating(final int periodMillis) {
        cancel();
        repeating = true;
        handle = Window.setInterval(this::run, periodMillis);
    }

    public void cancel() {
        if (handle == -1) {
            return;
        }
        if (repeating) {
            Window.clearInterval(handle);
        } else {
            Window.clearTimeout(handle);
        }
        handle = -1;
    }

    private void fire() {
        handle = -1;
        run();
    }
}
