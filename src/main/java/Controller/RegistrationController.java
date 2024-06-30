package Controller;

import Data.ApplicantExperience;
import Data.DataStorage;

import java.util.Comparator;

public class RegistrationController {
    private final DataStorage datastorage;
    private final ApplicantRegistrator registrator;

    public RegistrationController() {
        registrator = new ApplicantRegistrator();
        datastorage = new DataStorage();
    }

    public void registerUserDefinedSkills(String[] skills) {
        registrator.registerUserDefinedSkills(skills);
    }

    public void registerDefaultSkills(String[] skills) {registrator.registerDefaultSkills(skills);}

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

    private static class compareJobDates implements Comparator<ApplicantExperience> {

        @Override
        public int compare(ApplicantExperience o1, ApplicantExperience o2) {
            return o1.getYearBegin() - o2.getYearBegin();
        }
    }
}
