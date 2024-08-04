package gui.infobase;

import controller.InfobaseMainframe;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Settings extends JPanel{
    InfobaseMainframe main;
    private JLabel languageLabel;
    private JComboBox languageComboBox;
    private JLabel themeLabel;
    private JComboBox themeComboBox;
    private JButton apply;
    private JButton discard;



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
        GridBagConstraints gbc_languageLabel  = new GridBagConstraints();
        gbc_languageLabel .anchor = GridBagConstraints.NORTHWEST;
        gbc_languageLabel .insets = new Insets(0, 0, 5, 5);
        gbc_languageLabel .gridx = 0;
        gbc_languageLabel .gridy = 0;
        add(languageLabel , gbc_languageLabel );

        languageComboBox = new JComboBox();
        languageComboBox.addItem("===Select Language===");
        languageComboBox.addItem("en");
        languageComboBox.addItem("cn");
        GridBagConstraints gbc_languageComboBox = new GridBagConstraints();
        gbc_languageComboBox.anchor = GridBagConstraints.NORTHWEST;
        gbc_languageComboBox.insets = new Insets(0, 0, 5, 5);
        gbc_languageComboBox.gridx = 1;
        gbc_languageComboBox.gridy = 0;
        add(languageComboBox, gbc_languageComboBox);

        themeLabel = new JLabel("Theme");
        GridBagConstraints gbc_themeLabel = new GridBagConstraints();
        gbc_themeLabel.anchor = GridBagConstraints.NORTHWEST;
        gbc_themeLabel.insets = new Insets(0, 0, 5, 5);
        gbc_themeLabel.gridx = 0;
        gbc_themeLabel.gridy = 1;
        add(themeLabel, gbc_themeLabel);

        themeComboBox = new JComboBox();
        themeComboBox.addItem("====Select Theme====");
        themeComboBox.addItem("Borealis");
        themeComboBox.addItem("Candle");
        themeComboBox.addItem("Nostalgia");
        themeComboBox.addItem("Night");
        themeComboBox.addItem("Mint");
        themeComboBox.addItem("Tide");
        themeComboBox.addItem("Unicorn");
        themeComboBox.addItem("Ink");

        //TODO maybe add? delete if not adding

        themeComboBox.addItem("Legoshi :3");
        GridBagConstraints gbc_themeComboBox = new GridBagConstraints();
        gbc_themeComboBox.anchor = GridBagConstraints.NORTHWEST;
        gbc_themeComboBox.insets = new Insets(0, 0, 5, 5);
        gbc_themeComboBox.gridx = 1;
        gbc_themeComboBox.gridy = 1;
        add(themeComboBox, gbc_themeComboBox);

        apply = new JButton("Apply Changes");
        apply.setEnabled(false);
        GridBagConstraints gbc_apply = new GridBagConstraints();
        gbc_apply.gridwidth = 2;
        gbc_apply.insets = new Insets(0, 0, 5, 5);
        gbc_apply.gridx = 0;
        gbc_apply.gridy = 2;
        add(apply, gbc_apply);

        discard = new JButton("Discard Changes");
        GridBagConstraints gbc_discard = new GridBagConstraints();
        gbc_discard.gridwidth = 2;
        gbc_discard.insets = new Insets(0, 0, 0, 5);
        gbc_discard.gridx = 0;
        gbc_discard.gridy = 3;
        add(discard, gbc_discard);
    }

    private void initListeners(){
        themeComboBox.addActionListener(e->{
            if((themeComboBox.getSelectedIndex()!=0)){apply.setEnabled(true);}
        });

        apply.addActionListener(e->{
            if(languageComboBox.getSelectedIndex()==0){
                main.setLanguage(main.getLanguage());
            } else {
                main.setLanguage(languageComboBox.getSelectedItem().toString());
            }
            main.setTheme(themeComboBox.getSelectedIndex());
            main.reload();
        });

        discard.addActionListener(e->{
            if(JOptionPane.showConfirmDialog(null, main.getLocale("EditApplicant.JOptionPane.discardConfirm"), "", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION){
                main.reload();
            }
        });
    }

}
