package other_GUItester.GUI_temp;

import Data.Applicant;
import Subsystems.ImageBase64;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class PersonalInfoBox_duplicate extends JPanel {


    public PersonalInfoBox_duplicate(Applicant applicant) {
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        this.setMinimumSize(new Dimension(Integer.MAX_VALUE, 100));
        this.setPreferredSize(new Dimension(Integer.MAX_VALUE, 100));
        this.setLayout(new GridBagLayout());
        ((GridBagLayout)this.getLayout()).columnWidths = new int[] {0, 0, 0};
        ((GridBagLayout)this.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
        ((GridBagLayout)this.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
        ((GridBagLayout)this.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

        Image img = ImageBase64.base64ToImage(applicant.getImageBase64());

        //===GET APPLICANT PICTURE===//
        JLabel picLabel = new JLabel(new ImageIcon(img.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        picLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        this.add(picLabel, new GridBagConstraints(0, 0, 1, 1, 1, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

        //TEXT_CONTAINER//
        JPanel textHolder = new JPanel();
        textHolder.setLayout(new GridBagLayout());
        this.add(textHolder, new GridBagConstraints(1, 0, 1, 3, 1, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

        //GridBagConstraints(int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty,
        // int anchor, int fill,
        // Insets insets, int ipadx, int ipady)

        //NAME//
        JLabel nameLabel = new JLabel(("NameHolder"));
        GridBagConstraints gbc_name=new GridBagConstraints(0, 0, 1, 1, 1, 0,
                GridBagConstraints.WEST, GridBagConstraints.BOTH,
                new Insets(0, 0, 2, 2), 0, 0);
        textHolder.add(nameLabel, gbc_name);

        //EMAIL//
        JLabel emailLabel = new JLabel(("EmailHolder"));
        GridBagConstraints gbc_Email=new GridBagConstraints(0, 1, 1, 1, 1, 0,
                GridBagConstraints.WEST, GridBagConstraints.BOTH,
                new Insets(0, 0, 2, 2), 0, 0);
        textHolder.add(emailLabel, gbc_Email);

        JLabel numberLabel = new JLabel(("NumberHolder"));
        GridBagConstraints gbc_Number=new GridBagConstraints(0, 2, 1, 1, 0,0,
                GridBagConstraints.WEST, GridBagConstraints.BOTH,
                new Insets(0, 0, 2, 2), 0, 0);
        textHolder.add(numberLabel, gbc_Number);



//        var divider = new JSeparator();
//        this.add(divider, new GridBagConstraints(0, 3, -1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));


        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent arg0) {
                int adjustedFontSizeTitle=(int)(textHolder.getWidth()/25);                                                     //scaling of title font
                int adjustedFontSizeBody=(int)(textHolder.getWidth()/35);                                                      //scaling of body font
                Font bodyFont=new Font("Comic Sans MS", Font.PLAIN, (int)adjustedFontSizeBody);                                     //body font
                Font smallBodyFont=new Font("Comic Sans MS", Font.PLAIN, (int)(Math.round(adjustedFontSizeBody*0.8)));              //smaller body
                nameLabel.setFont(bodyFont);
                emailLabel.setFont(bodyFont);
                numberLabel.setFont(bodyFont);

                System.out.println("InnerPanelSize= "+textHolder.getWidth()+"X"+textHolder.getHeight());
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                //showInfo();
            }
        });

    }

}

