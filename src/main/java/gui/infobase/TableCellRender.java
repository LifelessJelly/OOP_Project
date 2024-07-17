package gui.infobase;

import controller.InfobaseMainframe;
import data.Applicant;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TableCellRender extends JTextArea implements TableCellRenderer {

    InfobaseMainframe main;

    public TableCellRender(InfobaseMainframe main) {
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
        String birthDateText = main.getLocale("ApplicantListPage.JTable.applicantBirthDate");
        String ageText = main.getLocale("ApplicantListPage.JTable.applicantAge");
        String NRICText = main.getLocale("ApplicantListPage.JTable.applicantNRIC");
        String emailText = main.getLocale("ApplicantListPage.JTable.applicantEmail");
        String genderText = main.getLocale("ApplicantListPage.JTable.applicantGender");
        String skillText = main.getLocale("ApplicantListPage.JTable.applicantSkills");
        String shortlistText = main.getLocale("ApplicantListPage.JTable.applicantShortlist");
        String acceptText = main.getLocale("ApplicantListPage.JTable.applicantAccept");
        String shortlistStatus;
        String acceptStatus;

        if (applicant.isShortlisted()){
            shortlistStatus = main.getLocale("ApplicantListPage.JTable.applicantShortlisted");
        }
        else {
            shortlistStatus = main.getLocale("ApplicantListPage.JTable.applicantPending");
        }
        if (applicant.isAccepted()){
            acceptStatus = main.getLocale("ApplicantListPage.JTable.applicantAccepted");
        }
        else if (!applicant.isShortlisted()){
            acceptStatus = main.getLocale("ApplicantListPage.JTable.applicantPendingShortlist");
        }
        else {
            acceptStatus = main.getLocale("ApplicantListPage.JTable.applicantAwaitingApproval");
        }
        return  nameText + applicant.getName() + '\n' +
                birthDateText + applicant.getBirthdate() + '\n' +
                ageText + applicant.getAge() + '\n' +
                NRICText + applicant.getNRIC() + '\n' +
                emailText + applicant.getEmail() + '\n' +
                genderText + applicant.getGender() + '\n' +
                skillText + String.join(", ", applicant.getSkills()) + '\n' +
                shortlistText + shortlistStatus + '\n' +
                acceptText + acceptStatus + '\n';
    }
}
