package GUI.Registration;

import javax.swing.*;
import java.awt.*;

public class SkillsInfo extends SlidingPanel{
    Mainframe mainframe;
    JLabel enterSkillsLabel;
    JLabel skillsSubLabel;
    JScrollPane skillsScrollPane;
    JList<String> skillsList;
    JTextField skillsField;
    JPanel buttonsPanel;
    GridBagLayout layout;
    Insets movingInsets;



    public SkillsInfo(Mainframe mainframe){
        this.mainframe = mainframe;
        initComponents();
    }

    private void initComponents() {

        movingInsets = new Insets(0, 0, 5, 0);
        layout = new GridBagLayout();

        GridBagConstraints enterSKillsConstraints = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        GridBagConstraints skillsSubsConstraints = new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        GridBagConstraints skillsScrollConstraints = new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        GridBagConstraints skillsFieldConstraints = new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        GridBagConstraints buttonsPanelConstraints = new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);


        this.setLayout(layout);

        enterSkillsLabel = new JLabel("Tell us about your skills");
        enterSkillsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        enterSkillsLabel.setFont(enterSkillsLabel.getFont().deriveFont(22f));
        this.add(enterSkillsLabel, enterSKillsConstraints);

        skillsSubLabel = new JLabel("Please separately type in each of your skills");
        skillsSubLabel.setHorizontalAlignment(SwingConstants.LEFT);
        skillsSubLabel.setFont(skillsSubLabel.getFont().deriveFont(18f));
        this.add(skillsSubLabel, skillsSubsConstraints);

        skillsScrollPane = new JScrollPane();
        skillsList = new JList<>();
        skillsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        skillsList.setFont(skillsList.getFont().deriveFont(18f));
        skillsScrollPane.setViewportView(skillsList);
        this.add(skillsScrollPane, skillsScrollConstraints);

        skillsField = new JTextField();
        skillsField.setFont(skillsField.getFont().deriveFont(18f));
        skillsField.setEditable(true);
        this.add(skillsField, skillsFieldConstraints);

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());
        JButton backButton = new JButton("Back");
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        backButton.setFont(backButton.getFont().deriveFont(18f));
        GridBagConstraints backButtonConstraints = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        buttonsPanel.add(backButton, backButtonConstraints);
        JPanel blankSpacerPanel = new JPanel();
        blankSpacerPanel.setMinimumSize(new Dimension(640, 0));
        blankSpacerPanel.setPreferredSize(new Dimension(640, 0));
        GridBagConstraints blankSpacerConstraints = new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        buttonsPanel.add(blankSpacerPanel, blankSpacerConstraints);
        JButton nextButton = new JButton("Next");
        nextButton.setHorizontalAlignment(SwingConstants.CENTER);
        nextButton.setFont(backButton.getFont().deriveFont(18f));
        GridBagConstraints nextButtonConstraints = new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        buttonsPanel.add(nextButton, nextButtonConstraints);
        this.add(buttonsPanel, buttonsPanelConstraints);
    }
}
