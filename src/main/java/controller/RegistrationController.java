package controller;

import data.Applicant;
import data.ApplicantExperience;
import data.DataStorage;

public class RegistrationController {
    private final DataStorage datastorage;
    private final ApplicantRegistrator registrator;

    public RegistrationController() {
        registrator = new ApplicantRegistrator();
        datastorage = new DataStorage();
    }

    public void registerBasicInfo(String name, int day, String month, int year, String nricFin, String email, String gender){
        registrator.registerBasicInfo(name, day, month, year, nricFin, email, gender);
    }

    public String[] getSkills() {
        return datastorage.getSkills();
    }

    public void addSkill(String skill) {
        datastorage.addSkill(skill);
    }

    public void removeSkill(int index) {
        datastorage.removeSkill(index);
    }

    public ApplicantExperience[] getExperience() {
        return datastorage.getExperience();
    }

    public void addExperience(String company, String position, int yearStart, int yearEnd) {
        datastorage.addExperience(company, position, yearStart, yearEnd);
    }

    public void removeExperience(int index) {
        datastorage.removeExperience(index);
    }

    public void registerExperience(ApplicantExperience[] experience) {
        registrator.registerApplicantExperiences(experience);
    }

    public String getEmail() {
        return registrator.getEmail();
    }

    public void registerImageFromFile(String pathToImage){
        registrator.registerImageFromFile(pathToImage);
    }

    public void registerImageBase64(String imageBase64) {
        registrator.registerImageFromBase64(imageBase64);
    }

    public Applicant createApplicant() {
        return registrator.createApplicant();
    }

    public void registerAllSkills(String[] defaultSkills, String[] userDefinedSkills) {
        registrator.registerAllSkills(defaultSkills, userDefinedSkills);
    }
}
