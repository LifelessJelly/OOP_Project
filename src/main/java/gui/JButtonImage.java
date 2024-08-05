package gui;

import controller.ImageBase64;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A custom JButton that changes its icon when the mouse enters or exits its area.
 *
 * <p> When the mouse hovers over the button,
 * it displays the "on" icon, and when the mouse leaves, it reverts to the "off" icon.</p>
 *
 */
public class JButtonImage extends JButton {
    private final JButton thisReference;
    public JButtonImage(String iconOff, String iconOn) {
        super(new ImageIcon(ImageBase64.base64ToImage(iconOff)));
        thisReference = this;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                thisReference.setIcon(new ImageIcon(ImageBase64.base64ToImage(iconOn)));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                thisReference.setIcon(new ImageIcon(ImageBase64.base64ToImage(iconOff)));
            }
        });
    }
}
