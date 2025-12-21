package com.BG.sw.lab4.encoding;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BasicEncoder {

    //1
    public String encodeString(String original) {
        return Base64.getEncoder().encodeToString(original.getBytes(StandardCharsets.UTF_8));
    }

    public String decodeString(String expectedEncoded) {
        byte[] decoded = Base64.getDecoder().decode(expectedEncoded);
        return new String(decoded, StandardCharsets.UTF_8);
    }

    //2
    public String encodeBytes(byte[] original) {
        return Base64.getEncoder().encodeToString(original);
    }

    public byte[] decodeBytes(String expectedEncoded) {
        return Base64.getDecoder().decode(expectedEncoded);
    }

    //3
    public String encodeEmpty() {
        return encodeString("");
    }

    public String decodeEmpty(String expectedEncoded) {
        return decodeString(expectedEncoded);
    }
}
