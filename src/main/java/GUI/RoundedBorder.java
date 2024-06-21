package GUI;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

public class RoundedBorder extends AbstractBorder {
    private final Color COLOUR;
    private final int RADIUS = 8;
    private final int THICKNESS = 4;
    private final Insets INSETS;
    private final BasicStroke STROKE;
    private final int STROKEPAD;
    private final RenderingHints HINTS;

    RoundedBorder(){
        COLOUR = UIManager.getDefaults().getColor("Table.background");
        STROKE = new BasicStroke(THICKNESS);
        STROKEPAD = THICKNESS / 2;
        HINTS = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int pad = RADIUS + STROKEPAD;
        INSETS = new Insets(pad, pad, pad, pad);
    }
    @Override
    public Insets getBorderInsets(Component c) {
        return INSETS;
    }
    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        return getBorderInsets(c);
    }
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
        RoundRectangle2D.Double roundedBorder = new RoundRectangle2D.Double(
                STROKEPAD,
                STROKEPAD,
                width - THICKNESS,
                height - THICKNESS,
                RADIUS,
                RADIUS);
        Area area = new Area(roundedBorder);
        g2d.setRenderingHints(HINTS);


        Component parentComponent = c.getParent();
        if (parentComponent != null) {
            Color bgColor = parentComponent.getBackground();
            Rectangle rect = new Rectangle(0, 0, width, height);
            Area borderRegion = new Area(rect);
            g2d.setClip(borderRegion);
            g2d.setColor(bgColor);
            g2d.fillRect(0, 0, width, height);
            g2d.setClip(null);
        }
        g2d.setColor(COLOUR);
        g2d.setStroke(STROKE);
        g2d.draw(roundedBorder);
    }

}
