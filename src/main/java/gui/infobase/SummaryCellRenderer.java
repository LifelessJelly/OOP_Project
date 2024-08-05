package gui.infobase;

import controller.InfobaseMainframe;
import data.Applicant;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class SummaryCellRenderer extends JTextArea implements TableCellRenderer {

    private final InfobaseMainframe main;

    public SummaryCellRenderer(InfobaseMainframe main) {
        this.main = main;
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        this.setOpaque(true);
        this.setEditable(false);
        this.setFont(this.getFont().deriveFont(16f));

    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean inFocus, int row, int column){
        if (value instanceof Applicant){
            setText(printApplicantDetails((Applicant)value));
            setSize(table.getColumnModel().getColumn(column).getWidth(),
                    getPreferredSize().height);

            if(selected){
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            } else {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
            }
            setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY));
        }

        return this;
    }

    /**
     * Generates a formatted string containing details of the applicant including name, birthdate, age, NRIC, email, gender, skills, shortlist status, and acceptance status.
     *
     * @param applicant the Applicant object for which details are to be printed
     * @return a formatted string with the applicant's details
     */
    private String printApplicantDetails(Applicant applicant){
        String nameText = main.getLocale("ApplicantListPage.JTable.applicantName");
        String ageText = main.getLocale("ApplicantListPage.JTable.applicantAge");
        String emailText = main.getLocale("ApplicantListPage.JTable.applicantEmail");
        String jobText = "Job role: ";//main.getLocale("ApplicantListPage.JTable.applicantJob");


        return  nameText + applicant.getName() + '\n' +
                ageText + applicant.getAge() + '\n' +
                emailText + applicant.getEmail() + '\n'+
                jobText + applicant.getJobRole();
    }
}