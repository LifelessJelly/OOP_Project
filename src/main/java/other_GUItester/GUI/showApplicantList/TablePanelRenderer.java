package other_GUItester.GUI.showApplicantList;

import other_GUItester.Data.ApplicantData;
import other_GUItester.GUI.showApplicantList.PersonalInfoBox_duplicate;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TablePanelRenderer extends JLabel implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        //if the passed through object (value) is of type ApplicantData,... ...
        if(value instanceof ApplicantData){
            return new PersonalInfoBox_duplicate((ApplicantData) value);
            //remember to change the personalinfobox to take in objects of Applicant data!!!!!!!!!!!!
        }
        else{System.out.println("Ermmm what the sigma"+(1/0));
            return null;
        }

    }
}