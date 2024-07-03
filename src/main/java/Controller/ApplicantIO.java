package Controller;


import Data.Applicant;
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

    //still broken don't use
    public static Applicant readApplicant(File pathToApplicantsFile) {
        String fileDir = pathToApplicantsFile.getPath();
        String parentDir = pathToApplicantsFile.getParent();
        return JsonReaderWriter.jsonToModel(FileContentEncryptor.decrypt(readFile(fileDir), parentDir), Applicant.class);

    }


    /**
     *
     * @param applicant
     * @param pathToDirectory
     */
    public static void writeApplicant(Applicant applicant, String pathToDirectory) {
        try {
            try (FileWriter newFile = new FileWriter(pathToDirectory + "\\" + new String(SHA256.getHasherHex().hashString(String.valueOf(System.currentTimeMillis()))) + ".oop")) {
                newFile.write(FileContentEncryptor.encrypt(JsonReaderWriter.modelToJson(applicant), pathToDirectory));
            }
        }
        catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }


    private static String readFile(String pathToFile) {
        try {
            return new String(Files.readAllBytes(Paths.get(pathToFile)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
