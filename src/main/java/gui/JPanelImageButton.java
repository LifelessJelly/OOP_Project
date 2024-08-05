package gui;

import controller.ImageBase64;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A custom JPanel that combines an image and text, functioning as a button.
 *
 * <p>This class allows for the creation of a panel containing an image and a text label,
 * where the image can change based on mouse hover events. The position of the text relative
 * to the image can be specified using predefined constants.</p>
 *
 * <p>The image is displayed using a base64 encoded string, which is converted to an ImageIcon.
 * The panel uses a GridBagLayout to arrange its components.</p>
 *
 * <p>Mouse events are handled to change the image when the mouse enters or exits the invisible button area.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * JPanelImageButton button = new JPanelImageButton("Click Me", "base64ImageOff", "base64ImageOn", 100, 50, JPanelImageButton.LEFT);
 * </pre>
 *
 * @see JPanel
 */
public class JPanelImageButton extends JPanel{
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int TOP = 2;
    public static final int BOTTOM = 3;
    private final InvisibleButton invisibleButton;
    private final JLabel text;
    private final JLabel imageLabel;


    /**
     * Constructs a JPanelImageButton with specified text, images, dimensions, and position.
     *
     * @param textDisplayed the text to display on the button.
     * @param base64ImageOff the base64 encoded string of the image to display when not hovered.
     * @param base64ImageOn the base64 encoded string of the image to display when hovered.
     * @param width the width of the image.
     * @param height the height of the image.
     * @param position the position of the text relative to the image (LEFT, RIGHT, TOP, BOTTOM).
     * @throws IllegalArgumentException if the position is not one of the predefined constants.
     */
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
        imageLabel = new JLabel(scaledImage);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        text = new JLabel(textDisplayed);
        text.setMinimumSize(new Dimension(150, height));
        text.setPreferredSize(new Dimension(150, height));
        text.setFont(text.getFont().deriveFont(18f));
        text.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(text, textPosConstraints);
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

    /**
     * Adds an ActionListener to the invisible button.
     *
     * @param actionListener the ActionListener to be added.
     */
    public void addActionListener(ActionListener actionListener){
        invisibleButton.addActionListener(actionListener);
    }

    /**
     * Sets the size of the text box.
     *
     * @param size the new size for the text box.
     */
    public void setTextBoxSize(Dimension size){
        text.setMinimumSize(size);
        text.setPreferredSize(size);
    }

    /**
     * Sets the font size of the text label.
     *
     * @param size the new font size.
     */
    public void setFontSize(float size){
        text.setFont(text.getFont().deriveFont(size));
    }

    /**
     * Sets the background color of the button components.
     *
     * @param color the new background color.
     */
    public void setButtonBackground(Color color){
        text.setBackground(color);
        imageLabel.setBackground(color);
        invisibleButton.setBackground(color);
        this.setBackground(color);

    }

}
