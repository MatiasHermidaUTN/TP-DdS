package ar.edu.utn.frba.dds.notificaciones;

import ar.edu.utn.frba.dds.comunidades.Perfil;
import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.incidentes.Incidente;

import java.util.List;

public class WhatsappSender implements Notificador {

    @Override
    public Boolean mandarNotificacionDeIncidenteNuevo(Incidente incidente, Usuario usuario) {
        return null;
    }

    @Override
    public Boolean mandarNotificacionDeConclusionDeIncidente(Incidente incidente, Usuario usuario) {
        return null;
    }

    @Override
    public Boolean mandarResumenDeIncidentes(List<Incidente> incidentesNuevos, List<Incidente> incidentesConcluidos, Usuario usuario) {
        return null;
    }

    @Override
    public Boolean mandarNotificacionRevisionDeIncidentesCercano(List<Incidente> incidente, Usuario usuario) {
        return null;
    }
}
