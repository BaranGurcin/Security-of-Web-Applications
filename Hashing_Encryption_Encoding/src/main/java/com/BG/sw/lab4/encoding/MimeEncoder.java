package com.BG.sw.lab4.encoding;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MimeEncoder {

    //1
    public String encodeLongString(String original) {
        byte[] bytes = original.getBytes(StandardCharsets.UTF_8);
        return Base64.getMimeEncoder().encodeToString(bytes);
    }

    public String decodeLongString(String expectedEncoded) {
        byte[] decodedBytes = Base64.getMimeDecoder().decode(expectedEncoded); 
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }

    //2
    public String encodeShortString(String original) {
        byte[] bytes = original.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(bytes); 
    }

    public String decodeShortString(String expectedEncoded) {
        byte[] decodedBytes = Base64.getDecoder().decode(expectedEncoded);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }

    //3
    public String encodeUnsafeBytes(byte[] original) {
        return Base64.getEncoder().encodeToString(original);
    }

    public byte[] decodeUnsafeBytes(String expectedEncoded) {
        return Base64.getDecoder().decode(expectedEncoded);
    }
}
