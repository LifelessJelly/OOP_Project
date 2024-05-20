package Subsystems;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManagerModel {
    private final List<Manager> managers = new ArrayList<>();

    public static class Manager {

        public Manager.ManagerCredentials managerCredentials;
        public Manager.ManagerDetails managerDetails;

        public Manager(){
            managerCredentials = new Manager.ManagerCredentials();
            managerDetails = new Manager.ManagerDetails();
        }

        void updateCredentials(String usernameHash, String passwordHash) {
            managerCredentials.encryptedUsername = usernameHash;
            managerCredentials.encryptedPassword = passwordHash;
        }

        boolean checkCredentials(String usernamePlainText, String passwordPlainText) {
            String usernameEncrypted = new Encryptor(usernamePlainText).getEncodedStr();
            String passwordEncrypted = new Encryptor(passwordPlainText).getEncodedStr();
            return usernameEncrypted.equals(managerCredentials.encryptedUsername) && passwordEncrypted.equals(managerCredentials.encryptedPassword);
        }

        public static class ManagerCredentials extends Models.ModelCredentials {

        }
        public static class ManagerDetails extends Models.ModelDetails implements Models.ContentPrinter {

            @Override
            public String toString() {
                return super.toString();
            }

            @Override
            public void showContent() {

            }
        }
    }
}
