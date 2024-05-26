package Subsystems;

import java.util.ArrayList;
import java.util.List;

public class HRModel {
    private final List<HR> HRs = new ArrayList<>();

    public void addHR(HR hr) {
        HRs.add(hr);
    }
    public List<HR> getHRs() {
        return HRs;
    }

    public static class HR {

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
            String usernameEncrypted = new Encryptor(usernamePlainText).getEncodedStr();
            String passwordEncrypted = new Encryptor(passwordPlainText).getEncodedStr();
            return usernameEncrypted.equals(HRCredentials.encryptedUsername) && passwordEncrypted.equals(HRCredentials.encryptedPassword);
        }

        public static class HRCredentials extends Models.ModelCredentials {

        }
        public static class HRDetails extends Models.ModelDetails implements Models.ContentPrinter{

            @Override
            public void showContent() {

            }
        }
    }
}

