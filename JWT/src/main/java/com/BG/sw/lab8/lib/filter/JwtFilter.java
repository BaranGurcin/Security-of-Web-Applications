package com.BG.sw.lab8.lib.filter;

import tools.jackson.databind.ObjectMapper;

import static com.BG.sw.lab8.lib.http.HttpUtil.AUTHORIZATION;
import static com.BG.sw.lab8.lib.http.HttpUtil.HEADER_CONTENT_TYPE;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.BG.sw.lab8.lib.http.HttpRequest;
import com.BG.sw.lab8.lib.http.HttpResponse;
import com.BG.sw.lab8.lib.service.JwtService;



public class JwtFilter implements Filter {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final List<String> protectedPaths;

    public JwtFilter(List<String> protectedPaths) {
        this.protectedPaths = protectedPaths;
    }

    @Override
    public void doFilter(HttpRequest request, HttpResponse response, FilterChain chain) throws Exception{
        String path = request.getPath();

        boolean isprotected = protectedPaths.stream().anyMatch(path::startsWith);

        if (!isprotected) {
            chain.doFilter(request, response);
            return;
        }

        Optional<String> authHeader = request.getHeader(AUTHORIZATION);

        if (authHeader.isEmpty() || !authHeader.get().startsWith("Bearer ")) {
            response.setStatus(401, "Unauthorized");
            response.setHeader(HEADER_CONTENT_TYPE, "application/json");
            response.getWriter().println(objectMapper.writeValueAsString(
                    Map.of("message", "Missing or invalid Authorization header")
            ));
            response.flush();
            return;
        }

        String token = authHeader.orElse("").substring(7);
        final boolean isValidToken = JwtService.validateToken(token);
        
        
        if (!isValidToken) {
            response.setStatus(401, "Unauthorized");
            response.setHeader(HEADER_CONTENT_TYPE, "application/json");
            response.getWriter().println(objectMapper.writeValueAsString(
                    Map.of("message", "Invalid or expired token")
            ));
            response.flush();
            return;
        }

        chain.doFilter(request, response);
    }

    
}
