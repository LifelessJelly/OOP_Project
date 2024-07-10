package data;

import subsystems.SHA256;

public class Admin {

    //======ACCESS LEVELS======//
    public static final int ROOT = 0;
    public static final int ADMIN = 1;

    private final StaffCredentials staffCredentials;
    private final int securityLevel;
    private final int securityLevelRequired=1;

    //======CHECKS IF ACCESS LEVEL IS RIGHT (admin,root)======//
    public Admin(int securityLevel){
        switch (securityLevel){
            case ROOT:
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
            case ADMIN:
                break;
            default:
                throw new IllegalArgumentException("Invalid security level");
        }
        return securityLevelRequired >= this.securityLevel;
    }

    public void updateCredentials(String username, String password){
        staffCredentials.usernameHash = new String(SHA256.getHasherHex().hashString(username));
        staffCredentials.passwordHash = new String(SHA256.getHasherHex().hashString(password));
    }

    public boolean checkCredentials(String adminUsername, String adminPassword){
        return (new String(SHA256.getHasherHex().hashString(adminUsername)).equals(staffCredentials.usernameHash)
                && new String(SHA256.getHasherHex().hashString(adminPassword)).equals(staffCredentials.passwordHash));
    }

    private static class StaffCredentials {
        private String usernameHash;
        private String passwordHash;
    }
}
