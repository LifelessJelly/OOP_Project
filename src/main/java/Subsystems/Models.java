package Subsystems;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public abstract class Models {

    @Override
    public abstract String toString(); // Debug usage only, don't use for frontend
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
        public ArrayList<Applicant> getApplicantsByIndex(int[] indices) {
            ArrayList<Applicant> applicants = new ArrayList<>();
            for (int i : indices){
                applicants.add(applicants.get(i));
            }
            return applicants;
        }


        public static class Applicant {
            public ApplicantSummary applicantSummary;
            public ApplicantDetails applicantDetails;
            public ArrayList<ApplicantPreviousJobExperience> applicantPreviousJobExperiences;
            public ApplicantMetadata applicantMetadata;

            public Applicant() {
                applicantSummary = new ApplicantSummary();
                applicantDetails = new ApplicantDetails();
                applicantPreviousJobExperiences = new ArrayList<>();
                applicantMetadata = new ApplicantMetadata();
            }

            public void setApplicantDetails(String applicantName, long birthdate, int age, String nationality, String gender, String NRIC_Fin_Passport, Image applicantImage){
                applicantDetails.applicantName = applicantName;
                applicantDetails.birthdate = birthdate;
                applicantDetails.age = age;
                applicantDetails.nationality = nationality;
                applicantDetails.gender = gender;
                applicantDetails.NRIC_Fin_Passport = NRIC_Fin_Passport;
                applicantDetails.applicantImage = applicantImage;
            }

            public static class compareJobDates implements Comparator<ApplicantPreviousJobExperience> {

                @Override
                public int compare(ApplicantPreviousJobExperience o1, ApplicantPreviousJobExperience o2) {
                    return o1.yearBegin - o2.yearBegin;
                }
            }



            public void generatePreviousJobExperience(String company, String jobTitle, int startYear, int endYear) {
                applicantPreviousJobExperiences.add(new ApplicantPreviousJobExperience(company, jobTitle, startYear, endYear));
                applicantPreviousJobExperiences.sort(new compareJobDates());
            }

            public void addJobExperience(ArrayList<ApplicantPreviousJobExperience> jobExperienceList){
                applicantPreviousJobExperiences.addAll(jobExperienceList);
            }

            public void setResume(String resume){
                applicantSummary.resume = resume;
            }

            public void toggleApplicantShortlist(){
                applicantMetadata.isShortlisted = !applicantMetadata.isShortlisted;
            }

            public void removeSkill(int index){
                applicantSummary.skills.remove(index);
            }

            public void addSkill(String skill){
                applicantSummary.skills.add(skill);
            }

            @Override
            public String toString() {
                return "Applicant{" +
                        "applicantSummary=" + applicantSummary +
                        ", applicantDetails=" + applicantDetails +
                        ", applicantPreviousJobExperience=" + applicantPreviousJobExperiences +
                        ", applicantMetadata=" + applicantMetadata +
                        '}';
            }


        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        public static class ApplicantDetails extends Subsystems.Models {
            private String applicantName;
            private long birthdate;
            private int age;
            private String nationality;
            private String gender;
            private String NRIC_Fin_Passport;
            private Image applicantImage;

            @Override
            public String toString() {
                return "ApplicantDetails{" +
                        "applicantName='" + applicantName + '\'' +
                        ", birthdate=" + new Date(birthdate) +
                        ", age=" + age +
                        ", nationality='" + nationality + '\'' +
                        ", gender='" + gender + '\'' +
                        ", NRIC/Fin/Passport='" + NRIC_Fin_Passport + '\'' +
                        '}';
            }


            @Override
            public void showContent() {

            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        public static class ApplicantSummary extends Subsystems.Models {
            private String resume;
            private ArrayList<String> skills;

            @Override
            public String toString() {
                return "ApplicantSummary{" +
                        "resume=" + resume +
                        ", skills=" + skills +
                        '}';
            }

            @Override
            public void showContent() {

            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        public static class ApplicantPreviousJobExperience extends Subsystems.Models {
            private final String previousCompanyName;
            private final String previousJobTitle;
            private int yearBegin;
            int yearEnd;

            public ApplicantPreviousJobExperience(String companyName, String jobTitle, int yearBegin, int yearEnd) {
                previousCompanyName = companyName;
                previousJobTitle = jobTitle;
                yearBegin = yearBegin;
                yearEnd = yearEnd;
            }

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
            public void showContent() {

            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        public static class ApplicantMetadata extends Subsystems.Models {
            private final int applicantID;
            private final long applicationDate;
            private boolean isShortlisted;
            private static int applicantIDCounter;

            public ApplicantMetadata() {
                applicantID = applicantIDCounter++;
                applicationDate = System.currentTimeMillis();
                isShortlisted = false;
            }

            @Override
            public String toString() {
                return "ApplicantMetadata{" +
                        "applicantID=" + applicantID +
                        ", applicationDate=" + new Date(applicationDate) +
                        ", isShortlisted=" + isShortlisted +
                        '}';
            }


            @Override
            public void showContent() {

            }
        }
    }

}
