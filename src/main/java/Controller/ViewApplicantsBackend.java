package Controller;


import Data.ApplicantModel;
import Subsystems.jsonReaderWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ViewApplicantsBackend {

    private ApplicantModel data;

    ViewApplicantsBackend(String pathToApplicantsFile) throws IOException {
        data = jsonReaderWriter.jsonToModel(Files.readString(Paths.get(pathToApplicantsFile)), ApplicantModel.class);

    }

}
