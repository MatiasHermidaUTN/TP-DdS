package ar.edu.utn.frba.dds.notificaciones.medios;

import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.incidentes.Incidente;
import ar.edu.utn.frba.dds.notificaciones.FactoryNotificacion;
import ar.edu.utn.frba.dds.notificaciones.Notificacion;
import lombok.Getter;
import lombok.Setter;


import java.util.List;


@Setter @Getter
public class AdapterWhatsappSender implements Notificador {
    private WhatsappSender whatsappSender;

    @Override
    public Boolean mandarNotificacionDeIncidenteNuevo(Incidente incidente, Usuario usuario) {
        Notificacion notificacion = FactoryNotificacion.crearNotificacionDeIncidenteNuevo(incidente);
        return whatsappSender.mandarNotificacion(notificacion, usuario.getTelefono());
    }

    @Override
    public Boolean mandarNotificacionDeConclusionDeIncidente(Incidente incidente, Usuario usuario) {
        Notificacion notificacion = FactoryNotificacion.crearNotificacionDeConclusionDeIncidente(incidente);
        return whatsappSender.mandarNotificacion(notificacion, usuario.getTelefono());
    }

    @Override
    public Boolean mandarResumenDeIncidentes(List<Incidente> incidentesNuevos, List<Incidente> incidentesConcluidos, Usuario usuario) {
        Notificacion notificacion = FactoryNotificacion.crearNotificacionDeResumenDeIncidentes(incidentesNuevos, incidentesConcluidos);
        return whatsappSender.mandarNotificacion(notificacion, usuario.getTelefono());
    }

    @Override
    public Boolean mandarNotificacionRevisionDeIncidentesCercano(List<Incidente> incidentesCercanos, Usuario usuario) {
        Notificacion notificacion = FactoryNotificacion.crearNotificacionDeRevisionDeIncidentesCercanos(incidentesCercanos);
        return whatsappSender.mandarNotificacion(notificacion, usuario.getTelefono());
    }

    @Override
    public Boolean mandarNotificacionInformeSemanal(String msjInformeSemanal, Usuario usuario) {
        Notificacion notificacion = FactoryNotificacion.crearNotificacionDeInformeSemanal(msjInformeSemanal);
        return whatsappSender.mandarNotificacion(notificacion, usuario.getTelefono());
    }
}

