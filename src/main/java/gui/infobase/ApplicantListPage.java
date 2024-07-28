package gui.infobase;


import controller.DataIO;
import controller.InfobaseMainframe;
import data.Applicant;
import data.Staff;
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
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

//import controller.MainFrame;

public class ApplicantListPage extends JPanel{
    private final InfobaseMainframe main;
    private TableRowSorter<TableModel> sorter;
    private JTable table;
    private JCheckBox shortlistedCheckBox;
    private JCheckBox acceptedCheckBox;
    private JTextField filterField;
    private JCheckBox waitingShortlist;
    private JCheckBox programmingCheckBox;
    private JCheckBox industrialCheckBox;
    private JCheckBox artisticCheckBox;
    private JCheckBox communicationCheckBox;
    private JButton addApplicantButton;
    private JButton editApplicantButton;
    private JButton removeApplicantButton;
    private double zoomFactor;
    private float alpha;

    public ApplicantListPage(InfobaseMainframe mainframe) {
        this.main = mainframe;
        this.setLayout(new GridBagLayout());
        this.zoomFactor = 1;
        this.alpha = 1;

        initComponents();
        initListeners();
        //CREATE INSTANCE OF TABLEMODEL HERE
        //DefaultTableModel model=new DefaultTableModel();
    }

    private void initComponents() {
        JLabel applicantListLabel = new JLabel("Applicant List");

        applicantListLabel.setFont(applicantListLabel.getFont().deriveFont(22f));
        applicantListLabel.setFont(applicantListLabel.getFont().deriveFont(Font.BOLD));

        GridBagConstraints staffListConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 60, 20, 20), 0, 0);
        this.add(applicantListLabel, staffListConstraints);

