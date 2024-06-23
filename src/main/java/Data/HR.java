package Data;

import Subsystems.SHA256;

public class HR implements Models{

    HR.HRCredentials HRCredentials;
    HR.HRDetails HRDetails;

    public HR(){
        HRCredentials = new HRCredentials();
        HRDetails = new HRDetails();
    }

    public void updateCredentials(String usernameHash, String passwordHash) {
        HRCredentials.encryptedUsername = usernameHash;
        HRCredentials.encryptedPassword = passwordHash;
    }

    public boolean checkCredentials(String usernamePlainText, String passwordPlainText) {
        String usernameEncrypted = new String(SHA256.getHasherHex().hashString(usernamePlainText));
        String passwordEncrypted = new String(SHA256.getHasherHex().hashString(passwordPlainText));
        return usernameEncrypted.equals(HRCredentials.encryptedUsername) && passwordEncrypted.equals(HRCredentials.encryptedPassword);
    }

    @Override
    public void showContent() {

    }

    public static class HRCredentials extends Models.ModelCredentials {

    }
    public static class HRDetails extends Models.ModelDetails{

    }
}


