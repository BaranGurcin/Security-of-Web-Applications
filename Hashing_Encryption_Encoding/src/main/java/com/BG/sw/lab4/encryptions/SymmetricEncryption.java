package com.BG.sw.lab4.encryptions;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class SymmetricEncryption {

    private final String algorithm;
    private final SecretKeySpec keySpec;

    public SymmetricEncryption(String algorithm, String key) throws Exception {
        this.algorithm = algorithm;

        byte[] keyBytes = key.getBytes("UTF-8");
        int keyLength = switch (algorithm) {
            case "AES" -> 16;
            case "DES" -> 8;
            case "Blowfish" -> Math.min(keyBytes.length, 16);
            default -> throw new IllegalArgumentException("Unsupported algorithm: " + algorithm);
        };
        byte[] keyFixed = new byte[keyLength];
        System.arraycopy(keyBytes, 0, keyFixed, 0, Math.min(keyBytes.length, keyLength));

        keySpec = new SecretKeySpec(keyFixed, algorithm);
    }

    public String encrypt(String plainText) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public String decrypt(String cipherText) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decoded = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted, "UTF-8");
    }
}
