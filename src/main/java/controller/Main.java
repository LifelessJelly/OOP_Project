package controller;


import com.formdev.flatlaf.FlatDarkLaf;
import gui.staff_portal.LoginScreen;


import javax.swing.*;


public class Main {



    public static void main(String[] args) {


        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);

        }

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new LoginScreen());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}

