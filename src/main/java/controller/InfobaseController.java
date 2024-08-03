package controller;

import data.*;
import subsystems.JsonReaderWriter;
import subsystems.SHA256;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

public class InfobaseController {
    private final Staff loggedInStaffInstance;
    private EditsDataStorage editsDataStorageInstance;
    private final ApplicantDataStorage applicantDataStorage;
    private int index;

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


    public void addApplicant(String name, int day, String month, int year,String email, String nric, String gender, String base64Img, String[] skills, String pdfFile){
        String birthString = String.valueOf(day) + ' ' + month + ' ' + year;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);
        LocalDate birthDate = LocalDate.parse(birthString, formatter);
        Applicant applicant=new Applicant(name, birthDate.toEpochDay(), LocalDate.now().getYear()-birthDate.getYear(), email, nric, gender, base64Img, skills, pdfFile);
        applicantDataStorage.addApplicant(applicant);
        System.out.println("applicant added");
        File applicantDirectory = new File(System.getProperty("user.dir") + "\\" + "applicants");
        DataIO.writeFile(applicantDirectory + "\\" + new String(SHA256.getHasherHex().hashString(String.valueOf(System.nanoTime()))) + "_Applicant.json", JsonReaderWriter.modelToJson(applicant));
    }

    public void addApplicant(Applicant applicant){
        applicantDataStorage.addApplicant(applicant);
        File applicantDirectory = new File(System.getProperty("user.dir") + "\\" + "applicants");
        DataIO.writeFile(applicantDirectory + "\\" + new String(SHA256.getHasherHex().hashString(String.valueOf(System.nanoTime()))) + "_Applicant.json", JsonReaderWriter.modelToJson(applicant));
        System.out.println("applicant added via object");
    }

    public void removeApplicant(int index){
        applicantDataStorage.removeApplicant(index);
        File applicantDirectory = new File(System.getProperty("user.dir") + "\\" + "applicants");
        File[] contents = applicantDirectory.listFiles();
        assert contents != null;
        Collections.reverse(Arrays.asList(contents));
        File targetApplicant = contents[index];
        targetApplicant.delete();

    }

    // CALL THIS METHOD BEFORE INTERFACING WITH DATA METHODS
    public void setApplicantInstance(int index){
        this.index = index;
        editsDataStorageInstance = new EditsDataStorage(getApplicantInstance());
    }

    public Applicant getApplicantInstance(){
        return applicantDataStorage.getApplicantAt(index);
    }

    public void applyApplicantEdits(String name, int day, String month, int year, String nricFin, String email, String gender, String s){
        String dateString = String.valueOf(day) + ' ' + month + ' ' + year;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        Applicant edittedApplicant = new Applicant(name, localDate.toEpochDay(), LocalDate.now().getYear()-localDate.getYear(), email, nricFin, gender, s, editsDataStorageInstance.getSkills(), getApplicantInstance());
        applicantDataStorage.editApplicant(index, edittedApplicant);
        overwriteIndex(index, edittedApplicant);
    }

    private void overwriteIndex(int index, Applicant newApplicant){
        File applicantDirectory = new File(System.getProperty("user.dir") + "\\" + "applicants");
        File[] contents = applicantDirectory.listFiles();
        if (contents != null) {
            Collections.reverse(Arrays.asList(contents));
            File targetApplicantFile = contents[index];
            if (targetApplicantFile.delete()) {
                DataIO.writeFile(targetApplicantFile.getAbsolutePath(), JsonReaderWriter.modelToJson(newApplicant));
            }
            else {
                System.out.println("Can't delete file");
            }
        }
    }

    public BufferedImage getImage() {
        return getApplicantInstance().getImage();
    }
    public String getName() {
        return getApplicantInstance().getName();
    }

    public String getNRIC() {
        return getApplicantInstance().getNRIC();
    }

    public String getEmail() {
        return getApplicantInstance().getEmail();
    }

    public String[] getSkills() {
        return editsDataStorageInstance.getSkills();
    }

    public void addSkill(String editSkillTextField) {
        editsDataStorageInstance.addSkill(editSkillTextField);
    }

    public void removeSkill(int selectedIndex) {
        editsDataStorageInstance.removeSkill(selectedIndex);
    }

    public void editSkill(int index, String editedSkill) {
        editsDataStorageInstance.editSkill(index, editedSkill);
    }

    public Applicant[] getApplicants() {
        return applicantDataStorage.getApplicants();
    }

    public Staff getUser() {
        return loggedInStaffInstance;
    }

    public void shortlistApplicantForInterview(int selectedRow) {
        setApplicantInstance(selectedRow);
        Applicant applicantsToShortlist = applicantDataStorage.getApplicantAt(index);
        applicantsToShortlist.setStatus(Applicant.SHORTLISTED_PENDING_DATE);
        applicantDataStorage.editApplicant(index, applicantsToShortlist);
        overwriteIndex(index, applicantsToShortlist);
    }

    public void confirmApplicantShortlist(){
        Applicant applicant = getApplicantInstance();
        applicant.setStatus(Applicant.SHORTLISTED_TO_INTERVIEW);
        overwriteIndex(index, applicant);
    }

    public void confirmApplicantJob(){
        Applicant applicant = getApplicantInstance();
        applicant.setStatus(Applicant.ACCEPTED);
        overwriteIndex(index, applicant);
    }

    public void acceptApplicant(int selectedRow) {
        setApplicantInstance(selectedRow);
        Applicant applicantToAccept = applicantDataStorage.getApplicantAt(index);
        applicantToAccept.setStatus(Applicant.ACCEPTED_WAITING_JOB);
        applicantDataStorage.editApplicant(index, applicantToAccept);
        overwriteIndex(index, applicantToAccept);
    }

    public String getBirthdate() {
        return getApplicantInstance().getBirthdate();
    }

    public String getGender() {
        return getApplicantInstance().getGender();
    }

    public void setInterviewTime(long time) {
        Applicant applicantToInterview = applicantDataStorage.getApplicantAt(index);
        applicantToInterview.setInterviewTime(time);
        overwriteIndex(index, applicantToInterview);
    }

    public String getInterviewTime(){
        return getApplicantInstance().getInterviewTime();
    }

    public Applicant getApplicantAt(int index) {
        return applicantDataStorage.getApplicantAt(index);
    }

    public void setJobRole(String applicantAssignedField) {
        Applicant applicantToAssign = getApplicantInstance();
        applicantToAssign.setJobRole(applicantAssignedField);
    }
}
