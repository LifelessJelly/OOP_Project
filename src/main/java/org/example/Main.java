package org.example;

import Subsystems.ApplicantModel;
import Subsystems.jsonReaderWriter;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Date;


public class Main extends JFrame {
    public static void main(String[] args) {
        ApplicantModel applicantModel = new ApplicantModel();
        InputApplicantBackend inputApplicantBackend = new InputApplicantBackend(applicantModel);
        inputApplicantBackend.addApplicant("John", new Date(), 25, "Singapore", "Bisexual", "T000000000000", null, "I like kids", new ArrayList<>());
        inputApplicantBackend.commitAndPushAllApplicants();
        System.out.println(applicantModel);
        String json = jsonReaderWriter.modelToJson(applicantModel);
        System.out.println(json);
    }
}
