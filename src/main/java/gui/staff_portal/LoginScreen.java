package gui.staff_portal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ButtonGroup;

    public class LoginScreen extends JPanel{
        //private MainFrame main;
        private JTextField usernameField;
        private JTextField passwordField;
        private final ButtonGroup buttonGroup = new ButtonGroup();

        public LoginScreen(/*MainFrame main*/){
            //this.main=main;
            GridBagLayout gridBagLayout = new GridBagLayout();
            gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
            gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
            setLayout(gridBagLayout);

            JRadioButton rdbtnHr = new JRadioButton("HR");
            buttonGroup.add(rdbtnHr);
            GridBagConstraints gbc_rdbtnHr = new GridBagConstraints();
            gbc_rdbtnHr.anchor = GridBagConstraints.EAST;
            gbc_rdbtnHr.insets = new Insets(0, 0, 5, 5);
            gbc_rdbtnHr.gridx = 1;
            gbc_rdbtnHr.gridy = 2;
            add(rdbtnHr, gbc_rdbtnHr);

            JRadioButton rdbtnManager = new JRadioButton("Manager");
            buttonGroup.add(rdbtnManager);
            GridBagConstraints gbc_rdbtnManager = new GridBagConstraints();
            gbc_rdbtnManager.anchor = GridBagConstraints.WEST;
            gbc_rdbtnManager.insets = new Insets(0, 0, 5, 5);
            gbc_rdbtnManager.gridx = 2;
            gbc_rdbtnManager.gridy = 2;
            add(rdbtnManager, gbc_rdbtnManager);

            //===LOGIN PAGE TEXT===//
            JLabel lblLoginPage = new JLabel("Select role");
            GridBagConstraints gbc_lblLoginPage = new GridBagConstraints();
            gbc_lblLoginPage.gridx = 0;                                                     //grid
            gbc_lblLoginPage.gridy = 1;
            gbc_lblLoginPage.weightx=1;                                                     //weight
            gbc_lblLoginPage.weighty=1;
            gbc_lblLoginPage.gridwidth = 4;                                                 //width
            gbc_lblLoginPage.anchor=GridBagConstraints.CENTER;                              //anchor
            gbc_lblLoginPage.insets = new Insets(30, 0, 5, 5);          //inset
            add(lblLoginPage,gbc_lblLoginPage);
            //add w/ constraints



            //===USERNAME LABEL===//
            JLabel lblUsername = new JLabel("Username:");
            GridBagConstraints gbc_lblUsername = new GridBagConstraints();
            gbc_lblUsername.gridx = 0;
            gbc_lblUsername.gridy = 3;
            gbc_lblUsername.weightx=1;
            gbc_lblUsername.weighty=0.5;
            gbc_lblUsername.gridwidth = 2;
            gbc_lblUsername.anchor = GridBagConstraints.EAST;
            gbc_lblUsername.insets = new Insets(20, 0, 10, 10);
            add(lblUsername, gbc_lblUsername);

            //===USERNAME TEXT FIELD===//
            usernameField = new JTextField();
            usernameField.setEnabled(false);
            GridBagConstraints gbc_usernameField = new GridBagConstraints();
            gbc_usernameField.gridx = 2;
            gbc_usernameField.gridy = 3;
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
            gbc_lblPassword.gridy = 4;
            gbc_lblPassword.weightx = 1;
            gbc_lblPassword.weighty=0.5;
            gbc_lblPassword.gridwidth = 2;
            gbc_lblPassword.anchor = GridBagConstraints.EAST;
            gbc_lblPassword.insets = new Insets(1, 0, 5, 5);
            add(lblPassword, gbc_lblPassword);

            //===PASSWORD TEXT FIELD===//
            passwordField = new JTextField();
            passwordField.setEnabled(false);
            GridBagConstraints gbc_passwordField = new GridBagConstraints();
            gbc_passwordField.gridx = 2;
            gbc_passwordField.gridy = 4;
            gbc_passwordField.weightx = 1;
            gbc_passwordField.weighty=0.5;
            gbc_passwordField.gridwidth = 3;
            gbc_passwordField.anchor = GridBagConstraints.WEST;
            gbc_passwordField.insets = new Insets(1, 0, 5, 5);
            add(passwordField, gbc_passwordField);
            passwordField.setColumns(10);

            JButton btnLogin = new JButton("Login");
            btnLogin.addActionListener(arg0 -> {

            });
            GridBagConstraints gbc_btnLogin = new GridBagConstraints();
            gbc_btnLogin.gridx = 0;
            gbc_btnLogin.gridy = 6;
            gbc_btnLogin.weightx=1;
            gbc_btnLogin.weighty=1;
            gbc_btnLogin.gridwidth = 4;
            gbc_btnLogin.anchor=GridBagConstraints.CENTER;
            gbc_btnLogin.insets = new Insets(50, 0, 5, 5);
            add(btnLogin, gbc_btnLogin);

            JLabel lblValidTest = new JLabel("Login Outcome here:");
            GridBagConstraints gbc_lblValidTest = new GridBagConstraints();
            gbc_lblValidTest.gridx = 0;
            gbc_lblValidTest.gridy = 7;
            gbc_lblValidTest.weightx=1;
            gbc_lblValidTest.weighty=1;
            gbc_lblValidTest.gridwidth = 4;
            gbc_lblValidTest.insets = new Insets(0, 0, 5, 5);
            add(lblValidTest, gbc_lblValidTest);

            rdbtnHr.addActionListener(arg0 -> {
                rdbtnManager.setEnabled(true);
                rdbtnHr.setEnabled(false);
                lblLoginPage.setText("Login Page (HR):");
                usernameField.setEnabled(true);
                usernameField.setText(null);
                passwordField.setEnabled(true);
                passwordField.setText(null);
            });
            rdbtnManager.addActionListener(arg0 -> {
                rdbtnManager.setEnabled(false);
                rdbtnHr.setEnabled(true);
                lblLoginPage.setText("Login Page (Manager):");
                usernameField.setEnabled(true);
                usernameField.setText(null);
                passwordField.setEnabled(true);
                passwordField.setText(null);
            });

            //===COMPONENT LISTENER (WINDOW)===//
            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent arg0) {
                    int adjustedFontSizeTitle= getWidth()/25;
                    int adjustedFontSizeBody= getWidth()/35;

                    Font bodyFont=new Font("Comic Sans MS", Font.PLAIN, adjustedFontSizeBody);
                    Font smallBodyFont=new Font("Comic Sans MS", Font.PLAIN, (int)(Math.round(adjustedFontSizeBody*0.8)));

                    lblLoginPage.setFont(new Font("Comic Sans MS", Font.BOLD, adjustedFontSizeTitle));
                    lblUsername.setFont(bodyFont);
                    lblPassword.setFont(bodyFont);
                    lblValidTest.setFont(smallBodyFont);
                    rdbtnHr.setFont(smallBodyFont);
                    rdbtnManager.setFont(smallBodyFont);

                    usernameField.setFont(smallBodyFont);
                    passwordField.setFont(smallBodyFont);

                    btnLogin.setFont(bodyFont);

                    System.out.println(getWidth()+"X"+getHeight());
                }
            });


        }
    }



