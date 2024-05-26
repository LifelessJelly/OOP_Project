package org.example;


import Subsystems.ApplicantModel;
import Subsystems.Models;
import Subsystems.jsonReaderWriter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ViewApplicantsBackend {

    private final ApplicantModel data;

    /**
     * @param pathToApplicantsFile the path to the .json file
     * @throws IOException is thrown if the file is missing (ideally shouldn't happen)
     */
    ViewApplicantsBackend(String pathToApplicantsFile) throws IOException {
        data = jsonReaderWriter.jsonToModel(Files.readString(Paths.get(pathToApplicantsFile)), ApplicantModel.class);
    }

    /**
     * @return the applicant class model
     */
    public ApplicantModel getData() {
        return data;
    }
}
