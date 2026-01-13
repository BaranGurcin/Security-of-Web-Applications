package com.BG.sw.lab8.lib.filter;


import com.BG.sw.lab8.lib.http.HttpRequest;
import com.BG.sw.lab8.lib.http.HttpResponse;

@FunctionalInterface
public interface Filter {
    void doFilter(HttpRequest request, HttpResponse response, FilterChain chain) throws Exception;
}
