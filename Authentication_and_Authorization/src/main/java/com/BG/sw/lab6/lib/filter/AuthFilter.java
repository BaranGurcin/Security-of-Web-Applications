package com.BG.sw.lab6.lib.filter;

import java.util.Optional;

import com.BG.sw.lab6.lib.http.Cookie;
import com.BG.sw.lab6.lib.http.HttpRequest;
import com.BG.sw.lab6.lib.http.HttpResponse;
import com.BG.sw.lab6.lib.http.Session;
import com.BG.sw.lab6.lib.http.SessionManager;

import java.util.List;

public class AuthFilter implements Filter {

    private final List<String> protectedPaths;

    public AuthFilter(List<String> protectedPaths) {
        this.protectedPaths = protectedPaths;
    }

    @Override
    public void doFilter(HttpRequest request, HttpResponse response, FilterChain chain) throws Exception {
    String requestPath = request.getPath();
    boolean isProtected = protectedPaths.stream().anyMatch(requestPath::startsWith);

    if (!isProtected) {
        chain.doFilter(request, response);
        return;
    }

    final String sessionId = request.getCookie("SESSION_ID")
        .map(Cookie::getValue)
        .orElse(null);

    final Optional<Session> session = SessionManager.getSession(sessionId);

    if (session.isEmpty() || session.get().isExpired() || session.get().getAttribute("username") == null) {
        response.setStatus(302, "Found");
        response.setHeader("Location", "/login");
        return;
    }
    chain.doFilter(request, response);
 }
}