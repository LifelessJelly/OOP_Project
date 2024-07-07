package other_GUItester.Controller;


import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import GUI;

import com.formdev.flatlaf.FlatDarkLaf;

import GUI.AdminLogin;
import GUI.ApplicantDescriptionPage;
import GUI.ManagerDashboard;
import GUI.ManagerDashboard_ex;
import GUI.SelectionPage;
import GUI.StaffDashboard;
import GUI.StaffDisplayPage;
import GUI.StaffLogin;
//import GUI.RegUserScren;
import GUI.TestPanel;
import GUI.option1test;

public class MainFrame extends JFrame {

    private CardLayout card;
    //public Controller controller;
    public TestPanel testPanel;

    int screenWidth;
    int screenHeight;


    public MainFrame(){
        FlatDarkLaf.setup();
        screenWidth=640;
        screenHeight=360;
        //this.setSize(screenWidth,screenHeight);
        this.setMinimumSize(new Dimension(screenWidth,screenHeight));
        this.setTitle("Test Pane");								//JFrame title

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//sets JFrame close operation

        this.card=new CardLayout();								//Creates a new instance of card layout, assigns to card field
        this.setLayout(this.card);								//sets current MainFrame instance layout to the Card field (instance)
        //testPanel = new TestPanel(this);
        this.showSelectionPage();

        this.setVisible(true);									//sets this instance of MainFrame to be visible
    }

    public void showTestPanel(){
        TestPanel testPanel=new TestPanel(this);
        this.add(testPanel,"Test Panel 1");
        this.card.show(this.getContentPane(), "Test Panel 1");
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

    public void showApplicantDescriptionPage(){
        ApplicantDescriptionPage appDesc=new ApplicantDescriptionPage(this);
        this.add(appDesc,"Applicant Description");
        this.card.show(this.getContentPane(), "Applicant Description");
    }

    public void showStaffDisplayPage(){
        StaffDisplayPage staffDisp=new StaffDisplayPage(this);
        this.add(staffDisp,"Staff Display");
        this.card.show(this.getContentPane(), "Staff Display");
    }
    public void showManagerDash(){
        ManagerDashboard mang=new ManagerDashboard(this);
        this.add(mang,"mang");
        this.card.show(this.getContentPane(), "mang");
    }
    public void experimental(){
        ManagerDashboard_ex mang=new ManagerDashboard_ex(this);
        this.add(mang,"mang");
        this.card.show(this.getContentPane(), "mang");
    }
    public void showStaffDash(){
        StaffDashboard s=new StaffDashboard(this);
        this.add(s,"staff");
        this.card.show(this.getContentPane(), "staff");
    }
    public void showOption(){
        option1test wtf= new option1test(this);
        this.add(wtf,"gg");
        this.card.show(this.getContentPane(),"gg");
    }

	/*public void getScreenSize(){
		screenWidth=this.getContentPane().getWidth();
		screenHeight=this.getContentPane().getHeight()
	}*/

    /*public static void main(String[] args) {
        new MainFrame();
    }*/
}