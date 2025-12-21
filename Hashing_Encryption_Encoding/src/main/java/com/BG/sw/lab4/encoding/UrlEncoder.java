package com.BG.sw.lab4.encoding;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class UrlEncoder {

    //1
    public String encodeUrl(String original) {
        byte[] bytes = original.getBytes(StandardCharsets.UTF_8);
        return Base64.getUrlEncoder().encodeToString(bytes);
    }

    public String decodeUrl(String base64) {
        byte[] decodedBytes = Base64.getUrlDecoder().decode(base64);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }

    //2
    public String encodeUnsafeBytes(byte[] input) {
        return Base64.getUrlEncoder().encodeToString(input); 
    }

    public byte[] decodeUnsafeBytes(String base64) {
        return Base64.getUrlDecoder().decode(base64);
    }

    //3
    public String encodeSimpleString(String original) {
        byte[] bytes = original.getBytes(StandardCharsets.UTF_8);
        return Base64.getUrlEncoder().encodeToString(bytes);
    }

    public String decodeSimpleString(String base64) {
        byte[] decodedBytes = Base64.getUrlDecoder().decode(base64);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
}
