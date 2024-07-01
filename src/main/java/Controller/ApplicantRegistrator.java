package Controller;

import Data.ApplicantExperience;
import Subsystems.ImageBase64;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;

public class ApplicantRegistrator {
    private String name;
    private int age;
    private long date;
    private String nricFin;
    private String email;
    private String gender;
    private String imageBase64;
    private String[] defaultSkills;
    private String[] userDefinedSkills;
    private ApplicantExperience[] applicantExperiences;



    public void registerBasicInfo(String name, int day, String month, int year, String nricFin, String email, String gender) {
        this.name = name;
        String dateString = String.valueOf(day) + ' ' + month + ' ' + year;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        this.date = localDate.toEpochDay();
        this.age = LocalDate.now().getYear() - localDate.getYear();
        this.nricFin = nricFin;
        this.email = email;
        this.gender = gender;
    }
    public String getEmail(){
        return email;
    }

    public void registerUserDefinedSkills(String[] skills) {
        userDefinedSkills = skills;
        System.out.println(Arrays.toString(userDefinedSkills));
    }

    public void registerDefaultSkills(String[] skills) {
        defaultSkills = skills;
        System.out.println(Arrays.toString(defaultSkills));
    }
    public void registerApplicantExperiences(ApplicantExperience[] experiences) {
        applicantExperiences = experiences;
    }

    public void registerImageFromFile(String pathToImage) {
        imageBase64 = ImageBase64.imageToBase64(pathToImage);
    }
    public void registerImageFromBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
}
