package org.example;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {
    private final String encodedStr;
    public Encryptor(String string) throws NoSuchAlgorithmException {
        this(string, 69420);
    }
    public Encryptor(String string, int iterations) throws NoSuchAlgorithmException {
        encodedStr = encrypt(string, iterations);
    }
    private static byte[] getSHAOutput(String string) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(string.getBytes(StandardCharsets.UTF_8));
    }
    private String encrypt(String string, int iterations) throws NoSuchAlgorithmException {
        String currentString = string;
        for (int i = 0; i < iterations; i++) {
            byte[] hashHolder;

            hashHolder = getSHAOutput(currentString);

            BigInteger bigInt = new BigInteger(1, hashHolder);
            StringBuilder hexOut = new StringBuilder(bigInt.toString(16));
            while (hexOut.length() < 32) {
                hexOut.insert(0, "0");
            }
            currentString = hexOut.toString();
        }
        return currentString;

    }
    public String getEncodedStr() {
        return encodedStr;
    }
}

