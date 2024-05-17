package org.example;

import Subsystems.Encryptor;

import java.security.NoSuchAlgorithmException;

public class LoginRequest {
    boolean loginSuccess;
    LoginRequest(String username, String password, HRDataBase dataBase) throws NoSuchAlgorithmException {
        loginSuccess = dataBase.matchCredentials(new Encryptor(username), new Encryptor(password));
    }
}
