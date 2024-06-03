package Data;

import Subsystems.ImageBase64;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

//Class model implementation for applicants

public class ApplicantModel implements Models {
    public ApplicantModel(){
        List<Applicant> applicants = new ArrayList<>();
    }

    private final List<Applicant> applicants = new ArrayList<>();

    public void addApplicant(List<Applicant> applicant) {
        applicants.addAll(applicant);
    }

    public List<Applicant> getApplicants() {
        return applicants;
    }

    public List<Applicant> getApplicantsByIndex(int[] indices) {
        List<Applicant> newApplicants = new ArrayList<>();
        for (int i : indices){
            newApplicants.add(applicants.get(i));
        }
        return newApplicants;
    }

    @Override
    public void showContent() {

    }

    @Override
    public String toString() {
        return "ApplicantList{" +
                "applicants=" + applicants +
                '}';
    }



    public static class Applicant {
        public ApplicantSummary applicantSummary;
        public ApplicantDetails applicantDetails;
        public List<ApplicantPrevExp> applicantPrevExps;
        public ApplicantMetadata applicantMetadata;

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
                               String email){
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

        public String getName() {
            return applicantDetails.name;
        }

        public static class compareJobDates implements Comparator<ApplicantPrevExp> {

            @Override
            public int compare(ApplicantPrevExp o1, ApplicantPrevExp o2) {
                return o1.yearBegin - o2.yearBegin;
            }
        }

        public void generatePreviousJobExperience(String company, String jobTitle, int startYear, int endYear) {
            applicantPrevExps.add(new ApplicantPrevExp(company, jobTitle, startYear, endYear));
            applicantPrevExps.sort(new Applicant.compareJobDates());
        }

        public void addJobExperience(List<ApplicantPrevExp> jobExperienceList){
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

        public String getPathToImage(){
            return applicantDetails.imageBase64;
        }


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        public static class ApplicantDetails extends Models.ModelDetails {

            public ApplicantDetails() {
                super();
            }

            @Override
            public String toString() {
                return super.toString();
            }

        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        public static class ApplicantSummary {
            private String resume;
            private final List<String> skills = new ArrayList<>();

            @Override
            public String toString() {
                return "ApplicantSummary{" +
                        "resume=" + resume +
                        ", skills=" + skills +
                        '}';
            }

        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        public static class ApplicantPrevExp {
            private final String previousCompanyName;
            private final String previousJobTitle;
            private final int yearBegin;
            private final int yearEnd;

            public ApplicantPrevExp(String companyName, String jobTitle, int yearBegin, int yearEnd) {
                previousCompanyName = companyName;
                previousJobTitle = jobTitle;
                this.yearBegin = yearBegin;
                this.yearEnd = yearEnd;
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
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        public static class ApplicantMetadata {
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
        }
    }
}

