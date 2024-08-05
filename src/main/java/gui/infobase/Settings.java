package gui.infobase;

import controller.InfobaseMainframe;
import sun.management.GarbageCollectionNotifInfoCompositeData;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Settings extends JPanel{
    final InfobaseMainframe main;
    private JLabel languageLabel;
    private JComboBox<String> languageComboBox;
    private JLabel themeLabel;
    private JComboBox<String> themeComboBox;
    private JButton apply;


    //TODO: LOOK BELOW there is a todo if uw do :))



    public Settings(InfobaseMainframe main) {
        this.main=main;
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0,0,0};
        //gridBagLayout.rowWeights = new double[]{1,1,1,1};
        setLayout(gridBagLayout);
        initComponents();
        initListeners();
    }

    private void initComponents(){
        languageLabel = new JLabel("Language");
        languageLabel.setFont(languageLabel.getFont().deriveFont(24f));
        GridBagConstraints gbc_languageLabel  = new GridBagConstraints(0, 0, 1, 1, 1, 0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 50, 5, 0), 0, 0);
        this.add(languageLabel , gbc_languageLabel );

        languageComboBox = new JComboBox<>(new String[]{"English", "Chinese"});
        languageComboBox.setMaximumSize(new Dimension(300, 50));
        languageComboBox.setPreferredSize(new Dimension(300, 50));
        String language = main.getLanguage();
        switch (language){
            case "en":
                languageComboBox.setSelectedIndex(0);
                break;
            case "cn":
                languageComboBox.setSelectedIndex(1);
                break;
        }
        languageComboBox.setFont(languageComboBox.getFont().deriveFont(18f));
        GridBagConstraints gbc_languageComboBox = new GridBagConstraints(0, 1, 1, 1, 1, 0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 50, 20, 0), 0, 0);
        this.add(languageComboBox, gbc_languageComboBox);

        themeLabel = new JLabel("App Theme");
        themeLabel.setFont(themeLabel.getFont().deriveFont(24f));
        GridBagConstraints gbc_themeLabel = new GridBagConstraints(0, 2, 1, 1, 1, 0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 50, 5, 0), 0, 0);
        this.add(themeLabel , gbc_themeLabel );

        themeComboBox = new JComboBox<>(new String[]{"Borealis", "Candle", "Nostalgia", "Night", "Mint", "Tide", "Unicorn", "Ink"});
        themeComboBox.setMaximumSize(new Dimension(300, 50));
        themeComboBox.setPreferredSize(new Dimension(300, 50));
        int theme = main.getCurrentTheme();
        themeComboBox.setSelectedIndex(theme-1);
        themeComboBox.setFont(themeComboBox.getFont().deriveFont(18f));
        GridBagConstraints gbc_themeComboBox = new GridBagConstraints(0, 3, 1, 1, 1, 0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 50, 20, 0), 0, 0);
        this.add(themeComboBox, gbc_themeComboBox);

        JPanel buttonContainer = new JPanel();
        GridBagConstraints gbc_buttonContainer = new GridBagConstraints(0, 4, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 50, 5, 0), 0, 0);

        apply = new JButton("Apply Changes");
        apply.setFont(apply.getFont().deriveFont(18f));
        GridBagConstraints gbc_apply = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 5, 0), 0, 0);
        buttonContainer.add(apply, gbc_apply);

        this.add(buttonContainer, gbc_buttonContainer);

    }

    private void initListeners(){

        apply.addActionListener(e->{
            String language = (String) languageComboBox.getSelectedItem();
            switch (Objects.requireNonNull(language)){
                case "English":
                    main.setLanguage("en");
                    break;
                case "Chinese":
                    main.setLanguage("cn");
                    break;
            }
            main.changeTheme(themeComboBox.getSelectedIndex()+1);
            main.reload();
        });

    }

}
