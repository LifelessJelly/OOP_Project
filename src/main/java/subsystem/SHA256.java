package subsystem;

public class SHA256 {
    public static Hasher getHasherByte(){
        return new Hasher(false);
    }
    public static Hasher getHasherHex(){
        return new Hasher(true);
    }
}
