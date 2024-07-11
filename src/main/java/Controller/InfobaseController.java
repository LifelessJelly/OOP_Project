package controller;

import data.Applicant;
import data.ApplicantExperience;

import java.awt.image.BufferedImage;

public class InfobaseController {
    Applicant applicantInstance;
    Applicant tempApplicantToEdit;

    public void setApplicantInstance(Applicant applicant){
        applicantInstance = applicant;
        tempApplicantToEdit = new Applicant(applicant);
    }

    public void applyApplicantEdits(){
        // a bunch of if statement checks here
        applicantInstance = tempApplicantToEdit;
    }

    public BufferedImage getImage() {
        return applicantInstance.getImage();
    }

    public String getImageBase64() {
        return applicantInstance.getImageBase64();
    }

    public String getName() {
        return applicantInstance.getName();
    }

    public String getNRIC() {
        return applicantInstance.getNRIC();
    }

    public String getEmail() {
        return applicantInstance.getEmail();
    }

    public String[] getSkills() {
        return applicantInstance.getSkills();
    }

    /**
     * Retrieves the array of ApplicantExperience objects associated with the current applicant instance.
     *
     * @return An array of ApplicantExperience objects representing the experiences of the applicant.
     */
    public ApplicantExperience[] getExperiences() {
        return applicantInstance.getExperiences();
    }

    public void addSkill(String editSkillTextField) {

    }
}
