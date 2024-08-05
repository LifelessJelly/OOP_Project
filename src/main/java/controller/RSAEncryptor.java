package controller;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAEncryptor {
    //Uses file location name to generate a hash for the secret key
    /**
     * Encrypts a given plaintext using AES encryption with a secret key derived from the file location name.
     *
     * @param plainText the plaintext to be encrypted
     * @param key the key used for encryption
     * @return a Base64 encoded string representing the encrypted plaintext
     * @throws RuntimeException if there are any issues during the encryption process
     */
    public static String encrypt(String plainText, String key) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(SHA256.getHasherByte().hashString(key), "AES");
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(new byte[16]));
            return Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8)));
        }
        catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | NoSuchAlgorithmException |
               NoSuchPaddingException | InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Decrypts a given ciphertext using AES decryption with a secret key derived from the file location name.
     *
     * @param cipherText the ciphertext to be decrypted
     * @param key the key used for decryption
     * @return the decrypted plaintext as a string
     * @throws RuntimeException if there are any issues during the decryption process
     */
    public static String decrypt(String cipherText, String key) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(SHA256.getHasherByte().hashString(key), "AES");
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(new byte[16]));
            return new String(cipher.doFinal(Base64.getDecoder().decode(cipherText)));
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | NoSuchAlgorithmException |
                 NoSuchPaddingException | InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }
    }
}
