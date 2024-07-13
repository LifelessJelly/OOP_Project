package gui.infobase;

import controller.InfobaseMainframe;
import data.Applicant;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
    //object arraylist
    private final InfobaseMainframe main;

    public TableModel(InfobaseMainframe main) {
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
        Applicant applicant = main.getController().getApplicants()[rowIndex];
        switch (columnIndex) {
            case 0:
                return applicant.getImage();
            case 1:
                return printApplicantDetails(applicant);
        }
        throw new IndexOutOfBoundsException();
    }

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