package Subsystems;


public class Pointer<T> {
    private T referent;

    public Pointer(T initialValue) {
        referent = initialValue;
    }

    public void set(T newVal) {
        referent = newVal;
    }

    public T get() {
        return referent;
    }
}