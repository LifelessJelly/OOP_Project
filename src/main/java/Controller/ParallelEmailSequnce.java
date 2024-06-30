package Controller;

public class ParallelEmailSequnce implements Runnable{
    private final String toEmail;
    private final String subject;
    private final String body;

    public ParallelEmailSequnce(String toEmail, String subject, String body) {
        this.toEmail = toEmail;
        this.subject = subject;
        this.body = body;
    }

    @Override
    public void run() {
        new EmailSender(toEmail, subject, body);
    }
}
