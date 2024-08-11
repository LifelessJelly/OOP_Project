package data;

//Class model implementation for applicants

import controller.ImageBase64;

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

    //Copy Constructor
    public Applicant(Applicant applicantToCopyFrom){
        this.applicantDetails = new ApplicantDetails(applicantToCopyFrom.applicantDetails);
        this.skills = Arrays.copyOf(applicantToCopyFrom.skills, applicantToCopyFrom.skills.length);
        this.applicantMetadata = new ApplicantMetadata(applicantToCopyFrom.applicantMetadata);
    }

    /**
     * Constructs a new {@link Applicant} instance with the specified details.
     *
     * <p>This constructor initializes the applicant's metadata and details using the provided
     * parameters. It creates a new instance of {@link ApplicantMetadata} and {@link ApplicantDetails}
     * to store the applicant's information.</p>
     *
     * @param name the name of the applicant. Must not be null.
     * @param birthdate the birthdate of the applicant in milliseconds since epoch.
     * @param age the age of the applicant. Must be a non-negative integer.
     * @param email the email address of the applicant. Must not be null.
     * @param nric the National Registration Identity Card number of the applicant. Must not be null.
     * @param gender the gender of the applicant. Must not be null.
     * @param imageBase64 the Base64 encoded string representation of the applicant's image.
     *                    Must not be null.
     * @param skills an array of skills associated with the applicant. Must not be null.
     * @param pdfBase64 the Base64 encoded string representation of the applicant's PDF document.
     *                  Must not be null.
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

    /**
     * Constructs a new {@link Applicant} instance with the specified details.
     *
     * <p>This constructor initializes the applicant's metadata and details using the provided
     * parameters. It also copies the metadata and PDF base64 representation from an existing
     * {@link Applicant} object if provided.</p>
     *
     * @param name the name of the applicant. Must not be null.
     * @param birthdate the birthdate of the applicant in milliseconds since epoch.
     * @param age the age of the applicant. Must be a non-negative integer.
     * @param email the email address of the applicant. Must not be null.
     * @param nric the National Registration Identity Card number of the applicant. Must not be null.
     * @param gender the gender of the applicant. Must not be null.
     * @param imageBase64 the Base64 encoded string representation of the applicant's image.
     *                    Must not be null.
     * @param skills an array of skills associated with the applicant. Must not be null.
     * @param oldApplicant an existing {@link Applicant} object to copy metadata and PDF base64 from.
     *                     Can be null, in which case only the provided parameters are used.
     */
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

    public boolean isObjectNull(){
        return applicantDetails == null || applicantMetadata == null;
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
        if (applicantMetadata.interviewTime == -1){
            return "---";
        }
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

        //Copy Constructor
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

        //Copy Constructor
        private ApplicantMetadata(ApplicantMetadata applicantMetadataToCopyFrom) {
            this.applicationDate = applicantMetadataToCopyFrom.applicationDate;
            status = applicantMetadataToCopyFrom.status;
            this.interviewTime = applicantMetadataToCopyFrom.interviewTime;
            this.jobRoleAssigned = applicantMetadataToCopyFrom.jobRoleAssigned;
        }
    }
}


