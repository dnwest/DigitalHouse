package com.group4.alucar.security.jwt.secret;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class KeyGenerator {
    private KeyGenerator() {
    }

    static KeyPair generateKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return keyPair;
    }
}
