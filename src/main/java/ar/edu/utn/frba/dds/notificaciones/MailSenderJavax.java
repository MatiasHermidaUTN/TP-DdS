package ar.edu.utn.frba.dds.notificaciones;

import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.incidentes.Incidente;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class MailSenderJavax implements Notificador {

    @Override
    public Boolean mandarNotificacionDeIncidenteNuevo(Incidente incidente, Usuario usuario) {


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
                + " ahora no se encuentra disponible. Origen: " + incidente.getNombreComunidad();

        try {
            email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(usuario.getEmail(), true));
            email.setSubject("Incidente en " + incidente.getEstablecimiento().getNombre());
            email.setText(_body, "utf-8", "html");
            System.out.print("Mandando email\n");
            Transport.send(email);
            System.out.print("Email enviado\n");
            return Boolean.TRUE;
        } catch (MessagingException e) {
            System.out.print("Error: " + e);
            return Boolean.FALSE;
        }

    }

    @Override
    public Boolean mandarNotificacionDeConclusionDeIncidente(Incidente incidente, Usuario usuario) {

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
        String _body = "El incidente con el/la " + incidente.getServicio().getNombre()
                + " de " + incidente.getEstablecimiento().getNombre() + " ya fue resuelto. El/la " + incidente.getServicio().getNombre()
                + " ahora se encuentra disponible. Origen: " + incidente.getNombreComunidad();

        try {
            email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(usuario.getEmail(), true));
            email.setSubject("Resolución de incidente en " + incidente.getEstablecimiento().getNombre());
            email.setText(_body, "utf-8", "html");
            System.out.print("Mandando email");
            Transport.send(email);
            System.out.print("Email enviado");
            return Boolean.TRUE;
        } catch (MessagingException e) {
            System.out.print("Error: " + e);
            return Boolean.FALSE;
        }

    }

    @Override
    public Boolean mandarResumenDeIncidentes(List<Incidente> incidentesNuevos, List<Incidente> incidentesConcluidos, Usuario usuario) {

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
        StringBuilder str = new StringBuilder();
        str.append("Ocurrieron los siguientes incidentes:<ul>");
        if(!incidentesNuevos.isEmpty()) {
            incidentesNuevos.stream().forEach(i -> str.append("<li>El/la " + i.getServicio().getNombre()
                    + " de " + i.getEstablecimiento().getNombre() + " no se encuentra disponible. Origen: "+ i.getNombreComunidad() + "</li>"));
        }
        if(!incidentesConcluidos.isEmpty()) {
            incidentesConcluidos.stream().forEach(i -> str.append("<li>El/la " + i.getServicio().getNombre()
                    + " de " + i.getEstablecimiento().getNombre() + " ya se encuentra disponible. Origen: "+ i.getNombreComunidad() + "</li>"));
        }
        str.append("</ul>Eso es todo. Lo mantendremos informado en sus horarios elegidos.");
        String _body = str.toString();

        try {
            email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(usuario.getEmail(), true));
            email.setSubject("Incidentes");
            email.setText(_body, "utf-8", "html");
            System.out.print("Mandando email");
            Transport.send(email);
            System.out.print("Email enviado");
            return Boolean.TRUE;
        } catch (MessagingException e) {
            System.out.print("Error: " + e);
            return Boolean.FALSE;
        }

    }

    @Override
    public Boolean mandarNotificacionRevisionDeIncidentesCercano(List<Incidente> incidentes, Usuario usuario) {

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ddstpa2023@gmail.com", "hwxklytiqfhzftwe");
            }
        });

        MimeMessage email = new MimeMessage(session);
        StringBuilder str = new StringBuilder();
        str.append("Ocurrieron los siguientes incidentes:<ul>");
        if(!incidentes.isEmpty()) {
            incidentes.stream().forEach(i -> str.append("<li>El/la " + i.getServicio().getNombre()
                    + " de " + i.getEstablecimiento().getNombre() + " presenta un incidente y se encuentra en sus cercanias."
                    + " Le sugerimos que se acerce a revisar el estado del mismo. Origen: "+ i.getNombreComunidad() + "</li>"));
        }
        str.append("</ul>Eso es todo. Muchas Gracias.");
        String _body = str.toString();

        try {
            email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(usuario.getEmail(), true));
            email.setSubject("Incidentes");
            email.setText(_body, "utf-8", "html");
            System.out.print("Mandando email");
            Transport.send(email);
            System.out.print("Email enviado");
            return Boolean.TRUE;
        } catch (MessagingException e) {
            System.out.print("Error: " + e);
            return Boolean.FALSE;
        }
    }
}
