package Data;

import Subsystems.ImageBase64;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//Class model implementation for applicants

public class Applicant{

    private final ApplicantSummary applicantSummary;
    private final ApplicantDetails applicantDetails;
    private final List<ApplicantExperience> applicantPrevExps;
    private final ApplicantMetadata applicantMetadata;

    public Applicant() {
        applicantSummary = new ApplicantSummary();
        applicantDetails = new ApplicantDetails();
        applicantPrevExps = new ArrayList<>();
        applicantMetadata = new ApplicantMetadata();
    }

    public void setDetails(String applicantName,
                           long birthdate,
                           int age,
                           String nationality,
                           String gender,
                           String NRIC_Fin_Passport,
                           String pathToImage,
                           String email)
    {
        applicantDetails.name = applicantName;
        applicantDetails.birthdate = birthdate;
        applicantDetails.age = age;
        applicantDetails.nationality = nationality;
        applicantDetails.gender = gender;
        applicantDetails.NRIC_Fin_Passport = NRIC_Fin_Passport;
        applicantDetails.imageBase64 = ImageBase64.imageToBase64(pathToImage);
        applicantDetails.email = email;
    }

    public int getId() {
        return applicantMetadata.applicantID;
    }

    private static class compareJobDates implements Comparator<ApplicantExperience> {

        @Override
        public int compare(ApplicantExperience o1, ApplicantExperience o2) {
            return o1.getYearBegin() - o2.getYearBegin();
        }
    }

    public void generatePreviousJobExperience(String company, String jobTitle, int startYear, int endYear) {
        applicantPrevExps.add(new ApplicantExperience(company, jobTitle, startYear, endYear));
        applicantPrevExps.sort(new Applicant.compareJobDates());
    }

    public void addJobExperience(List<ApplicantExperience> jobExperienceList){
        applicantPrevExps.addAll(jobExperienceList);
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

    public String getImageBase64(){
        return applicantDetails.imageBase64;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static class ApplicantDetails {
        private String name;
        private long birthdate;
        private int age;
        private String nationality;
        private String gender;
        private String NRIC_Fin_Passport;
        private String imageBase64;
        private String email;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static class ApplicantSummary {
        private String resume;
        private final List<String> skills = new ArrayList<>();
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


