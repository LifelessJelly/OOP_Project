package controller;

import data.*;
import subsystems.ImageBase64;
import subsystems.JsonReaderWriter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class InfobaseController {
    Applicant applicantInstance;
    Staff loggedInStaffInstance;
    EditsDataStorage editsDataStorageInstance;
    ApplicantDataStorage applicantDataStorage;
    int index;
    Image image;

    public InfobaseController(Staff loggedInStaffInstance) {
        this.loggedInStaffInstance = loggedInStaffInstance;
        String path = System.getProperty("user.dir") + "\\" + "applicants";
        applicantDataStorage = new ApplicantDataStorage();
        File dir = new File(path);
        File[] files = dir.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.getName().endsWith(".json")) {
                String json = DataIO.readFile(file.getAbsolutePath());
                Applicant applicant = JsonReaderWriter.jsonToModel(json, Applicant.class);
                if (!applicant.getName().isEmpty()){
                    applicantDataStorage.addApplicant(applicant);
                }
            }
        }
    }

    public void addApplicant(Applicant applicant){
        applicantDataStorage.addApplicant(applicant);
    }

    public void setApplicantInstance(Applicant applicant, int index){
        this.index = index;
        applicantInstance = applicant;
        image = applicant.getImage();
        editsDataStorageInstance = new EditsDataStorage();
        editsDataStorageInstance.importSkills(applicant.getSkills());
    }

    public void applyApplicantEdits(String name, int day, String month, int year, String nricFin, String email, String gender){
        String dateString = String.valueOf(day) + ' ' + month + ' ' + year;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        Applicant edittedApplicant = new Applicant(name, localDate.toEpochDay(), LocalDate.now().getYear()-localDate.getYear(), email, nricFin, gender, ImageBase64.imageToBase64(image), editsDataStorageInstance.getSkills());
        edittedApplicant.copyApplicantMetadata(applicantInstance);
        applicantDataStorage.editApplicant(index, edittedApplicant);
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
        return editsDataStorageInstance.getSkills();
    }

    /**
     * Retrieves the array of ApplicantExperience objects associated with the current applicant instance.
     *
     * @return An array of ApplicantExperience objects representing the experiences of the applicant.
     */
    public ApplicantExperience[] getExperiences() {
        return editsDataStorageInstance.getExperience();
    }

    public void addSkill(String editSkillTextField) {
        editsDataStorageInstance.addSkill(editSkillTextField);
    }

    public Applicant[] getApplicants() {
        return applicantDataStorage.getApplicants();
    }

    public Staff getUser() {
        return loggedInStaffInstance;
    }

    public void shortlistApplicant(int selectedRow) {
        Applicant applicantsToShortlist = applicantDataStorage.getApplicants()[selectedRow];
        applicantsToShortlist.setStatus(Applicant.SHORTLISTED);
        new ParallelEmailSequnce("joseph_chiu@outlook.com", "Application to Operate On Peasants LLC", "Greetings, \n We are pleased to announce that you have been shortlisted for interview. Please report to the company building tomorrow 9am. We look forward to seeing you there").run();

    }

    public void acceptApplicant(int selectedRow) {
        Applicant applicantToAccept = applicantDataStorage.getApplicants()[selectedRow];
        applicantToAccept.setStatus(Applicant.ACCEPTED);
    }
}
