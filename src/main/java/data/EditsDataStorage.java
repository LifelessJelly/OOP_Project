package data;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditsDataStorage {

    private String name;
    private int age;
    private String gender;
    private String nric;
    private BufferedImage image;
    private String email;
    private String birthdate;
    private final List<String> skills;

    public EditsDataStorage(Applicant applicant) {
        skills = new ArrayList<>();
        if (applicant != null) {
            String[] skills = applicant.getSkills();
            if (skills != null) {
                this.skills.addAll(Arrays.asList(skills));
            }
            name = applicant.getName();
            age = applicant.getAge();
            gender = applicant.getGender();
            nric = applicant.getNRIC();
            image = applicant.getImage();
            email = applicant.getEmail();
            birthdate = applicant.getBirthdate();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNric() {
        return nric;
    }

    public void setNric(String nric) {
        this.nric = nric;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //add applicant experience array
    //remove the experience, pass through index

}
