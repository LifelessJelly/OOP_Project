package Controller;

import Data.ApplicantModel;
import Data.HRModel;
import Data.ManagerModel;
import Subsystems.Pointer;


import java.io.IOException;

public class Controller {
    public ApplicantModel applicantModel;
    public HRModel hrModel;
    public ManagerModel managerModel;

    public void applicantShortlister(int[] indices, ApplicantModel applicantModel) {
        new ApplicantShortlister(indices, new Pointer<>(applicantModel));
    }

    public boolean loginRequest(String username, String password, String domain) throws IOException {
        return new LoginRequest(username, password, domain).isLoginSuccessful();
    }

    public void sendEmail(String email, String subject, String body) {
        new EmailSender(email, subject, body);
    }


}
