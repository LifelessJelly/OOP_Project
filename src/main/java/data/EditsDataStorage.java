package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditsDataStorage {
    private final List<String> skills;


    public EditsDataStorage(Applicant applicant) {
        skills = new ArrayList<>();
        if (applicant != null) {
            String[] skills = applicant.getSkills();
            if (skills != null) {
                this.skills.addAll(Arrays.asList(skills));
            }
        }
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

    public void editSkill(int index, String skill) {
        skills.set(index, skill);
    }
}
