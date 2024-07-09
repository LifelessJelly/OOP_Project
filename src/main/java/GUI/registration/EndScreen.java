package GUI.registration;

import data.Applicant;
import GUI.SlidingPanel;
import subsystem.JsonReaderWriter;

import javax.swing.*;
import java.awt.*;

public class EndScreen extends SlidingPanel {
    JLabel endLabel;
    JLabel bestWishesLabel;
    JLabel closeWindowLabel;
    JButton printJsonButton;
    GridBagLayout layout;
    GridBagConstraints endConstraints;
    GridBagConstraints bestWishesConstraints;
    GridBagConstraints closeWindowConstraints;
    GridBagConstraints printJsonConstraints;

    public EndScreen(RegistrationMainframe registrationMainframe) {
        this.registrationMainframe = registrationMainframe;
        initComponents();
    }

    private void initComponents() {
        movingInsets = new Insets(0, 0, 0, 0);
        layout = new GridBagLayout();
        this.setLayout(layout);

        endConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        bestWishesConstraints = new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        closeWindowConstraints = new GridBagConstraints(0, 2, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        printJsonConstraints = new GridBagConstraints(0, 3, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);

        endLabel = new JLabel("Thank you for applying!");
        endLabel.setFont(endLabel.getFont().deriveFont(24f));
        endLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(endLabel, endConstraints);

        bestWishesLabel = new JLabel("We wish you good luck on your application");
        bestWishesLabel.setFont(bestWishesLabel.getFont().deriveFont(20f));
        bestWishesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(bestWishesLabel, bestWishesConstraints);

        closeWindowLabel = new JLabel("You may now close this app, or download a copy of your applicant json");
        closeWindowLabel.setFont(closeWindowLabel.getFont().deriveFont(20f));
        closeWindowLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(closeWindowLabel, closeWindowConstraints);

        printJsonButton = new JButton("Print JSON");
        printJsonButton.setFont(printJsonButton.getFont().deriveFont(20f));
        printJsonButton.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(printJsonButton, printJsonConstraints);

        printJsonButton.addActionListener(e -> {
            Applicant newApplicant = registrationMainframe.getController().createApplicant();
            System.out.println(JsonReaderWriter.modelToJson(newApplicant));
        });
    }

    @Override
    protected void updateAnimation(){
        layout.setConstraints(endLabel, endConstraints);
        layout.setConstraints(bestWishesLabel, bestWishesConstraints);
        layout.setConstraints(closeWindowLabel, closeWindowConstraints);
        layout.setConstraints(printJsonButton, printJsonConstraints);
        this.revalidate();
        this.repaint();
    }
}
