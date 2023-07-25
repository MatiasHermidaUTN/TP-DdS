package ar.edu.utn.frba.dds.notificaciones;

import ar.edu.utn.frba.dds.comunidades.Perfil;
import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.incidentes.Incidente;


import java.util.List;


public class AdapterMailSender implements Notificador {

    MailSenderJavax mailSender = new MailSenderJavax();

    @Override
    public Boolean mandarNotificacionDeIncidenteNuevo(Incidente incidente, Usuario usuario) {
        return mailSender.mandarNotificacionDeIncidenteNuevo(incidente, usuario);
    }

    @Override
    public Boolean mandarNotificacionDeConclusionDeIncidente(Incidente incidente, Usuario usuario) {
        return mailSender.mandarNotificacionDeConclusionDeIncidente(incidente, usuario);
    }

    @Override
    public Boolean mandarResumenDeIncidentes(List<Incidente> incidentesNuevos, List<Incidente> incidentesConcluidos, Usuario usuario) {
        return mailSender.mandarResumenDeIncidentes(incidentesNuevos, incidentesConcluidos, usuario);
    }

    @Override
    public Boolean mandarNotificacionRevisionDeIncidentesCercano(List<Incidente> incidentesCercanos, Usuario usuario) {
        return mailSender.mandarNotificacionRevisionDeIncidentesCercano(incidentesCercanos, usuario);
    }

}
