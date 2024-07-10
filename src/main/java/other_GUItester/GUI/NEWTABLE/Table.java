package other_GUItester.GUI.NEWTABLE;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import javax.swing.border.MatteBorder;
//import TableModel;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

//import controller.MainFrame;

public class Table extends JPanel{
    private Object ArrayList;
    private JTable table;
    //private MainFrame main;
    private TableModel modelTable;

    //===fakomanega===//
    public String pic1() {
        return "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";
    }

    public Table(/*MainFrame main*/) {
        /*this.main=main;*/
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{80,80,80,80,80,80,80,80};
        gridBagLayout.rowHeights = new int[]{36,36,36,36,36,36,36,36,36,36};
        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        //CREATE INSTANCE OF TABLEMODEL HERE
        //DefaultTableModel model=new DefaultTableModel();


        JButton btnToSelectionPage = new JButton("To selection page");
        btnToSelectionPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //showSelectionPage();
            }
        });
        GridBagConstraints gbc_btnToSelectionPage = new GridBagConstraints();
        gbc_btnToSelectionPage.anchor = GridBagConstraints.EAST;
        gbc_btnToSelectionPage.insets = new Insets(0, 0, 5, 5);
        gbc_btnToSelectionPage.gridx = 7;
        gbc_btnToSelectionPage.gridy = 0;
        gbc_btnToSelectionPage.weightx = 0.2;
        gbc_btnToSelectionPage.weighty = 0.2;
        add(btnToSelectionPage, gbc_btnToSelectionPage);

        JLabel lblStaffList = new JLabel("Staff List");
        GridBagConstraints gbc_lblStaffList = new GridBagConstraints();
        gbc_lblStaffList.insets = new Insets(0, 0, 5, 0);
        gbc_lblStaffList.gridx = 0;
        gbc_lblStaffList.gridy = 0;
        gbc_lblStaffList.gridwidth=8;
        gbc_lblStaffList.weightx=0.2;
        gbc_lblStaffList.weighty=0.2;
        add(lblStaffList, gbc_lblStaffList);

        //GET ARRAY FROM CONTROLLER, or data as test haha!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //===SAMPLE DATA===//
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
        ArrayList<Object[]> data= new ArrayList<>();
        data.add(new Object[]{pic1(),"John Doe\n25 years old\n18398401\n1@email.com"});
        data.add(new Object[]{pic1(),"Jane Smith\n30 years old\n92830189\n1@email.com"});
        data.add(new Object[]{pic1(),"Michael Johnson\n28 years old\n12345678\n1@email.com"});
        data.add(new Object[]{pic1(),"Carmen Winstead\n89 years old\n69696969\n1@email.com"});
        data.add(new Object[]{pic1(),"Quandale Dingle\n69 years old\n09128312\n1@email.com"});
        data.add(new Object[]{pic1(),"Edward Epstein\n12312 years old\n59763645\n1@email.com"});
        data.add(new Object[]{pic1(),"Tan Kin Lian\n28 years old\n94675264\n1@email.com"});
        data.add(new Object[]{pic1(),"nigger\n1891 years old\n96436753\n1@email.com"});
        data.add(new Object[]{pic1(),"name1\n31 years old\n11100011\n1@email.com"});
        data.add(new Object[]{pic1(),"kill my self\n76 years old\n96437541\n1@email.com"});
        data.add(new Object[]{pic1(),"gg\n5 years old\n37830289\n1@email.com"});
        data.add(new Object[]{pic1(),"Skibidi Toilet\n99 years old\n99999999\n1@email.com"});
        data.add(new Object[]{pic1(),"Polis\n277 years old\n999\n1@email.com"});
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

        TableModel mod=new TableModel(data);
        JTable table=new JTable(mod);
        JScrollPane tableScroll=new JScrollPane(table);
        tableScroll.setMaximumSize(new Dimension(getWidth(),(int)(Math.round(0.7*getHeight()))));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setRowHeight(100);
        ////////
        table.getColumnModel().getColumn(0).setCellRenderer(new TableImageRender());
        table.getColumnModel().getColumn(1).setCellRenderer(new TableCellRender());
        ////////
        table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.GRAY));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        GridBagConstraints gbc_table = new GridBagConstraints();
        gbc_table.gridheight = 8;
        gbc_table.fill = GridBagConstraints.BOTH;
        gbc_table.insets = new Insets(0, 0, 5, 5);
        gbc_table.gridx = 1;
        gbc_table.gridy = 1;
        gbc_table.gridwidth=6;
        gbc_table.weightx=1;
        gbc_table.weighty=1;
        add(tableScroll, gbc_table);
        // TODO Auto-generated constructor stub

        JButton btnAdd=new JButton();
        GridBagConstraints gbc_btnAdd= new GridBagConstraints();
        gbc_btnAdd.anchor = GridBagConstraints.EAST;
        gbc_btnAdd.insets = new Insets(0, 0, 0, 5);
        gbc_btnAdd.gridx=1;
        gbc_btnAdd.gridy=9;
        gbc_btnAdd.gridwidth=2;
        gbc_btnAdd.weightx=1;
        gbc_btnAdd.weighty=1;
        btnAdd.setText("ADD");
        add(btnAdd,gbc_btnAdd);

        JButton btnEdit=new JButton();
        GridBagConstraints gbc_btnEdit= new GridBagConstraints();
        gbc_btnEdit.insets = new Insets(0, 0, 0, 5);
        gbc_btnEdit.gridx=3;
        gbc_btnEdit.gridy=9;
        gbc_btnEdit.gridwidth=2;
        gbc_btnEdit.weightx=1;
        gbc_btnEdit.weighty=1;
        btnEdit.setText("EDIT");
        add(btnEdit,gbc_btnEdit);

        JButton btnDel=new JButton();
        GridBagConstraints gbc_btnDel= new GridBagConstraints();
        gbc_btnDel.anchor = GridBagConstraints.WEST;
        gbc_btnDel.insets = new Insets(0, 0, 0, 5);
        gbc_btnDel.gridx=5;
        gbc_btnDel.gridy=9;
        gbc_btnDel.gridwidth=2;
        gbc_btnDel.weightx=1;
        gbc_btnDel.weighty=1;
        btnDel.setText("DELETE");
        add(btnDel,gbc_btnDel);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent arg0) {
                int adjustedFontSizeTitle=(int)(main.getContentPane().getWidth()/25);                                                     //scaling of title font
                int adjustedFontSizeBody=(int)(main.getContentPane().getWidth()/35);                                                      //scaling of body font
                Font bodyFont=new Font("Comic Sans MS", Font.PLAIN, (int)adjustedFontSizeBody);                                     //body font
                Font smallBodyFont=new Font("Comic Sans MS", Font.PLAIN, (int)(Math.round(adjustedFontSizeBody*0.8)));              //smaller body
                Font smallerBodyFont=new Font("Comic Sans MS", Font.PLAIN, (int)(Math.round(adjustedFontSizeBody*0.3)));

                lblStaffList.setFont(bodyFont);
                btnAdd.setFont(smallBodyFont);
                btnEdit.setFont(smallBodyFont);
                btnDel.setFont(smallBodyFont);
                table.setFont(smallBodyFont);

                tableScroll.setMaximumSize(new Dimension(getWidth(),(int)(Math.round(0.7*getHeight()))));
                table.setRowHeight((int)(Math.round(0.1*getHeight())));
                btnToSelectionPage.setFont(new Font("Comic Sans MS", Font.PLAIN, (int)(Math.round(adjustedFontSizeBody*0.5))));

                for(int i=0;i<table.getRowCount();++i){
                    //for(int j=0;j<table.getColumnCount();++j){
                    TableCellRenderer render=table.getCellRenderer(i,1);
                    Component comp=table.prepareRenderer(render,i,1);
                    ((JComponent)comp).setFont(smallerBodyFont);
                }

                /*for(int i=0;i<table.getRowCount();++i){
                    //for(int j=0;j<table.getColumnCount();++j){
                    TableCellRenderer render=table.getCellRenderer(i,0);
                    BufferedImage img=table.prepareRenderer(render,i,0);
                    ((BufferedImage)img);
                }*/


                //System.out.println(main.getContentPane().getWidth()+"X"+main.getContentPane().getHeight());
                System.out.println(table.getWidth()+"X"+table.getHeight());
            }
        });
    }
}

