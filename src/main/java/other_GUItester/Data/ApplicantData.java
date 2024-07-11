package data;

public class ApplicantData {
    public int index;
    public String name;
    public String email;
    public int contact;

    public ApplicantData(int i, String n,String e,int c) {
        this.index=i;
        this.name=n;
        this.email=e;
        this.contact=c;
    }

    public int getIndex(){return index;}
    public void setIndex(int i){this.index=i;}

    public String getName(){return name;}
    public void setName(String n){this.name=n;}

    public String getEmail(){return email;}
    public void setEmail(String e){this.email=e;}

    public int getContact(){return contact;}
    public void setContact(int c){this.contact=c;}
}
