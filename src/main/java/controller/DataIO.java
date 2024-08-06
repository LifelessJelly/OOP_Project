package controller;


import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//possible rewrite of this file
//change it so that it handles both read and write of the json file for applicants
//also change it so that it handles only a single applicant at a time for security reasons
//store every applicant's data discretely
public class DataIO {

    /**
     * gets path to any given file
     *
     * @param pathToFile the file's path in the directory
     *
     */
    public static String readFile(String pathToFile) {
        try {
            return new String(Files.readAllBytes(Paths.get(pathToFile)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * writes a file to a directory
     *
     * @param directoryOfFile gets file directory
     * @param content content of folder in string
     *
     */
    public static void writeFile(String directoryOfFile, String content) {
        try {
            try (FileWriter fileWriter = new FileWriter(directoryOfFile)) {
                fileWriter.write(content);
                System.out.println("file written to " + directoryOfFile);
            }
        } catch (IOException ex) {
            System.out.println("smth went wrong");
            throw new RuntimeException(ex);

        }
    }

}
