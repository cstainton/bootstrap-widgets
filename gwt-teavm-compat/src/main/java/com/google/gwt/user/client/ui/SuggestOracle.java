package com.google.gwt.user.client.ui;

import java.util.Collection;
import java.util.List;

/** Supplies completion suggestions for a {@link SuggestBox}. */
public abstract class SuggestOracle {

    /** One completion offered to the user. */
    public interface Suggestion {
        String getDisplayString();

        String getReplacementString();
    }

    /** A request for suggestions matching a query. */
    public static class Request {

        private String query;
        private int limit = 20;

        public Request() {
        }

        public Request(final String query) {
            this.query = query;
        }

        public Request(final String query, final int limit) {
            this.query = query;
            this.limit = limit;
        }

        public String getQuery() {
            return query;
        }

        public void setQuery(final String query) {
            this.query = query;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(final int limit) {
            this.limit = limit;
        }
    }

    /** The suggestions produced for a {@link Request}. */
    public static class Response {

        private Collection<? extends Suggestion> suggestions;

        public Response() {
        }

        public Response(final Collection<? extends Suggestion> suggestions) {
            this.suggestions = suggestions;
        }

        public Collection<? extends Suggestion> getSuggestions() {
            return suggestions;
        }

        public void setSuggestions(final Collection<? extends Suggestion> suggestions) {
            this.suggestions = suggestions;
        }
    }

    /** Notified when suggestions are ready. */
    public interface Callback {
        void onSuggestionsReady(Request request, Response response);
    }

    public abstract void requestSuggestions(Request request, Callback callback);

    public void requestDefaultSuggestions(final Request request, final Callback callback) {
        requestSuggestions(request, callback);
    }

    public boolean isDisplayStringHTML() {
        return false;
    }
}
