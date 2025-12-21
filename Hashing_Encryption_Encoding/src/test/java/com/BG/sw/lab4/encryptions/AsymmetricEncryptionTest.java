package com.BG.sw.lab4.encryptions;

import org.junit.jupiter.api.Test;

import com.BG.sw.lab4.encryptions.AsymmetricEncryption;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AsymmetricEncryptionTest {
    @Test
    void testRSA() throws Exception {
        var keyPair = AsymmetricEncryption.generateKeyPair("RSA", 2048);
        var cipherText = AsymmetricEncryption.encrypt("asymmetric test", keyPair.publicKey(), keyPair.algorithm());
        var plainText = AsymmetricEncryption.decrypt(cipherText, keyPair.privateKey(), keyPair.algorithm());
        System.out.println("Cipher Text: " + cipherText);
        System.out.println("Plain Text: " + plainText);
        assertEquals("asymmetric test", plainText);
    }
}