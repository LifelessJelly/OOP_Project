package other_GUItester.GUI.showApplicantList;

import javax.swing.*;
import java.awt.*;
import PersonalInfoBox_duplicate;
public class ListPanelRenderer implements ListCellRenderer<PersonalInfoBox_duplicate>{
    public ListPanelRenderer() {setOpaque(true);}

    public Component getListRedererComp(
        JList<? extends JPanel> list,
        //upper bound wildcard, <? extends JPanel>, where wildcard ? matches JPanel and its subtypes, (is an object of ---)
        //variable named list
        JPanel value,
        int index,
        boolean selected,
        boolean inFocus){

        if(selected){
            value.setBackground(list.getSelectionBackground());
            value.setForeground(list.getSelectionForeground());
        }

        else{
            value.setBackground(list.getBackground());
            value.setForeground(list.getForeground());
        }

        if(inFocus){
            value.setBorder(BorderFactory.createLineBorder(list.getSelectionBackground()));
        }
        else{
            value.setBorder(BorderFactory.createLineBorder(list.getBackground()));
        }

        return value;
    }
}
