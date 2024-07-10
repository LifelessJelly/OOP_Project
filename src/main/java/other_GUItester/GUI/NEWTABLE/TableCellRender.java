package other_GUItester.GUI.NEWTABLE;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TableCellRender extends JTextArea implements TableCellRenderer {
    public TableCellRender() {
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(true);
        setEditable(false);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,boolean selected, boolean inFocus, int row, int column){
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
