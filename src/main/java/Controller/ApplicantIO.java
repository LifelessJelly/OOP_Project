package Controller;


import Data.ApplicantModel;
import Subsystems.JsonReaderWriter;
import Subsystems.PasswordHasher;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//possible rewrite of this file
//change it so that it handles both read and write of the json file for applicants
//also change it so that it handles only a single applicant at a time for security reasons
//store every applicant's data discretely
public class ApplicantIO {

    public static ApplicantModel.Applicant readApplicant(String pathToApplicantsFile) {
        try {
            return JsonReaderWriter.jsonToModel(Files.readString(Paths.get(pathToApplicantsFile)), ApplicantModel.Applicant.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void writeApplicant(ApplicantModel.Applicant applicant, String pathToDirectory) {
        try {
            try (FileWriter fileWriter = new FileWriter(pathToDirectory + "\\" + new PasswordHasher(String.valueOf(applicant.getId())).getEncodedStr() + ".json")) {
                fileWriter.write(JsonReaderWriter.modelToJson(applicant));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
