package data;

import java.util.HashMap;
import java.util.Map;

public final class TranslationTable {

    private static TranslationTable instance;

    private final Map<TranslationKey, String> translationTable;
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
        put("cn", "EditApplicant.JLabel.birth_date", "出生日期: ");

        put("en", "EditApplicant.JLabel.nric", "NRIC: ");
        put("cn", "EditApplicant.JLabel.nric", "身份证号：");

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
        put("cn", "ApplicantListPage.JTable.applicantBirthDate", "出生日期： ");

        put("en", "ApplicantListPage.JTable.applicantAge", "Age: ");
        put("cn", "ApplicantListPage.JTable.applicantAge", "年纪：");

        put("en", "ApplicantListPage.JTable.applicantNRIC", "NRIC: ");
        put("cn", "ApplicantListPage.JTable.applicantNRIC", "身份证号：");

        put("en", "ApplicantListPage.JTable.applicantEmail", "Email: ");
        put("cn", "ApplicantListPage.JTable.applicantEmail", "电子邮件地址： ");

        put("en", "ApplicantListPage.JTable.applicantGender", "Gender: ");
        put("cn", "ApplicantListPage.JTable.applicantGender", "性别： ");

        put("en", "ApplicantListPage.JTable.applicantSkills", "Skills: ");
        put("cn", "ApplicantListPage.JTable.applicantSkills", "某些技能：");

        put("en", "ApplicantListPage.JTable.applicantStatus", "Status:  ");
        put("cn", "ApplicantListPage.JTable.applicantStatus", "状态： ");

        put("en", "ApplicantListPage.JTable.applicantShortlisted", "Shortlisted (Date to be set)");
        put("cn", "ApplicantListPage.JTable.applicantShortlisted", "入围参加面试 (日期未决定)");

        put("en", "ApplicantListPage.JTable.applicantPending", "Pending");
        put("cn", "ApplicantListPage.JTable.applicantPending", "未决");

        put("en", "ApplicantListPage.JTable.applicantAccepted", "Accepted (Position to be set)");
        put("cn", "ApplicantListPage.JTable.applicantAccepted", "进入公司 (公司位置未决定)");

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

        put("en", "ApplicantListPage.JCheckBox.shortlistedWaitingHR", "Shortlisted (Interview date unknown)");
        put("cn", "ApplicantListPage.JCheckBox.shortlistedWaitingHR", "入围 (面试日期未知)");

        put("en", "ApplicantListPage.JCheckBox.shortlisted", "Shortlisted");
        put("cn", "ApplicantListPage.JCheckBox.shortlisted", "入围了");

        put("en", "ApplicantListPage.JCheckBox.acceptedWaitingManager", "Accepted (Job unknown)");
        put("cn", "ApplicantListPage.JCheckBox.acceptedWaitingManager", "进入公司 (工作未知)");

        put("en", "ApplicantListPage.JCheckBox.accepted", "Accepted");
        put("cn", "ApplicantListPage.JCheckBox.accepted", "进入公司");

        put("en", "ApplicantListPage.JLabel.excludeSkill", "Exclude Skills: ");
        put("cn", "ApplicantListPage.JLabel.excludeSkill", "排除以下的技能");

        put("en", "EditApplicant.JOptionPane.discardConfirm", "Are you sure you want to discard the current changes?");
        put("cn", "EditApplicant.JOptionPane.discardConfirm", "您确定把当前的更改摒弃吗？");

        put("en", "ShortlistPage.JCheckBox.programming", "Programming");
        put("cn", "ShortlistPage.JCheckBox.programming", "编程设计");

        put("en", "ShortlistPage.JCheckBox.industrial", "Industrial");
        put("cn", "ShortlistPage.JCheckBox.industrial", "产业的");

        put("en", "ShortlistPage.JCheckBox.artistic", "Artistic");
        put("cn", "ShortlistPage.JCheckBox.artistic", "艺术的");

        put("en", "ShortlistPage.JCheckBox.communication", "Communication");
        put("cn", "ShortlistPage.JCheckBox.communication", "沟通");

        put("en", "ApplicantListPage.ToolTip.editApplicantEnabled", "Edit Applicant");
        put("cn", "ApplicantListPage.ToolTip.editApplicantEnabled", "更改申请者");

        put("en", "ApplicantListPage.ToolTip.editApplicantDisabled", "Edit Applicant (Select an applicant)");
        put("cn", "ApplicantListPage.ToolTip.editApplicantDisabled", "更改申请者 (请选位申请者)");

        put("en", "ApplicantListPage.ToolTip.setInterviewDateEnabled", "Set Interview Date");
        put("cn", "ApplicantListPage.ToolTip.setInterviewDateEnabled", "选面试日期");

        put("en", "ApplicantListPage.ToolTip.setInterviewDateDisabled", "Set Interview Date (Requires applicant to be shortlisted for interview by manager)");
        put("cn", "ApplicantListPage.ToolTip.setInterviewDateDisabled", "选面试日期 (申请者需要经理为他入围参加面试)");

        put("en", "ApplicantListPage.ToolTip.removeApplicantEnabled", "Remove Applicant");
        put("cn", "ApplicantListPage.ToolTip.removeApplicantEnabled", "除去申请者");

