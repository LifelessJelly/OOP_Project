package GUI.Registration;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.pow;
import static java.lang.Math.round;

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
    private int loopCycles;
    private GridBagConstraints enterSKillsConstraints;
    private GridBagConstraints skillsSubsConstraints;
    private GridBagConstraints skillsScrollConstraints;
    private GridBagConstraints skillsFieldConstraints;
    private GridBagConstraints addRemovePanelConstraints;
    private GridBagConstraints buttonsPanelConstraints;
    private JPanel addRemovePanel;


    public SkillsInfo(Mainframe mainframe){
        this.mainframe = mainframe;
        initComponents();
    }

    private void initComponents() {

        movingInsets = new Insets(0, 0, 5, 0);
        layout = new GridBagLayout();

        enterSKillsConstraints = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        skillsSubsConstraints = new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        skillsScrollConstraints = new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        skillsFieldConstraints = new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        buttonsPanelConstraints = new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        addRemovePanelConstraints = new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
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

        addRemovePanel = new JPanel();
        addRemovePanel.setLayout(new GridBagLayout());
        JButton addButton = new JButton("Add");
        addRemovePanel.add(addButton);

        JButton removeButton = new JButton("Remove");
        addRemovePanel.add(removeButton);
        this.add(addRemovePanel, addRemovePanelConstraints);

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



    @Override
    public void slideInLeft(){
        movingInsets.right = getStartPos();
        this.setVisible(true);
        updateAnimation();
        loopCycles = 0;
        new Timer(10, e -> {
            if (loopCycles < 40){
                movingInsets.right = (int) round(pow(movingInsets.right, (1/1.05))-1.5);
                if (movingInsets.right < 0){
                    movingInsets.right = 0;
                }
                updateAnimation();
                ++loopCycles;
            }
            else {
                ((Timer)e.getSource()).stop();
            }
        }).start();
    }

    @Override
    public void slideInRight() {

    }

    @Override
    public void slideOutLeft() {

    }

    @Override
    public void slideOutRight() {

    }

    private void updateAnimation(){
        layout.setConstraints(enterSkillsLabel, enterSKillsConstraints);
        layout.setConstraints(skillsSubLabel, skillsSubsConstraints);
        layout.setConstraints(skillsScrollPane, skillsScrollConstraints);
        layout.setConstraints(skillsField, skillsFieldConstraints);
        layout.setConstraints(addRemovePanel, addRemovePanelConstraints);
        layout.setConstraints(buttonsPanel, buttonsPanelConstraints);
        this.revalidate();
        this.repaint();
    }

    private int getStartPos(){
        int startPos = 0;
        for (int i = 0; i < 40; ++i ){
            startPos = (int) pow(startPos + 1.5, 1.05);
        }
        return startPos;
    }
}
