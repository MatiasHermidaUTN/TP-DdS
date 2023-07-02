package ar.edu.utn.frba.dds.notificaciones;

import ar.edu.utn.frba.dds.comunidades.Perfil;
import ar.edu.utn.frba.dds.incidentes.Incidente;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSenderJavax {

    public void mandarNotificacion(Incidente incidente, Perfil perfil) {

        Properties properties = new Properties();
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ddstpa2023@gmail.com", "hwxklytiqfhzftwe");
            }
        });

        MimeMessage email = new MimeMessage(session);
        String _body = "Ocurrió un incidente con el/la " + incidente.getServicio().getNombre()
                + " de " + incidente.getEstablecimiento().getNombre() + ". El/la " + incidente.getServicio().getNombre()
                + " ahora no se encuentra disponible.";
        try {
            email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(perfil.getUsuario().getEmail(), true));
            email.setSubject("Incidente en " + incidente.getEstablecimiento().getNombre());
            email.setText(_body, "utf-8", "html");
            System.out.print("Mandando email");
            Transport.send(email);
            System.out.print("Email enviado");
        } catch (MessagingException e) {
            System.out.print("Error: " + e);
        }

    }

}
