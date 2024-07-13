package gui.infobase;


import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TableImageRender extends JLabel implements TableCellRenderer {

    public TableImageRender() {
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(SwingConstants.TOP);
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected,
                                                   boolean inFocus, int row, int column) {
        if (value instanceof BufferedImage) { //if value is an instance of a String
            //BufferedImage image = ImageIO.read(new File((String) value));
            BufferedImage image = (BufferedImage) value;

            //creating a new ImageIcon that is scaled to table width, height and set to smooth scaling
            ImageIcon iconScaled = new ImageIcon(image.getScaledInstance(
                    120, 120, Image.SCALE_SMOOTH));
            setIcon(iconScaled);
            //set the icon to null if the Object value is not a String
            if (selected) {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            } else {
                setBackground(table.getBackground());
                setForeground(table.getForeground());

            }

            return this;
        }
        throw new IllegalArgumentException("No image instance of class BufferedImage found");
    }
}

