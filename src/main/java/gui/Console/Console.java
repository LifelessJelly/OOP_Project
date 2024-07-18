package GUI.Console;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.*;

import GUI.JPanelImageButton;


public class Console extends JPanel {
    public Console(){
        this.setBackground(Color.LIGHT_GRAY);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{1,1};
        gridBagLayout.rowHeights = new int[]{1, 5};
        gridBagLayout.columnWeights = new double[]{0.1,0.9};
        gridBagLayout.rowWeights = new double[]{0.1, 1.0};
        setLayout(gridBagLayout);

        JLabel lblWelcomename = new JLabel("WELCOME, (NAME)");
        GridBagConstraints gbc_lblWelcomename = new GridBagConstraints();
        gbc_lblWelcomename.anchor = GridBagConstraints.NORTH;
        gbc_lblWelcomename.gridwidth = 9;
        gbc_lblWelcomename.insets = new Insets(0, 0, 0, 0);
        gbc_lblWelcomename.gridx = 0;
        gbc_lblWelcomename.gridy = 0;
        gbc_lblWelcomename.weightx=1;
        gbc_lblWelcomename.weighty=0.1;
        add(lblWelcomename, gbc_lblWelcomename);

        //set to whatever panel I suppose
        JPanel btnPanel=new JPanel();
        btnPanel.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbc_btnPanel=new GridBagConstraints();
        gbc_btnPanel.insets = new Insets(0, 0, 0, 5);
        gbc_btnPanel.gridx=0;
        gbc_btnPanel.gridy=1;
        gbc_btnPanel.weightx=0.1;
        gbc_btnPanel.weighty=1;
        gbc_btnPanel.fill=GridBagConstraints.BOTH;
        add(btnPanel,gbc_btnPanel);

        JPanel panelPanel=new JPanel();
        panelPanel.setBackground(Color.DARK_GRAY);
        GridBagConstraints gbc_panelPanel=new GridBagConstraints();
        gbc_panelPanel.insets = new Insets(0, 0, 0, 5);
        gbc_panelPanel.gridx=1;
        gbc_panelPanel.gridy=1;
        gbc_panelPanel.weightx=1;
        gbc_panelPanel.weighty=1;
        gbc_panelPanel.fill=GridBagConstraints.BOTH;
        add(panelPanel,gbc_panelPanel);


        //for texts, add if needed
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent arg0) {
                int adjustedFontSizeTitle=(int)(getWidth()/25);
                lblWelcomename.setFont(new Font("Comic Sans MS", Font.BOLD, (int)adjustedFontSizeTitle));
            }
        });
    }
}
