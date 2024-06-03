package Controller;

import Data.ApplicantModel;
import GUI.MainApplication;
import GUI.PersonalInfoBox;
import Subsystems.JsonReaderWriter;
import Subsystems.PasswordHasher;

import javax.swing.*;


import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;


public class Main extends JFrame {

    public Main(ApplicantModel.Applicant applicant){
        this.add(new PersonalInfoBox(applicant));
        this.add(new JSeparator());
        this.add(new PersonalInfoBox(applicant));
        this.add(new JSeparator());
        this.add(new PersonalInfoBox(applicant));
        this.setVisible(true);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(480, 240));
        pack();
        setLocationRelativeTo(getOwner());
    }

    public static void main(String[] args) {
        ApplicantModel.Applicant littleJohn = new ApplicantModel.Applicant();
        littleJohn.setDetails("Little John",
                new Date().getTime(),
                20, "Bangladeshi", "Male", "123456789", "E:\\IDEAProjects\\OOP_Project\\src\\main\\java\\WhatsApp Image 2024-05-31 at 23.27.42_a7d7c681.jpg",
                "littleJohn@student.tp.edu.sg");
        littleJohn.setResume("I love eating galvanised steel bars");
        String json = JsonReaderWriter.modelToJson(littleJohn);

        new Main(littleJohn);

    }
}

