package controller;

import data.Applicant;
import subsystems.ImageBase64;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Stream;

//TODO Deprecate this in the future, use only EditsDataStorage
@Deprecated
public class ApplicantRegistrator {
    private String name;
    private int age;
    private long birthDate;
    private String nricFin;
    private String email;
    private String gender;
    private String imageBase64;
    private String[] allSkills;

    public void registerBasicInfo(String name, int day, String month, int year, String nricFin, String email, String gender) {
        this.name = name;
        String dateString = String.valueOf(day) + ' ' + month + ' ' + year;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        this.birthDate = localDate.toEpochDay();
        this.age = LocalDate.now().getYear() - localDate.getYear();
        this.nricFin = nricFin;
        this.email = email;
        this.gender = gender;
    }
    public String getEmail(){
        return email;
    }


    public void registerAllSkills(String[] defaultSkills, String[] userDefinedSkills) {
        allSkills = Stream.concat(
                Arrays.stream(defaultSkills), Arrays.stream(userDefinedSkills)
        ).toArray(String[]::new);
    }


    public void registerImageFromBase64(Image imageBase64) {
        this.imageBase64 = ImageBase64.imageToBase64(imageBase64);
    }

    public Applicant createApplicant() {
        return new Applicant(name, birthDate, age, email, nricFin, gender, imageBase64, allSkills);
    }
}
