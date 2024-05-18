package org.example;

import Subsystems.Models;
import Subsystems.jsonReaderWriter;

import java.util.ArrayList;

public class InputApplicantBackend {

    final Models.ApplicantModel ptrToArray;
    final ArrayList<Models.ApplicantModel.Applicant> applicants;

    InputApplicantBackend(Models.ApplicantModel applicantModel) {
        applicants = new ArrayList<>();
        ptrToArray = applicantModel;
    }
    public void addApplicant(Models.ApplicantModel.Applicant applicant) {
        applicants.add(applicant);
    }

    public void addApplicantFromJson(String applicantJson) {
        applicants.add(jsonReaderWriter.jsonToModel(applicantJson, Models.ApplicantModel.Applicant.class));
    }

    public void commitAndPushAllApplicants(){
        ptrToArray.addApplicant(applicants);
    }

}