        put("en", "ApplicantListPage.ToolTip.removeApplicantDisabled", "Remove Applicant (Select an applicant)");
        put("cn", "ApplicantListPage.ToolTip.removeApplicantDisabled", "除去申请者 (请选位申请者)");

        put("en", "ApplicantListPage.ToolTip.acceptApplicantEnabled", "Accept Applicant");
        put("cn", "ApplicantListPage.ToolTip.acceptApplicantEnabled", "招收申请者");

        put("en", "ApplicantListPage.ToolTip.acceptApplicantDisabled", "Accept Applicant (Requires applicant to pass the interview process)");
        put("cn", "ApplicantListPage.ToolTip.acceptApplicantDisabled", "招收申请者 (申请者需要经过面试而成功)");

        put("en", "ApplicantListPage.ToolTip.addApplicant", "Add Applicant");
        put("cn", "ApplicantListPage.ToolTip.addApplicant", "加入申请者");

        put("en", "ApplicantListPage.ToolTip.addApplicantJSON", "Add Applicant (JSON)");
        put("cn", "ApplicantListPage.ToolTip.addApplicantJSON", "加入申请者 (JSON)");

        put("en", "ApplicantListPage.ToolTip.viewApplicantPDFDisabled", "View Applicant PDF (Select an applicant)");
        put("cn", "ApplicantListPage.ToolTip.viewApplicantPDFDisabled", "读申请者的简历 (请选位申请者)");

        put("en", "ApplicantListPage.ToolTip.viewApplicantPDFEnabled", "View Applicant PDF");
        put("cn", "ApplicantListPage.ToolTip.viewApplicantPDFEnabled", "读申请者的简历");

        put("en", "ApplicantListPage.ToolTip.setJobRoleDisabled", "Set Job Role (Requires applicant to be accepted)");
        put("cn", "ApplicantListPage.ToolTip.setJobRoleDisabled", "安排工作 (申请者需要先被进入公司)");

        put("en", "ApplicantListPage.ToolTip.setJobRoleEnabled", "Set Job Role");
        put("cn", "ApplicantListPage.ToolTip.setJobRoleEnabled", "安排工作");

        put("en", "ApplicantListPage.ToolTip.shortlistApplicantDisabled", "Shortlist Applicant (Requires applicant to be waiting to be shortlisted)");
        put("cn", "ApplicantListPage.ToolTip.shortlistApplicantDisabled", "入围申请者 (申请者需要正在等待入围)");

        put("en", "ApplicantListPage.ToolTip.shortlistApplicantEnabled", "Shortlist Applicant");
        put("cn", "ApplicantListPage.ToolTip.shortlistApplicantEnabled", "入围申请者");

        put("en", "AddApplicant.JButton.addPDF", "Select Resume from computer (.pdf file)");
        put("cn", "AddApplicant.JButton.addPDF", "从电脑里选出简历 (.pdf file)");

        put("en", "Console.JMenu.manager", "(Manager)");
        put("cn", "Console.JMenu.manager", "(经理)");

        put("en", "Console.JPanelImageButton.viewApplicants", "View Applicants");
        put("cn", "Console.JPanelImageButton.viewApplicants", "查看申请者");

        put("en", "Console.JPanelImageButton.viewSummary", "View Summary");
        put("cn", "Console.JPanelImageButton.viewSummary", "查看简介");

        put("en", "Console.JPanelImageButton.settings", "Settings");
        put("cn", "Console.JPanelImageButton.settings", "设置");

        put("en", "AddApplicant.JOptionPane.addConfirm", "Confirm add applicant? (WARNING: You will not be able to change the resume!)");
        put("cn", "AddApplicant.JOptionPane.addConfirm", "确定加入申请者？ (警告: 您之后不能再改简历！)");

        put("en", "Console.JOptionPane.discardConfirm", "Leaving this page will discard your changes. Continue?");
        put("cn", "Console.JOptionPane.discardConfirm", "离开这面会除去您现在的更改。 继续？");

        put("en", "ApplicantInfoKeyingPage.JOptionPane.timeError", "The time selected is earlier the current time, please choose a valid time");
        put("cn", "ApplicantInfoKeyingPage.JOptionPane.timeError", "选的时间比现在的时间早， 请选个应用的时间");

        put("en", "ApplicantInfoKeyPage.JOptionPane.addConfirmInterview", "Confirm the selected time? An email will be sent out to the applicant and you will not be able to alter the timing");
        put("cn", "ApplicantInfoKeyPage.JOptionPane.addConfirmInterview", "确定选的时间？电子邮件之后会被发出而时间不能再改");

        put("en", "ApplicantInfoKeyingPage.JLabel.selectTime", "Select a date and time for the interview to take place");
        put("cn", "ApplicantInfoKeyingPage.JLabel.selectTime", "请给面试约个时间和日期");

        put("en", "ApplicantListPage.JTable.applicantInterviewTime", "Interview time:");
        put("cn", "ApplicantListPage.JTable.applicantInterviewTime", "面试时间：");

        put("en", "ApplicantListPage.JLabel.applicantList", "Applicant List");
        put("cn", "ApplicantListPage.JLabel.applicantList", "申请者表");


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
