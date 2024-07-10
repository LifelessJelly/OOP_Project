package other_GUItester.GUI.NEWTABLE;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import Subsystems.ImageBase64;
public class TableImageRender extends JLabel implements TableCellRenderer {
    private ImageBase64 converter;

    public TableImageRender() {
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
        setOpaque(true);
        this.converter = new ImageBase64();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected,
                                                   boolean inFocus, int row, int column) {
        if (value instanceof String) { //if value is an instance of a String
            try {
                //BufferedImage image = ImageIO.read(new File((String) value));
                BufferedImage image = converter.base64ToImage((String) value);

                if (image != null) {
                    //creating a new ImageIcon that is scaled to table width, height and set to smooth scaling
                    ImageIcon iconScaled = new ImageIcon(image.getScaledInstance(
                            table.getWidth(), table.getHeight(), Image.SCALE_SMOOTH));
                    setIcon(iconScaled);
                } else {
                    setIcon(null);
                }
                //set the icon to null if the Object value is not a String
                if (selected) {
                    setBackground(table.getSelectionBackground());
                    setForeground(table.getSelectionForeground());
                } else {
                    setBackground(table.getBackground());
                    setForeground(table.getForeground());

                }
            } catch (Exception e) {
                setIcon(null);
                System.out.println(e);
            }
        }
        return this;
    }
}

