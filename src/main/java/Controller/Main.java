package Controller;

import GUI.Registration.Mainframe;
import GUI.Infobase.InfobaseMainframe;


import Subsystems.ImageBase64;
import com.formdev.flatlaf.FlatDarkLaf;


import javax.swing.*;


public class Main {



    public static void main(String[] args) {


        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        System.out.println(ImageBase64.imageToBase64("C:\\Users\\User\\Downloads\\Lab73_Java\\OOP_Project\\check_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24(1).png"));
        new InfobaseMainframe();
    }
}

