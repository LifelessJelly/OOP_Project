package controller;

import com.formdev.flatlaf.FlatLightLaf;
import gui.staff_portal.LoginScreen;
import gui.staff_portal.RegisterScreen;

import javax.swing.*;
import java.awt.*;

public class LoginMainframe extends JFrame {
    CardLayout cardLayout;
    JPanel loginPanel;
    JPanel registerPanel;
    private final LoginController loginController;
    public LoginMainframe() {

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);

        }
        loginController = new LoginController();
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

    /**
     * Displays the login screen by adding the login panel to the content pane
     * and switching to the "LoginScreen" card in the card layout.
     *
     * <p>This method utilizes a {@link CardLayout} to manage different panels
     * within the same container. It adds the {@link JPanel} representing the
     * login screen to the content pane and makes it visible.</p>
     */
    public void showLogin(){
        this.getContentPane().add(loginPanel, "LoginScreen");
        cardLayout.show(this.getContentPane(), "LoginScreen");
    }

    /**
     * Displays the registration screen by adding the register panel to the content pane
     * and switching to the "RegisterScreen" card in the card layout.
     *
     * <p>This method utilizes a {@link CardLayout} to manage different panels
     * within the same container. It adds the {@link JPanel} representing the
     * registration screen to the content pane and makes it visible.</p>
     */
    public void showRegister(){
        this.getContentPane().add(registerPanel, "RegisterScreen");
        cardLayout.show(this.getContentPane(), "RegisterScreen");
    }

    /**
     * Checks the authorization of a user based on the provided username and password.
     *
     * <p>This method verifies the login credentials using the {@link LoginController}.
     * If the credentials are valid, it disposes of the current frame and opens the
     * main application frame with the logged-in user's information. Otherwise, it returns false.</p>
     *
     * @param username the username of the user attempting to log in. Must not be null.
     * @param password the password of the user attempting to log in. Must not be null.
     * @return true if the authorization is successful; false otherwise.
     */
    public boolean checkAuthorisation(String username, char[] password){
        if (!loginController.verifyLogin(username, password)){
            return false;
        }
        this.dispose();
        new InfobaseMainframe(loginController.getLoggedInUser());
        return true;
    }

    /**
     * Registers a new staff member with the specified details.
     *
     * <p>This method delegates the registration process to the
     * {@link LoginController#registerNewStaff(String, String, char[], int)} method.
     * It requires the staff member's display name, username, password, and security level.</p>
     *
     * @param displayName the display name of the staff member. Must not be null or empty.
     * @param username the username for the staff member. Must not be null or empty.
     * @param password the password for the staff member, represented as a character array.
     *                 Must not be null or empty.
     * @param securityLevel the security level assigned to the staff member. Must be a valid integer.
     * @return true if the registration was successful; false otherwise.
     */
    public boolean registerStaff(String displayName, String username, char[] password, int securityLevel){
        return LoginController.registerNewStaff(displayName, username, password, securityLevel);
    }
}
