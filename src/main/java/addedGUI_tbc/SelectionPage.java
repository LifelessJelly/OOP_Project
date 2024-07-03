package addedGUI_tbc;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import addedGUI_tbc.MainFrame_other;

public class SelectionPage extends JPanel {
    private MainFrame_other main;
    public SelectionPage(MainFrame_other main) {
        this.main=main;

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        this.setLayout(gridBagLayout);

        //===lblCompanyName===//
        JLabel lblCompanyName = new JLabel("COMPANY NAME");
        GridBagConstraints gbc_lblCompanyName = new GridBagConstraints();
        gbc_lblCompanyName.insets = new Insets(30, 0, 5, 0);
        gbc_lblCompanyName.gridx = 0;
        gbc_lblCompanyName.gridy = 0;
        gbc_lblCompanyName.gridwidth=11;
        gbc_lblCompanyName.anchor=GridBagConstraints.CENTER;
        gbc_lblCompanyName.weightx=1;
        gbc_lblCompanyName.weighty=1;
        add(lblCompanyName, gbc_lblCompanyName);
        //===end===//

		/*////////////////////////GridBagConstraints gbc_lblCompanyName = new GridBagConstraints();
		gbc_lblCompanyName.gridx = 0;
		//gbc_lblLoginPage.gridx = 0;
		gbc_lblCompanyName.gridwidth = 3;
		gbc_lblCompanyName.insets = new Insets(30, 0, 5, 5);
		gbc_lblCompanyName.gridy = 0;
		gbc_lblCompanyName.anchor=GridBagConstraints.CENTER;
		gbc_lblCompanyName.weightx=1;
		gbc_lblCompanyName.weighty=1;
		/////////////////////////add(lblCompanyName,gbc_lblCompanyName);*/

        //===lblLoginAs===//
        JLabel lblLoginAs = new JLabel("Login as:");
        GridBagConstraints gbc_lblLoginAs = new GridBagConstraints();
        gbc_lblLoginAs.gridwidth = 11;
        gbc_lblLoginAs.insets = new Insets(0, 0, 5, 0);
        gbc_lblLoginAs.gridx = 0;
        gbc_lblLoginAs.gridy = 1;
        gbc_lblLoginAs.weightx=1;
        gbc_lblLoginAs.weighty=1;
        add(lblLoginAs, gbc_lblLoginAs);
        //===end===//

        JButton applicantButton = new JButton("Applicant");
        applicantButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //showApplicantDescriptionPage();
            }
        });

        JButton staffButton = new JButton("Staff");
        staffButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                showStaffLogin();
            }
        });

        JButton adminButton = new JButton("Admin");
        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                showAdminLogin();
            }
        });

        GridBagConstraints gbc_staffButton = new GridBagConstraints();
        gbc_staffButton.fill = GridBagConstraints.CENTER;
        gbc_staffButton.insets = new Insets(0, 0, 5, 5);
        gbc_staffButton.gridx = 5;
        gbc_staffButton.gridy = 2;
        gbc_staffButton.weightx=0.1;
        gbc_staffButton.weighty=0.2;
        add(staffButton, gbc_staffButton);

        GridBagConstraints gbc_applicantButton = new GridBagConstraints();
        gbc_applicantButton.insets = new Insets(0, 0, 5, 5);
        gbc_applicantButton.fill = GridBagConstraints.CENTER;
        gbc_applicantButton.gridx = 5;
        gbc_applicantButton.gridy = 3;
        gbc_applicantButton.weightx=0.1;
        gbc_applicantButton.weighty=0.2;
        add(applicantButton, gbc_applicantButton);



        GridBagConstraints gbc_adminButton = new GridBagConstraints();
        gbc_adminButton.fill = GridBagConstraints.CENTER;
        gbc_adminButton.insets = new Insets(0, 0, 5, 5);
        gbc_adminButton.gridx = 5;
        gbc_adminButton.gridy = 4;
        gbc_adminButton.weightx=0.1;
        gbc_adminButton.weighty=0.2;
        add(adminButton, gbc_adminButton);
        //===end===//

        //===spacer===//
        JLabel spacer = new JLabel("");
        GridBagConstraints gbc_spacer = new GridBagConstraints();
        gbc_spacer.gridheight = 3;
        gbc_spacer.gridwidth = 11;
        gbc_spacer.fill=GridBagConstraints.CENTER;
        gbc_spacer.gridx = 0;
        gbc_spacer.gridy = 5;
        gbc_spacer.weightx=2;
        gbc_spacer.weighty=2;
        add(spacer, gbc_spacer);



        //===COMPONENT LISTENER (WINDOW)===//
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent arg0) {
                int adjustedFontSizeTitle=(int)(main.getContentPane().getWidth()/25);
                int adjustedFontSizeBody=(int)(main.getContentPane().getWidth()/35);
                Font bodyFont=new Font("Comic Sans MS", Font.PLAIN, (int)adjustedFontSizeBody);
                Font smallBodyFont=new Font("Comic Sans MS", Font.PLAIN, (int)(Math.round(adjustedFontSizeBody*0.8)));

                lblCompanyName.setFont(new Font("Comic Sans MS", Font.BOLD, (int)adjustedFontSizeTitle));
                lblLoginAs.setFont(bodyFont);
                adminButton.setFont(smallBodyFont);
                applicantButton.setFont(smallBodyFont);
                staffButton.setFont(smallBodyFont);
                spacer.setFont(new Font("Comic Sans MS", Font.BOLD, (int)adjustedFontSizeTitle));

					/*lblLoginPage.setFont(new Font("Comic Sans MS", Font.BOLD, (int)adjustedFontSizeTitle));
					lblUsername.setFont(bodyFont);
					lblPassword.setFont(bodyFont);
					lblValidTest.setFont(smallBodyFont);
					usernameField.setFont(smallBodyFont);
					passwordField.setFont(smallBodyFont);
					btnLogin.setFont(bodyFont);*/

                System.out.println(main.getContentPane().getWidth()+"X"+main.getContentPane().getHeight());
            }
        });

    }

    public void showStaffLogin()				{this.main.showStaffLogin()					;}
    //public void showApplicantDescriptionPage()	{this.main.showApplicantDescriptionPage()	;}
    public void showAdminLogin()				{this.main.showAdminLogin()					;}


}
