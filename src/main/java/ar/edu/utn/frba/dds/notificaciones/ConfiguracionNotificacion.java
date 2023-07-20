package ar.edu.utn.frba.dds.notificaciones;

import ar.edu.utn.frba.dds.incidentes.Incidente;

public interface ConfiguracionNotificacion {

    public void notificarIncidenteNuevo(Incidente incidente);

    public void notificarConclusionDeIncidente(Incidente incidente);

}
