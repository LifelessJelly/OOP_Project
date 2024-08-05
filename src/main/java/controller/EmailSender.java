package controller;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailSender {

    /**
     * Initializes an EmailSender object with the specified email details and sends an email using SMTP.
     *
     * @param toEmail The recipient's email address.
     * @param subject The subject of the email.
     * @param body The body content of the email.
     */
    public EmailSender(String toEmail, String subject, String body){
        final String fromEmail = RSAEncryptor.decrypt("MADKD5gc1TJOb1WyBwABxc2OqK15yn4+DVxrh5IkLY9n/Y77josQl0uSEUKkNUuK", "e50f6732cf7bfab4fbcd991790a631734a92def836c33fa4083e0daa825edbb1");
        final String password = RSAEncryptor.decrypt("GUaP9vKIS/LmQ2L7mk1DFA==", "e50f6732cf7bfab4fbcd991790a631734a92def836c33fa4083e0daa825edbb1");
        System.out.println("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.office365.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        sendEmail(session, toEmail, fromEmail, subject, body);
    }

    /**
     * Sends an email using the provided JavaMail Session with the specified email details.
     *
     * @param session The JavaMail Session object for sending the email.
     * @param toEmail The recipient's email address.
     * @param fromEmail The sender's email address.
     * @param subject The subject of the email.
     * @param body The body content of the email.
     */
    private static void sendEmail(Session session, String toEmail, String fromEmail, String subject, String body){
        try
        {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(fromEmail, "Operate On Peasants LLC"));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("EMail Sent Successfully!!");
        }
        catch (Exception e) {
            System.out.println("Error sending email");
        }
    }

}
