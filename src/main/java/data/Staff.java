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

    /**
     * Updates the credentials for a staff member by hashing the provided username and password.
     *
     * <p>This method takes a username and a password (as a character array), hashes both using
     * the SHA-256 algorithm, and stores the resulting hashes in the {@code staffCredentials} object.</p>
     *
     * @param username the username to be hashed and updated. Must not be null.
     * @param password the password to be hashed and updated. Must not be null or empty.
     * @throws NullPointerException if either the username or password is null.
     */
    public void updateCredentials(String username, char[] password){
        staffCredentials.usernameHash = new String(SHA256.getHasherHex().hashString(username));
        staffCredentials.passwordHash = new String(SHA256.getHasherHex().hashString(new String(password)));
    }

    /**
     * Checks the provided credentials against the stored username and password hashes.
     *
     * <p>This method hashes the given username and password using the SHA-256 algorithm
     * and compares the resulting hashes with the stored values in the {@code staffCredentials} object.</p>
     *
     * @param username the username to be checked. Must not be null.
     * @param password the password to be checked. Must not be null or empty.
     * @return {@code true} if the provided credentials match the stored hashes; {@code false} otherwise.
     * @throws NullPointerException if either the username or password is null.
     */
    public boolean checkCredentials(String username, char[] password){
        return (new String(SHA256.getHasherHex().hashString(username)).equals(staffCredentials.usernameHash)
                && new String(SHA256.getHasherHex().hashString(new String(password))).equals(staffCredentials.passwordHash));
    }

    public String getDisplayName(){
        return displayName;
    }

    /**
     * Checks if the provided username exists by comparing its hashed value
     * with the stored username hash.
     *
     * <p>This method hashes the input username using SHA-256 and compares
     * the resulting hash with the stored username hash in the staff credentials.</p>
     *
     * @param username the username to check for existence. Must not be null.
     * @return {@code true} if the hashed username matches the stored username hash;
     *         {@code false} otherwise.
     */
    public boolean checkUsernameExists(String username){
        return new String(SHA256.getHasherHex().hashString(username)).equals(staffCredentials.usernameHash);
    }

    private static class StaffCredentials {
        private String usernameHash;
        private String passwordHash;
    }
}
