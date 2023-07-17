package ar.edu.utn.frba.dds.notificaciones;

import ar.edu.utn.frba.dds.comunidades.Perfil;
import ar.edu.utn.frba.dds.incidentes.Incidente;

public class AdapterMailSender implements Notificador{

    MailSenderJavax mailSender = new MailSenderJavax();

    @Override
    public Boolean mandarNotificacion(Incidente incidente, Perfil perfil) {

        return mailSender.mandarNotificacion(incidente, perfil);
    }
}
