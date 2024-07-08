package other_GUItester.Controller;

import other_GUItester.Data.Storage;

import javax.swing.*;

public class Controller {
    public Storage stor=new Storage();

    public void preloadApplicant(){
        stor.addApplicantData("John Doe", 		"JDoe8877@gmail.com", 	96716245);
        stor.addApplicantData("Legoshi", 		"Lego1999@hotmail,com", 91827461);
        stor.addApplicantData("Tan Kin Lian", 	"TanKinLian@gmail.com", 81865845);
    }

    public DefaultListModel<JPanel> getModelList() {
        return stor;
    }

    public Controller() {
        // TODO Auto-generated constructor stub
    }

}
