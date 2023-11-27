package ar.edu.utn.frba.dds.models.notificaciones.medios;

import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import ar.edu.utn.frba.dds.models.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.notificaciones.FactoryNotificacion;
import ar.edu.utn.frba.dds.models.notificaciones.Notificacion;


import java.util.List;


public class AdapterMailSender implements Notificador {

    MailSenderJavax mailSender = new MailSenderJavax();

    @Override
    public Boolean porMail() {
        return true;
    }

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
        Notificacion notificacion = FactoryNotificacion.crearNotificacionDeRevisionDeIncidentesCercanos(incidentesCercanos);
        return mailSender.mandarNotificacion(notificacion, usuario.getEmail());
    }

    @Override
    public Boolean mandarNotificacionInformeSemanal(String msjInformeSemanal, Usuario usuario) {
        Notificacion notificacion = FactoryNotificacion.crearNotificacionDeInformeSemanal(msjInformeSemanal);
        return mailSender.mandarNotificacion(notificacion, usuario.getEmail());
    }

}
