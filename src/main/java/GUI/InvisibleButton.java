package gui;

import javax.swing.*;
import java.awt.*;

public class InvisibleButton extends JButton {

    public InvisibleButton() {
        super();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0));
        super.paintComponent(g);
    }
}
