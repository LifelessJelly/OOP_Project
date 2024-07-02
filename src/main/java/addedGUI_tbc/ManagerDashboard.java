package addedGUI_tbc;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import addedGUI_tbc.MainFrame_other;

public class ManagerDashboard extends JPanel{
    private MainFrame_other main;
    private CardLayout card;
    public ManagerDashboard(MainFrame_other main) {
        this.main=main;
        setSize(640,360);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{107, 188, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        JLabel lblWelcome = new JLabel("Welcome, NAMEHERE");
        GridBagConstraints gbc_lblWelcome = new GridBagConstraints();
        gbc_lblWelcome.gridwidth = 8;
        gbc_lblWelcome.insets = new Insets(0, 0, 5, 5);
        gbc_lblWelcome.gridx = 0;
        gbc_lblWelcome.gridy = 0;
        gbc_lblWelcome.anchor=GridBagConstraints.CENTER;
        gbc_lblWelcome.weightx=1;
        gbc_lblWelcome.weighty=0.2;
        add(lblWelcome, gbc_lblWelcome);

        JPanel btnPanel = new JPanel();
        GridBagConstraints gbc_btnPanel = new GridBagConstraints();
        gbc_btnPanel.gridheight = 8;
        gbc_btnPanel.insets = new Insets(0, 0, 0, 0);
        gbc_btnPanel.fill = GridBagConstraints.BOTH;
        gbc_btnPanel.gridx = 0;
        gbc_btnPanel.gridy = 1;
        gbc_btnPanel.weightx=0.1;
        gbc_btnPanel.weighty=0.1;
        add(btnPanel, gbc_btnPanel);
        btnPanel.setLayout(new GridLayout(0, 1, 0, 0));

        JButton btnShortlist = new JButton("shortlist");
        btnShortlist.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ////////////////////////////showSelectionPage();
            }
        });
        btnPanel.add(btnShortlist);
        JButton btnViewShortlist = new JButton("view shortlist");
        btnViewShortlist.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /////////////////////////showStaffLogin();
            }
        });
        btnPanel.add(btnViewShortlist);
        JButton btnGenShortlist = new JButton("generate shortlist");
        btnPanel.add(btnGenShortlist);
        JButton btnSettings = new JButton("settings");
        btnPanel.add(btnSettings);

        JPanel panelPanel = new JPanel();
        panelPanel.setLayout(card);
        //panelPanel.setSize((int)(Math.round(main.getWidth()*0.75)),(int)(Math.round(main.getHeight()*0.75)));
        GridBagConstraints gbc_panelPanel = new GridBagConstraints();
        gbc_panelPanel.gridheight = 7;
        gbc_panelPanel.gridwidth = 7;
        gbc_panelPanel.fill = GridBagConstraints.BOTH;
        gbc_panelPanel.gridx = 1;
        gbc_panelPanel.gridy = 2;
        gbc_panelPanel.weightx=1;
        gbc_panelPanel.weighty=1;
        add(panelPanel, gbc_panelPanel);
        // TODO Auto-generated constructor stub

        //===COMPONENT LISTENER (WINDOW)===//
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent arg0) {
                int adjustedFontSizeTitle=(int)(main.getContentPane().getWidth()/25);
                int adjustedFontSizeBody=(int)(main.getContentPane().getWidth()/35);
                Font bodyFont=new Font("Comic Sans MS", Font.PLAIN, (int)adjustedFontSizeBody);
                Font smallBodyFont=new Font("Comic Sans MS", Font.PLAIN, (int)(Math.round(adjustedFontSizeBody*0.8)));
                lblWelcome.setFont(bodyFont);
                btnShortlist.setFont(smallBodyFont);
                btnViewShortlist.setFont(smallBodyFont);
                btnGenShortlist.setFont(smallBodyFont);
                btnSettings.setFont(smallBodyFont);
                //panelPanel.setSize((int)(Math.round(main.getWidth()*0.75)),(int)(Math.round(main.getHeight()*0.75)));

            }
        });
    }
    private void showSelectionPage(){this.main.showSelectionPage();}
    private void showStaffLogin(){this.main.showStaffLogin();}
}

