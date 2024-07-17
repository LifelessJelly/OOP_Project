package gui.infobase;

import controller.InfobaseMainframe;
import data.Applicant;

import javax.swing.table.AbstractTableModel;
import java.util.regex.Pattern;

public class ApplicantTableModel extends AbstractTableModel {
    //object arraylist
    private final InfobaseMainframe main;



    /**
     * Creates a new table model based on the list of applicants
     * @param main The infobase's mainframe object
     */
    public ApplicantTableModel(InfobaseMainframe main) {



        this.main = main;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public int getRowCount() {
        return main.getController().getApplicants().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return main.getController().getApplicants()[rowIndex];
    }
}