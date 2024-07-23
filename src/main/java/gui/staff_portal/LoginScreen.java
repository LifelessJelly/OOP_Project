package gui.staff_portal;

import com.formdev.flatlaf.ui.FlatLineBorder;
import controller.LoginMainframe;
import data.Staff;
import gui.ImageEmbedded;
import gui.StretchIcon;
import subsystems.ImageBase64;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ButtonGroup;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class LoginScreen extends JPanel {
    private JButton loginButton;
    private LoginMainframe main;
    private boolean passwordIsFilled;
    private boolean usernameIsFilled;
    private boolean domainSelected;
    private static final Color UNMET_REQUIREMENT_COLOUR = new Color(218, 67, 67);
    private JRadioButton hrButton;
    private JRadioButton managerButton;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JLabel badLoginWarnLabel;
    private ButtonGroup domainButtonGroup;
    private JButton registerButton;

    public LoginScreen(LoginMainframe main){
            this.main=main;
            this.setLayout(new GridBagLayout());
            this.setBackground(new Color(0, 0, 0, 0));
            initComponents();
            initListeners();
        }

    private void initComponents() {

        // can probably be its own class
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setMinimumSize(new Dimension(750, 100));
        loginPanel.setPreferredSize(new Dimension(750, 100));
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setBorder( new FlatLineBorder( new Insets( 16, 16, 16, 16 ), new Color(0, 0, 0, 0), -20, 90 ) );
        GridBagConstraints loginPanelConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                new Insets(50, 50, 50, 50), 0, 0);
        this.add(loginPanel, loginPanelConstraints);

        Image picture = ImageBase64.base64ToImage(ImageEmbedded.BACKGROUND_PIC);
        JLabel backgroundPic = new JLabel(new StretchIcon(picture, false));
        GridBagConstraints backgroundConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        this.add(backgroundPic, backgroundConstraints);

        JLabel companyLogo = new JLabel(new ImageIcon(ImageBase64.base64ToImage(ImageEmbedded.COMPANY_LOGO)));
        GridBagConstraints companyLogoConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(75, 75, 0, 0), 0, 0);
        loginPanel.add(companyLogo, companyLogoConstraints);

        JPanel domainPanel = new JPanel(new GridBagLayout());
        domainPanel.setOpaque(false);

        JLabel domainLabel = new JLabel("Select your domain: ");
        domainLabel.setFont(domainLabel.getFont().deriveFont(18f));
        GridBagConstraints domainLabelConstraints = new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(10, 75, 0, 0), 0, 0);
        loginPanel.add(domainLabel, domainLabelConstraints);

        domainButtonGroup = new ButtonGroup();

        hrButton = new JRadioButton("HR");
        hrButton.setOpaque(false);
        hrButton.setFont(hrButton.getFont().deriveFont(18f));
        domainButtonGroup.add(hrButton);
        GridBagConstraints HRButtonConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(0, 10, 0, 0), 0, 0);
        domainPanel.add(hrButton, HRButtonConstraints);


        managerButton = new JRadioButton("Manager");
        managerButton.setOpaque(false);
        managerButton.setFont(managerButton.getFont().deriveFont(18f));
        domainButtonGroup.add(managerButton);
        GridBagConstraints ManagerButtonConstraints = new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(0, 10, 0, 0), 0, 0);
        domainPanel.add(managerButton, ManagerButtonConstraints);

        GridBagConstraints domainPanelConstraints = new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(20, 75, 0, 0), 0, 0);
        loginPanel.add(domainPanel, domainPanelConstraints);

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(usernameLabel.getFont().deriveFont(18f));
        GridBagConstraints usernameLabelConstraints = new GridBagConstraints(0, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(10, 75, 0, 0), 0, 0);
        loginPanel.add(usernameLabel, usernameLabelConstraints);

        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(500, 50));
        usernameField.setMinimumSize(new Dimension(500, 50));
        usernameField.setFont(usernameField.getFont().deriveFont(18f));
        GridBagConstraints usernameFieldConstraints = new GridBagConstraints(0, 4, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(10, 75, 0, 0), 0, 0);
        loginPanel.add(usernameField, usernameFieldConstraints);


        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(passwordLabel.getFont().deriveFont(18f));
        GridBagConstraints passwordLabelConstraints = new GridBagConstraints(0, 5, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(10, 75, 0, 0), 0, 0);
        loginPanel.add(passwordLabel, passwordLabelConstraints);

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(500, 50));
        passwordField.setMinimumSize(new Dimension(500, 50));
        passwordField.setFont(passwordField.getFont().deriveFont(18f));
        GridBagConstraints passwordFieldConstraints = new GridBagConstraints(0, 6, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(10, 75, 0, 0), 0, 0);
        loginPanel.add(passwordField, passwordFieldConstraints);


        badLoginWarnLabel = new JLabel("");
        badLoginWarnLabel.setForeground(UNMET_REQUIREMENT_COLOUR);
        GridBagConstraints badLoginWarnConstraints = new GridBagConstraints(0, 7, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(10, 75, 0, 0), 0, 0);
        loginPanel.add(badLoginWarnLabel, badLoginWarnConstraints);


        loginButton = new JButton("LOGIN");
        loginButton.setEnabled(false);
        loginButton.setBackground(new Color(56, 109, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(new FlatLineBorder(new Insets(16, 16, 16, 16 ), new Color(255, 255, 255), -20, 90));
        loginButton.setMinimumSize(new Dimension(500, 50));
        loginButton.setPreferredSize(new Dimension(500, 50));
        loginButton.setFont(loginButton.getFont().deriveFont(20f));
        loginButton.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints loginButtonConstraints = new GridBagConstraints(0, 8, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(10, 0, 0, 0), 0, 0);
        loginPanel.add(loginButton, loginButtonConstraints);


        registerButton = new JButton("Not an registered employee? Click on me!");
        registerButton.setFont(registerButton.getFont().deriveFont(16f));
        registerButton.setBackground(Color.WHITE);
        registerButton.setBorder(BorderFactory.createEmptyBorder());
        registerButton.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints registerButtonConstraints = new GridBagConstraints(0, 9, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(10, 0, 0, 0), 0, 0);
        loginPanel.add(registerButton, registerButtonConstraints);
        //===COMPONENT LISTENER (WINDOW)===//

    }

    private void initListeners() {
        hrButton.addActionListener(e -> {
            domainSelected = true;
            updateLoginButton();
        });

        managerButton.addActionListener(e -> {
            domainSelected = true;
            updateLoginButton();
        });

        passwordField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                removeUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                passwordIsFilled = passwordField.getPassword().length != 0;
                updateLoginButton();
            }
        });

        usernameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                removeUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                usernameIsFilled = !usernameField.getText().isEmpty();
                updateLoginButton();
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent arg0) {
                int adjustedFontSizeTitle= getWidth()/25;
                int adjustedFontSizeBody= getWidth()/35;

                Font bodyFont=new Font("Comic Sans MS", Font.PLAIN, adjustedFontSizeBody);
                Font smallBodyFont=new Font("Comic Sans MS", Font.PLAIN, (int)(Math.round(adjustedFontSizeBody*0.8)));

//                    lblLoginPage.setFont(new Font("Comic Sans MS", Font.BOLD, adjustedFontSizeTitle));
//                    lblUsername.setFont(bodyFont);
//                    lblPassword.setFont(bodyFont);
//                    lblValidTest.setFont(smallBodyFont);
//                    rdbtnHr.setFont(smallBodyFont);
//                    rdbtnManager.setFont(smallBodyFont);

//                    btnLogin.setFont(bodyFont);

                System.out.println(getWidth()+"X"+getHeight());
            }
        });

        loginButton.addActionListener(e -> {
            int domainSelected = -1;
            if (hrButton.isSelected()){
                domainSelected = Staff.HR;
            }
            else if (managerButton.isSelected()){
                domainSelected = Staff.MANAGER;
            }
            if (main.checkAuthorisation(usernameField.getText(), passwordField.getPassword(), domainSelected)) {
                usernameField.setText("");
                passwordField.setText("");
                badLoginWarnLabel.setText("");
            }
            else {
                badLoginWarnLabel.setText("Invalid username, password or domain");
                usernameField.setText("");
                passwordField.setText("");
                hrButton.setSelected(false);
                managerButton.setSelected(false);
                domainButtonGroup.clearSelection();
            }
        });

        registerButton.addActionListener(e -> {
            badLoginWarnLabel.setText("");
            main.showRegister();
            usernameField.setText("");
            passwordField.setText("");
            hrButton.setSelected(false);
            managerButton.setSelected(false);
            domainButtonGroup.clearSelection();
            domainSelected = false;
        });

    }

    private void updateLoginButton(){
            loginButton.setEnabled(passwordIsFilled && usernameIsFilled && domainSelected);
        }

    }



