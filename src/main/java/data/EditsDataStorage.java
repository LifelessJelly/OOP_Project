package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class EditsDataStorage {
    private final ArrayList<String> skills;
    private final ArrayList<ApplicantExperience> experience;

    public EditsDataStorage() {
        skills = new ArrayList<>();
        experience = new ArrayList<>();
    }

    //skill getter
    public String[] getSkills() {
        return skills.toArray(new String[0]);
    }

    //skill adder
    public void addSkill(String skill) {
        skills.add(skill);
    }

    //skill remove
    public void removeSkill(int index){
        skills.remove(index);
    }

    //get applicant experience array
    public ApplicantExperience[] getExperience() {
        return experience.toArray(new ApplicantExperience[0]);
    }

    public void importSkills(String[] skills) {
        if (skills != null) {
            this.skills.addAll(Arrays.asList(skills));
        }
    }

    //add applicant experience array
    //remove the experience, pass through index
    public void removeExperience(int index){
        experience.remove(index);
    }


}
