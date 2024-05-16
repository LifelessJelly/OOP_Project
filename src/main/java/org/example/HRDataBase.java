package org.example;

import java.util.Objects;
import java.util.Vector;

public class HRDataBase {
    private final Vector<Encryptor> encryptedUsernames;
    private final Vector<Encryptor> encryptedPasswords;
    public HRDataBase() {
        encryptedUsernames = new Vector<>();
        encryptedPasswords = new Vector<>();
    }
    public void insertCredentials(Encryptor username, Encryptor password) {
        encryptedUsernames.add(username);
        encryptedPasswords.add(password);
    }
    public boolean matchCredentials(Encryptor username, Encryptor password) {
        for (int i = 0; i < encryptedUsernames.size(); i++) {
            if  (
                    Objects.equals(encryptedUsernames.elementAt(i).getEncodedStr(), username.getEncodedStr())
                            &&
                            Objects.equals(encryptedPasswords.elementAt(i).getEncodedStr(), password.getEncodedStr())
            )
            {
                return true;
            }
        }
        return false;
    }
}

