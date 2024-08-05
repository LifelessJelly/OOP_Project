package gui.infobase;


import controller.DataIO;
import controller.InfobaseMainframe;
import data.Applicant;
import data.Staff;
import gui.ImageEmbedded;
import gui.JButtonImage;
import controller.ImageBase64;
import controller.JsonReaderWriter;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


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
    private JButton viewApplicantPDFButton;

    public ApplicantListPage(InfobaseMainframe mainframe) {
        this.main = mainframe;
        this.setLayout(new GridBagLayout());
        this.zoomFactor = 1;
        this.alpha = 1;
        this.setBackground(new Color(47, 47, 47));

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
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0);
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

        viewApplicantPDFButton = new JButtonImage(ImageEmbedded.ViewApplicantPDFIcon.VIEWPDF_OFF, ImageBase64.imageToBase64(main.getImage("ViewPDFIcon")));
        viewApplicantPDFButton.setBorder(BorderFactory.createEmptyBorder());
        viewApplicantPDFButton.setEnabled(false);
        viewApplicantPDFButton.setToolTipText(main.getLocale("ApplicantListPage.ToolTip.viewApplicantPDFDisabled"));
        GridBagConstraints viewApplicantPDFConstraints = new GridBagConstraints(0, 10, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
        buttonPanel.add(viewApplicantPDFButton, viewApplicantPDFConstraints);

        //HR Staff button set
        if (main.getController().getUser().authorised(Staff.HR)){
            addApplicantTextButton = new JButtonImage(ImageEmbedded.AddApplicantJsonIcon.ADDJSON_OFF, ImageBase64.imageToBase64(main.getImage("AddApplicantJsonIcon")));
            addApplicantTextButton.setBorder(BorderFactory.createEmptyBorder());
            addApplicantTextButton.setToolTipText(main.getLocale("ApplicantListPage.ToolTip.addApplicantJSON"));
            GridBagConstraints addApplicantTextConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
            buttonPanel.add(addApplicantTextButton, addApplicantTextConstraints);

            addApplicantButton = new JButtonImage(ImageEmbedded.AddApplicantIcon.ADDAPPLICANT_OFF, ImageBase64.imageToBase64(main.getImage("AddApplicantIcon")));
            addApplicantButton.setBorder(BorderFactory.createEmptyBorder());
            addApplicantButton.setToolTipText(main.getLocale("ApplicantListPage.ToolTip.addApplicant"));
            GridBagConstraints addApplicantConstraints = new GridBagConstraints(0, 1, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
            buttonPanel.add(addApplicantButton, addApplicantConstraints);

            editApplicantButton = new JButtonImage(ImageEmbedded.EditApplicantIcon.EDITAPPLICANT_OFF, ImageBase64.imageToBase64(main.getImage("EditApplicantIcon")));
            editApplicantButton.setBorder(BorderFactory.createEmptyBorder());
            editApplicantButton.setToolTipText(main.getLocale("ApplicantListPage.ToolTip.editApplicantDisabled"));
            editApplicantButton.setEnabled(false);
            GridBagConstraints editApplicantConstraints = new GridBagConstraints(0, 2, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
            buttonPanel.add(editApplicantButton, editApplicantConstraints);

            removeApplicantButton=new JButtonImage(ImageEmbedded.DeleteApplicantIcon.DELETEAPPLICANT_OFF, ImageBase64.imageToBase64(main.getImage("DeleteApplicantIcon")));
            removeApplicantButton.setBorder(BorderFactory.createEmptyBorder());
            removeApplicantButton.setToolTipText(main.getLocale("ApplicantListPage.ToolTip.removeApplicantDisabled"));
            removeApplicantButton.setEnabled(false);
            GridBagConstraints removeApplicantConstraints = new GridBagConstraints(0, 3, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
            buttonPanel.add(removeApplicantButton, removeApplicantConstraints);

            setInterviewDateButton = new JButtonImage(ImageEmbedded.SetInterviewIcon.SETINTERVIEW_OFF, ImageBase64.imageToBase64(main.getImage("SetInterviewIcon")));
            setInterviewDateButton.setBorder(BorderFactory.createEmptyBorder());
            setInterviewDateButton.setToolTipText(main.getLocale("ApplicantListPage.ToolTip.setInterviewDateDisabled"));
            setInterviewDateButton.setEnabled(false);
            GridBagConstraints setInterviewDateConstraints = new GridBagConstraints(0, 4, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
            buttonPanel.add(setInterviewDateButton, setInterviewDateConstraints);

            acceptApplicantButton = new JButtonImage(ImageEmbedded.AcceptApplicantIcon.ACCEPTAPPLICANT_OFF, ImageBase64.imageToBase64(main.getImage("AcceptApplicantIcon")));
            acceptApplicantButton.setBorder(BorderFactory.createEmptyBorder());
            acceptApplicantButton.setToolTipText(main.getLocale("ApplicantListPage.ToolTip.acceptApplicantDisabled"));
            acceptApplicantButton.setEnabled(false);
            GridBagConstraints acceptApplicantConstraints = new GridBagConstraints(0, 5, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
            buttonPanel.add(acceptApplicantButton, acceptApplicantConstraints);

        }
        if (main.getController().getUser().authorised(Staff.MANAGER)){

            shortlistApplicantButton = new JButtonImage(ImageEmbedded.ShortlistAddIcon.SHORTLISTADD_OFF, ImageBase64.imageToBase64(main.getImage("ShortlistAddIcon")));
            shortlistApplicantButton.setBorder(BorderFactory.createEmptyBorder());
            shortlistApplicantButton.setToolTipText(main.getLocale("ApplicantListPage.ToolTip.shortlistApplicantDisabled"));
            shortlistApplicantButton.setEnabled(false);
            GridBagConstraints shortlistApplicantConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
            buttonPanel.add(shortlistApplicantButton, shortlistApplicantConstraints);

            acceptApplicantButton = new JButtonImage(ImageEmbedded.AcceptApplicantIcon.ACCEPTAPPLICANT_OFF, ImageBase64.imageToBase64(main.getImage("AcceptApplicantIcon")));
            acceptApplicantButton.setBorder(BorderFactory.createEmptyBorder());
            acceptApplicantButton.setToolTipText(main.getLocale("ApplicantListPage.ToolTip.acceptApplicantDisabled"));
            acceptApplicantButton.setEnabled(false);
            GridBagConstraints acceptApplicantConstraints = new GridBagConstraints(0, 1, 1, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
            buttonPanel.add(acceptApplicantButton, acceptApplicantConstraints);

            setJobRoleButton = new JButtonImage(ImageEmbedded.SetJobRoleIcon.SETJOBROLE_OFF, ImageBase64.imageToBase64(main.getImage("SetJobRoleIcon")));
            setJobRoleButton.setBorder(BorderFactory.createEmptyBorder());
            setJobRoleButton.setToolTipText(main.getLocale("ApplicantListPage.ToolTip.setJobRoleDisabled"));
            setJobRoleButton.setEnabled(false);
            GridBagConstraints setJobRoleConstraints = new GridBagConstraints(0, 2, 1, 1, 1, 0,
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

        shortlistedWaitingHRCheckBox.addActionListener(e -> updateModel());

        shortlistedCheckBox.addActionListener(e -> updateModel());

        acceptedCheckBox.addActionListener(e -> updateModel());

        acceptedWaitingManagerCheckBox.addActionListener(e -> updateModel());

        programmingCheckBox.addActionListener(e -> updateModel());

        industrialCheckBox.addActionListener(e -> updateModel());

        artisticCheckBox.addActionListener(e -> updateModel());

        communicationCheckBox.addActionListener(e -> updateModel());

        viewApplicantPDFButton.addActionListener(e -> {
            int selectedRow = table.convertRowIndexToModel(table.getSelectedRow());
            if (selectedRow == -1) {
                return;
            }
            main.getController().setApplicantInstance(selectedRow);
            main.getController().openPDF();
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            if (table.getSelectedRow() != -1){
                viewApplicantPDFButton.setEnabled(true);
                viewApplicantPDFButton.setToolTipText(main.getLocale("ApplicantListPage.ToolTip.viewApplicantPDFEnabled"));
            }
            else {
                viewApplicantPDFButton.setEnabled(false);
                viewApplicantPDFButton.setToolTipText(main.getLocale("ApplicantListPage.ToolTip.viewApplicantPDFDisabled"));
            }
        });

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
                   main.getController().addApplicantModel(applicantToBeWritten);
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

            addApplicantButton.addMouseListener(getGraphicalUpdaters());
            addApplicantTextButton.addMouseListener(getGraphicalUpdaters());
            editApplicantButton.addMouseListener(getGraphicalUpdaters());
            removeApplicantButton.addMouseListener(getGraphicalUpdaters());
            setInterviewDateButton.addMouseListener(getGraphicalUpdaters());
            acceptApplicantButton.addMouseListener(getGraphicalUpdaters());

            table.getSelectionModel().addListSelectionListener(e -> {
                if (table.getSelectedRow() != -1) {
                    int status = main.getController().getApplicantAt(table.convertRowIndexToModel(table.getSelectedRow())).getStatus();
                    editApplicantButton.setEnabled(true);
                    editApplicantButton.setToolTipText(main.getLocale("ApplicantListPage.ToolTip.editApplicantEnabled"));
                    removeApplicantButton.setEnabled(true);
                    removeApplicantButton.setToolTipText(main.getLocale("ApplicantListPage.ToolTip.removeApplicantEnabled"));
                    setInterviewDateButton.setEnabled(status == Applicant.SHORTLISTED_PENDING_DATE);
                    setInterviewDateButton.setToolTipText(status == Applicant.SHORTLISTED_PENDING_DATE ? main.getLocale("ApplicantListPage.ToolTip.setInterviewDateEnabled") : main.getLocale("ApplicantListPage.ToolTip.setInterviewDateDisabled"));
                    acceptApplicantButton.setEnabled(status == Applicant.SHORTLISTED_TO_INTERVIEW);
                    acceptApplicantButton.setToolTipText(status == Applicant.SHORTLISTED_TO_INTERVIEW ? main.getLocale("ApplicantListPage.ToolTip.acceptApplicantEnabled") : main.getLocale("ApplicantListPage.ToolTip.acceptApplicantDisabled"));
                }
                else {
                    editApplicantButton.setEnabled(false);
                    editApplicantButton.setToolTipText(main.getLocale("ApplicantListPage.ToolTip.editApplicantDisabled"));
                    setInterviewDateButton.setEnabled(false);
                    setInterviewDateButton.setToolTipText(main.getLocale("ApplicantListPage.ToolTip.setInterviewDateDisabled"));
                    acceptApplicantButton.setEnabled(false);
                    acceptApplicantButton.setToolTipText(main.getLocale("ApplicantListPage.ToolTip.acceptApplicantDisabled"));
                    removeApplicantButton.setEnabled(false);
                    removeApplicantButton.setToolTipText(main.getLocale("ApplicantListPage.ToolTip.removeApplicantDisabled"));
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

            shortlistApplicantButton.addMouseListener(getGraphicalUpdaters());
            acceptApplicantButton.addMouseListener(getGraphicalUpdaters());
            setJobRoleButton.addMouseListener(getGraphicalUpdaters());

            table.getSelectionModel().addListSelectionListener(e -> {
                if (table.getSelectedRow() != -1) {
                    int status = main.getController().getApplicantAt(table.convertRowIndexToModel(table.getSelectedRow())).getStatus();
                    setJobRoleButton.setEnabled(status == Applicant.ACCEPTED_WAITING_JOB);
                    setJobRoleButton.setToolTipText(status == Applicant.ACCEPTED_WAITING_JOB ? main.getLocale("ApplicantListPage.ToolTip.setJobRoleEnabled") : main.getLocale("ApplicantListPage.ToolTip.setJobRoleDisabled"));
                    shortlistApplicantButton.setEnabled(status == Applicant.WAITING_SHORTLIST);
                    shortlistApplicantButton.setToolTipText(status == Applicant.WAITING_SHORTLIST ? main.getLocale("ApplicantListPage.ToolTip.shortlistApplicantEnabled") : main.getLocale("ApplicantListPage.ToolTip.shortlistApplicantDisabled"));
                    acceptApplicantButton.setEnabled(status == Applicant.SHORTLISTED_TO_INTERVIEW);
                    acceptApplicantButton.setToolTipText(status == Applicant.SHORTLISTED_TO_INTERVIEW ? main.getLocale("ApplicantListPage.ToolTip.acceptApplicantEnabled") : main.getLocale("ApplicantListPage.ToolTip.acceptApplicantDisabled"));
                }
                else {
                    setJobRoleButton.setEnabled(false);
                    setJobRoleButton.setToolTipText(main.getLocale("ApplicantListPage.ToolTip.setJobRoleDisabled"));
                    shortlistApplicantButton.setEnabled(false);
                    shortlistApplicantButton.setToolTipText(main.getLocale("ApplicantListPage.ToolTip.shortlistApplicantDisabled"));
                    acceptApplicantButton.setEnabled(false);
                    acceptApplicantButton.setToolTipText("ApplicantListPage.ToolTip.acceptApplicantDisabled");
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
        chooser.setAcceptAllFileFilterUsed(false);
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

    private MouseAdapter getGraphicalUpdaters(){
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mousePressed(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mouseReleased(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseDragged(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                mouseMoved(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mouseEntered(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mouseExited(e);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                main.getContentPane().validate();
                main.getContentPane().repaint();
            }
        };
    }
}


