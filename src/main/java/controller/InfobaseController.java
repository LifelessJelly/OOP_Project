package controller;

import data.Applicant;
import data.ApplicantExperience;
import subsystems.JsonReaderWriter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class InfobaseController {
    Applicant applicantInstance;
    Applicant tempApplicantToEdit;
    List<Applicant> applicantList;

    public InfobaseController() {
        String path = System.getProperty("user.dir") + "\\" + "applicants";
        applicantList = new ArrayList<>();
        File dir = new File(path);
        File[] files = dir.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.getName().endsWith(".json")) {
                String json = DataIO.readFile(file.getAbsolutePath());
                Applicant applicant = JsonReaderWriter.jsonToModel(json, Applicant.class);
                if (!applicant.getName().isEmpty()){

                    applicantList.add(applicant);
                }
            }
        }
        System.out.println(applicantList.size());
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
        return applicantList.toArray(new Applicant[0]);
    }
}
