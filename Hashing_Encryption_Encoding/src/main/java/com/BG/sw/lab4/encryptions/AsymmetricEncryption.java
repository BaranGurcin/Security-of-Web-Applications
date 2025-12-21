package com.BG.sw.lab4.encryptions;

import javax.crypto.Cipher;
import java.security.*;

import java.util.Base64;

public class AsymmetricEncryption {

    // Key 
    public record KeyPairHolder(PublicKey publicKey, PrivateKey privateKey, String algorithm) {}

    //RSA 
    public static KeyPairHolder generateKeyPair(String algorithm, int KeySize) throws Exception {
        KeyPairGenerator KeyGen = KeyPairGenerator.getInstance(algorithm);
        KeyGen.initialize(KeySize);
        KeyPair pair = KeyGen.generateKeyPair();
        return new KeyPairHolder(pair.getPublic(), pair.getPrivate(), algorithm);
    }

    // Encrypt
    public static String encrypt(String plainText, PublicKey publicKey, String algorithm) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm); 
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    // Decrypt 
    public static String decrypt(String cipherText, PrivateKey privateKey, String algorithm) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decoded = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted, "UTF-8");
    }
}
