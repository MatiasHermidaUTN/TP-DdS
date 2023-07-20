package ar.edu.utn.frba.dds.notificaciones;

import ar.edu.utn.frba.dds.comunidades.Perfil;
import ar.edu.utn.frba.dds.incidentes.Incidente;

import java.util.List;

public interface Notificador {

    public void mandarNotificacionDeIncidenteNuevo(Incidente incidente, Perfil perfil);

    public void mandarNotificacionDeConclusionDeIncidente(Incidente incidente, Perfil perfil);

    public void mandarResumenDeIncidentes(List<Incidente> incidentesNuevos, List<Incidente> incidentesConcluidos, Perfil perfil);
}
