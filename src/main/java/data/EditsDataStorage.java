package data;

import java.util.ArrayList;
import java.util.Arrays;

public class EditsDataStorage {

    private String name;
    private long birthdate;
    private int age;
    private String gender;
    private String nric;
    private String imageBase64;
    private String email;

    private final ArrayList<String> skills;

    public EditsDataStorage() {
        skills = new ArrayList<>();
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

    public void importSkills(String[] skills) {
        if (skills != null) {
            this.skills.addAll(Arrays.asList(skills));
        }
    }
    //add applicant experience array
    //remove the experience, pass through index

}
