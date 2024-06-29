package Controller;

import java.util.ArrayList;

public class Controller {
    private final ApplicantRegistrator registrator;
    private final ArrayList<String> skills;

    public Controller() {
        registrator = new ApplicantRegistrator();
        skills = new ArrayList<>();
    }

    public void registerSkills(String[] skills) {
        registrator.registerSkills(skills);
    }

    public void registerBasicInfo(String name, int day, String month, int year, String nricFin, String email, String gender){
        registrator.registerBasicInfo(name, day, month, year, nricFin, email, gender);
    }

    public String[] getSkills() {
        return skills.toArray(new String[0]);
    }
    public void addSkill(String skill) {
        skills.add(skill);
    }
    public void removeSkill(int index) {
        skills.remove(index);
    }

    public String getEmail() {
        return registrator.getEmail();
    }
}
