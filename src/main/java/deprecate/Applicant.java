package deprecate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Date;

public class Applicant {
    private final int applicantID;
    private final Date applicantionDate;
    private boolean isShortlisted;
    private static int applicantIDCounter;
    private ApplicantSummary applicantSummary;
    private ApplicantDetails applicantDetails;
    Applicant(){
        applicantID = ++applicantIDCounter;
        applicantionDate = new Date();
        isShortlisted = false;
    }
    public void shortListApplicant(){
        isShortlisted = true;
    }
    public void delistApplicant(){
        isShortlisted = false;
    }
    public int getApplicantID() {
        return applicantID;
    }
    public Date getApplicantionDate() {
        return applicantionDate;
    }

    private boolean isShortlisted() {
        return isShortlisted;
    }
    @Override
    public String toString() {
        return "Applicant ID: " + applicantID + "\n" +
                "Applicant Date: " + applicantionDate + "\n" +
                "Shortlisted?: " + isShortlisted + "\n";
    }
}

class ApplicantDetails {
    private String applicantName;
    private Date birthdate;
    private int age;
    private String nationality;
    private String gender;
    private String nric_Fin_Passport;
    ApplicantDetails(String applicantName, Date birthdate, int age, String nationality, String gender, String nric_Fin_Passport){
        this.applicantName = applicantName;
        this.birthdate = birthdate;
        this.age = age;
        this.nationality = nationality;
        this.gender = gender;
        this.nric_Fin_Passport = nric_Fin_Passport;
    }
    //Simplify getters and setters, some may be redundant

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public int getAge() {
        return age;
    }
    //can probably be removed with an automatic age updater
    public void setAge(int age) {
        this.age = age;
    }

    public String getNric_Fin_Passport() {
        return nric_Fin_Passport;
    }

    public void setNric_Fin_Passport(String nric_Fin_Passport) {
        this.nric_Fin_Passport = nric_Fin_Passport;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Applicant Name: " + applicantName + '\n' +
                "Date of Birth: " + birthdate + '\n' +
                "Age: " + age + '\n' +
                "nationality: " + nationality + '\n' +
                "Gender: " + gender + '\n' +
                "NRIC / FIN / Passport: " + nric_Fin_Passport + '\n';
    }
}

class ApplicantSummary {
    // consider replacing with custom classes
    String resume;
    String[] skills;
    JobExperience[] recentJobExperience;
    ApplicantSummary(){
        this(null, null, null);
    }
    ApplicantSummary(String resume, String[] skills, JobExperience[] recentJobExperience){
        this.resume = resume;
        this.skills = skills;
        this.recentJobExperience = recentJobExperience;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public JobExperience[] getRecentJobExperience() {
        return recentJobExperience;
    }

    public void setRecentJobExperience(JobExperience[] recentJobExperience) {
        this.recentJobExperience = recentJobExperience;
    }
    @Override
    public String toString() {
        StringBuilder jobExperienceString = new StringBuilder();
        for (var jobExperience : recentJobExperience) {
            jobExperienceString.append(jobExperience.toString()).append('\n');
        }
        return "Resume Transcript: " + resume + "\n\n" +
                "Skills" + String.join(" ", skills) + "\n\n" + "Job Experience: " + jobExperienceString;
    }
}

class JobExperience {
    private String previousCompanyName;
    private final String previousJobTitle;
    private int yearBegin;
    int yearEnd;
    JobExperience(String previousCompanyName, String previousJobTitle, int yearBegin, int yearEnd){
        this.previousCompanyName = previousCompanyName;
        this.previousJobTitle = previousJobTitle;
        this.yearBegin = yearBegin;
        this.yearEnd = yearEnd;
    }
    public String getPreviousCompanyName() {
        return previousCompanyName;
    }
    public void setPreviousCompanyName(String previousCompanyName) {
        this.previousCompanyName = previousCompanyName;
    }
    public String getPreviousJobTitle() {
        return previousJobTitle;
    }

    public int getYearBegin() {
        return yearBegin;
    }

    public void setYearBegin(int yearBegin) {
        this.yearBegin = yearBegin;
    }

    public int getYearEnd() {
        return yearEnd;
    }

    public void setYearEnd(int yearEnd) {
        this.yearEnd = yearEnd;
    }

    @Override
    public String toString(){
        return "Previous Company: " + previousCompanyName + '\n' +
                "Previous Job Title: " + previousJobTitle + '\n' +
                "From: " + yearBegin + "to " + yearEnd + '\n';
    }
}

