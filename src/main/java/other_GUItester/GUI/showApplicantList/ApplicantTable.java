package other_GUItester.GUI.showApplicantList;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.util.List;

import Controller.MainFrame;
//import other_GUItester.Data.AdminData;
import other_GUItester.Data.ApplicantData;
import other_GUItester.Data.ApplicantModel;

public class ApplicantTable extends JPanel{
    private int noApplicant;
    private MainFrame main;
    public ApplicantTable(MainFrame main) {
        this.setSize(640,360);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40};
        gridBagLayout.rowHeights = new int[]{45,45,45,45,45,45,45,45};
        gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        JLabel lblEditApplicants = new JLabel("Edit Applicants");
        GridBagConstraints gbc_lblEditApplicants = new GridBagConstraints();
        gbc_lblEditApplicants.gridwidth = 17;
        gbc_lblEditApplicants.gridx = 0;
        gbc_lblEditApplicants.gridy = 0;
        add(lblEditApplicants, gbc_lblEditApplicants);

        List<ApplicantData> app= Arrays.asList(
                new ApplicantData(1, "Jonathan","onathan.v.2@gmail.com",97651988),
                new ApplicantData(2,"Legoshi","lego1999@hotmail.com",77777777),
                new ApplicantData(3,"Tan Kin Lian","honglimpark@msn.com",88887777),
                new ApplicantData(99,"filler","filleremail",99999999),
                new ApplicantData(99,"filler","filleremail",99999999),
                new ApplicantData(99,"filler","filleremail",99999999),
                new ApplicantData(99,"filler","filleremail",99999999),
                new ApplicantData(99,"filler","filleremail",99999999),
                new ApplicantData(99,"filler","filleremail",99999999),
                new ApplicantData(99,"filler","filleremail",99999999),
                new ApplicantData(99,"filler","filleremail",99999999),
                new ApplicantData(99,"filler","filleremail",99999999),
                new ApplicantData(99,"filler","filleremail",99999999),
                new ApplicantData(99,"filler","filleremail",99999999),
                new ApplicantData(99,"filler","filleremail",99999999)
        );

        ApplicantModel appModel = new ApplicantModel(app);

        //noApplicant=5;  //value to be set when the datastorage etc is made
        JTable appTable = new JTable(/*noApplicant,1*/appModel);
        GridBagConstraints gbc_appTable = new GridBagConstraints(1,1,14,5,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(0,0,0,0),0,0);
        appTable.setDefaultRenderer(ApplicantModel.class,new TablePanelRenderer());
        //appTable.setValueAt((new PersonalInfoBox_duplicate(1,"name1","peepee@gmail.com",92839283)),1,1);
        //appTable.setValueAt((new PersonalInfoBox_duplicate(2,"name2","peepee@gmail1.com",345345345)),2,1);
        //appTable.setValueAt((new PersonalInfoBox_duplicate(3,"name2","peepee@gmail2.com",43564364)),3,1);
        //appTable.setValueAt((new PersonalInfoBox_duplicate(4,"name2","peepee@gmail3.com",3756757)),4,1);
        //appTable.setValueAt((new PersonalInfoBox_duplicate(5,"name2","peepee@gmail4.com",345326322)),5,1);
    }
}