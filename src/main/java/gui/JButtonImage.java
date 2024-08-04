package gui;

import subsystems.ImageBase64;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
