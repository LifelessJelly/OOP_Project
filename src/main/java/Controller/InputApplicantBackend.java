package Controller;

import Data.ApplicantModel;
import Subsystems.Pointer;
import Subsystems.JsonReaderWriter;


import java.util.ArrayList;
import java.util.Date;

public class InputApplicantBackend {

    private final Pointer<ApplicantModel> ptrToArray;
    private final ArrayList<ApplicantModel.Applicant> applicants;

    InputApplicantBackend(Pointer<ApplicantModel> applicantModel) {
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
                             String pathToImage,
                             String resume,
                             String email,
                             ArrayList<ApplicantModel.Applicant.ApplicantPrevExp> jobExperience){

        addApplicant(name, birthdate.getTime(), age, nationality, gender, NRIC_Fin_Passport, pathToImage, resume, email, jobExperience);
    }

    public void addApplicant(String name,
                             long birthdate,
                             int age,
                             String nationality,
                             String gender,
                             String NRIC_Fin_Passport,
                             String pathToImage,
                             String resume,
                             String email,
                             ArrayList<ApplicantModel.Applicant.ApplicantPrevExp> jobExperience) {

        ApplicantModel.Applicant applicant = new ApplicantModel.Applicant();
        applicant.setDetails(name, birthdate, age, nationality, gender, NRIC_Fin_Passport, pathToImage, email);
        applicant.setResume(resume);
        applicant.addJobExperience(jobExperience);
        applicants.add(applicant);

    }

    public void addApplicantFromJson(String applicantJson) {
        applicants.add(JsonReaderWriter.jsonToModel(applicantJson, ApplicantModel.Applicant.class));
    }

    public void commitAndPushAllApplicants(){
        ptrToArray.get().addApplicant(applicants);
        System.gc(); // Calls garbage collector
    }

}
