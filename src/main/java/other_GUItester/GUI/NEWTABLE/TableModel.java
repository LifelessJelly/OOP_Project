package other_GUItester.GUI.NEWTABLE;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableModel extends AbstractTableModel {
    //object arraylist
    private ArrayList<Object[]> data;

    public TableModel(ArrayList<Object[]> data) {
        this.data = data;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }
}