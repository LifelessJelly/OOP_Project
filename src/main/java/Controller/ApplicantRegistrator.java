package Controller;

import Data.ApplicantModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class ApplicantRegistrator {
    ApplicantModel.Applicant applicant;
    String name;
    int age;
    long date;
    String nricFin;
    String email;
    String gender;

    public void setBasicInfo(String name, int day, String month, int year, String nricFin, String email, String gender) {
        this.name = name;
        String dateString = String.valueOf(day) + ' ' + month + ' ' + String.valueOf(year);
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
}
