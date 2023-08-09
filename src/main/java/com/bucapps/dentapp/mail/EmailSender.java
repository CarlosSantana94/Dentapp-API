package com.bucapps.dentapp.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
public class EmailSender {

    public static void sendEmail(String recipient, String subject, String content) {
        // Tu dirección de correo y contraseña
        final String senderEmail = "cgsp94@gmail.com";
        final String password = "lpqzgijuhgwmuwqf";

        // Configuración de propiedades
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Crear una sesión con autenticación
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, password);
            }
        });

        try {
            // Crear el mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(content);

            // Enviar el mensaje
            Transport.send(message);

            System.out.println("Correo enviado exitosamente.");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Error al enviar el correo: " + e.getMessage());
        }
    }
}
