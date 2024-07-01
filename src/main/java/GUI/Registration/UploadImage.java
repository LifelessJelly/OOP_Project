package GUI.Registration;

import GUI.ImageEmbedded;
import Subsystems.ImageBase64;

import javax.swing.*;
import java.awt.*;

public class UploadImage extends SlidingPanel {
    JLabel uploadImageLabel;
    JPanel uploadImagePanel;
    GridBagLayout layout;
    GridBagConstraints uploadImageLabelConstraints;
    GridBagConstraints uploadImagePanelConstraints;

    public UploadImage(Mainframe mainframe) {
        this.mainframe = mainframe;
        initComponents();
    }

    private void initComponents() {
        movingInsets = new Insets(0, 0, 0, 0);
        layout = new GridBagLayout();
        this.setLayout(layout);

        uploadImageLabelConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        uploadImagePanelConstraints = new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);


        uploadImageLabel = new JLabel("Upload portrait photo (optional)");
        uploadImageLabel.setFont(uploadImageLabel.getFont().deriveFont(22f));
        uploadImageLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(uploadImageLabel, uploadImageLabelConstraints);


        uploadImagePanel = new JPanel();
        uploadImagePanel.setLayout(new GridBagLayout());
        JButton uploadImageButton = new JButton(new ImageIcon(ImageBase64.base64ToImage(ImageEmbedded.DEFAULT_APPLICANT_IMAGE).getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
        GridBagConstraints uploadImageButtonConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        uploadImagePanel.add(uploadImageButton, uploadImageButtonConstraints);
        this.add(uploadImagePanel, uploadImagePanelConstraints);
    }

    @Override
    protected void updateAnimation(){
        layout.setConstraints(uploadImageLabel, uploadImageLabelConstraints);
        layout.setConstraints(uploadImagePanel, uploadImagePanelConstraints);
        this.revalidate();
        this.repaint();
    }

}
