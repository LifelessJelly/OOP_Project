package GUI.Registration;

import GUI.ImageEmbedded;
import Subsystems.ImageBase64;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class UploadImage extends SlidingPanel {
    JLabel uploadImageLabel;
    JLabel sizeLimitLabel;
    JPanel uploadImagePanel;
    Image profileImage;
    JPanel nextPrevPagePanel;
    GridBagLayout layout;
    String imageBase64String;
    GridBagConstraints uploadImageLabelConstraints;
    GridBagConstraints sizeLimitLabelConstraints;
    GridBagConstraints uploadImagePanelConstraints;
    GridBagConstraints nextPrevPagePanelConstraints;

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
        sizeLimitLabelConstraints = new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        uploadImagePanelConstraints = new GridBagConstraints(0, 2, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        nextPrevPagePanelConstraints = new GridBagConstraints(0, 3, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);

        uploadImageLabel = new JLabel("Upload portrait photo (optional)");
        uploadImageLabel.setFont(uploadImageLabel.getFont().deriveFont(22f));
        uploadImageLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(uploadImageLabel, uploadImageLabelConstraints);

        sizeLimitLabel = new JLabel("Maximum file size limit of 16MB");
        sizeLimitLabel.setFont(sizeLimitLabel.getFont().deriveFont(18f));
        sizeLimitLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(sizeLimitLabel, sizeLimitLabelConstraints);

        uploadImagePanel = new JPanel();
        uploadImagePanel.setLayout(new GridBagLayout());
        JButton imageButton = new JButton(new ImageIcon(ImageBase64.base64ToImage(ImageEmbedded.ADD_IMAGE_PICTURE).getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
        GridBagConstraints imageButtonConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        uploadImagePanel.add(imageButton, imageButtonConstraints);

        JButton removeButton = new JButton("Clear image");
        removeButton.setFont(removeButton.getFont().deriveFont(18f));
        removeButton.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints removeButtonConstraints = new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        uploadImagePanel.add(removeButton, removeButtonConstraints);

        this.add(uploadImagePanel, uploadImagePanelConstraints);

        nextPrevPagePanel = new JPanel();
        nextPrevPagePanel.setLayout(new GridBagLayout());

        JButton nextButton = new JButton("Next");
        nextButton.setFont(nextButton.getFont().deriveFont(18f));
        GridBagConstraints nextButtonConstraints = new GridBagConstraints(2, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        nextPrevPagePanel.add(nextButton, nextButtonConstraints);

        JLabel loadingLabel = new JLabel("Loading...");
        loadingLabel.setFont(loadingLabel.getFont().deriveFont(18f));
        loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loadingLabel.setMinimumSize(new Dimension(640, 20));
        loadingLabel.setPreferredSize(new Dimension(640, 20));
        GridBagConstraints blankSpacerPanelConstraints = new GridBagConstraints(1, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        nextPrevPagePanel.add(loadingLabel, blankSpacerPanelConstraints);

        JButton backButton = new JButton("Back");
        backButton.setFont(backButton.getFont().deriveFont(18f));
        GridBagConstraints backButtonConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        nextPrevPagePanel.add(backButton, backButtonConstraints);

        this.add(nextPrevPagePanel, nextPrevPagePanelConstraints);

        imageButton.addActionListener(e -> {
            JFileChooser j = getjFileChooser();
            j.setPreferredSize(new Dimension(640, 480));
            Action details = j.getActionMap().get("viewTypeDetails");
            details.actionPerformed(null);
            int r = j.showOpenDialog(null);
            loadingLabel.setText("Loading...");
            File selectedFile;
            if (r == JFileChooser.APPROVE_OPTION) {
                selectedFile = j.getSelectedFile().getAbsoluteFile();
                String pathToImage = selectedFile.getAbsolutePath();
                //huge performance impact here, the file gets read twice
                imageBase64String = ImageBase64.imageToBase64(pathToImage);
                profileImage = ImageBase64.base64ToImage(imageBase64String).getScaledInstance(120, 120, Image.SCALE_SMOOTH);
                imageButton.setIcon(new ImageIcon(profileImage));
                this.repaint();
                loadingLabel.setText("");
            }
        });

        nextButton.addActionListener(e -> {

            mainframe.getController().registerImageBase64(ImageEmbedded.DEFAULT_APPLICANT_IMAGE);

            mainframe.panelOutroRight();
        });

        backButton.addActionListener(e -> {
            mainframe.panelOutroLeft();
        });


    }

    private static JFileChooser getjFileChooser() {
        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        j.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()){
                    return true;
                }
                else {
                    String filename = f.getName().toLowerCase();
                    return filename.endsWith(".jpg") || filename.endsWith(".png") || filename.endsWith(".jpeg");
                }
            }

            @Override
            public String getDescription() {
                return "Image files (*.png, *.jpg, *.jpeg)";
            }
        });
        j.setFileSelectionMode(JFileChooser.FILES_ONLY);
        return j;
    }

    @Override
    protected void updateAnimation(){
        layout.setConstraints(uploadImageLabel, uploadImageLabelConstraints);
        layout.setConstraints(sizeLimitLabel, sizeLimitLabelConstraints);
        layout.setConstraints(uploadImagePanel, uploadImagePanelConstraints);
        layout.setConstraints(nextPrevPagePanel, nextPrevPagePanelConstraints);
        this.revalidate();
        this.repaint();
    }

}
