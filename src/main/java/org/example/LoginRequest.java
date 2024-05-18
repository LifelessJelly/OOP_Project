package org.example;

import Subsystems.Encryptor;
import deprecate.HRDataBase;

import java.security.NoSuchAlgorithmException;


/**
 * Class LoginRequest
 * Handles login request from the frontend login page
 * <p>
 * LoginRequest(String username, String password, String domain)
 * Takes in username and password of the admin employee, and the domain ("HR" or "MANAGER") to access the specified database
 * [usage]
 * new LoginRequest("Bentillo John Matthew Reales", "PINOY_pride@123", "HR").isLoginSuccessful()
 */
// Redo this class
public class LoginRequest {
    public boolean loginSuccess;
    LoginRequest(String username, String password, String domain) throws NoSuchAlgorithmException {
        // Fetches the encrypted passwords
        switch (domain) {
            case "HR":
                //code
                break;
            case "MANAGER":
                // more code
                break;



        }
    }

    public boolean isLoginSuccessful() {
        return loginSuccess;
    }
}
