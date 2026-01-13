package com.BG.sw.lab8.lib.service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.BG.sw.lab8.lib.dto.Apikey;
import com.BG.sw.lab8.lib.security.SecurityUtil;

public class ApiKeyService {
    private final Map<String, Apikey> apiKeyStore = new ConcurrentHashMap<>();

    public Optional<Apikey> getApiKey(String key) {
        return Optional.ofNullable(apiKeyStore.get(key));
    }

    public Apikey createApiKey() {
        String keyValue = generateSecureToken();
        Apikey apikey = new Apikey(keyValue, Instant.now().plusSeconds(36000 * 60), true);
        apiKeyStore.put(keyValue, apikey);
        return apikey;
    }

    private String generateSecureToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[32];
        secureRandom.nextBytes(bytes);
        return SecurityUtil.base64Encoding(bytes);
    }
}