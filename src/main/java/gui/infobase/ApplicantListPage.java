package gui.infobase;


import controller.DataIO;
import controller.InfobaseMainframe;
import data.Applicant;
import gui.ImageEmbedded;
import subsystems.ImageBase64;
import subsystems.JsonReaderWriter;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

//import controller.MainFrame;

public class ApplicantListPage extends JPanel{
    private final InfobaseMainframe main;
    private final TableRowSorter<TableModel> sorter;
    private final JTable table;
    private JCheckBox shortlistedCheckBox;
    private JCheckBox acceptedCheckBox;
    private JTextField filterField;
    private JCheckBox waitingShortlist;
    private JCheckBox programmingCheckBox;
    private JCheckBox industrialCheckBox;
    private JCheckBox artisticCheckBox;
    private JCheckBox communicationCheckBox;

    public ApplicantListPage(InfobaseMainframe mainframe) {
        this.main = mainframe;

        this.setLayout(new GridBagLayout());

        //CREATE INSTANCE OF TABLEMODEL HERE
        //DefaultTableModel model=new DefaultTableModel();



        JLabel staffListLabel = new JLabel("Applicant List");
        GridBagConstraints staffListConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        this.add(staffListLabel, staffListConstraints);

//FROM HERE
        TableModel applicantModel = new ApplicantTableModel(main);
        table = new JTable(applicantModel);
        sorter = new TableRowSorter<>(applicantModel);
        table.setRowSorter(sorter);
        table.setMinimumSize(new Dimension(700, 300));
        table.getTableHeader().setUI(null);
        TableColumn pictureColumn = table.getColumnModel().getColumn(0);
        TableColumn infoColumn = table.getColumnModel().getColumn(1);
        pictureColumn.setPreferredWidth(120);
        pictureColumn.setMaxWidth(120);

        JScrollPane tableScroll=new JScrollPane(table);
        tableScroll.setMinimumSize(new Dimension(700, 300));
        tableScroll.setPreferredSize(new Dimension(700, 300));
        tableScroll.setMaximumSize(new Dimension(getWidth(),(int)(Math.round(0.7*getHeight()))));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        table.setRowHeight(200);
        table.setMinimumSize(new Dimension(0, 100));
        ////////
        table.getColumnModel().getColumn(0).setCellRenderer(new TableImageRender());
        table.getColumnModel().getColumn(1).setCellRenderer(new TableCellRender(main));
        ////////
        table.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //UNTIL HERE

        GridBagConstraints scrollTableConstraints = new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        add(tableScroll, scrollTableConstraints);

        JPanel filterMenu = getFilterMenu();
        GridBagConstraints filterMenuConstraints = new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 100, 0, 0), 0, 0);
        filterMenu.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
        this.add(filterMenu, filterMenuConstraints);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());

        JButton addApplicantButton = new JButton(new ImageIcon(ImageBase64.base64ToImage(ImageEmbedded.ADD_APPLICANT_ICON)));
        GridBagConstraints addApplicantConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
        buttonPanel.add(addApplicantButton, addApplicantConstraints);
        addApplicantButton.addActionListener(e -> {
            JFileChooser j = getjFileChooser();
            j.setPreferredSize(new Dimension(640, 480));
            Action details = j.getActionMap().get("viewTypeDetails");
            details.actionPerformed(null);
            int r = j.showOpenDialog(null);
            File selectedFile;
            selectedFile = j.getSelectedFile().getAbsoluteFile();
            if (r == JFileChooser.APPROVE_OPTION) {

                //huge performance impact here, the file gets read twice
                new Thread(() -> {
                    Applicant newApplicant = JsonReaderWriter.jsonToModel(DataIO.readFile(selectedFile.getAbsolutePath()), Applicant.class);
                    main.getController().addApplicant(newApplicant);
                    updateModel();
                }).start();

            }

        });

        JButton editApplicantButton = new JButton(new ImageIcon(ImageBase64.base64ToImage(ImageEmbedded.EDIT_ICON)));
        GridBagConstraints editApplicantConstraints = new GridBagConstraints(1, 0, 1, 1, 0, 1,
                GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
        buttonPanel.add(editApplicantButton, editApplicantConstraints);

        GridBagConstraints buttonPanelConstraints = new GridBagConstraints(0, 2, 1, 1, 1, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);

        this.add(buttonPanel, buttonPanelConstraints);


        editApplicantButton.addActionListener(e -> {
            int selectedRow = table.convertRowIndexToModel(table.getSelectedRow());
            if (selectedRow == -1) {
                return;
            }
            main.showEditApplicant(main.getController().getApplicants()[selectedRow], selectedRow);

        });

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent arg0) {
                //scaling of title font
                float adjustedFontSizeBody= (float) main.getContentPane().getWidth() /50;

                staffListLabel.setFont(staffListLabel.getFont().deriveFont(adjustedFontSizeBody));
                editApplicantButton.setFont(editApplicantButton.getFont().deriveFont(adjustedFontSizeBody));
                System.out.println(table.getWidth()+"X"+ table.getHeight());
            }
        });
    }

    private JPanel getFilterMenu() {
        JPanel filterMenu = new JPanel(new GridBagLayout());
        {
            ((GridBagLayout)filterMenu.getLayout()).rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0,  1e-4};
            JLabel nameFilterLabel = new JLabel(main.getLocale("ApplicantListPage.JLabel.filterName"));
            nameFilterLabel.setFont(nameFilterLabel.getFont().deriveFont(18f));
            GridBagConstraints nameFilterConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 10, 0), 0, 0);
            filterMenu.add(nameFilterLabel, nameFilterConstraints);

            filterField = new JTextField();
            filterField.setColumns(20);
            filterField.setMinimumSize(new Dimension(200, 20));
            filterField.setFont(filterField.getFont().deriveFont(16f));
            GridBagConstraints filterFieldConstraints = new GridBagConstraints(0, 1, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(filterField, filterFieldConstraints);



            filterField.getDocument().addDocumentListener(new DocumentListener() {

                @Override
                public void insertUpdate(DocumentEvent e) {
                    removeUpdate(e);
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    changedUpdate(e);
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    updateModel();
                }

            });

            JLabel statusFilterLabel = new JLabel(main.getLocale("ApplicantListPage.JLabel.filterStatus"));
            statusFilterLabel.setFont(statusFilterLabel.getFont().deriveFont(18f));
            GridBagConstraints statusFilterConstarints = new GridBagConstraints(0, 2, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 10, 0), 0, 0);
            filterMenu.add(statusFilterLabel, statusFilterConstarints);

            waitingShortlist = new JCheckBox(main.getLocale("ApplicantListPage.JCheckBox.waitingShortlist"));
            waitingShortlist.setSelected(true);
            waitingShortlist.setFont(waitingShortlist.getFont().deriveFont(16f));
            GridBagConstraints waitingFilterConstraints = new GridBagConstraints(0, 3, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(waitingShortlist, waitingFilterConstraints);
            waitingShortlist.addActionListener(e -> updateModel());

            shortlistedCheckBox = new JCheckBox(main.getLocale("ApplicantListPage.JCheckBox.shortlisted"));
            shortlistedCheckBox.setSelected(true);
            shortlistedCheckBox.setFont(shortlistedCheckBox.getFont().deriveFont(16f));
            GridBagConstraints statusFilterConstraints = new GridBagConstraints(0, 4, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(shortlistedCheckBox, statusFilterConstraints);
            shortlistedCheckBox.addActionListener(e -> updateModel());


            acceptedCheckBox = new JCheckBox(main.getLocale("ApplicantListPage.JCheckBox.accepted"));
            acceptedCheckBox.setSelected(true);
            acceptedCheckBox.setFont(acceptedCheckBox.getFont().deriveFont(16f));
            GridBagConstraints acceptedFilterConstraints = new GridBagConstraints(0, 5, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(acceptedCheckBox, acceptedFilterConstraints);
            acceptedCheckBox.addActionListener(e -> updateModel());

            programmingCheckBox = new JCheckBox(main.getLocale("ShortlistPage.JCheckBox.programming"));
            programmingCheckBox.setFont(programmingCheckBox.getFont().deriveFont(18f));
            GridBagConstraints programmingConstraints = new GridBagConstraints(0, 6, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(programmingCheckBox, programmingConstraints);
            programmingCheckBox.addActionListener(e -> {
                updateModel();
            });

            industrialCheckBox = new JCheckBox(main.getLocale("ShortlistPage.JCheckBox.industrial"));
            industrialCheckBox.setFont(industrialCheckBox.getFont().deriveFont(18f));
            GridBagConstraints industrialConstraints = new GridBagConstraints(0, 7, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(industrialCheckBox, industrialConstraints);
            industrialCheckBox.addActionListener(e -> {
                updateModel();
            });

            artisticCheckBox = new JCheckBox(main.getLocale("ShortlistPage.JCheckBox.artistic"));
            artisticCheckBox.setFont(artisticCheckBox.getFont().deriveFont(18f));
            GridBagConstraints artisticConstraints = new GridBagConstraints(0, 8, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(artisticCheckBox, artisticConstraints);
            artisticCheckBox.addActionListener(e -> {
                updateModel();
            });

            communicationCheckBox = new JCheckBox(main.getLocale("ShortlistPage.JCheckBox.communication"));
            communicationCheckBox.setFont(communicationCheckBox.getFont().deriveFont(18f));
            GridBagConstraints communicationConstraints = new GridBagConstraints(0, 9, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(communicationCheckBox, communicationConstraints);
            communicationCheckBox.addActionListener(e -> {
                updateModel();
            });

        }
        updateModel();
        return filterMenu;
    }
    private void updateModel(){
        List<Integer> shortlistStages = new ArrayList<>();
        if (waitingShortlist.isSelected()) {
            shortlistStages.add(ApplicantRowFilter.STAGE_AWAITING_SHORTLIST);
        }
        if (shortlistedCheckBox.isSelected()) {
            shortlistStages.add(ApplicantRowFilter.STAGE_AWAITING_ACCEPTANCE);
        }
        if (acceptedCheckBox.isSelected()) {
            shortlistStages.add(ApplicantRowFilter.STAGE_ACCEPTED);
        }

        List<String> skillFilters = new ArrayList<>();
        if (programmingCheckBox.isSelected()){
            skillFilters.add("Programming");
        }
        if (industrialCheckBox.isSelected()){
            skillFilters.add("Industrial");
        }
        if (artisticCheckBox.isSelected()){
            skillFilters.add("Artistic");
        }
        if (communicationCheckBox.isSelected()){
            skillFilters.add("Communication");
        }

        sorter.setRowFilter(new ApplicantRowFilter(
                shortlistStages.stream().mapToInt(i->i).toArray(),
                filterField.getText(),
                skillFilters.toArray(new String[0])));
        table.setRowSorter(sorter);
    }

    private static JFileChooser getjFileChooser() {
        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        j.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()){
                    return true;
                }
                else {
                    String filename = f.getName().toLowerCase();
                    return filename.endsWith(".json");
                }
            }

            @Override
            public String getDescription() {
                return "JSON Files (*.json)";
            }
        });
        j.setFileSelectionMode(JFileChooser.FILES_ONLY);
        return j;
    }
}

