package ar.edu.utn.frba.dds.notificaciones;

import ar.edu.utn.frba.dds.comunidades.Perfil;
import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.incidentes.Incidente;


import java.util.List;


public class AdapterMailSender implements Notificador {

    MailSenderJavax mailSender = new MailSenderJavax();

    @Override
    public Boolean mandarNotificacionDeIncidenteNuevo(Incidente incidente, Usuario usuario) {
        Notificacion notificacion = FactoryNotificacion.crearNotificacionDeIncidenteNuevo(incidente);
        return mailSender.mandarNotificacion(notificacion, usuario.getEmail());
    }

    @Override
    public Boolean mandarNotificacionDeConclusionDeIncidente(Incidente incidente, Usuario usuario) {
        Notificacion notificacion = FactoryNotificacion.crearNotificacionDeConclusionDeIncidente(incidente);
        return mailSender.mandarNotificacion(notificacion, usuario.getEmail());
    }

    @Override
    public Boolean mandarResumenDeIncidentes(List<Incidente> incidentesNuevos, List<Incidente> incidentesConcluidos, Usuario usuario) {
        Notificacion notificacion = FactoryNotificacion.crearNotificacionDeResumenDeIncidentes(incidentesNuevos, incidentesConcluidos);
        return mailSender.mandarNotificacion(notificacion, usuario.getEmail());
    }

    @Override
    public Boolean mandarNotificacionRevisionDeIncidentesCercano(List<Incidente> incidentesCercanos, Usuario usuario) {
        return mailSender.mandarNotificacionRevisionDeIncidentesCercano(incidentesCercanos, usuario);
    }

    @Override
    public Boolean mandarNotificacionInformeSemanal(String msjInformeSemanal, Usuario usuario) {
        return mailSender.mandarNotificacionInformeSemanal(msjInformeSemanal, usuario);
    }

}
