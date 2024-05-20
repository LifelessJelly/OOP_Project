package Subsystems;

import java.awt.*;
import java.util.Date;

public abstract class Models {

    public static String storeImage(Image image){
        return "work in progress";
    }


    public interface ContentPrinter {
        @Override
        String toString(); // Debug usage only, don't use for frontend
        void showContent(); //some Swing UI stuff here
    }

    public abstract static class ModelDetails {
        String name;
        long birthdate;
        int age;
        String nationality;
        String gender;
        String NRIC_Fin_Passport;
        String pathToImage;

        @Override
        public String toString() {
            return "ApplicantDetails{" +
                    "applicantName='" + this.name + '\'' +
                    ", birthdate=" + new Date(this.birthdate) +
                    ", age=" + this.age +
                    ", nationality='" + this.nationality + '\'' +
                    ", gender='" + this.gender + '\'' +
                    ", NRIC/Fin/Passport='" + this.NRIC_Fin_Passport + '\'' +
                    '}';
        }
    }
    public abstract static class ModelCredentials {
        String encryptedUsername;
        String encryptedPassword;


    }

}