//FROM HERE
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
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 40, 0, 0), 0, 0);
        this.add(tableScroll, scrollTableConstraints);

        JPanel filterMenu = new JPanel(new GridBagLayout());
        {
            ((GridBagLayout)filterMenu.getLayout()).rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0,  1e-4};
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

            waitingShortlist = new JCheckBox(main.getLocale("ApplicantListPage.JCheckBox.waitingShortlist"));
            waitingShortlist.setSelected(true);
            waitingShortlist.setFont(waitingShortlist.getFont().deriveFont(16f));
            GridBagConstraints waitingFilterConstraints = new GridBagConstraints(0, 3, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(waitingShortlist, waitingFilterConstraints);

            shortlistedCheckBox = new JCheckBox(main.getLocale("ApplicantListPage.JCheckBox.shortlisted"));
            shortlistedCheckBox.setSelected(true);
            shortlistedCheckBox.setFont(shortlistedCheckBox.getFont().deriveFont(16f));
            GridBagConstraints statusFilterConstraints = new GridBagConstraints(0, 4, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(shortlistedCheckBox, statusFilterConstraints);

            acceptedCheckBox = new JCheckBox(main.getLocale("ApplicantListPage.JCheckBox.accepted"));
            acceptedCheckBox.setSelected(true);
            acceptedCheckBox.setFont(acceptedCheckBox.getFont().deriveFont(16f));
            GridBagConstraints acceptedFilterConstraints = new GridBagConstraints(0, 5, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(acceptedCheckBox, acceptedFilterConstraints);

            programmingCheckBox = new JCheckBox(main.getLocale("ShortlistPage.JCheckBox.programming"));
            programmingCheckBox.setFont(programmingCheckBox.getFont().deriveFont(18f));
            GridBagConstraints programmingConstraints = new GridBagConstraints(0, 6, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(programmingCheckBox, programmingConstraints);

            industrialCheckBox = new JCheckBox(main.getLocale("ShortlistPage.JCheckBox.industrial"));
            industrialCheckBox.setFont(industrialCheckBox.getFont().deriveFont(18f));
            GridBagConstraints industrialConstraints = new GridBagConstraints(0, 7, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(industrialCheckBox, industrialConstraints);

            artisticCheckBox = new JCheckBox(main.getLocale("ShortlistPage.JCheckBox.artistic"));
            artisticCheckBox.setFont(artisticCheckBox.getFont().deriveFont(18f));
            GridBagConstraints artisticConstraints = new GridBagConstraints(0, 8, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(artisticCheckBox, artisticConstraints);

            communicationCheckBox = new JCheckBox(main.getLocale("ShortlistPage.JCheckBox.communication"));
            communicationCheckBox.setFont(communicationCheckBox.getFont().deriveFont(18f));
            GridBagConstraints communicationConstraints = new GridBagConstraints(0, 9, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(communicationCheckBox, communicationConstraints);
        }
        updateModel();
        GridBagConstraints filterMenuConstraints = new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 200, 0, 0), 0, 0);
        filterMenu.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
        this.add(filterMenu, filterMenuConstraints);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());

        //HR Staff button set
        if (main.getController().getUser().authorised(Staff.HR)){
            addApplicantButton = new JButton(new ImageIcon(ImageBase64.base64ToImage(ImageEmbedded.ADD_APPLICANT_ICON)));
            addApplicantButton.setBorder(BorderFactory.createEmptyBorder());
            GridBagConstraints addApplicantConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                    GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
            buttonPanel.add(addApplicantButton, addApplicantConstraints);

            editApplicantButton = new JButton(new ImageIcon(ImageBase64.base64ToImage(ImageEmbedded.EDIT_ICON)));
            editApplicantButton.setBorder(BorderFactory.createEmptyBorder());
            GridBagConstraints editApplicantConstraints = new GridBagConstraints(1, 0, 1, 1, 0, 1,
                    GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
            buttonPanel.add(editApplicantButton, editApplicantConstraints);

            removeApplicantButton=new JButton(new ImageIcon((ImageBase64.base64ToImage(ImageEmbedded.REMOVE_ICON)).getScaledInstance(60,60,Image.SCALE_SMOOTH)));
            removeApplicantButton.setBorder(BorderFactory.createEmptyBorder());
            GridBagConstraints removeApplicantConstraints = new GridBagConstraints(2, 0, 1, 1, 0, 1,
                    GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
            buttonPanel.add(removeApplicantButton, removeApplicantConstraints);
        }
        if (main.getController().getUser().authorised(Staff.MANAGER)){
            JButton shortlistApplicantButton = new JButton(new ImageIcon(ImageBase64.base64ToImage(ImageEmbedded.ADD_TO_SHORTLIST_ICON)));

            shortlistApplicantButton.setBorder(BorderFactory.createEmptyBorder());
            GridBagConstraints shortlistApplicantConstraints = new GridBagConstraints(2, 0, 1, 1, 1, 1,
                    GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
            buttonPanel.add(shortlistApplicantButton, shortlistApplicantConstraints);
        }

        GridBagConstraints buttonPanelConstraints = new GridBagConstraints(0, 2, 1, 1, 0, 0,
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

        waitingShortlist.addActionListener(e -> updateModel());
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
                /*JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                j.setFileFilter(new FileFilter() {
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
                        return "JSON Files (*.json)";
                    }
                });
                j.setFileSelectionMode(JFileChooser.FILES_ONLY);
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

                } */


            });

            editApplicantButton.addActionListener(e -> {
                int selectedRow = table.convertRowIndexToModel(table.getSelectedRow());
                if (selectedRow == -1) {
                    return;
                }
                main.showEditApplicant(main.getController().getApplicants()[selectedRow], selectedRow);
            });

            //TODO: complete the remove applicant listener
            removeApplicantButton.addActionListener(e -> {
                int selectedRow = table.convertRowIndexToModel(table.getSelectedRow());
                removeApplicant(selectedRow);
                this.repaint();
                this.revalidate();
            });
        }

//        this.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent arg0) {
//                //scaling of title font
//                float adjustedFontSizeBody= (float) main.getContentPane().getWidth() /50;
//                System.out.println(table.getWidth()+"X"+ table.getHeight());
//            }
//        });
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

    public void removeApplicant(int selectedRow){
        main.getController().removeApplicant(selectedRow);

    }
}

