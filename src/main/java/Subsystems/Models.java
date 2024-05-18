package Subsystems;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

public abstract class Models {

    @Override
    public abstract String toString(); // Debug usage only, don't use for frontend
    public abstract void updateInfo(StringTokenizer st);
    public abstract void showContent(); //some Swing UI stuff here


    public static class ApplicantModel {
        private ArrayList<Applicant> applicants;

        @Override
        public String toString() {
            return "ApplicantList{" +
                    "applicants=" + applicants +
                    '}';
        }

        public void addApplicant(ArrayList<Applicant> applicant) {
            applicants.addAll(applicant);
        }


        public static class Applicant {
            ApplicantSummary applicantSummary;
            ApplicantDetails applicantDetails;
            ApplicantPreviousJobExperience applicantPreviousJobExperience;
            ApplicantMetadata applicantMetadata;

            @Override
            public String toString() {
                return "Applicant{" +
                        "applicantSummary=" + applicantSummary +
                        ", applicantDetails=" + applicantDetails +
                        ", applicantPreviousJobExperience=" + applicantPreviousJobExperience +
                        ", applicantMetadata=" + applicantMetadata +
                        '}';
            }


        }

        public static class ApplicantDetails extends Subsystems.Models {
            private String applicantName;
            private long birthdate;
            private int age;
            private String nationality;
            private String gender;
            private String nric_Fin_Passport;
            private Image ApplicantImage;

            @Override
            public String toString() {
                return "ApplicantDetails{" +
                        "applicantName='" + applicantName + '\'' +
                        ", birthdate=" + birthdate +
                        ", age=" + age +
                        ", nationality='" + nationality + '\'' +
                        ", gender='" + gender + '\'' +
                        ", nric_Fin_Passport='" + nric_Fin_Passport + '\'' +
                        '}';
            }

            //TODO have another class that handles automatic age updating that will specifically change the age parameter with the respective string-tokenizer
            @Override
            public void updateInfo(StringTokenizer st) {
                st.nextToken();
                applicantName = st.nextToken().substring(1);
            }

            @Override
            public void showContent() {

            }
        }

        public static class ApplicantSummary extends Subsystems.Models {
            private ArrayList<String> resume;
            private ArrayList<String> skills;

            @Override
            public String toString() {
                return "ApplicantSummary{" +
                        "resume=" + resume +
                        ", skills=" + skills +
                        '}';
            }

            @Override
            public void updateInfo(StringTokenizer st) {

            }

            @Override
            public void showContent() {

            }
        }

        public static class ApplicantPreviousJobExperience extends Subsystems.Models {
            private String previousCompanyName;
            private String previousJobTitle;
            private int yearBegin;
            int yearEnd;

            @Override
            public String toString() {
                return "ApplicantPreviousJobExperience{" +
                        "previousCompanyName='" + previousCompanyName + '\'' +
                        ", previousJobTitle='" + previousJobTitle + '\'' +
                        ", yearBegin=" + yearBegin +
                        ", yearEnd=" + yearEnd +
                        '}';
            }

            @Override
            public void updateInfo(StringTokenizer st) {

            }

            @Override
            public void showContent() {

            }
        }

        public static class ApplicantMetadata extends Subsystems.Models {
            private int applicantID;
            private Date applicantionDate;
            private boolean isShortlisted;
            private static int applicantIDCounter;

            @Override
            public String toString() {
                return "ApplicantMetadata{" +
                        "applicantID=" + applicantID +
                        ", applicantionDate=" + applicantionDate +
                        ", isShortlisted=" + isShortlisted +
                        '}';
            }

            @Override
            public void updateInfo(StringTokenizer st) {

            }

            @Override
            public void showContent() {

            }
        }
    }

    public static class HumanResourcesModel {

        static class HumanResources {

        }
        static class HumanResourcesMetadata extends Subsystems.Models {
            private int hrID;

        }

    }
}
