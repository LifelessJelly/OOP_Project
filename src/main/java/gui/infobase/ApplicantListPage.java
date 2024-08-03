package gui.infobase;


import controller.DataIO;
import controller.InfobaseMainframe;
import controller.PDFBase64;
import data.Applicant;
import data.Staff;
import gui.ImageEmbedded;
import subsystems.ImageBase64;
import subsystems.JsonReaderWriter;

import javax.imageio.ImageIO;
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
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;


public class ApplicantListPage extends JPanel{
    private final InfobaseMainframe main;
    private TableRowSorter<TableModel> sorter;
    private JTable table;
    private JCheckBox shortlistedCheckBox;
    private JCheckBox acceptedCheckBox;
    private JTextField filterField;
    private JCheckBox waitingShortlistCheckBox;
    private JCheckBox programmingCheckBox;
    private JCheckBox industrialCheckBox;
    private JCheckBox artisticCheckBox;
    private JCheckBox communicationCheckBox;
    private JButton addApplicantButton;
    private JButton editApplicantButton;
    private JButton removeApplicantButton;
    private double zoomFactor;
    private float alpha;
    private JButton setInterviewDateButton;
    private JButton shortlistApplicantButton;
    private JButton setJobRoleButton;
    private JCheckBox acceptedWaitingManagerCheckBox;
    private JCheckBox shortlistedWaitingHRCheckBox;
    private JButton acceptApplicantButton;
    private JButton addApplicantTextButton;
    public ApplicantListPage(InfobaseMainframe mainframe) {
        this.main = mainframe;
        this.setLayout(new GridBagLayout());
        this.zoomFactor = 1;
        this.alpha = 1;

        initComponents();
        initListeners();
    }

