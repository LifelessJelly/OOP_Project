package gui.infobase;

import controller.InfobaseMainframe;
import gui.ImageEmbedded;
import subsystems.ImageBase64;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

public class ShortlistPage extends JPanel {
    private final JTable table;
    private final TableModel applicantModel;
    private InfobaseMainframe main;
    private JTextField filterField;
    private TableRowSorter<TableModel> sorter;
    private JCheckBox programmingCheckBox;
    private JCheckBox industrialCheckBox;
    private JCheckBox artisticCheckBox;
    private JCheckBox communicationCheckBox;

    public ShortlistPage(InfobaseMainframe main) {
        this.setLayout(new GridBagLayout());
        this.main = main;

        applicantModel = new ApplicantTableModel(main);
        table = new JTable(applicantModel);
        JScrollPane tableScroll = getjScrollPane();
        GridBagConstraints scrollTableConstraints = new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        this.add(tableScroll, scrollTableConstraints);

        JPanel filterMenu = getFilterMenu();
        GridBagConstraints filterMenuConstraints = new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 100, 0, 0), 0, 0);
        filterMenu.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
        this.add(filterMenu, filterMenuConstraints);

        JButton addToShortlistButton = new JButton(new ImageIcon(ImageBase64.base64ToImage(ImageEmbedded.ADD_TO_SHORTLIST_ICON)));
        GridBagConstraints addToShortlistButtonConstraints = new GridBagConstraints(0, 2, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
        this.add(addToShortlistButton, addToShortlistButtonConstraints);

        addToShortlistButton.addActionListener(e -> {
            if (table.getSelectedRow() == -1){
                return;
            }
            main.getController().shortlistApplicant(table.convertRowIndexToModel(table.getSelectedRow()));
            main.getController().acceptApplicant(table.convertRowIndexToModel(table.getSelectedRow()));
            updateModel();
        });

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent arg0) {
                float adjustedFontSizeBody= (float) main.getContentPane().getWidth() /50;                                                      //scaling of body font
                tableScroll.setPreferredSize(new Dimension((int)(Math.round(0.7*getWidth())),(int)(Math.round(0.7*getHeight()))));
                System.out.println(table.getWidth()+"X"+ table.getHeight());
            }
        });
    }

    private JPanel getFilterMenu() {
        JPanel filterMenu = new JPanel(new GridBagLayout());
        {
            ((GridBagLayout)filterMenu.getLayout()).rowWeights = new double[]{0, 0, 0, 0, 0,  1e-4};
            JLabel nameFilterLabel = new JLabel(main.getLocale("ApplicantListPage.JLabel.filterName"));
            nameFilterLabel.setFont(nameFilterLabel.getFont().deriveFont(18f));
            GridBagConstraints nameFilterConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 10, 0), 0, 0);
            filterMenu.add(nameFilterLabel, nameFilterConstraints);

            filterField = new JTextField();
            filterField.setColumns(20);
            filterField.setFont(filterField.getFont().deriveFont(16f));
            GridBagConstraints filterFieldConstraints = new GridBagConstraints(0, 1, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(filterField, filterFieldConstraints);

            programmingCheckBox = new JCheckBox(main.getLocale("ShortlistPage.JCheckBox.programming"));
            programmingCheckBox.setFont(programmingCheckBox.getFont().deriveFont(18f));
            GridBagConstraints programmingConstraints = new GridBagConstraints(0, 2, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(programmingCheckBox, programmingConstraints);
            programmingCheckBox.addActionListener(e -> {
                updateModel();
            });

            industrialCheckBox = new JCheckBox(main.getLocale("ShortlistPage.JCheckBox.industrial"));
            industrialCheckBox.setFont(industrialCheckBox.getFont().deriveFont(18f));
            GridBagConstraints industrialConstraints = new GridBagConstraints(0, 3, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(industrialCheckBox, industrialConstraints);
            industrialCheckBox.addActionListener(e -> {
                updateModel();
            });

            artisticCheckBox = new JCheckBox(main.getLocale("ShortlistPage.JCheckBox.artistic"));
            artisticCheckBox.setFont(artisticCheckBox.getFont().deriveFont(18f));
            GridBagConstraints artisticConstraints = new GridBagConstraints(0, 4, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(artisticCheckBox, artisticConstraints);
            artisticCheckBox.addActionListener(e -> {
                updateModel();
            });

            communicationCheckBox = new JCheckBox(main.getLocale("ShortlistPage.JCheckBox.communication"));
            communicationCheckBox.setFont(communicationCheckBox.getFont().deriveFont(18f));
            GridBagConstraints communicationConstraints = new GridBagConstraints(0, 5, 1, 1, 0, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0);
            filterMenu.add(communicationCheckBox, communicationConstraints);
            communicationCheckBox.addActionListener(e -> {
                updateModel();
            });


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
        }
        updateModel();
        return filterMenu;
    }
    private JScrollPane getjScrollPane() {
        JScrollPane tableScroll=new JScrollPane(table);
        sorter = new TableRowSorter<>(applicantModel);
        table.setRowSorter(sorter);
        table.setMinimumSize(new Dimension(700, 300));
        table.getTableHeader().setUI(null);
        TableColumn pictureColumn = table.getColumnModel().getColumn(0);
        TableColumn infoColumn = table.getColumnModel().getColumn(1);
        pictureColumn.setPreferredWidth(120);
        pictureColumn.setMaxWidth(120);

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
        //TODO make another row with a checkbox to easily select which applicants to shortlist
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return tableScroll;
    }

    private void updateModel(){
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

        sorter.setRowFilter(new ApplicantRowFilter(new int[]{1}, filterField.getText(), skillFilters.toArray(new String[0])));
        table.setRowSorter(sorter);
    }
}
