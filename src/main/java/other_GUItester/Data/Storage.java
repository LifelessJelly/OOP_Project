package other_GUItester.Data;

import java.util.Vector;

public class Storage {

    private Vector<String>HRUsername=new Vector<String>(10,5);
    private Vector<String>HRPasswords=new Vector<String>(10,5);

    private Vector<String>managerUsername=new Vector<String>(10,5);
    private Vector<String>managerPasswords=new Vector<String>(10,5);

    private Vector<String>adminUsername=new Vector<String>(10,5);
    private Vector<String>adminPasswords=new Vector<String>(10,5);

    private Vector<String>applicantName=new Vector<String>(10,5);
    private Vector<String>applicantEmail=new Vector<String>(10,5);
    private Vector<Integer>applicantContact=new Vector<Integer>(10,5);

    public void addUsername(String role, String username) throws Exception{
        if(role=="HR"){this.HRUsername.add(username);}
        else if(role=="manager"){this.managerUsername.add(username);}
        else if(role=="admin"){this.adminUsername.add(username);}
        else{throw new Exception("ermm what the sigma");}
    }

    public void addPassword(String role, String password) throws Exception{
        if(role=="HR"){this.HRPasswords.add(password);}
        else if(role=="manager"){this.managerPasswords.add(password);}
        else if(role=="admin"){this.adminPasswords.add(password);}
        else{throw new Exception("ermm what the sigma");}
    }

    public void addApplicantData(String name,String email,int contact){
        this.applicantName.add(name);
        this.applicantEmail.add(email);
        this.applicantContact.add(contact);
    }

}
