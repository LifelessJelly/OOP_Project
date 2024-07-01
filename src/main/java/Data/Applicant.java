package Data;

//Class model implementation for applicants

public class Applicant{

    private final ApplicantDetails applicantDetails;
    private final ApplicantMetadata applicantMetadata;
    private ApplicantExperience[] applicantPrevExps;
    private String[] userDefinedSkills;
    private String[] defaultSkills;

    public Applicant(String name, long birthdate, int age, String email, String nric, String gender, String imageBase64, String[] userDefinedSkills, String[] defaultSkills, ApplicantExperience[] applicantPrevExps) {
        this.userDefinedSkills = userDefinedSkills;
        this.defaultSkills = defaultSkills;
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

    public int getId() {
        return applicantMetadata.applicantID;
    }

    public String getImageBase64(){
        return applicantDetails.imageBase64;
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
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static class ApplicantMetadata {
        private final int applicantID;
        private final long applicationDate;
        private boolean isShortlisted;
        private boolean isAccepted;
        private static int applicantIDCounter;
        private long interviewDate;

        public ApplicantMetadata() {
            applicantID = applicantIDCounter++;
            applicationDate = System.currentTimeMillis();
            isShortlisted = false;
            isAccepted = false;
            interviewDate = -1;
        }
    }
}


