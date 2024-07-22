package data;

import java.util.HashMap;
import java.util.Map;

public final class TranslationTable {

    private static TranslationTable instance;

    Map<TranslationKey, String> translationTable;


    /**
     * Constructor for TranslationTable class.
     * Initializes the translation table with the English and Chinese translations for various UI elements.
     */
    private TranslationTable() {
        translationTable = new HashMap<>();
        put("en", "EditApplicant.JButton.removeImage", "Remove image");
        put("cn", "EditApplicant.JButton.removeImage", "除去图片");

        put("en", "EditApplicant.JLabel.applicant_name", "Name: ");
        put("cn", "EditApplicant.JLabel.applicant_name", "姓名: ");

        put("en", "EditApplicant.JLabel.birth_date", "Date of birth: ");
        put("cn", "EditApplicant.JLabel.birth_date", "生日: ");

        put("en", "EditApplicant.JLabel.nric", "NRIC: ");
        put("cn", "EditApplicant.JLabel.nric", "身份证号码：");

        put("en", "EditApplicant.JLabel.email", "Email: ");
        put("cn", "EditApplicant.JLabel.email", "电子邮件地址");

        put("en", "EditApplicant.JLabel.gender", "Gender: ");
        put("cn", "EditApplicant.JLabel.gender", "性别： ");

        put("en", "EditApplicant.JLabel.skills", "Skills: ");
        put("cn", "EditApplicant.JLabel.skills", "某些技能：");

        put("en", "EditApplicant.JButton.add", "Add");
        put("cn", "EditApplicant.JButton.add", "加入");

        put("en", "EditApplicant.JButton.remove", "Remove");
        put("cn", "EditApplicant.JButton.remove", "除去");

        put("en", "EditApplicant.JButton.edit", "Edit");
        put("cn", "EditApplicant.JButton.edit", "更改");

        put("en", "EditApplicant.JLabel.experiences", "Experiences: ");
        put("cn", "EditApplicant.JLabel.experiences", "经验");

        put("en", "EditApplicant.JLabel.company", "Company: ");
        put("cn", "EditApplicant.JLabel.company", "公司： ");

        put("en", "EditApplicant.JLabel.position", "Position: ");
        put("cn", "EditApplicant.JLabel.position", "位置： ");

        put("en", "EditApplicant.JButton.save_changes", "Save Changes");
        put("cn", "EditApplicant.JButton.save_changes", "保存");

        put("en", "EditApplicant.JLabel.discard_changes", "Discard Changes");
        put("cn", "EditApplicant.JLabel.discard_changes", "摒弃");
        
        put("en", "ApplicantListPage.JTable.applicantName", "Name: ");
        put("cn", "ApplicantListPage.JTable.applicantName", "姓名: ");

        put("en", "ApplicantListPage.JTable.applicantBirthDate", "Date of birth: ");
        put("cn", "ApplicantListPage.JTable.applicantBirthDate", "生日： ");

        put("en", "ApplicantListPage.JTable.applicantAge", "Age: ");
        put("cn", "ApplicantListPage.JTable.applicantAge", "年纪：");

        put("en", "ApplicantListPage.JTable.applicantNRIC", "NRIC: ");
        put("cn", "ApplicantListPage.JTable.applicantNRIC", "身份证号码：");

        put("en", "ApplicantListPage.JTable.applicantEmail", "Email: ");
        put("cn", "ApplicantListPage.JTable.applicantEmail", "电子邮件地址： ");

        put("en", "ApplicantListPage.JTable.applicantGender", "Gender: ");
        put("cn", "ApplicantListPage.JTable.applicantGender", "性别： ");

        put("en", "ApplicantListPage.JTable.applicantSkills", "Skills: ");
        put("cn", "ApplicantListPage.JTable.applicantSkills", "某些技能：");

        put("en", "ApplicantListPage.JTable.applicantStatus", "Status:  ");
        put("cn", "ApplicantListPage.JTable.applicantStatus", "状态： ");

        put("en", "ApplicantListPage.JTable.applicantShortlisted", "Shortlisted for interview");
        put("cn", "ApplicantListPage.JTable.applicantShortlisted", "入围参加面试: ");

        put("en", "ApplicantListPage.JTable.applicantPending", "Pending");
        put("cn", "ApplicantListPage.JTable.applicantPending", "未决");

        put("en", "ApplicantListPage.JTable.applicantAccepted", "Accepted");
        put("cn", "ApplicantListPage.JTable.applicantAccepted", "进入公司");

        put("en", "ApplicantListPage.JTable.applicantPendingShortlist", "Pending shortlist");
        put("cn", "ApplicantListPage.JTable.applicantPendingShortlist", "入围未决定");

        put("en", "ApplicantListPage.JTable.applicantAwaitingApproval", "Awaiting approval");
        put("cn", "ApplicantListPage.JTable.applicantAwaitingApproval", "等待批准");

        put("en", "ApplicantListPage.JLabel.filterName", "Filter by name:");
        put("cn", "ApplicantListPage.JLabel.filterName", "姓名过滤： ");

        put("en", "ApplicantListPage.JLabel.filterStatus", "Filter by status: ");
        put("cn", "ApplicantListPage.JLabel.filterStatus", "状态过滤： ");

        put("en", "ApplicantListPage.JCheckBox.waitingShortlist", "Waiting shortlist");
        put("cn", "ApplicantListPage.JCheckBox.waitingShortlist", "等待入围");

        put("en", "ApplicantListPage.JCheckBox.shortlisted", "Shortlisted");
        put("cn", "ApplicantListPage.JCheckBox.shortlisted", "入围了");

        put("en", "ApplicantListPage.JCheckBox.accepted", "Accepted");
        put("cn", "ApplicantListPage.JCheckBox.accepted", "进入公司");

        put("en", "EditApplicant.JOptionPane.discardConfirm", "Are you sure you want to discard the current changes?");
        put("cn", "EditApplicant.JOptionPane.discardConfirm", "您确定把当前的更改摒弃吗？");

        put("en", "ShortlistPage.JCheckBox.programming", "Programming");
        put("cn", "ShortlistPage.JCheckBox.programming", "Programming");

        put("en", "ShortlistPage.JCheckBox.industrial", "Industrial");
        put("cn", "ShortlistPage.JCheckBox.industrial", "Industrial");

        put("en", "ShortlistPage.JCheckBox.artistic", "Artistic");
        put("cn", "ShortlistPage.JCheckBox.artistic", "Artistic");

        put("en", "ShortlistPage.JCheckBox.communication", "Communication");
        put("cn", "ShortlistPage.JCheckBox.communication", "Communication");


    }

    /**
     * Returns the instance of the TranslationTable class.
     *
     *
     * @return the instance of the TranslationTable class
     */
    public static TranslationTable getInstance() {
        if (instance == null) {
            instance = new TranslationTable();
        }
        return instance;
    }
    
    private void put(String language, String key, String translation) {
        translationTable.put(new TranslationKey(language, key), translation);
    }

    /**
     * Retrieves the translation for the given TranslationKey from the translation table.
     *
     * @param key The TranslationKey to look up in the translation table.
     * @return The translation corresponding to the provided key.
     */
    public String getTranslation(TranslationKey key) {
        return translationTable.get(key);
    }

}
