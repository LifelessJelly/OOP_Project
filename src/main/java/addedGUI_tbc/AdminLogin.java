package addedGUI_tbc;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;

import GUI.SlidingPanel;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminLogin extends SlidingPanel{
    private MainFrame_other main;
    private JTextField usernameField;
    private JTextField passwordField;

    public AdminLogin(MainFrame_other main){
        this.main=main;
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        JButton btnToSelectionPage = new JButton("To selection page");
        btnToSelectionPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                showSelectionPage();
            }
        });
        GridBagConstraints gbc_btnToSelectionPage = new GridBagConstraints();
        gbc_btnToSelectionPage.insets = new Insets(0, 0, 5, 5);
        gbc_btnToSelectionPage.gridx = 3;
        gbc_btnToSelectionPage.gridy = 0;
        add(btnToSelectionPage, gbc_btnToSelectionPage);

        //===LOGIN PAGE TEXT===//
        JLabel lblLoginPage = new JLabel("Login Page (ADMIN):");
        GridBagConstraints gbc_lblLoginPage = new GridBagConstraints();
        gbc_lblLoginPage.gridx = 0;                                              //grid
        gbc_lblLoginPage.gridy = 1;
        gbc_lblLoginPage.weightx=1;                                              //weight
        gbc_lblLoginPage.weighty=1;
        gbc_lblLoginPage.gridwidth = 4;                                          //width
        gbc_lblLoginPage.anchor=GridBagConstraints.CENTER;                       //anchor
        gbc_lblLoginPage.insets = new Insets(30, 0, 5, 5);  //inset
        add(lblLoginPage,gbc_lblLoginPage);                                     //add w/ constraints

        //===USERNAME LABEL===//
        JLabel lblUsername = new JLabel("Username:");
        GridBagConstraints gbc_lblUsername = new GridBagConstraints();
        gbc_lblUsername.gridx = 0;
        gbc_lblUsername.gridy = 2;
        gbc_lblUsername.weightx=1;
        gbc_lblUsername.weighty=0.5;
        gbc_lblUsername.gridwidth = 2;
        gbc_lblUsername.anchor = GridBagConstraints.EAST;
        gbc_lblUsername.insets = new Insets(20, 0, 10, 10);
        add(lblUsername, gbc_lblUsername);

        //===USERNAME TEXT FIELD===//
        usernameField = new JTextField();
        GridBagConstraints gbc_usernameField = new GridBagConstraints();
        gbc_usernameField.gridx = 2;
        gbc_usernameField.gridy = 2;
        gbc_usernameField.weightx = 1;
        gbc_usernameField.weighty=0.5;
        gbc_usernameField.gridwidth = 4;
        gbc_usernameField.anchor = GridBagConstraints.WEST;
        gbc_usernameField.insets = new Insets(20, 0, 10, 10);
        add(usernameField, gbc_usernameField);
        usernameField.setColumns(10);

        //===PASSWORD LABEL===//
        JLabel lblPassword = new JLabel("Password:");
        GridBagConstraints gbc_lblPassword = new GridBagConstraints();
        gbc_lblPassword.gridx = 0;
        gbc_lblPassword.gridy = 3;
        gbc_lblPassword.weightx = 1;
        gbc_lblPassword.weighty=0.5;
        gbc_lblPassword.gridwidth = 2;
        gbc_lblPassword.anchor = GridBagConstraints.EAST;
        gbc_lblPassword.insets = new Insets(1, 0, 5, 5);
        add(lblPassword, gbc_lblPassword);

        //===PASSWORD TEXT FIELD===//
        passwordField = new JTextField();
        GridBagConstraints gbc_passwordField = new GridBagConstraints();
        gbc_passwordField.gridx = 2;
        gbc_passwordField.gridy = 3;
        gbc_passwordField.weightx = 1;
        gbc_passwordField.weighty=0.5;
        gbc_passwordField.gridwidth = 3;
        gbc_passwordField.anchor = GridBagConstraints.WEST;
        gbc_passwordField.insets = new Insets(1, 0, 5, 5);
        add(passwordField, gbc_passwordField);
        passwordField.setColumns(10);

        //===LOGIN BUTTON===//
        JButton btnLogin = new JButton("Login");
        GridBagConstraints gbc_btnLogin = new GridBagConstraints();
        gbc_btnLogin.gridx = 0;
        gbc_btnLogin.gridy = 5;
        gbc_btnLogin.weightx=1;
        gbc_btnLogin.weighty=1;
        gbc_btnLogin.gridwidth = 4;
        gbc_btnLogin.anchor=GridBagConstraints.CENTER;
        gbc_btnLogin.insets = new Insets(50, 0, 5, 5);
        add(btnLogin, gbc_btnLogin);

        //===VALIDITY CHECK LABEL===//
        JLabel lblValidTest = new JLabel("TESTTESTTESTTEST");
        GridBagConstraints gbc_lblValidTest = new GridBagConstraints();
        gbc_lblValidTest.gridx = 0;
        gbc_lblValidTest.gridy = 6;
        gbc_lblValidTest.weightx=1;
        gbc_lblValidTest.weighty=1;
        gbc_lblValidTest.gridwidth = 4;
        gbc_lblValidTest.insets = new Insets(0, 0, 5, 5);
        add(lblValidTest, gbc_lblValidTest);

        //===COMPONENT LISTENER (WINDOW)===//
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent arg0) {
                int adjustedFontSizeTitle= main.getContentPane().getWidth()/25;                                                     //scaling of title font
                int adjustedFontSizeBody= main.getContentPane().getWidth()/35;                                                      //scaling of body font
                Font bodyFont=new Font("Comic Sans MS", Font.PLAIN, (int)adjustedFontSizeBody);                                     //body font
                Font smallBodyFont=new Font("Comic Sans MS", Font.PLAIN, (int)(Math.round(adjustedFontSizeBody*0.8)));              //smaller body

                lblLoginPage.setFont(new Font("Comic Sans MS", Font.BOLD, (int)adjustedFontSizeTitle));
                lblUsername.setFont(bodyFont);
                lblPassword.setFont(bodyFont);
                lblValidTest.setFont(smallBodyFont);
                usernameField.setFont(smallBodyFont);
                passwordField.setFont(smallBodyFont);
                btnLogin.setFont(bodyFont);
                btnToSelectionPage.setFont(new Font("Comic Sans MS", Font.PLAIN, (int)(Math.round(adjustedFontSizeBody*0.5))));

                System.out.println(main.getContentPane().getWidth()+"X"+main.getContentPane().getHeight());
            }
        });


    }
    public void showSelectionPage(){
        /*this.main.showSelectionPage();*/
        //...see Reference_MainFrame...//
    }

}
