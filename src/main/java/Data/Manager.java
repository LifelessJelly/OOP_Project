package Data;

import Subsystems.SHA256;

public class Manager implements Models{

    public Manager.ManagerCredentials managerCredentials;
    public Manager.ManagerDetails managerDetails;

    public Manager(){
        managerCredentials = new ManagerCredentials();
        managerDetails = new Manager.ManagerDetails();
    }

    public void updateCredentials(String usernameHash, String passwordHash) {
        managerCredentials.encryptedUsername = usernameHash;
        managerCredentials.encryptedPassword = passwordHash;
    }

    public boolean checkCredentials(String usernamePlainText, String passwordPlainText) {
        String usernameEncrypted = new String(SHA256.getHasherHex().hashString(usernamePlainText));
        String passwordEncrypted = new String(SHA256.getHasherHex().hashString(passwordPlainText));
        return usernameEncrypted.equals(managerCredentials.encryptedUsername) && passwordEncrypted.equals(managerCredentials.encryptedPassword);
    }

    @Override
    public void showContent() {

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

