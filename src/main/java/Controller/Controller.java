package Controller;

public class Controller {
    private final ApplicantRegistrator registrator;

    public Controller() {
        registrator = new ApplicantRegistrator();
    }

    public ApplicantRegistrator getRegistrator() {
        return registrator;
    }
}
