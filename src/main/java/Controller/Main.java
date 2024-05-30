package Controller;

import Data.ApplicantModel;
import Subsystems.Pointer;
import Subsystems.jsonReaderWriter;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Date;


public class Main extends JFrame {
    public static void main(String[] args) {
        ApplicantModel applicantModel = jsonReaderWriter.jsonToModel("", ApplicantModel.class);
        InputApplicantBackend inputApplicantBackend = new InputApplicantBackend(new Pointer<>(applicantModel));
        inputApplicantBackend.addApplicant("John", new Date(), 25, "Singapore", "Bisexual", "T000000000000", null, "I like kids", new ArrayList<>());
        inputApplicantBackend.addApplicant("Nigger", new Date(), 18, "Singapore", "Dog", "Execution", null, "Hi", new ArrayList<>());
        
        inputApplicantBackend.commitAndPushAllApplicants();
        System.out.println(applicantModel);
        String json = jsonReaderWriter.modelToJson(applicantModel);
        StringBuilder jsonFormater = new StringBuilder(json);
        
        System.out.println(json);
    }
}
