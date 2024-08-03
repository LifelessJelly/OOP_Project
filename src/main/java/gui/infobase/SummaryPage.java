package gui.infobase;

import controller.InfobaseMainframe;
import data.Applicant;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SummaryPage extends JPanel {
    InfobaseMainframe main;
    private JLabel summaryListLabel;
    private TableModel applicantModel;
    private JTable table;
    private TableColumn pictureColumn;
    private TableColumn infoColumn;
    private JScrollPane tableScroll;

    public SummaryPage(InfobaseMainframe main) {
        this.main = main;
        this.setLayout(new GridBagLayout());

        initComponent();
        initListeners();
    }

    private void initComponent() {
        summaryListLabel = new JLabel("Applicant Summary");
        summaryListLabel.setFont(summaryListLabel.getFont().deriveFont(20f));
        GridBagConstraints summaryListConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        this.add(summaryListLabel, summaryListConstraints);

        applicantModel = new ApplicantTableModel(main);
        table = new JTable(applicantModel);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(applicantModel);
        //TableRowSorter uses applicantModel as underlying model
        table.setRowSorter(sorter);
        table.setMinimumSize(new Dimension(700, 300));
        table.getTableHeader().setUI(null);
        pictureColumn = table.getColumnModel().getColumn(0);
        infoColumn = table.getColumnModel().getColumn(1);
        pictureColumn.setPreferredWidth(120);
        pictureColumn.setMaxWidth(120);

        tableScroll=new JScrollPane(table);
        tableScroll.setMinimumSize(new Dimension(700, 300));
        tableScroll.setPreferredSize(new Dimension(700, 300));
        tableScroll.setMaximumSize(new Dimension(getWidth(),(int)(Math.round(0.7*getHeight()))));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        table.setRowHeight(200);
        table.setMinimumSize(new Dimension(0, 100));
        ////////
        table.getColumnModel().getColumn(0).setCellRenderer(new TableImageRender());
        table.getColumnModel().getColumn(1).setCellRenderer(new SummaryCellRenderer(main));
        ////////
        table.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        GridBagConstraints scrollTableConstraints = new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        add(tableScroll, scrollTableConstraints);

        sorter.setRowFilter(new ApplicantRowFilter(
                new int[]{Applicant.ACCEPTED},
                "",
                null));
        table.setRowSorter(sorter);

        summaryListLabel.setText("Applicant Summary- "+(sorter.getViewRowCount())+" applicants accepted");
    }

    private void initListeners(){
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.convertRowIndexToModel(table.getSelectedRow());
                Applicant selectedApplicant = main.getController().getApplicantAt(selectedRow);
                if (e.getClickCount() == 2) {
                    JDialogSpawner(selectedApplicant);
                }
            }
        });
    }
    //TODO:ADD TRANSLATION
    private void JDialogSpawner(Applicant applicantInstance){
        JDialog extraDialog = new JDialog();

        extraDialog.setTitle(applicantInstance.getName()+"'s extra info");
        extraDialog.setSize(300,300);
        extraDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setAutoscrolls(true);
        textArea.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(166,14.8F,55.7F)));
        textArea.setFont(textArea.getFont().deriveFont(18f));
        textArea.setLineWrap(true);

        textArea.setText(main.getLocale("ApplicantListPage.JTable.applicantName")+applicantInstance.getName()+"\n"+
                        main.getLocale("ApplicantListPage.JTable.applicantBirthDate")+applicantInstance.getBirthdate()+"\n"+
                        main.getLocale("ApplicantListPage.JTable.applicantAge")+applicantInstance.getAge()+'\n'+
                        main.getLocale("ApplicantListPage.JTable.applicantNRIC")+applicantInstance.getNRIC()+'\n'+
                        main.getLocale("ApplicantListPage.JTable.applicantEmail")+applicantInstance.getEmail()+'\n'+
                        main.getLocale("ApplicantListPage.JTable.applicantGender")+applicantInstance.getGender()+'\n'+
                        main.getLocale("ApplicantListPage.JTable.applicantSkills") + String.join(", ", applicantInstance.getSkills())
        );

        JScrollPane txtAreaScroll = new JScrollPane();
        txtAreaScroll.setViewportView(textArea);
        txtAreaScroll.setAutoscrolls(true);
        extraDialog.add(txtAreaScroll);
        extraDialog.setVisible(true);
      }
}


