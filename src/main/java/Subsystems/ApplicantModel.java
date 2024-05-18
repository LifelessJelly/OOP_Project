package Subsystems;

import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

@Deprecated
public abstract class ApplicantModel {

    public abstract void updateInfo(StringTokenizer st);

    static class ApplicantList extends ApplicantModel {
        private ArrayList<Applicant> applicants;

        @Override
        public String toString() {
            return "ApplicantList{" +
                    "applicants=" + applicants +
                    '}';
        }

        @Override
        public void updateInfo(StringTokenizer st) {

        }
    }

    static class Applicant extends ApplicantModel {
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

        @Override
        public void updateInfo(StringTokenizer st) {

        }
    }
    static class ApplicantDetails extends ApplicantModel {
        private String applicantName;
        private long birthdate;
        private int age;
        private String nationality;
        private String gender;
        private String nric_Fin_Passport;

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

        @Override
        public void updateInfo(StringTokenizer st) {

        }
    }
    static class ApplicantSummary extends ApplicantModel {
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
    }
    static class ApplicantPreviousJobExperience extends ApplicantModel {
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
    }
    static class ApplicantMetadata extends ApplicantModel {
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
    }
}

