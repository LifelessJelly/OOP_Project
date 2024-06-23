package Controller;

import Data.HR;
import Data.Manager;
import Subsystems.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


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
    LoginRequest(String username, String password, String domain) throws IOException {
        // Fetches the encrypted passwords

    }

    public boolean isLoginSuccessful() {
        return loginSuccess;
    }
}
