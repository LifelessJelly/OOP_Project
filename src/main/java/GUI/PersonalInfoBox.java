package GUI;

import Data.Applicant;
import Subsystems.ImageBase64;

import javax.swing.*;
import java.awt.*;

public class PersonalInfoBox extends JPanel {
    public PersonalInfoBox(Applicant applicant) {



        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        this.setMinimumSize(new Dimension(Integer.MAX_VALUE, 100));
        this.setPreferredSize(new Dimension(Integer.MAX_VALUE, 100));
        this.setLayout(new GridBagLayout());
        ((GridBagLayout)this.getLayout()).columnWidths = new int[] {0, 0, 0};
        ((GridBagLayout)this.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
        ((GridBagLayout)this.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
        ((GridBagLayout)this.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

        Image img = ImageBase64.base64ToImage(applicant.getImageBase64());

        JLabel picLabel = new JLabel(new ImageIcon(img.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        picLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        this.add(picLabel, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));


        JPanel textHolder = new JPanel();
        textHolder.setLayout(new GridBagLayout());
        this.add(textHolder, new GridBagConstraints(1, 0, 1, 3, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
        textHolder.add(new JLabel("Tiktok Rizz Party"),
                new GridBagConstraints(0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 2, 2), 0, 0));
        textHolder.add(new JLabel("Sigma balls"),
                new GridBagConstraints(0, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 2, 2), 0, 0));
        textHolder.add(new JLabel("Buttermilk crispy chicken burger"),
                new GridBagConstraints(0, 2, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 2, 2), 0, 0));
        textHolder.add(new JLabel("Texas Instruments"),
                new GridBagConstraints(0, 3, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 2, 2), 0, 0));


//        var divider = new JSeparator();
//        this.add(divider, new GridBagConstraints(0, 3, -1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));


    }
}

