package ar.edu.utn.frba.dds.notificaciones;

import ar.edu.utn.frba.dds.comunidades.Perfil;
import ar.edu.utn.frba.dds.incidentes.Incidente;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class AdapterMailSender implements Notificador{

    MailSenderJavax mailSender = new MailSenderJavax();

    @Override
    public void mandarNotificacion(Incidente incidente, Perfil perfil) {

        this.mailSender.mandarNotificacion(incidente, perfil);

    }
}
