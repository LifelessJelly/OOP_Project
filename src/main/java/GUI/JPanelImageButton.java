package GUI;

import Subsystems.ImageBase64;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JPanelImageButton extends JPanel{
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int TOP = 2;
    public static final int BOTTOM = 3;
    private final InvisibleButton invisibleButton;

    public JPanelImageButton(String textDisplayed, String base64ImageOff, String base64ImageOn, int width, int height, int position) {
        GridBagConstraints textPosConstraints;

        switch (position){
            case LEFT:
                textPosConstraints = new GridBagConstraints(0, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 10), 0, 0);

                break;
            case RIGHT:
                textPosConstraints = new GridBagConstraints(2, 1, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 10, 0, 0), 0, 0);
                break;
            case TOP:
                textPosConstraints = new GridBagConstraints(1, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 10, 0), 0, 0);
                break;
            case BOTTOM:
                textPosConstraints = new GridBagConstraints(1, 2, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(10, 0, 0, 0), 0, 0);
                break;
            default:
                throw new IllegalArgumentException();
        }
        this.setLayout(new GridBagLayout());
        GridBagConstraints invisibleButtonConstraints = new GridBagConstraints(0, 0, 3, 3, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        GridBagConstraints iconConstraints = new GridBagConstraints(1, 1, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        ImageIcon scaledImage = new ImageIcon(ImageBase64.base64ToImage(base64ImageOff).getScaledInstance(width, height, Image.SCALE_SMOOTH));
        invisibleButton = new InvisibleButton();
        JLabel imageLabel = new JLabel(scaledImage);
        this.add(new JLabel(textDisplayed), textPosConstraints);
        this.add(imageLabel, iconConstraints);
        this.add(invisibleButton, invisibleButtonConstraints);
        invisibleButton.setRolloverEnabled(true);

        invisibleButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                imageLabel.setIcon(new ImageIcon(ImageBase64.base64ToImage(base64ImageOn).getScaledInstance(width, height, Image.SCALE_SMOOTH)));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                imageLabel.setIcon(new ImageIcon(ImageBase64.base64ToImage(base64ImageOff).getScaledInstance(width, height, Image.SCALE_SMOOTH)));
            }
        });
    }

    public void addActionListener(ActionListener actionListener){
        invisibleButton.addActionListener(actionListener);
    }

}
