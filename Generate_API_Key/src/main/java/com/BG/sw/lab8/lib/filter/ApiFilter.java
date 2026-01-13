package com.BG.sw.lab8.lib.filter;

import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.BG.sw.lab8.lib.dto.Apikey;
import com.BG.sw.lab8.lib.http.HttpRequest;
import com.BG.sw.lab8.lib.http.HttpResponse;
import com.BG.sw.lab8.lib.service.ApiKeyService;

@Slf4j
public class ApiFilter implements Filter {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ApiKeyService apiKeyService = new ApiKeyService();
    private final List<String> protectedPaths;

    public ApiFilter(List<String> protectedPaths) {
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

        Optional<String> apiKeyHeader = request.getHeader("X-API-KEY");
        if (apiKeyHeader.isEmpty()) {
            response.setStatus(401, "Unauthorized");
            response.setHeader("Content-Type", "application/json");
            try {
                response.getWriter().println(objectMapper.writeValueAsString(Map.of("message", "Missing 'API-KEY' header")));
            } catch (Exception e) {
                response.setStatus(500, "Internal Server Error");
            }
            response.flush();
            return;
        }

        final String apiKey = apiKeyHeader.get();
        Optional<Apikey> apiKeyOpt = apiKeyService.getApiKey(apiKey);

        if (apiKeyOpt.isEmpty()) {
            response.setStatus(403, "Forbidden");
            response.setHeader("Content-Type", "application/json");
            try {
                response.getWriter().println(objectMapper.writeValueAsString(Map.of("message", "Invalid API key")));
            } catch (Exception e) {
                response.setStatus(500, "Internal Server Error");
            }
            response.flush();
            return;
        }

        Apikey apikey = apiKeyOpt.get();

        if (!apikey.isActive() ) {
            response.setStatus(403, "Forbidden");
            response.setHeader("Content-Type", "application/json");
            try {
                response.getWriter().println(objectMapper.writeValueAsString(Map.of("message", "API key is inactive or expired")));
            } catch (Exception e) {
                response.setStatus(500, "Internal Server Error");
            }
            response.flush();
            return;
        }

        chain.doFilter(request, response);
    }
}