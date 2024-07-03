package Subsystems;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
    private final boolean isHexRepresented;
    public Hasher(boolean isHexRepresentation){
        this.isHexRepresented = isHexRepresentation;             //setting of hex representation bool?
    }
    public byte[] hashString(String stringBeHashed){
        String currentString = stringBeHashed;
        byte[] hashHolder = new byte[32];
        for (int i = 0; i < 100000; i++){   //100k iterations?

            hashHolder = getSHAOutput(currentString);
            BigInteger bigInt = new BigInteger(1, hashHolder);
            StringBuilder hexOut = new StringBuilder(bigInt.toString(16));
            while (hexOut.length() < 32) {
                hexOut.insert(0, "0");
            }
            currentString = hexOut.toString();
        }
        if (isHexRepresented){
            return currentString.getBytes(StandardCharsets.UTF_8);
        }
        else {
            return hashHolder;
        }
    }


    private static byte[] getSHAOutput(String string)  {
        MessageDigest digest;
        try {
            //CHANGING THE ALGORITHM HERE WILL VOID ALL HASHED AND ENCRYPTED PERSONAL INFORMATION!
            digest = MessageDigest.getInstance("SHA3-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return digest.digest(string.getBytes(StandardCharsets.UTF_8));
    }
}
