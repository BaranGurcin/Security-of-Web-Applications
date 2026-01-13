package com.BG.sw.lab8.lib.service;

import java.util.Map;

import com.BG.sw.lab8.lib.dto.User;
import com.BG.sw.lab8.lib.security.SecurityUtil;

import tools.jackson.databind.ObjectMapper;


public class JwtService {
    private static final String KEY = "supppersecretkeyyy";
    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static boolean validateToken(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length != 3) {
                return false;
            }
            String header = parts[0];
            String payload = parts[1];
            String signature = parts[2];

            String expectedSignature = generateSignature(header, payload);
            return expectedSignature.equals(signature);
        } catch (Exception e) {
            return false;
        }

    }
    
    public static String generateToken(User user) {
        final String header = generateHeader();
        final String payload = generatePayload(user);
        final String signature = generateSignature(header, payload);
        return String.format("%s.%s.%s", header, payload, signature);
    }

    private static String generateSignature(String headerBase64Url, String payloadBase64Url) {
        String dataTosign = String.format("%s.%s", headerBase64Url, payloadBase64Url);
        byte[] encryptedHash = SecurityUtil.sign(dataTosign.getBytes(), KEY);
        return SecurityUtil.base64Encoding(encryptedHash);
    }

    private static String generatePayload(User user) {
        try {
            final String jsonString = objectMapper.writeValueAsString(Map.of(
                    "sub", user.getUsername(),
                    "role", user.getRole().name(),
                    "exp", System.currentTimeMillis() + 3600000,
                    "iat", System.currentTimeMillis()
            ));
            return SecurityUtil.base64Encoding(jsonString);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate JWT payload", e);
        }
    }

    private static String generateHeader() {
        try {
            final String jsonString = objectMapper.writeValueAsString(Map.of(
                    "alg", "HS256",
                    "typ", "JWT"
            ));
            return SecurityUtil.base64Encoding(jsonString);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate JWT header", e);
        }
    }


}
