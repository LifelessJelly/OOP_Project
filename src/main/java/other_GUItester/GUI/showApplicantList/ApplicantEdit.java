package other_GUItester.GUI.showApplicantList;

import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ApplicantEdit extends JDialog{
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField emailField;

    public ApplicantEdit() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{107, 107, 107, 107, 107, 107};
        gridBagLayout.rowHeights = new int[]{45, 45, 45, 45, 45, 45, 45, 45};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);

        JLabel title = new JLabel("EDIT APPLICANT INFO");
        GridBagConstraints gbc_title = new GridBagConstraints();
        gbc_title.gridwidth = 6;
        gbc_title.insets = new Insets(0, 0, 5, 0);
        gbc_title.gridx = 0;
        gbc_title.gridy = 1;
        getContentPane().add(title, gbc_title);

        JLabel lblUsername = new JLabel("Username:");
        GridBagConstraints gbc_lblUsername = new GridBagConstraints();
        gbc_lblUsername.anchor = GridBagConstraints.EAST;
        gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
        gbc_lblUsername.gridx = 2;
        gbc_lblUsername.gridy = 2;
        getContentPane().add(lblUsername, gbc_lblUsername);



        JLabel lblPassword = new JLabel("Password:");
        GridBagConstraints gbc_lblPassword = new GridBagConstraints();
        gbc_lblPassword.anchor = GridBagConstraints.EAST;
        gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
        gbc_lblPassword.gridx = 2;
        gbc_lblPassword.gridy = 3;
        getContentPane().add(lblPassword, gbc_lblPassword);

        usernameField = new JTextField();
        GridBagConstraints gbc_usernameField = new GridBagConstraints();
        gbc_usernameField.fill = GridBagConstraints.HORIZONTAL;
        gbc_usernameField.gridwidth = 2;
        gbc_usernameField.insets = new Insets(0, 0, 5, 5);
        gbc_usernameField.gridx = 3;
        gbc_usernameField.gridy = 2;
        getContentPane().add(usernameField, gbc_usernameField);
        usernameField.setColumns(10);

        passwordField = new JTextField();
        GridBagConstraints gbc_passwordField = new GridBagConstraints();
        gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordField.insets = new Insets(0, 0, 5, 5);
        gbc_passwordField.gridwidth = 2;
        gbc_passwordField.gridx = 3;
        gbc_passwordField.gridy = 3;
        getContentPane().add(passwordField, gbc_passwordField);
        passwordField.setColumns(10);

        emailField = new JTextField();
        GridBagConstraints gbc_emailField = new GridBagConstraints();
        gbc_emailField.anchor = GridBagConstraints.WEST;
        gbc_emailField.insets = new Insets(0, 0, 5, 5);
        gbc_emailField.gridwidth = 2;
        gbc_emailField.gridx = 3;
        gbc_emailField.gridy = 4;
        getContentPane().add(emailField, gbc_emailField);
        emailField.setColumns(10);

        JButton btnEdit = new JButton("Apply Edit (exit)");
        GridBagConstraints gbc_btnEdit = new GridBagConstraints();
        gbc_btnEdit.insets = new Insets(0, 0, 5, 5);
        gbc_btnEdit.gridx = 3;
        gbc_btnEdit.gridy = 6;
        getContentPane().add(btnEdit, gbc_btnEdit);
        // TODO Auto-generated constructor stub
    }

}

