package controller;

import data.Staff;
import subsystems.JsonReaderWriter;
import subsystems.SHA256;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LoginController {
    public static boolean verifyLogin(String username, char[] password, int domain) {
        String path = System.getProperty("user.dir") + "\\" + "staff";
        File dir = new File(path);
        File[] files = dir.listFiles();
        if (files == null) {
            return false;
        }
        for (File file : files) {
            if (file.getName().endsWith(".json")) {
                String json = DataIO.readFile(file.getAbsolutePath());
                Staff staff = JsonReaderWriter.jsonToModel(json, Staff.class);
                if (staff.checkCredentials(username, password, domain)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean registerNewStaff(String username, char[] password, int securityLevel) {
        String path = System.getProperty("user.dir") + "\\" + "staff";
        File dir = new File(path);
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().endsWith(".json")) {
                    String json = DataIO.readFile(file.getAbsolutePath());
                    Staff staff = JsonReaderWriter.jsonToModel(json, Staff.class);
                    if (staff.checkUsernameExists(username)) {
                        return false;
                    }
                }
            }
        }
        Staff newStaff =  new Staff(securityLevel);
        newStaff.updateCredentials(username, password);
        try {
            try (FileWriter fileWriter = new FileWriter(path + "\\" + new String(SHA256.getHasherHex().hashString(String.valueOf(System.nanoTime()))) + "_Staff.json")) {
                fileWriter.write(JsonReaderWriter.modelToJson(newStaff));
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return true;
    }
}
