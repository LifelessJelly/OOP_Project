package gui.infobase;


import controller.InfobaseMainframe;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

//import controller.MainFrame;

public class ApplicantListPage extends JPanel{
    private final InfobaseMainframe main;
    private final TableModel applicantModel;
    private final TableRowSorter<TableModel> sorter;
    private final JTable table;
    private JCheckBox shortlistedCheckBox;
    private JCheckBox acceptedCheckBox;
    private JTextField filterField;
    private JCheckBox waitingShortlist;

    public ApplicantListPage(InfobaseMainframe mainframe) {
        this.main = mainframe;

        this.setLayout(new GridBagLayout());

        //CREATE INSTANCE OF TABLEMODEL HERE
        //DefaultTableModel model=new DefaultTableModel();



        JLabel staffListLabel = new JLabel("Applicant List");
        GridBagConstraints staffListConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        this.add(staffListLabel, staffListConstraints);


        applicantModel = new ApplicantTableModel(main);
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
        GridBagConstraints scrollTableConstraints = new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        add(tableScroll, scrollTableConstraints);

        JPanel filterMenu = getFilterMenu();
        GridBagConstraints filterMenuConstraints = new GridBagConstraints(1, 1, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 100, 0, 0), 0, 0);
        filterMenu.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
        this.add(filterMenu, filterMenuConstraints);

        JButton editApplicantButton = new JButton("Edit Applicant");
        editApplicantButton.setLayout(new GridBagLayout());
        GridBagConstraints buttonConstraints = new GridBagConstraints(0, 2, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0);
        this.add(editApplicantButton, buttonConstraints);

        editApplicantButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                return;
            }
            main.showEditApplicant(main.getController().getApplicants()[selectedRow]);
        });




        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent arg0) {
                //scaling of title font
                float adjustedFontSizeBody= (float) main.getContentPane().getWidth() /50;                                                      //scaling of body font
//                Font bodyFont=new Font("Comic Sans MS", Font.PLAIN, adjustedFontSizeBody);                                     //body font
//                Font smallBodyFont=new Font("Comic Sans MS", Font.PLAIN, (int)(Math.round(adjustedFontSizeBody*0.8)));
                //smaller body

                staffListLabel.setFont(staffListLabel.getFont().deriveFont(adjustedFontSizeBody));
                editApplicantButton.setFont(editApplicantButton.getFont().deriveFont(adjustedFontSizeBody));


                tableScroll.setPreferredSize(new Dimension((int)(Math.round(0.7*getWidth())),(int)(Math.round(0.7*getHeight()))));
                filterMenu.setPreferredSize(new Dimension((int)(Math.round(0.2*getWidth())),(int)(Math.round(0.7*getHeight()))));
//
//                for(int i=0;i<table.getRowCount();++i){
//                    //for(int j=0;j<table.getColumnCount();++j){
//                    TableCellRenderer render=table.getCellRenderer(i,1);
//                    Component comp=table.prepareRenderer(render,i,1);
//                    comp.setFont(smallerBodyFont);
//                }

                /*for(int i=0;i<table.getRowCount();++i){
                    //for(int j=0;j<table.getColumnCount();++j){
                    TableCellRenderer render=table.getCellRenderer(i,0);
                    BufferedImage img=table.prepareRenderer(render,i,0);
                    ((BufferedImage)img);
                }*/


                //System.out.println(main.getContentPane().getWidth()+"X"+main.getContentPane().getHeight());
                System.out.println(table.getWidth()+"X"+ table.getHeight());
            }
        });
    }

    private JPanel getFilterMenu() {
        JPanel filterMenu = new JPanel(new GridBagLayout());
        {
            JLabel nameFilterLabel = new JLabel("Filter by name:");
            nameFilterLabel.setFont(nameFilterLabel.getFont().deriveFont(18f));
            GridBagConstraints nameFilterConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 5, 10, 0), 0, 0);
            filterMenu.add(nameFilterLabel, nameFilterConstraints);

            filterField = new JTextField();
            filterField.setColumns(20);
            filterField.setFont(filterField.getFont().deriveFont(16f));
            GridBagConstraints filterFieldConstraints = new GridBagConstraints(0, 1, 1, 1, 0, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 5, 0, 0), 0, 0);
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

            JLabel statusFilterLabel = new JLabel("Filter status by:");
            statusFilterLabel.setFont(statusFilterLabel.getFont().deriveFont(18f));
            GridBagConstraints statusFilterConstarints = new GridBagConstraints(0, 2, 1, 1, 0, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 5, 10, 0), 0, 0);
            filterMenu.add(statusFilterLabel, statusFilterConstarints);

            waitingShortlist = new JCheckBox("Waiting shortlist");
            waitingShortlist.setSelected(true);
            waitingShortlist.setFont(waitingShortlist.getFont().deriveFont(16f));
            GridBagConstraints waitingFilterConstraints = new GridBagConstraints(0, 3, 1, 1, 0, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(waitingShortlist, waitingFilterConstraints);
            waitingShortlist.addActionListener(e -> updateModel());

            shortlistedCheckBox = new JCheckBox("Shortlisted");
            shortlistedCheckBox.setSelected(true);
            shortlistedCheckBox.setFont(shortlistedCheckBox.getFont().deriveFont(16f));
            GridBagConstraints statusFilterConstraints = new GridBagConstraints(0, 4, 1, 1, 0, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(shortlistedCheckBox, statusFilterConstraints);
            shortlistedCheckBox.addActionListener(e -> updateModel());


            acceptedCheckBox = new JCheckBox("Accepted");
            acceptedCheckBox.setSelected(true);
            acceptedCheckBox.setFont(acceptedCheckBox.getFont().deriveFont(16f));
            GridBagConstraints acceptedFilterConstraints = new GridBagConstraints(0, 5, 1, 1, 0, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(acceptedCheckBox, acceptedFilterConstraints);
            acceptedCheckBox.addActionListener(e -> updateModel());

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

        sorter.setRowFilter(new ApplicantRowFilter(shortlistStages.stream().mapToInt(i->i).toArray(), filterField.getText(), null));
        table.setRowSorter(sorter);
    }
}

