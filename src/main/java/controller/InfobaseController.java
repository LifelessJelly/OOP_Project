package controller;

import data.Applicant;
import data.ApplicantExperience;
import subsystems.JsonReaderWriter;

import java.awt.image.BufferedImage;

public class InfobaseController {
    Applicant applicantInstance;
    Applicant tempApplicantToEdit;
    private Applicant nathan;
    private Applicant kwekXinJayden;
    private Applicant shitKai;
    private Applicant gtfoOng;
    private Applicant bendTooLow;

    public InfobaseController() {
        String path = System.getProperty("user.dir") + "\\";
//        nathan = JsonReaderWriter.jsonToModel(ApplicantIO.readFile(path + "875f20b07a408171d0b93af41dee737fc77bf7c4f34b8177899dfef17281e68f_Applicant.json"), Applicant.class);
        kwekXinJayden = JsonReaderWriter.jsonToModel(ApplicantIO.readFile(path + "977518f927af3faed090be7f46c8f712b7f646751786d9ad00ed9523c8bae48e_Applicant.json"), Applicant.class);
        nathan = new Applicant(kwekXinJayden);
        shitKai = new Applicant(kwekXinJayden);
        gtfoOng = new Applicant(kwekXinJayden);
        bendTooLow = new Applicant(kwekXinJayden);
//        shitKai = JsonReaderWriter.jsonToModel(ApplicantIO.readFile(path + "391357fad663d5e8eafc9346859b3a27c274701876072f9b1fd09873c3231af3_Applicant.json"), Applicant.class);
//        gtfoOng = JsonReaderWriter.jsonToModel(ApplicantIO.readFile(path + "af35fa8902982ebc32407e2c72d36361795599bcb655637a91b551a3cff6d86_Applicant.json"), Applicant.class);
//        bendTooLow = JsonReaderWriter.jsonToModel(ApplicantIO.readFile(path + "c9fb2d8aa22d1fd66fd6d84caba2934fbead977528c2d480263ea2b7a6948957_Applicant.json"), Applicant.class);
    }

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

    public Applicant[] getApplicants() {
        return new Applicant[]{kwekXinJayden, shitKai, gtfoOng, bendTooLow, nathan};
    }
}
