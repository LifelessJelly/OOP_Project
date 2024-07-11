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
        translationTable.put(new TranslationKey("en", "EditApplicant.JButton.removeImage"), "Remove image");
        translationTable.put(new TranslationKey("cn", "EditApplicant.JButton.removeImage"), "除去图片");

        translationTable.put(new TranslationKey("en", "EditApplicant.JLabel.applicant_name"), "Applicant name: ");
        translationTable.put(new TranslationKey("cn", "EditApplicant.JLabel.applicant_name"), "申请者姓名: ");

        translationTable.put(new TranslationKey("en", "EditApplicant.JLabel.birth_date"), "Date of birth: ");
        translationTable.put(new TranslationKey("cn", "EditApplicant.JLabel.birth_date"), "生日: ");

        translationTable.put(new TranslationKey("en", "EditApplicant.JLabel.nric"), "NRIC: ");
        translationTable.put(new TranslationKey("cn", "EditApplicant.JLabel.nric"), "身份证号码：");

        translationTable.put(new TranslationKey("en", "EditApplicant.JLabel.email"), "Email: ");
        translationTable.put(new TranslationKey("cn", "EditApplicant.JLabel.email"), "电子邮件地址");

        translationTable.put(new TranslationKey("en", "EditApplicant.JLabel.gender"), "Gender: ");
        translationTable.put(new TranslationKey("cn", "EditApplicant.JLabel.gender"), "性别： ");

        translationTable.put(new TranslationKey("en", "EditApplicant.JLabel.skills"), "Skills: ");
        translationTable.put(new TranslationKey("cn", "EditApplicant.JLabel.skills"), "某些技能：");

        translationTable.put(new TranslationKey("en", "EditApplicant.JButton.add"), "Add");
        translationTable.put(new TranslationKey("cn", "EditApplicant.JButton.add"), "加入");

        translationTable.put(new TranslationKey("en", "EditApplicant.JButton.remove"), "Remove");
        translationTable.put(new TranslationKey("cn", "EditApplicant.JButton.remove"), "除去");

        translationTable.put(new TranslationKey("en", "EditApplicant.JButton.edit"), "Edit");
        translationTable.put(new TranslationKey("cn", "EditApplicant.JButton.edit"), "更改");

        translationTable.put(new TranslationKey("en", "EditApplicant.JLabel.experiences"), "Experiences: ");
        translationTable.put(new TranslationKey("cn", "EditApplicant.JLabel.experiences"), "经验");

        translationTable.put(new TranslationKey("en", "EditApplicant.JLabel.company"), "Company: ");
        translationTable.put(new TranslationKey("cn", "EditApplicant.JLabel.company"), "公司： ");

        translationTable.put(new TranslationKey("en", "EditApplicant.JLabel.position"), "Position: ");
        translationTable.put(new TranslationKey("cn", "EditApplicant.JLabel.position"), "位置： ");

        translationTable.put(new TranslationKey("en", "EditApplicant.JButton.save_changes"), "Save Changes");
        translationTable.put(new TranslationKey("cn", "EditApplicant.JButton.save_changes"), "保存");

        translationTable.put(new TranslationKey("en", "EditApplicant.JLabel.discard_changes"), "Discard Changes");
        translationTable.put(new TranslationKey("cn", "EditApplicant.JLabel.discard_changes"), "摒弃");

    }

    /**
     * Returns the instance of the TranslationTable class.
     *
     *
     * @return the instance of the TranslationTable class
     */
    public static synchronized TranslationTable getInstance() {
        if (instance == null) {
            instance = new TranslationTable();
        }
        return instance;
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
