//package addedGUI_tbc;

/*
package Controller;

import java.awt.CardLayout;

import javax.swing.JFrame;

import com.formdev.flatlaf.FlatDarkLaf;

import GUI.AdminLogin;
import GUI.ApplicantDescriptionPage;
import GUI.SelectionPage;
import GUI.StaffLogin;
//import GUI.RegUserScren;
import GUI.TestPanel;

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
		this.setSize(screenWidth,screenHeight);
		this.setTitle("Test Pane");								//JFrame title
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//sets JFrame close operation

		this.card=new CardLayout();								//Creates a new instance of card layout, assigns to card field
		this.setLayout(this.card);								//sets current MainFrame instance layout to the Card field (instance)

		this.showSelectionPage();                               //first page
		this.setVisible(true);									//sets this instance of MainFrame to be visible
	}

	//===TESTPANEL(unused)===//
	public void showTestPanel(){
		TestPanel testPanel=new TestPanel(this);
		this.add(testPanel,"Test Panel 1");
		this.card.show(this.getContentPane(), "Test Panel 1");
	}

	//===Staff Login===//
	public void showStaffLogin(){
		StaffLogin loginStaff=new StaffLogin(this);
		this.add(loginStaff,"Staff Login");
		this.card.show(this.getContentPane(), "Staff Login");
	}

	//===Selection Page===//
	public void showSelectionPage(){
		SelectionPage selection=new SelectionPage(this);
		this.add(selection,"Selection Page");
		this.card.show(this.getContentPane(), "Selection Page");
	}

	//===Admin Login===//
	public void showAdminLogin(){
		AdminLogin loginAdmin=new AdminLogin(this);
		this.add(loginAdmin,"Admin Login");
		this.card.show(this.getContentPane(), "Admin Login");
	}

	//===Applicant Description (don't use)===//
	//public void showApplicantDescriptionPage(){
	//	ApplicantDescriptionPage appDesc=new ApplicantDescriptionPage(this);
	//	this.add(appDesc,"Applicant Description");
	//	this.card.show(this.getContentPane(), "Applicant Description");
	//	//this page is unused
	//}

public static void main(String[] args) {
    new MainFrame();
}
}
*/
