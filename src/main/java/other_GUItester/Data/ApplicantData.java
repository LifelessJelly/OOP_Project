package other_GUItester.Data;

public class ApplicantData {
    public String name;
    public String email;
    public int contact;

    public ApplicantData(String n,String e,int c) {
        this.name=n;
        this.email=e;
        this.contact=c;
    }

    public String getName(){return name;}
    public void setName(String n){this.name=n;}

    public String getEmail(){return email;}
    public void setEmail(String e){this.email=e;}

    public int getContact(){return contact;}
    public void setContact(int c){this.contact=c;}
}
