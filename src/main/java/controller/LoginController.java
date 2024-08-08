package controller;

import data.Staff;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LoginController {
    private Staff loggedInStaff;

    /**
     * Verifies the login credentials of a staff member.
     *
     * <p>This method checks the provided username and password against stored credentials
     * in JSON files located in the "staff" directory within the current working directory.
     * It reads each JSON file, converts it to a {@link Staff} object, and checks if the
     * credentials match. If a match is found, the corresponding {@link Staff} object is
     * stored in the {@code loggedInStaff} variable.</p>
     *
     * @param username the username of the staff member attempting to log in. Must not be null.
     * @param password the password of the staff member attempting to log in. Must not be null.
     * @return {@code true} if the credentials are valid; {@code false} otherwise.
     */
    public boolean verifyLogin(String username, char[] password) {
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
                    loggedInStaff = staff;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Registers a new staff member with the provided details.
     *
     * <p>This method checks if the username already exists among the existing staff members
     * stored in JSON files within the "staff" directory. If the username is unique, it creates
     * a new {@link Staff} object with the specified display name and security level, updates
     * the credentials with the provided username and password, and saves the new staff member
     * as a JSON file in the same directory.</p>
     *
     * @param displayName  the display name of the new staff member. Must not be null or empty.
     * @param username     the username for the new staff member. Must not be null or empty.
     * @param password     the password for the new staff member, represented as a character array.
     *                     Must not be null or empty.
     * @param securityLevel an integer representing the security level of the new staff member.
     *                      This value determines the access rights of the staff member.
     * @return true if the registration was successful (username was unique and staff member was created),
     *         false if the username already exists.
     * @throws RuntimeException if an I/O error occurs while writing the staff member's data to a file.
     */
    public static boolean registerNewStaff(String displayName, String username, char[] password, int securityLevel) {
        String path = System.getProperty("user.dir") + "\\" + "staff";
        File dir = new File(path);
        File[] files = dir.listFiles();
        if (files == null) {
            if (dir.mkdir()) {
                System.out.println("staff directory created");
                files = dir.listFiles();
            }
            else {
                throw new RuntimeException("Application does not have permissions to create a directory in location");
            }
        }
        assert files != null;
        for (File file : files) {
            if (file.getName().endsWith(".json")) {
                String json = DataIO.readFile(file.getAbsolutePath());
                Staff staff = JsonReaderWriter.jsonToModel(json, Staff.class);
                if (staff.checkUsernameExists(username)) {
                    return false;
                }
            }
        }
        Staff newStaff =  new Staff(displayName, securityLevel);
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

    public Staff getLoggedInUser() {
        return loggedInStaff;
    }
}
