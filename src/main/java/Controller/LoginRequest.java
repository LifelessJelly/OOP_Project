package Controller;

import Data.HRModel;
import Data.ManagerModel;
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
        switch (domain) {
            case "HR":
                HRModel hrmodel = jsonReaderWriter.jsonToModel(Files.readString(Paths.get("./HR_Data.json")), HRModel.class);
                var hrList = hrmodel.getHRs();
                for (HRModel.HR hr : hrList) {
                    if (hr.checkCredentials(username, password)){
                        loginSuccess = true;
                        break;
                    }
                }
                break;
            case "MANAGER":
                ManagerModel managerModel = jsonReaderWriter.jsonToModel(Files.readString(Paths.get("./Manager_Data.json")), ManagerModel.class);
                var managerList = managerModel.getManagers();
                for (ManagerModel.Manager manager : managerList) {
                    if (manager.checkCredentials(username, password)){
                        loginSuccess = true;
                        break;
                    }
                }
                break;



        }
    }

    public boolean isLoginSuccessful() {
        return loginSuccess;
    }
}
