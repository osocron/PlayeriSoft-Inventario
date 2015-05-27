package playeriSoft.controlador;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Created by osocron on 21/05/15.
 * Clase que se encarga de crear correos electrónicos mandarlos pormedio del servidor smtp de google
 */
public class MailSender {

    public void sendMail(String asunto, String mensaje) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("osocron@gmail.com", "");
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("osocron@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("osocron@gmail.com"));
            message.setSubject(asunto);
            message.setText(mensaje);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
