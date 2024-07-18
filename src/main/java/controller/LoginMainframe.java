package controller;

import gui.staff_portal.LoginScreen;
import gui.staff_portal.RegisterScreen;

import javax.swing.*;
import java.awt.*;

public class LoginMainframe extends JFrame {
    CardLayout cardLayout;
    JPanel loginPanel;
    JPanel registerPanel;
    public LoginMainframe() {

        this.cardLayout = new CardLayout();
        this.loginPanel = new LoginScreen(this);
        this.registerPanel = new RegisterScreen(this);
        this.setLayout(this.cardLayout);
        this.setTitle("Operate On Peasants Login Page");
        this.setPreferredSize(new Dimension(1920, 1080));
        showLogin();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();

    }

    public void showLogin(){
        this.getContentPane().add(loginPanel, "LoginScreen");
        cardLayout.show(this.getContentPane(), "LoginScreen");
    }

    public void showRegister(){
        this.getContentPane().add(registerPanel, "RegisterScreen");
        cardLayout.show(this.getContentPane(), "RegisterScreen");
    }

    public boolean checkAuthorisation(String username, char[] password){
        if (!LoginController.verifyLogin(username, password)){
            return false;
        }
        this.dispose();
        new InfobaseMainframe();
        return true;
    }

    public void registerStaff(){

    }
}
