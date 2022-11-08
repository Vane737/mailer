package datos;

import java.io.*;
import java.net.*;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SMTPenviar {

    public void sendMail(String destinatario, String res) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "www.tecnoweb.org.bo");
        properties.put("mail.smtp.port", 25);
        properties.put("mail.smtp.auth", "false");
        properties.put("mail.smtp.starttls.enable", "false");
        boolean status = false;

        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("grupo09sa@tecnoweb.org.bo", "grup009grup009");
            }
        };

        Session session = Session.getInstance(properties, auth);
        try {
            BodyPart text = new MimeBodyPart();
            text.setContent(res, "text/html");

            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(text);

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("grupo09sa@tecnoweb.org.bo"));
            InternetAddress[] toAddresses = {new InternetAddress(destinatario)};
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject("Repuesta del Servidor");
            msg.setSentDate(new Date());
            //msg.setContent(res, "text/html");
            msg.setContent(multipart);

            //System.out.println(message);
            Transport.send(msg);
            status = true;
            System.out.println("Mensaje enviado con exito mediante SMTP a: " + destinatario);
        } catch (MessagingException e) {
            System.out.println("Error en envio de mensaje mediante SMTP : " + e.getMessage());
            e.printStackTrace();
        }
    }

    static protected String getMultiline(BufferedReader in) throws IOException {
        String lines = "";
        while (true) {
            String line = in.readLine();
            if (line == null) {
                // Server closed connection
                throw new IOException(" S : Server unawares closed the connection.");
            }
            if (line.charAt(3) == ' ') {
                lines = lines + "\n" + line;
                // No more lines in the server response
                break;
            }
            // Add read line to the list of lines
            lines = lines + "\n" + line;
        }
        return lines;
    }
}
