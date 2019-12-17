package com.musica.bl.Mail;

import com.musica.dl.ConnectionFactory;
import com.musica.dl.LogError;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail {

    public static void sendMail(String recepient, String subject, String text) throws Exception {

        Properties prop = System.getProperties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", ConnectionFactory.getSmptServer()); //optional, defined in SMTPTransport
        prop.put("mail.smtp.port", "587"); // default port 25

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ConnectionFactory.getEmail(),ConnectionFactory.getPasswordEmail());
            }
        });
        Message msg = prepareMessage(session,ConnectionFactory.getEmail(),recepient,subject,text);

        Transport.send(msg);

    }

    private static Message prepareMessage(Session session, String emailFrom, String recepient, String subject, String text) throws Exception {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailFrom));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject(subject);
            message.setText(text);
            return message;
        }catch (Exception e){
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error al mandar mensaje");
        }
    }
}
