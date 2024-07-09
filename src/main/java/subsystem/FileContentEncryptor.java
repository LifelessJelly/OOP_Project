package subsystem;

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

public class FileContentEncryptor {
    //Uses file location name to generate a hash for the secret key
    public static String encrypt(String plainText, String toFileLocation) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(SHA256.getHasherByte().hashString(toFileLocation), "AES");
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
    public static String decrypt(String cipherText, String fromFileLocation) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(SHA256.getHasherByte().hashString(fromFileLocation), "AES");
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
