package controller;

import data.Staff;
import subsystems.JsonReaderWriter;

import java.io.File;

public class LoginController {
    public static boolean verifyLogin(String username, char[] password) {
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
                if (staff.checkCredentials(username, password)) {
                    return true;
                }
            }
        }
        return false;
    }
}
