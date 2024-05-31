package Subsystems;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Encryptor class
 * The encryptor class provides a simple interface for encoding any single string of characters, using SHA-256 to hash
 * the string. For use case in passwords and usernames, it's not too robust to brute force password crackers, but it's
 * good enough for this project
 * <p>
 * Usage:
 * <p>
 * String mySecretPassword = password123;
 * <p>
 * String hashedPassword = new Encryptor(mySecretPassword).getEncodedStr();
 */
public class PasswordHasher {
    private final String encodedStr;
    public PasswordHasher(String string){
        this(string, 69420);
    }
    public PasswordHasher(String string, int iterations){
        encodedStr = encrypt(string, iterations);
    }
    private static byte[] getSHAOutput(String string)  {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return digest.digest(string.getBytes(StandardCharsets.UTF_8));
    }
    private String encrypt(String string, int iterations){
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

