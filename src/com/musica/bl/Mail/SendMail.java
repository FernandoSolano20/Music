package com.musica.bl.Mail;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail {
    private static final String EMAIL_FROM = "lsolanom1@ucenfotec.ac.cr";
    private static final String PASSWORD = "PillaSolano2015";
    private static final String SMTP_SERVER = "smtp.gmail.com";

    public static void sendMail(String recepient, String subject, String text) throws javax.mail.MessagingException {

        Properties prop = System.getProperties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", SMTP_SERVER); //optional, defined in SMTPTransport
        prop.put("mail.smtp.port", "587"); // default port 25

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_FROM,PASSWORD);
            }
        });
        Message msg = prepareMessage(session,EMAIL_FROM,recepient,subject,text);

        Transport.send(msg);

    }

    private static Message prepareMessage(Session session, String emailFrom, String recepient, String subject, String text) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailFrom));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject(subject);
            message.setText(text);
            return message;
        }catch (Exception e){

        }
        return null;
    }
}
