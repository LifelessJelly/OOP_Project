package data;

import controller.SHA256;


public class Staff {

    public static final int ROOT = 0;
    public static final int MANAGER = 1;
    public static final int HR = 2;

    private final StaffCredentials staffCredentials;
    private final int securityLevel;
    private final String displayName;

    public Staff(String displayName, int securityLevel){
        switch (securityLevel){
            case ROOT:
            case MANAGER:
            case HR:
                break;
            default:
                throw new IllegalArgumentException("Invalid security level");
                //throws error when the specified security level is not met
        }
        this.securityLevel = securityLevel;
        this.displayName = displayName;
        staffCredentials = new StaffCredentials();
    }

    //returns a boolean value specifying if the user passes security
    public boolean authorised(int securityLevelRequired){
        switch (securityLevelRequired){
            case ROOT:
            case MANAGER:
            case HR:
                break;
            default:
                throw new IllegalArgumentException("Invalid security level");
        }
        return securityLevelRequired == this.securityLevel;
    }

    //updates the staff credentials, creating a new String that has the hashed staff credentials
    public void updateCredentials(String username, char[] password){
        staffCredentials.usernameHash = new String(SHA256.getHasherHex().hashString(username));
        staffCredentials.passwordHash = new String(SHA256.getHasherHex().hashString(new String(password)));
    }

    //checks the staff credentials, returns a boolean value that signifies
    // if the hashed username and password is equal to the stored hash
    public boolean checkCredentials(String username, char[] password){
        return (new String(SHA256.getHasherHex().hashString(username)).equals(staffCredentials.usernameHash)
                && new String(SHA256.getHasherHex().hashString(new String(password))).equals(staffCredentials.passwordHash));
    }

    public String getDisplayName(){
        return displayName;
    }

    public boolean checkUsernameExists(String username){
        return new String(SHA256.getHasherHex().hashString(username)).equals(staffCredentials.usernameHash);
    }

    private static class StaffCredentials {
        private String usernameHash;
        private String passwordHash;
    }
}
