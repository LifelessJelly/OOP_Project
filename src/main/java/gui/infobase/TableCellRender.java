package gui.infobase;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TableCellRender extends JTextArea implements TableCellRenderer {
    public TableCellRender() {
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        this.setOpaque(true);
        this.setEditable(false);
        this.setFont(this.getFont().deriveFont(16f));

    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean inFocus, int row, int column){
        setText(value.toString());
        setSize(table.getColumnModel().getColumn(column).getWidth(),
                getPreferredSize().height);

        if(selected){
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        } else {
            setBackground(table.getBackground());
            setForeground(table.getForeground());
        }
        return this;
    }
}
