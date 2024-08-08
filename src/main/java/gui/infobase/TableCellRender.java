package gui.infobase;

import controller.InfobaseMainframe;
import data.Applicant;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TableCellRender extends JTextArea implements TableCellRenderer {

    private final InfobaseMainframe main;

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
        String birthDateText = main.getLocale("ApplicantListPage.JTable.applicantBirthDate");
        String ageText = main.getLocale("ApplicantListPage.JTable.applicantAge");
        String NRICText = main.getLocale("ApplicantListPage.JTable.applicantNRIC");
        String emailText = main.getLocale("ApplicantListPage.JTable.applicantEmail");
        String genderText = main.getLocale("ApplicantListPage.JTable.applicantGender");
        String skillText = main.getLocale("ApplicantListPage.JTable.applicantSkills");
        String shortlistText = main.getLocale("ApplicantListPage.JTable.applicantStatus");
        String interviewTimeText = main.getLocale("ApplicantListPage.JTable.applicantInterviewTime");
        String shortlistStatus;

        switch (applicant.getStatus()){
            case Applicant.WAITING_SHORTLIST:
                shortlistStatus = main.getLocale("ApplicantListPage.JTable.applicantPendingShortlist");
                break;
            case Applicant.SHORTLISTED_PENDING_DATE:
                shortlistStatus = main.getLocale("ApplicantListPage.JTable.applicantShortlisted");
                break;
            case Applicant.SHORTLISTED_TO_INTERVIEW:
                shortlistStatus = main.getLocale("ApplicantListPage.JTable.applicantShortlistSuccess");
                break;
            case Applicant.ACCEPTED_WAITING_JOB:
                shortlistStatus = main.getLocale("ApplicantListPage.JTable.applicantAccepted");
                break;
            case Applicant.ACCEPTED:
                shortlistStatus = main.getLocale("ApplicantListPage.JTable.applicantAcceptedSuccess");
                break;
            default:
                throw new IllegalArgumentException("This should not happen");
        }

        return  nameText + applicant.getName() + '\n' +
                birthDateText + applicant.getBirthdate() + '\n' +
                ageText + applicant.getAge() + '\n' +
                NRICText + applicant.getNRIC() + '\n' +
                emailText + applicant.getEmail() + '\n' +
                genderText + applicant.getGender() + '\n' +
                skillText + String.join(", ", applicant.getSkills()) + '\n' +
                shortlistText + shortlistStatus + '\n' +
                interviewTimeText + applicant.getInterviewTime();

    }
}