package org.example;

import Subsystems.ApplicantModel;
import Subsystems.Models;
import Subsystems.jsonReaderWriter;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class InputApplicantBackend {

    final ApplicantModel ptrToArray;
    final ArrayList<ApplicantModel.Applicant> applicants;

    InputApplicantBackend(ApplicantModel applicantModel) {
        applicants = new ArrayList<>();
        ptrToArray = applicantModel;
    }
    public void addApplicant(ApplicantModel.Applicant applicant) {
        applicants.add(applicant);
    }

    public void addApplicant(String name,
                             Date birthdate,
                             int age,
                             String nationality,
                             String gender,
                             String NRIC_Fin_Passport,
                             Image applicantImage,
                             String resume,
                             ArrayList<ApplicantModel.Applicant.ApplicantPrevExp> jobExperience){

        addApplicant(name, birthdate.getTime(), age, nationality, gender, NRIC_Fin_Passport, applicantImage, resume, jobExperience);
    }

    public void addApplicant(String name,
                             long birthdate,
                             int age,
                             String nationality,
                             String gender,
                             String NRIC_Fin_Passport,
                             Image applicantImage,
                             String resume,
                             ArrayList<ApplicantModel.Applicant.ApplicantPrevExp> jobExperience) {

        ApplicantModel.Applicant applicant = new ApplicantModel.Applicant();
        applicant.setApplicantDetails(name, birthdate, age, nationality, gender, NRIC_Fin_Passport, applicantImage);
        applicant.setResume(resume);
        applicant.addJobExperience(jobExperience);
        applicants.add(applicant);

    }

    public void addApplicantFromJson(String applicantJson) {
        applicants.add(jsonReaderWriter.jsonToModel(applicantJson, ApplicantModel.Applicant.class));
    }

    public void commitAndPushAllApplicants(){
        ptrToArray.addApplicant(applicants);
        System.gc(); // Calls garbage collector
    }

}
