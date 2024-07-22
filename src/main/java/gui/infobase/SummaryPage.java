package gui.infobase;

import controller.InfobaseMainframe;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class SummaryPage extends JPanel {
    InfobaseMainframe main;

    public SummaryPage(InfobaseMainframe main) {
        this.main = main;
        this.setLayout(new GridBagLayout());

        initComponent();
    }

    private void initComponent() {
        JLabel summaryListLabel = new JLabel("Applicant Summary");
        summaryListLabel.setFont(summaryListLabel.getFont().deriveFont(20f));
        GridBagConstraints summaryListConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        this.add(summaryListLabel, summaryListConstraints);

        TableModel applicantModel = new ApplicantTableModel(main);
        JTable table = new JTable(applicantModel);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(applicantModel);
        //TableRowSorter uses applicantModel as underlying model
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

        GridBagConstraints scrollTableConstraints = new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        add(tableScroll, scrollTableConstraints);

        sorter.setRowFilter(new ApplicantRowFilter(
                new int[]{ApplicantRowFilter.STAGE_ACCEPTED},
                "",
                null));
        table.setRowSorter(sorter);

        summaryListLabel.setText("Applicant Summary- "+(sorter.getViewRowCount())+" applicants accepted");
    }
}
