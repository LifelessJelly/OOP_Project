package org.example;

import java.security.NoSuchAlgorithmException;

public class LoginRequest {
    boolean loginSuccess;
    LoginRequest(String username, String password, SimpleDataBase dataBase) throws NoSuchAlgorithmException {
        loginSuccess = dataBase.matchCredentials(new Encryptor(username), new Encryptor(password));
    }
}
