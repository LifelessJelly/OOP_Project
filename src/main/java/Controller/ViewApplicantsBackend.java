package Controller;


import Data.ApplicantModel;
import Subsystems.JsonReaderWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//possible rewrite of this file
//change it so that it handles both read and write of the json file for applicants
//also change it so that it handles only a single applicant at a time for security reasons
//store every applicant's data discretely
public class ViewApplicantsBackend {

    private ApplicantModel data;

    ViewApplicantsBackend(String pathToApplicantsFile) throws IOException {
        data = JsonReaderWriter.jsonToModel(Files.readString(Paths.get(pathToApplicantsFile)), ApplicantModel.class);

    }

}
