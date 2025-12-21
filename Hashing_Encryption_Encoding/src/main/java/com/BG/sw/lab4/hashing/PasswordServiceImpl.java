package com.BG.sw.lab4.hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordServiceImpl implements PasswordService {
    private final String algorithm;

    public PasswordServiceImpl(String algorithm) {
        if (algorithm == null) {
            throw new IllegalArgumentException("Algorithm cannot be null");
        }
        try {
            MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Invalid algorithm: " + algorithm);
        }
        this.algorithm = algorithm;
    }

    @Override
    public String hashPassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing error", e);
        }
    }

    @Override
    public boolean checkPassword(String password, String storedHash) {
        if (password == null || storedHash == null) {
            throw new IllegalArgumentException("Password and hash cannot be null");
        }
        return hashPassword(password).equals(storedHash);
    }
}