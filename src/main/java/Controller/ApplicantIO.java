package Controller;


import Data.ApplicantModel;
import Subsystems.FileContentEncryptor;
import Subsystems.JsonReaderWriter;
import Subsystems.SHA256;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//possible rewrite of this file
//change it so that it handles both read and write of the json file for applicants
//also change it so that it handles only a single applicant at a time for security reasons
//store every applicant's data discretely
public class ApplicantIO {

    public static ApplicantModel.Applicant readApplicant(File pathToApplicantsFile) {
        String fileDir = pathToApplicantsFile.getPath();
        String parentDir = pathToApplicantsFile.getParent();
        return JsonReaderWriter.jsonToModel(FileContentEncryptor.decrypt(readFile(fileDir), parentDir), ApplicantModel.Applicant.class);

    }
    public static void writeApplicant(ApplicantModel.Applicant applicant, String pathToDirectory) {
        try {
            try (FileWriter newFile = new FileWriter(pathToDirectory + "\\" + new String(SHA256.getHasherHex().hashString(String.valueOf(applicant.getId()))) + ".oop")) {
                newFile.write(FileContentEncryptor.encrypt(JsonReaderWriter.modelToJson(applicant), pathToDirectory));
            }
        }
        catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
    private static String readFile(String pathToFile) {
        try {
            return Files.readString(Paths.get(pathToFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