    private void initComponents() {
        JLabel applicantListLabel = new JLabel("Applicant List");

        applicantListLabel.setFont(applicantListLabel.getFont().deriveFont(22f));
        applicantListLabel.setFont(applicantListLabel.getFont().deriveFont(Font.BOLD));

        GridBagConstraints staffListConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 60, 20, 20), 0, 0);
        this.add(applicantListLabel, staffListConstraints);


        TableModel applicantModel = new ApplicantTableModel(main);
        table = new JTable(applicantModel);
        sorter = new TableRowSorter<>(applicantModel);
        table.setRowSorter(sorter);
        table.setMinimumSize(new Dimension(700, 300));
        table.getTableHeader().setUI(null);
        TableColumn pictureColumn = table.getColumnModel().getColumn(0);
        pictureColumn.setPreferredWidth(120);
        pictureColumn.setMaxWidth(120);

        JScrollPane tableScroll=new JScrollPane(table);
        tableScroll.setBackground(new Color(47, 47, 47));
        tableScroll.setMinimumSize(new Dimension(700, 300));
        tableScroll.setPreferredSize(new Dimension(700, 300));
        tableScroll.setMaximumSize(new Dimension(getWidth(),(int)(Math.round(0.7*getHeight()))));
        tableScroll.getVerticalScrollBar().setBorder(BorderFactory.createEmptyBorder());
        tableScroll.setBorder(BorderFactory.createEmptyBorder());
        tableScroll.setViewportBorder(BorderFactory.createEmptyBorder());
        table.setBorder(BorderFactory.createEmptyBorder());
        table.getTableHeader().setBorder(BorderFactory.createEmptyBorder());
        table.setRowHeight(275);
        table.setMinimumSize(new Dimension(0, 100));

        table.getColumnModel().getColumn(0).setCellRenderer(new TableImageRender());
        table.getColumnModel().getColumn(1).setCellRenderer(new TableCellRender(main));

        table.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        GridBagConstraints scrollTableConstraints = new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 40, 0, 0), 0, 0);
        this.add(tableScroll, scrollTableConstraints);

        JPanel filterMenu = new JPanel(new GridBagLayout());
        {
            ((GridBagLayout)filterMenu.getLayout()).rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1e-4};
            ((GridBagLayout)filterMenu.getLayout()).columnWeights = new double[]{1e-4};
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

            JLabel statusFilterLabel = new JLabel(main.getLocale("ApplicantListPage.JLabel.filterStatus"));
            statusFilterLabel.setFont(statusFilterLabel.getFont().deriveFont(18f));
            GridBagConstraints statusFilterConstarints = new GridBagConstraints(0, 2, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 10, 0), 0, 0);
            filterMenu.add(statusFilterLabel, statusFilterConstarints);

            waitingShortlistCheckBox = new JCheckBox(main.getLocale("ApplicantListPage.JCheckBox.waitingShortlist"));
            waitingShortlistCheckBox.setSelected(true);
            waitingShortlistCheckBox.setFont(waitingShortlistCheckBox.getFont().deriveFont(16f));
            GridBagConstraints waitingFilterConstraints = new GridBagConstraints(0, 3, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(waitingShortlistCheckBox, waitingFilterConstraints);

            shortlistedWaitingHRCheckBox = new JCheckBox(main.getLocale("ApplicantListPage.JCheckBox.shortlistedWaitingHR"));
            shortlistedWaitingHRCheckBox.setSelected(true);
            shortlistedWaitingHRCheckBox.setFont(shortlistedWaitingHRCheckBox.getFont().deriveFont(16f));
            GridBagConstraints shortlistedWaitingFilterConstraints = new GridBagConstraints(0, 4, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(shortlistedWaitingHRCheckBox, shortlistedWaitingFilterConstraints);

            shortlistedCheckBox = new JCheckBox(main.getLocale("ApplicantListPage.JCheckBox.shortlisted"));
            shortlistedCheckBox.setSelected(true);
            shortlistedCheckBox.setFont(shortlistedCheckBox.getFont().deriveFont(16f));
            GridBagConstraints shortlistedFilterConstraints = new GridBagConstraints(0, 5, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(shortlistedCheckBox, shortlistedFilterConstraints);

            acceptedWaitingManagerCheckBox = new JCheckBox(main.getLocale("ApplicantListPage.JCheckBox.acceptedWaitingManager"));
            acceptedWaitingManagerCheckBox.setSelected(true);
            acceptedWaitingManagerCheckBox.setFont(acceptedWaitingManagerCheckBox.getFont().deriveFont(16f));
            GridBagConstraints acceptedWaitingFilterConstraints = new GridBagConstraints(0, 6, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(acceptedWaitingManagerCheckBox, acceptedWaitingFilterConstraints);

            acceptedCheckBox = new JCheckBox(main.getLocale("ApplicantListPage.JCheckBox.accepted"));
            acceptedCheckBox.setSelected(true);
            acceptedCheckBox.setFont(acceptedCheckBox.getFont().deriveFont(16f));
            GridBagConstraints acceptedFilterConstraints = new GridBagConstraints(0, 7, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 7, 0, 0), 0, 0);
            filterMenu.add(acceptedCheckBox, acceptedFilterConstraints);

            JLabel excludeSkillLabel = new JLabel(main.getLocale("ApplicantListPage.JLabel.excludeSkill"));
            excludeSkillLabel.setFont(excludeSkillLabel.getFont().deriveFont(18f));
            excludeSkillLabel.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints excludeSkillConstraints = new GridBagConstraints(0, 8, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(25, 5, 0, 0), 0, 0);
            filterMenu.add(excludeSkillLabel, excludeSkillConstraints);

            programmingCheckBox = new JCheckBox(main.getLocale("ShortlistPage.JCheckBox.programming"));
            programmingCheckBox.setFont(programmingCheckBox.getFont().deriveFont(18f));
            GridBagConstraints programmingConstraints = new GridBagConstraints(0, 9, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(programmingCheckBox, programmingConstraints);

            industrialCheckBox = new JCheckBox(main.getLocale("ShortlistPage.JCheckBox.industrial"));
            industrialCheckBox.setFont(industrialCheckBox.getFont().deriveFont(18f));
            GridBagConstraints industrialConstraints = new GridBagConstraints(0, 10, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(industrialCheckBox, industrialConstraints);

            artisticCheckBox = new JCheckBox(main.getLocale("ShortlistPage.JCheckBox.artistic"));
            artisticCheckBox.setFont(artisticCheckBox.getFont().deriveFont(18f));
            GridBagConstraints artisticConstraints = new GridBagConstraints(0, 11, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(artisticCheckBox, artisticConstraints);

            communicationCheckBox = new JCheckBox(main.getLocale("ShortlistPage.JCheckBox.communication"));
            communicationCheckBox.setFont(communicationCheckBox.getFont().deriveFont(18f));
            GridBagConstraints communicationConstraints = new GridBagConstraints(0, 12, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(communicationCheckBox, communicationConstraints);
        }
        updateModel();
        GridBagConstraints filterMenuConstraints = new GridBagConstraints(2, 1, 1, 1, 1, 1,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 200, 0, 0), 0, 0);
        filterMenu.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
        this.add(filterMenu, filterMenuConstraints);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());

        //HR Staff button set
        if (main.getController().getUser().authorised(Staff.HR)){
            addApplicantTextButton = new JButton(new ImageIcon(ImageBase64.base64ToImage(ImageEmbedded.ADD_APPLICANT_AS_JSON)));
            addApplicantTextButton.setBorder(BorderFactory.createEmptyBorder());
            addApplicantTextButton.setToolTipText("Add Applicant");
            GridBagConstraints addApplicantTextConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
            buttonPanel.add(addApplicantTextButton, addApplicantTextConstraints);

            addApplicantButton = new JButton(new ImageIcon(ImageBase64.base64ToImage(ImageEmbedded.ADD_APPLICANT_ICON)));
            addApplicantButton.setBorder(BorderFactory.createEmptyBorder());
            addApplicantButton.setToolTipText("Add Applicant");
            GridBagConstraints addApplicantConstraints = new GridBagConstraints(0, 1, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
            buttonPanel.add(addApplicantButton, addApplicantConstraints);

            editApplicantButton = new JButton(new ImageIcon(ImageBase64.base64ToImage(ImageEmbedded.EDIT_ICON)));
            editApplicantButton.setBorder(BorderFactory.createEmptyBorder());
            editApplicantButton.setEnabled(false);
            GridBagConstraints editApplicantConstraints = new GridBagConstraints(0, 2, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
            buttonPanel.add(editApplicantButton, editApplicantConstraints);

            removeApplicantButton=new JButton(new ImageIcon((ImageBase64.base64ToImage(ImageEmbedded.REMOVE_ICON))));
            removeApplicantButton.setBorder(BorderFactory.createEmptyBorder());
            removeApplicantButton.setEnabled(false);
            GridBagConstraints removeApplicantConstraints = new GridBagConstraints(0, 3, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
            buttonPanel.add(removeApplicantButton, removeApplicantConstraints);

            setInterviewDateButton = new JButton(new ImageIcon(ImageBase64.base64ToImage(ImageEmbedded.SET_INTERVIEW_ICON)));
            setInterviewDateButton.setBorder(BorderFactory.createEmptyBorder());
            setInterviewDateButton.setEnabled(false);
            GridBagConstraints setInterviewDateConstraints = new GridBagConstraints(0, 4, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
            buttonPanel.add(setInterviewDateButton, setInterviewDateConstraints);

            acceptApplicantButton = new JButton(new ImageIcon(ImageBase64.base64ToImage(ImageEmbedded.ACCEPT_ICON)));
            acceptApplicantButton.setBorder(BorderFactory.createEmptyBorder());
            acceptApplicantButton.setToolTipText("Accept Applicant");
            acceptApplicantButton.setEnabled(false);
            GridBagConstraints acceptApplicantConstraints = new GridBagConstraints(0, 5, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
            buttonPanel.add(acceptApplicantButton, acceptApplicantConstraints);

        }
        if (main.getController().getUser().authorised(Staff.MANAGER)){

            shortlistApplicantButton = new JButton(new ImageIcon(ImageBase64.base64ToImage(ImageEmbedded.ADD_TO_SHORTLIST_ICON)));
            shortlistApplicantButton.setBorder(BorderFactory.createEmptyBorder());
            shortlistApplicantButton.setEnabled(false);
            GridBagConstraints shortlistApplicantConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
            buttonPanel.add(shortlistApplicantButton, shortlistApplicantConstraints);

            acceptApplicantButton = new JButton(new ImageIcon(ImageBase64.base64ToImage(ImageEmbedded.ACCEPT_ICON)));
            acceptApplicantButton.setBorder(BorderFactory.createEmptyBorder());
            acceptApplicantButton.setToolTipText("Accept Applicant");
            acceptApplicantButton.setEnabled(false);
            GridBagConstraints acceptApplicantConstraints = new GridBagConstraints(0, 1, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
            buttonPanel.add(acceptApplicantButton, acceptApplicantConstraints);

            setJobRoleButton = new JButton(new ImageIcon(ImageBase64.base64ToImage(ImageEmbedded.SET_JOB_ROLE_ICON)));
            setJobRoleButton.setBorder(BorderFactory.createEmptyBorder());
            setJobRoleButton.setToolTipText("Accept Applicant");
            setJobRoleButton.setEnabled(false);
            GridBagConstraints setJobRoleConstraints = new GridBagConstraints(0, 2, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
            buttonPanel.add(setJobRoleButton, setJobRoleConstraints);
        }

        GridBagConstraints buttonPanelConstraints = new GridBagConstraints(1, 1, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);

        this.add(buttonPanel, buttonPanelConstraints);
    }

    private void initListeners() {


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

        waitingShortlistCheckBox.addActionListener(e -> updateModel());

        shortlistedCheckBox.addActionListener(e -> updateModel());

        acceptedCheckBox.addActionListener(e -> updateModel());

        programmingCheckBox.addActionListener(e -> updateModel());

        industrialCheckBox.addActionListener(e -> updateModel());

        artisticCheckBox.addActionListener(e -> updateModel());

        communicationCheckBox.addActionListener(e -> updateModel());

        //HR Staff button set
        if (main.getController().getUser().authorised(Staff.HR)) {
            addApplicantButton.addActionListener(e -> {
                main.showAddApplicant();
            });

            addApplicantTextButton.addActionListener(e-> {
                JFileChooser chooser=getjFileChooser();
                Action details = chooser.getActionMap().get("viewTypeDetails");
                details.actionPerformed(null);
                //detailed view of files
                int r = chooser.showOpenDialog(null);
                File selectedFile;
                selectedFile = chooser.getSelectedFile().getAbsoluteFile();
                String selectedJson=DataIO.readFile(selectedFile.getAbsolutePath());
                Applicant applicantToBeWritten=JsonReaderWriter.jsonToModel(selectedJson,Applicant.class);
                if (selectedFile.length() > 16777216) {
                    JOptionPane.showMessageDialog(null, "File size exceeds 16MiB", "Text file too large", JOptionPane.ERROR_MESSAGE);
                }

                //file processing
                if (r == JFileChooser.APPROVE_OPTION) {
                   main.getController().addApplicant(applicantToBeWritten);
                   main.getContentPane().validate();
                   main.getContentPane().repaint();
                }
            });
            editApplicantButton.addActionListener(e -> {
                int selectedRow = table.convertRowIndexToModel(table.getSelectedRow());
                if (selectedRow == -1) {
                    return;
                }
                main.showEditApplicant( selectedRow);
            });

            removeApplicantButton.addActionListener(e -> {
                int selectedRow = table.convertRowIndexToModel(table.getSelectedRow());
                main.getController().removeApplicant(selectedRow);
                main.getContentPane().validate();
                main.getContentPane().repaint();
            });

            setInterviewDateButton.addActionListener(e -> {
                int selectedRow = table.convertRowIndexToModel(table.getSelectedRow());
                if (selectedRow == -1) {
                    return;
                }
                if (main.getController().getApplicantAt(selectedRow).getStatus() == Applicant.SHORTLISTED_PENDING_DATE){
                    main.showApplicantKeyPage(Applicant.SHORTLISTED_PENDING_DATE, selectedRow);
                }
            });

            acceptApplicantButton.addActionListener(e -> {
                int selectedRow = table.convertRowIndexToModel(table.getSelectedRow());
                main.getController().acceptApplicant(selectedRow);
                updateModel();
                acceptApplicantButton.setEnabled(false);
            });

            table.getSelectionModel().addListSelectionListener(e -> {
                if (table.getSelectedRow() != -1) {
                    int status = main.getController().getApplicantAt(table.convertRowIndexToModel(table.getSelectedRow())).getStatus();
                    editApplicantButton.setEnabled(true);
                    setInterviewDateButton.setEnabled(status == Applicant.SHORTLISTED_PENDING_DATE);
                    acceptApplicantButton.setEnabled(status == Applicant.SHORTLISTED_TO_INTERVIEW);
                    removeApplicantButton.setEnabled(true);
                }
                else {
                    editApplicantButton.setEnabled(false);
                    setInterviewDateButton.setEnabled(false);
                    acceptApplicantButton.setEnabled(false);
                    removeApplicantButton.setEnabled(false);
                }
            });
        }
        if (main.getController().getUser().authorised(Staff.MANAGER)) {
            shortlistApplicantButton.addActionListener(e -> {
                int selectedRow = table.convertRowIndexToModel(table.getSelectedRow());
                main.getController().shortlistApplicantForInterview(selectedRow);
                updateModel();
                shortlistApplicantButton.setEnabled(false);
            });
            acceptApplicantButton.addActionListener(e -> {
                int selectedRow = table.convertRowIndexToModel(table.getSelectedRow());
                main.getController().acceptApplicant(selectedRow);
                updateModel();
                acceptApplicantButton.setEnabled(false);
            });
            setJobRoleButton.addActionListener(e -> {
                int selectedRow = table.convertRowIndexToModel(table.getSelectedRow());
                if (selectedRow == -1) {
                    return;
                }
                if (main.getController().getApplicantAt(selectedRow).getStatus() == Applicant.ACCEPTED_WAITING_JOB){
                    main.showApplicantKeyPage(Applicant.ACCEPTED_WAITING_JOB, selectedRow);
                }
            });

            table.getSelectionModel().addListSelectionListener(e -> {
                if (table.getSelectedRow() != -1) {
                    int status = main.getController().getApplicantAt(table.convertRowIndexToModel(table.getSelectedRow())).getStatus();
                    setJobRoleButton.setEnabled(status == Applicant.ACCEPTED_WAITING_JOB);
                    shortlistApplicantButton.setEnabled(status == Applicant.WAITING_SHORTLIST);
                    acceptApplicantButton.setEnabled(status == Applicant.SHORTLISTED_TO_INTERVIEW);
                }
                else {
                    setJobRoleButton.setEnabled(false);
                    shortlistApplicantButton.setEnabled(false);
                }
            });
        }

    }

    private void updateModel(){
        List<Integer> filterStages = getFilterStages();
        List<String> skillFilters = getExcludes();

        sorter.setRowFilter(new ApplicantRowFilter(
                filterStages.stream().mapToInt(i->i).toArray(),
                filterField.getText(),
                skillFilters.toArray(new String[0])));
        table.setRowSorter(sorter);
    }
    private static JFileChooser getjFileChooser(){
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    String filename = f.getName().toLowerCase();
                    return filename.endsWith(".json");
                }
            }
            @Override
            public String getDescription() {
                return "Text files (*.json)";
            }
        });
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setPreferredSize(new Dimension(640, 480));
        return chooser;
    }

    private List<String> getExcludes() {
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
        return skillFilters;
    }

    private List<Integer> getFilterStages() {
        List<Integer> filterStages = new ArrayList<>();
        if (waitingShortlistCheckBox.isSelected()) {
            filterStages.add(Applicant.WAITING_SHORTLIST);
        }
        if (shortlistedWaitingHRCheckBox.isSelected()) {
            filterStages.add(Applicant.SHORTLISTED_PENDING_DATE);
        }
        if (shortlistedCheckBox.isSelected()) {
            filterStages.add(Applicant.SHORTLISTED_TO_INTERVIEW);
        }
        if (acceptedWaitingManagerCheckBox.isSelected()) {
            filterStages.add(Applicant.ACCEPTED_WAITING_JOB);
        }
        if (acceptedCheckBox.isSelected()) {
            filterStages.add(Applicant.ACCEPTED);
        }
        return filterStages;
    }

    public double getZoomFactor(){
        return zoomFactor;
    }

    public void incrementKeyframe(double zoomFactor, float alpha){
        this.zoomFactor += zoomFactor;
        this.alpha += alpha;
        main.getContentPane().validate();
        main.getContentPane().repaint();
    }

    public void decrementKeyframe(double zoomFactor, float alpha){
        this.zoomFactor -= zoomFactor;
        this.alpha -= alpha;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(this.getWidth()/2, this.getHeight()/2);
        g2.scale(zoomFactor, zoomFactor);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.translate(-this.getWidth()/2, -this.getHeight()/2);
    }
}

