package data;

//Class model implementation for applicants

import subsystems.ImageBase64;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Applicant{

    private final ApplicantDetails applicantDetails;
    private final ApplicantExperience[] applicantPrevExps;
    private final String[] skills;
    private final ApplicantMetadata applicantMetadata;

    public Applicant(Applicant applicantToCopyFrom){
        this.applicantDetails = new ApplicantDetails(applicantToCopyFrom.applicantDetails);
        this.applicantPrevExps = Arrays.copyOf(applicantToCopyFrom.applicantPrevExps, applicantToCopyFrom.applicantPrevExps.length);
        this.skills = Arrays.copyOf(applicantToCopyFrom.skills, applicantToCopyFrom.skills.length);
        this.applicantMetadata = new ApplicantMetadata(applicantToCopyFrom.applicantMetadata);
    }


    public Applicant(String name, long birthdate, int age, String email, String nric, String gender, String imageBase64, String[] skills, ApplicantExperience[] applicantPrevExps) {
        this.skills = skills;
        this.applicantPrevExps = applicantPrevExps;
        applicantMetadata = new ApplicantMetadata();
        applicantDetails = new ApplicantDetails();
        applicantDetails.name = name;
        applicantDetails.birthdate = birthdate;
        applicantDetails.age = age;
        applicantDetails.email = email;
        applicantDetails.nric = nric;
        applicantDetails.gender = gender;
        applicantDetails.imageBase64 = imageBase64;
    }
//
//    public void setDetails(String applicantName,
//                           long birthdate,
//                           int age,
//                           String nationality,
//                           String gender,
//                           String NRIC_Fin_Passport,
//                           String pathToImage,
//                           String email)
//    {
//        applicantDetails.name = applicantName;
//        applicantDetails.birthdate = birthdate;
//        applicantDetails.age = age;
//        applicantDetails.gender = gender;
//        applicantDetails.NRIC_Fin_Passport = NRIC_Fin_Passport;
//        applicantDetails.imageBase64 = ImageBase64.imageToBase64(pathToImage);
//        applicantDetails.email = email;
//    }

    public BufferedImage getImage(){
        return ImageBase64.base64ToImage(applicantDetails.imageBase64);
    }

    public String getImageBase64(){
        return applicantDetails.imageBase64;
    }

    public String getName() {
        return applicantDetails.name;
    }

    public String getNRIC() {
        return applicantDetails.nric;
    }

    public String getEmail() {
        return applicantDetails.email;
    }

    public String[] getSkills() {
        return skills;
    }

    public ApplicantExperience[] getExperiences() {
        return applicantPrevExps;
    }




    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static class ApplicantDetails {
        private String name;
        private long birthdate;
        private int age;
        private String gender;
        private String nric;
        private String imageBase64;
        private String email;

        private ApplicantDetails() {
            this.name = "";
            this.birthdate = 0;
            this.age = 0;
            this.gender = "";
            this.nric = "";
            this.imageBase64 = "";
            this.email = "";
        }

        private ApplicantDetails(ApplicantDetails applicantDetailsToCopyFrom) {
            this.name = applicantDetailsToCopyFrom.name;
            this.birthdate = applicantDetailsToCopyFrom.birthdate;
            this.age = applicantDetailsToCopyFrom.age;
            this.gender = applicantDetailsToCopyFrom.gender;
            this.nric = applicantDetailsToCopyFrom.nric;
            this.imageBase64 = applicantDetailsToCopyFrom.imageBase64;
            this.email = applicantDetailsToCopyFrom.email;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static class ApplicantMetadata {
        private final long applicationDate;
        private boolean isShortlisted;
        private boolean isAccepted;
        private final long interviewDate;

        public ApplicantMetadata() {
            applicationDate = System.currentTimeMillis();
            isShortlisted = false;
            isAccepted = false;
            interviewDate = -1;
        }

        private ApplicantMetadata(ApplicantMetadata applicantMetadataToCopyFrom) {
            this.applicationDate = applicantMetadataToCopyFrom.applicationDate;
            this.isShortlisted = applicantMetadataToCopyFrom.isShortlisted;
            this.isAccepted = applicantMetadataToCopyFrom.isAccepted;
            this.interviewDate = applicantMetadataToCopyFrom.interviewDate;
        }
    }
}


