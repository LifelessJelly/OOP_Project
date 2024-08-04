package data;

//Class model implementation for applicants

import subsystems.ImageBase64;

import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class Applicant{

    private final ApplicantDetails applicantDetails;
    private final String[] skills;
    private final ApplicantMetadata applicantMetadata;
    public static final int WAITING_SHORTLIST = 1;
    public static final int SHORTLISTED_PENDING_DATE = 2;
    public static final int SHORTLISTED_TO_INTERVIEW = 3;
    public static final int ACCEPTED_WAITING_JOB = 4;
    public static final int ACCEPTED = 5;

    /**
     * Constructs a new Applicant object by copying the details, previous experiences, skills, and metadata from another Applicant object.
     *
     * @param applicantToCopyFrom The Applicant object to copy the information from.
     */
    public Applicant(Applicant applicantToCopyFrom){
        this.applicantDetails = new ApplicantDetails(applicantToCopyFrom.applicantDetails);
        this.skills = Arrays.copyOf(applicantToCopyFrom.skills, applicantToCopyFrom.skills.length);
        this.applicantMetadata = new ApplicantMetadata(applicantToCopyFrom.applicantMetadata);
    }

    /**
     * Constructs a new Applicant object with the specified details, skills, and previous experiences.
     *
     * @param name The name of the applicant.
     * @param birthdate The birthdate of the applicant.
     * @param age The age of the applicant.
     * @param email The email address of the applicant.
     * @param nric The National Registration Identity Card (NRIC) of the applicant.
     * @param gender The gender of the applicant.
     * @param imageBase64 The base64 encoded image of the applicant.
     * @param skills The array of skills possessed by the applicant.
     */
    public Applicant(String name, long birthdate, int age, String email, String nric, String gender, String imageBase64, String[] skills, String pdfBase64) {
        this.skills = skills;
        applicantMetadata = new ApplicantMetadata();
        applicantDetails = new ApplicantDetails();
        applicantDetails.name = name;
        applicantDetails.birthdate = birthdate;
        applicantDetails.age = age;
        applicantDetails.email = email;
        applicantDetails.nric = nric;
        applicantDetails.gender = gender;
        applicantDetails.imageBase64 = imageBase64;
        applicantDetails.pdfBase64 = pdfBase64;
    }

    public Applicant(String name, long birthdate, int age, String email, String nric, String gender, String imageBase64, String[] skills, Applicant oldApplicant) {
        this.skills = skills;
        applicantMetadata = oldApplicant.applicantMetadata;
        applicantDetails = new ApplicantDetails();
        applicantDetails.name = name;
        applicantDetails.birthdate = birthdate;
        applicantDetails.age = age;
        applicantDetails.email = email;
        applicantDetails.nric = nric;
        applicantDetails.gender = gender;
        applicantDetails.imageBase64 = imageBase64;
        applicantDetails.pdfBase64 = oldApplicant.getPdfBase64();
    }



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

    public String getBirthdate() {
        return LocalDate.ofEpochDay(applicantDetails.birthdate).toString();
    }

    public int getAge() {
        return applicantDetails.age;
    }

    public String getPdfBase64(){
        return applicantDetails.pdfBase64;
    }

    public int getStatus(){
        return applicantMetadata.status;
    }

    public void setStatus(int status){
        applicantMetadata.status = status;
    }

    public String getGender() {
        return applicantDetails.gender;
    }

    public String getInterviewTime() {
        DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm", Locale.ENGLISH);
        return formatter.format(new Date(applicantMetadata.interviewTime));
    }

    public void setInterviewTime(long time) {
        applicantMetadata.interviewTime = time;
    }

    public void setJobRole(String applicantAssignedField) {
        applicantMetadata.jobRoleAssigned = applicantAssignedField;
    }

    public String getJobRole(){
        return applicantMetadata.jobRoleAssigned;
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
        private String pdfBase64;
        /**
         * Default constructor for creating an empty ApplicantDetails object.
         * Initializes all fields to default values.
         */
        private ApplicantDetails() {
            this.name = "";
            this.birthdate = 0;
            this.age = 0;
            this.gender = "";
            this.nric = "";
            this.imageBase64 = "";
            this.email = "";
        }

        /**
         * Constructs a new ApplicantDetails object by copying the details from another ApplicantDetails object.
         *
         * @param applicantDetailsToCopyFrom The ApplicantDetails object to copy the information from.
         */
        private ApplicantDetails(ApplicantDetails applicantDetailsToCopyFrom) {
            this.name = applicantDetailsToCopyFrom.name;
            this.birthdate = applicantDetailsToCopyFrom.birthdate;
            this.age = applicantDetailsToCopyFrom.age;
            this.gender = applicantDetailsToCopyFrom.gender;
            this.nric = applicantDetailsToCopyFrom.nric;
            this.imageBase64 = applicantDetailsToCopyFrom.imageBase64;
            this.email = applicantDetailsToCopyFrom.email;
            this.pdfBase64 = applicantDetailsToCopyFrom.pdfBase64;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static class ApplicantMetadata {
        private final long applicationDate;
        private int status;
        private long interviewTime;
        private String jobRoleAssigned;

        private ApplicantMetadata() {
            applicationDate = System.currentTimeMillis();
            status = WAITING_SHORTLIST;
            interviewTime = -1;
        }

        private ApplicantMetadata(ApplicantMetadata applicantMetadataToCopyFrom) {
            this.applicationDate = applicantMetadataToCopyFrom.applicationDate;
            status = applicantMetadataToCopyFrom.status;
            this.interviewTime = applicantMetadataToCopyFrom.interviewTime;
            this.jobRoleAssigned = applicantMetadataToCopyFrom.jobRoleAssigned;
        }
    }
}


