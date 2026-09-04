/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 - 2018 GwtBootstrap3
 * %%
 * Modified from the GwtBootstrap3 original for the Bootstrap 5 track of
 * GWT Bootstrap: moved to the io.instanto.bootstrap5 namespace and
 * re-targeted at Bootstrap 5 markup, class names and JavaScript APIs. The
 * GwtBootstrap3 copyright above is retained as required by the Apache
 * License 2.0; the namespace changed, the attribution did not.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package io.instanto.bootstrap5.extras.markdown.client.ui;

import com.google.gwt.user.client.Timer;

import io.instanto.bootstrap5.client.ui.html.Div;
import io.instanto.bootstrap5.extras.markdown.client.Markdown;

/**
 * Displays Markdown as rendered HTML.
 *
 * <p>For applications that store Markdown and want to show it without a round
 * trip to a server-side renderer. The output is sanitised; see
 * {@link Markdown}.</p>
 */
public class MarkdownPanel extends Div {

    /** How long to keep waiting for the parser before giving up and showing the source. */
    private static final int READY_TIMEOUT_MILLIS = 5000;

    private static final int READY_POLL_MILLIS = 50;

    private String markdown = "";

    private Timer readyTimer;

    public MarkdownPanel() {
        addStyleName("gbm-markdown");
    }

    public MarkdownPanel(final String markdown) {
        this();
        setMarkdown(markdown);
    }

    public void setMarkdown(final String markdown) {
        this.markdown = markdown == null ? "" : markdown;
        render();
    }

    /**
     * Renders, or waits for the parser if it has not arrived yet.
     *
     * <p>The GWT module injects marked and DOMPurify as inline script text before the
     * application runs, so they are always ready. The TeaVM backend loads them by URL,
     * which is asynchronous, and a panel constructed during startup would otherwise
     * render its own source once and keep it. Rendering again when they land costs
     * nothing on GWT, where the first attempt already succeeds.</p>
     */
    private void render() {
        if (Markdown.isReady()) {
            stopWaiting();
            getElement().setInnerHTML(Markdown.toHtml(markdown));
            return;
        }
        // Show the source meanwhile; it is the honest fallback and stays readable.
        getElement().setInnerText(markdown);
        waitForParser();
    }

    private void waitForParser() {
        if (readyTimer != null) {
            return;
        }
        readyTimer = new Timer() {
            private int waited;

            @Override
            public void run() {
                waited += READY_POLL_MILLIS;
                if (Markdown.isReady()) {
                    stopWaiting();
                    getElement().setInnerHTML(Markdown.toHtml(markdown));
                } else if (waited >= READY_TIMEOUT_MILLIS) {
                    stopWaiting();
                }
            }
        };
        readyTimer.scheduleRepeating(READY_POLL_MILLIS);
    }

    private void stopWaiting() {
        if (readyTimer != null) {
            readyTimer.cancel();
            readyTimer = null;
        }
    }

    /** The Markdown source, as given. */
    public String getMarkdown() {
        return markdown;
    }

    /** The rendered HTML currently displayed. */
    public String getHTML() {
        return getElement().getInnerHTML();
    }
}
