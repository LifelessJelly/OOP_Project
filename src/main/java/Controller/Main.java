package Controller;

import Data.ApplicantModel;
import GUI.PersonalInfoBox;
import Subsystems.JsonReaderWriter;
import Subsystems.SHA256;

import javax.swing.*;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
        //Passes through the encryptor
        ApplicantIO.writeApplicant(littleJohn, System.getProperty("user.dir"));
        //Decrypts the file
        ApplicantModel.Applicant johnCopy = ApplicantIO.readApplicant(new File("E:\\IDEAProjects\\OOP_Project\\32f1898a5caefac84e5ae11cbfe9ab3d2fecdb420eb69e3d2e6b7ca46fd41c1a.oop"));
        //Will fail because the file is in a different directory (tampered with)
        try {
            Files.copy(new File("E:\\IDEAProjects\\OOP_Project\\32f1898a5caefac84e5ae11cbfe9ab3d2fecdb420eb69e3d2e6b7ca46fd41c1a.oop").toPath(), new File("E:\\IDEAProjects\\OOP_Project\\src\\dummyDirectory").toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            var willFail =  ApplicantIO.readApplicant(new File("E:\\IDEAProjects\\OOP_Project\\src\\dummyDirectory\\32f1898a5caefac84e5ae11cbfe9ab3d2fecdb420eb69e3d2e6b7ca46fd41c1a.oop"));
        }
        catch (Exception e) {
            System.out.println("lol won't decrypt this shit for u");
        }


        new Main(johnCopy);

    }
}

