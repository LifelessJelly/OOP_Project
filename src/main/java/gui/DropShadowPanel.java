package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DropShadowPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public final int pixels;

    public DropShadowPanel(int pix) {
        this.pixels = pix;
        Border border = BorderFactory.createEmptyBorder(pixels, pixels, pixels, pixels);
        this.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), border));
    }

    @Override
    protected void paintComponent(Graphics g) {
        int shade = 0;
        int topOpacity = 80;
        for (int i = 0; i < pixels; i++) {
            g.setColor(new Color(shade, shade, shade, (-topOpacity / pixels) * i + topOpacity));
            g.drawRect(i, i, this.getWidth() - ((i * 2) + 1 - pixels), this.getHeight() - ((i * 2) + 1 - pixels));
        }
    }
}
