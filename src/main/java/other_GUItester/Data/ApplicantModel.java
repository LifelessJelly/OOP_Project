package other_GUItester.Data;

import java.util.List;
import java.util.Objects;

import javax.swing.table.AbstractTableModel;

import Data.Applicant;
import other_GUItester.Data.ApplicantData;

public class ApplicantModel extends AbstractTableModel{
    private List<ApplicantData>applicants;


    public ApplicantModel(List<ApplicantData>applicants) {
        this.applicants = applicants;
    }

    @Override
    public int getRowCount() {return applicants.size();}
    //row count is the number of applicants

    @Override
    public int getColumnCount() {
        return 1;
    }
    //single-column JTable used to display the applicant info

    @Override
    public ApplicantData getValueAt(int rowIndex, int columnIndex) {
        return applicants.get(rowIndex);
    }
    //gets the applicantData object at the index

    @Override
    public String getColumnName(int column) {
        return "APPLICANT INFO";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return ApplicantData.class;
        //change to get data from controller!!!!!!!!!!
    }


}