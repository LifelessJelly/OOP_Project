package controller;

import data.Applicant;
import data.EditsDataStorage;

import java.awt.*;

public class RegistrationController {
    private final EditsDataStorage datastorage;
    private final ApplicantRegistrator registrator;

    public RegistrationController() {
        registrator = new ApplicantRegistrator();
        datastorage = new EditsDataStorage(null);
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

    public String getEmail() {
        return registrator.getEmail();
    }

    public void registerImageBase64(Image imageBase64) {
        registrator.registerImageFromBase64(imageBase64);
    }

    public Applicant createApplicant() {
        return registrator.createApplicant();
    }

    public void registerAllSkills(String[] defaultSkills, String[] userDefinedSkills) {
        registrator.registerAllSkills(defaultSkills, userDefinedSkills);
    }

}
