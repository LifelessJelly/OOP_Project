package Controller;

import GUI.Registration.Mainframe;


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

        new Mainframe();
    }
}

