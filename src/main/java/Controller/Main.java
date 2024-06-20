package Controller;

import Data.ApplicantModel;
import GUI.Registration.Mainframe;


import com.formdev.flatlaf.FlatDarkLaf;


import javax.swing.*;


public class Main {

    public Main(ApplicantModel.Applicant applicant){
        new Mainframe();
    }

    public static void main(String[] args) {


        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        new Main(new ApplicantModel.Applicant());
    }
}

