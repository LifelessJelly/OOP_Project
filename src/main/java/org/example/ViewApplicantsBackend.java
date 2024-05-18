package org.example;


import Subsystems.Models;
import Subsystems.jsonReaderWriter;

public class ViewApplicantsBackend {
    Models.ApplicantModel retrieveApplicantsData(String pathToApplicantsFile){
        return jsonReaderWriter.jsonToModel(pathToApplicantsFile, Models.ApplicantModel.class);

    }
}
