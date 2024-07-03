package addedGUI_tbc;

import java.awt.CardLayout;

import javax.swing.JFrame;

public class MainFrame_other extends JFrame {

    private CardLayout card;

    int screenWidth;
    int screenHeight;


    public MainFrame_other(){
        //FlatDarkLaf.setup();
        screenWidth=640;
        screenHeight=360;
        this.setSize(screenWidth,screenHeight);

        this.setTitle("Test Pane");								//JFrame title

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//sets JFrame close operation

        this.card=new CardLayout();								//Creates a new instance of card layout, assigns to card field
        this.setLayout(this.card);								//sets current MainFrame instance layout to the Card field (instance)
        //testPanel = new TestPanel(this);
        this.showSelectionPage();

        this.setVisible(true);									//sets this instance of MainFrame to be visible
    }


    public void showStaffLogin(){
        StaffLogin loginStaff=new StaffLogin(this);
        this.add(loginStaff,"Staff Login");
        this.card.show(this.getContentPane(), "Staff Login");
    }

    public void showSelectionPage(){
        SelectionPage selection=new SelectionPage(this);
        this.add(selection,"Selection Page");
        this.card.show(this.getContentPane(), "Selection Page");
    }

    public void showAdminLogin(){
        AdminLogin loginAdmin=new AdminLogin(this);
        this.add(loginAdmin,"Admin Login");
        this.card.show(this.getContentPane(), "Admin Login");
    }
    public void showManagerDash(){
        ManagerDashboard mang=new ManagerDashboard(this);
        this.add(mang,"mang");
        this.card.show(this.getContentPane(), "mang");
    }


    /*public static void main(String[] args) {
        new MainFrame();
    }*/
}
