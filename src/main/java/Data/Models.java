package Data;

import java.util.Date;

public interface Models {

        @Override
        String toString(); // Debug usage only, don't use for frontend
        void showContent(); //some Swing UI stuff here


    abstract class ModelDetails {
        String name;
        long birthdate;
        int age;
        String nationality;
        String gender;
        String NRIC_Fin_Passport;
        String imageBase64;
        String email;

        @Override
        public String toString() {
            return "ApplicantDetails{" +
                    "applicantName='" + this.name + '\'' +
                    ", birthdate=" + new Date(this.birthdate) +
                    ", age=" + this.age +
                    ", nationality='" + this.nationality + '\'' +
                    ", gender='" + this.gender + '\'' +
                    ", NRIC/Fin/Passport='" + this.NRIC_Fin_Passport + '\'' +
                    ", imageBase64='" + this.imageBase64 + '\'' +
                    ", email='" + this.email + '\'' +
                    '}';
        }
    }
    abstract class ModelCredentials {
        String encryptedUsername;
        String encryptedPassword;
    }
    

}
