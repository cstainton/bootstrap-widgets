package com.google.gwt.core.client;

import org.teavm.jso.browser.Window;

/** Defers work to a later browser task, matching the GWT scheduler surface. */
public abstract class Scheduler {

    public interface ScheduledCommand {
        void execute();
    }

    public interface RepeatingCommand {
        boolean execute();
    }

    private static final Scheduler INSTANCE = new Scheduler() {
        @Override
        public void scheduleDeferred(final ScheduledCommand cmd) {
            Window.setTimeout(cmd::execute, 0);
        }

        @Override
        public void scheduleFinally(final ScheduledCommand cmd) {
            Window.setTimeout(cmd::execute, 0);
        }

        @Override
        public void scheduleFixedDelay(final RepeatingCommand cmd, final int delayMs) {
            repeat(cmd, delayMs);
        }
    };

    public static Scheduler get() {
        return INSTANCE;
    }

    public abstract void scheduleDeferred(ScheduledCommand cmd);

    public abstract void scheduleFinally(ScheduledCommand cmd);

    public abstract void scheduleFixedDelay(RepeatingCommand cmd, int delayMs);

    private static void repeat(final RepeatingCommand cmd, final int delayMs) {
        Window.setTimeout(() -> {
            if (cmd.execute()) {
                repeat(cmd, delayMs);
            }
        }, delayMs);
    }
}
