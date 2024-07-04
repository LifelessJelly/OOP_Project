package Data;

import Subsystems.SHA256;

public class Staff {

    public static final int ROOT = 0;
    public static final int ADMIN = 1;
    public static final int MANAGER = 2;
    public static final int HR = 3;

    private final StaffCredentials staffCredentials;
    private final int securityLevel;

    public Staff(int securityLevel){
        switch (securityLevel){
            case ROOT:
            case MANAGER:
            case HR:
            case ADMIN:
                break;
            default:
                throw new IllegalArgumentException("Invalid security level");
        }
        this.securityLevel = securityLevel;
        staffCredentials = new StaffCredentials();
    }

    public boolean authorised(int securityLevelRequired){
        switch (securityLevelRequired){
            case ROOT:
            case MANAGER:
            case HR:
            case ADMIN:
                break;
            default:
                throw new IllegalArgumentException("Invalid security level");
        }
        return securityLevelRequired >= this.securityLevel;
    }

    //updates the staff credentials, creating a new String that has the hashed staff credentials
    public void updateCredentials(String username, String password){
        staffCredentials.usernameHash = new String(SHA256.getHasherHex().hashString(username));
        staffCredentials.passwordHash = new String(SHA256.getHasherHex().hashString(password));
    }

    //checks the staff credentials, returns a boolean value that signifies
    // if the hashed username and password is equal to the stored hash
    public boolean checkCredentials(String username, String password){
        return (new String(SHA256.getHasherHex().hashString(username)).equals(staffCredentials.usernameHash)
                && new String(SHA256.getHasherHex().hashString(password)).equals(staffCredentials.passwordHash));
    }

    private static class StaffCredentials {
        private String usernameHash;
        private String passwordHash;
    }
}
