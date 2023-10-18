package ar.edu.utn.frba.dds.models.notificaciones.estrategias;

import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import ar.edu.utn.frba.dds.models.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.notificaciones.cron.DiaSemana;

import java.util.List;

public class CuandoSucede implements ConfiguracionNotificacion{

    public CuandoSucede() {

    }

    @Override
    public Boolean apuro() {
        return true;
    }
    @Override
    public void notificarIncidenteNuevo(Incidente incidente, Usuario usuario) {
        usuario.getNotificador().mandarNotificacionDeIncidenteNuevo(incidente, usuario);
    }

    @Override
    public void notificarConclusionDeIncidente(Incidente incidente, Usuario usuario) {
        usuario.getNotificador().mandarNotificacionDeConclusionDeIncidente(incidente, usuario);
    }

    @Override
    public void notificarIncidentesCercanos(List<Incidente> incidentesCercanos, Usuario usuario) {
        usuario.getNotificador().mandarNotificacionRevisionDeIncidentesCercano(incidentesCercanos, usuario);
    }

    @Override
    public void notificarInformeSemanal(String msjInformeSemanal, Usuario usuario) {
        usuario.getNotificador().mandarNotificacionInformeSemanal(msjInformeSemanal, usuario);
    }

    @Override
    public void agregarHorario(DiaSemana diaDeLaSemana, Integer hora, Usuario usuario) {}

}
