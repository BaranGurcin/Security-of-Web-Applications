package com.BG.sw.lab5.lib.filter;


import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.BG.sw.lab5.lib.http.HttpRequest;
import com.BG.sw.lab5.lib.http.HttpResponse;

@Slf4j
public class Logging implements Filter {
private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    @Override
    public void doFilter(HttpRequest request, HttpResponse response, FilterChain chain) throws Exception {
        String timestamp = LocalDateTime.now().format(DTF);

        log.info("Request received: [{}] {} {}", timestamp, request.getMethod(), request.getPath());

        chain.doFilter(request, response);

        log.info("Response sent: [{}] {} {} → {} {}", 
                timestamp,
                request.getMethod(),
                request.getPath(),
                response.getStatusCode(),
                response.getStatusMessage());
    }
}