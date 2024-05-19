package org.example;


import Subsystems.Models;
import Subsystems.jsonReaderWriter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ViewApplicantsBackend {

    private Models.ApplicantModel data;

    ViewApplicantsBackend(String pathToApplicantsFile) throws IOException {
        data = jsonReaderWriter.jsonToModel(Files.readString(Paths.get(pathToApplicantsFile)), Models.ApplicantModel.class);


    }

}
