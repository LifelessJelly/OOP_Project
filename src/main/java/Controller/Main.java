package Controller;

import Data.ApplicantModel;
import Subsystems.JsonReaderWriter;
import Subsystems.PasswordHasher;

import javax.swing.*;


import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;


public class Main extends JFrame {
    public static void main(String[] args) {
        ApplicantModel.Applicant littleJohn = new ApplicantModel.Applicant();
        littleJohn.setDetails("Little John",
                new Date().getTime(),
                20, "Bangladeshi", "Male", "123456789", "E:\\IDEAProjects\\OOP_Project\\src\\main\\java\\WhatsApp Image 2024-05-31 at 23.27.42_a7d7c681.jpg",
                "littleJohn@student.tp.edu.sg");
        littleJohn.setResume("I love eating galvanised steel bars");
        String json = JsonReaderWriter.modelToJson(littleJohn);
        try {
            try (FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "\\" + new PasswordHasher(String.valueOf(littleJohn.getId())).getEncodedStr() + ".json")) {
                fileWriter.write(json);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

