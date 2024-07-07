package other_GUItester.Data;
import java.util.Vector;
public class AdminData {
    private String adminUsername;
    private String adminPassword;

    public AdminData(String adminUsername, String adminPassword) {
        this.adminUsername=adminUsername;
        this.adminPassword=adminPassword;
    }

    public AdminData() {
        this.adminUsername=null;
        this.adminPassword=null;
    }

    private void setAdminUsername(String adminUsername)	{this.adminUsername=adminUsername;}
    private String getAdminUsername()					{return adminUsername;}
    private void setAdminPassword(String adminPassword)	{this.adminPassword=adminPassword;}
    private String getAdminPassword()					{return adminPassword;}
}