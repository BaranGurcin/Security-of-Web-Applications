package com.BG.sw.lab5.lib.filter;


import java.util.ArrayList;
import java.util.List;

import com.BG.sw.lab5.lib.http.HttpRequest;
import com.BG.sw.lab5.lib.http.HttpResponse;


public class FilterChain {

    private final List<Filter> filters;
    private int currentPosition = 0; 
    private boolean isChainCompleted = false;

    public FilterChain(List<Filter> filters) {
        this.filters = filters;
    }

    public void doFilter(HttpRequest request, HttpResponse response) throws Exception {
        if (filters != null && currentPosition < filters.size()) {
            Filter filter = filters.get(currentPosition);
            currentPosition++;
            filter.doFilter(request, response, this);
        } else {
            this.isChainCompleted = true;
        }
    }

    /**
     * Checks if the chain has been fully processed. If a filter stops the chain
     * (by not calling chain.doFilter()), this will return false.
     *
     * @return true if all filters have been invoked, false otherwise.
     */
    public boolean isFullyProcessed() {
        return this.isChainCompleted;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final List<Filter> filters = new ArrayList<>();

        public Builder() {
        }


        public Builder addFilter(Filter filter) {
            this.filters.add(filter);
            return this;
        }


        public List<Filter> build() {
            return List.copyOf(this.filters);
        }
    }
}