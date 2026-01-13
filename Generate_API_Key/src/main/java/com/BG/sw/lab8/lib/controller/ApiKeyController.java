package com.BG.sw.lab8.lib.controller;

import tools.jackson.databind.ObjectMapper;

import static com.BG.sw.lab8.lib.http.HttpUtil.HEADER_CONTENT_TYPE;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;

import com.BG.sw.lab8.lib.BaseController;
import com.BG.sw.lab8.lib.dto.Apikey;
import com.BG.sw.lab8.lib.http.HttpRequest;
import com.BG.sw.lab8.lib.http.HttpResponse;
import com.BG.sw.lab8.lib.service.ApiKeyService;

public class ApiKeyController extends BaseController {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final ApiKeyService apiKeyService = new ApiKeyService();

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        try {
            response.setStatus(200, "OK");
            response.setHeader(HEADER_CONTENT_TYPE, "text/html; charset=UTF-8");

            var path = Path.of("src/main/resources/static/apikey.html");
            var writer = response.getWriter();
            try (var bufferedReader = Files.newBufferedReader(path)) {
                bufferedReader.transferTo(writer);
            }

        } catch (Exception e) {
            response.setStatus(500, "Internal Server Error");
            response.getWriter().println("Server error");
        }
        
    }

    @Override
    public void doPut(HttpRequest request, HttpResponse response) {
        Optional<String> param = request.getParam("api-key-value");
        if (param.isEmpty()) {
            response.setStatus(400, "Bad Request");
            response.getWriter().println(objectMapper.writeValueAsString(Map.of("message", "Missing 'api-key-value' paramater")));
            return;
        }

        String apiKeyValue = param.get();
        Optional<Apikey> apiKeyOpt = apiKeyService.getApiKey(apiKeyValue);

        if (apiKeyOpt.isEmpty()) {
            response.setStatus(404, "Not Found");
            response.getWriter().println(objectMapper.writeValueAsString(Map.of("message", "Invalid API key")));
            return;
        }

        response.setStatus(200, "OK");
        response.setHeader(HEADER_CONTENT_TYPE, "application/json");


        String jsonResponse = objectMapper.writeValueAsString(apiKeyOpt.get());
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {
        try { 
            var apiKey = apiKeyService.createApiKey();
            
            String jsonResponse = objectMapper.writeValueAsString(apiKey);
            response.setStatus(201, "Created");
            response.setHeader(HEADER_CONTENT_TYPE, "application/json");
            response.getWriter().println(jsonResponse);
            
        } catch (Exception e) {
            response.setStatus(500, "Internal Server Error");
            response.getWriter().println("Server error");
        }
    }

    
}
