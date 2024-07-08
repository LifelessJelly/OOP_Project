package other_GUItester.Data;

import other_GUItester.GUI.showApplicantList.PersonalInfoBox_duplicate;

import javax.swing.*;

public class ListModel {
    private DefaultListModel<JPanel> modelList;

    public ListModel() {
        modelList = new DefaultListModel<>();
        for (int i = 0; i < modelList.getSize(); i++) {
            modelList.addElement(modelList.getElementAt(i));
        }
    }

    public DefaultListModel<JPanel> getModelList() {
        return modelList;
    }

    public void addPanel(PersonalInfoBox_duplicate panel){
        modelList.addElement(panel);
    }

    public void removePanel(int index){
        modelList.remove(index);
    }
}
