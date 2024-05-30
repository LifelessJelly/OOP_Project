package Data;

import Subsystems.Encryptor;

import java.util.ArrayList;
import java.util.List;

public class ManagerModel implements Models{
    private final List<Manager> managers = new ArrayList<>();

    public void addManager(Manager manager){
        managers.add(manager);
    }
    public List<Manager> getManagers(){
        return managers;
    }

    @Override
    public void showContent() {

    }

    public static class Manager {

        public Manager.ManagerCredentials managerCredentials;
        public Manager.ManagerDetails managerDetails;

        public Manager(){
            managerCredentials = new Manager.ManagerCredentials();
            managerDetails = new Manager.ManagerDetails();
        }

        public void updateCredentials(String usernameHash, String passwordHash) {
            managerCredentials.encryptedUsername = usernameHash;
            managerCredentials.encryptedPassword = passwordHash;
        }

        public boolean checkCredentials(String usernamePlainText, String passwordPlainText) {
            String usernameEncrypted = new Encryptor(usernamePlainText).getEncodedStr();
            String passwordEncrypted = new Encryptor(passwordPlainText).getEncodedStr();
            return usernameEncrypted.equals(managerCredentials.encryptedUsername) && passwordEncrypted.equals(managerCredentials.encryptedPassword);
        }

        public static class ManagerCredentials extends Models.ModelCredentials {

        }
        public static class ManagerDetails extends Models.ModelDetails {

            @Override
            public String toString() {
                return super.toString();
            }

        }
    }
}
