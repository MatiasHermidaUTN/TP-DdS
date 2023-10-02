package ar.edu.utn.frba.dds.models.notificaciones.medios;

import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import ar.edu.utn.frba.dds.models.incidentes.Incidente;

import java.util.List;

public interface Notificador {

    public Boolean mandarNotificacionDeIncidenteNuevo(Incidente incidente, Usuario usuario);

    public Boolean mandarNotificacionDeConclusionDeIncidente(Incidente incidente, Usuario usuario);

    public Boolean mandarResumenDeIncidentes(List<Incidente> incidentesNuevos, List<Incidente> incidentesConcluidos, Usuario usuario);

    public Boolean mandarNotificacionRevisionDeIncidentesCercano(List<Incidente> incidentesCercanos, Usuario usuario);

    public Boolean mandarNotificacionInformeSemanal(String msjInformeSemanal, Usuario usuario);
}
