package ar.edu.utn.frba.dds.notificaciones.estrategias;

import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.incidentes.Incidente;
import ar.edu.utn.frba.dds.notificaciones.Horario;
import ar.edu.utn.frba.dds.notificaciones.medios.Notificador;
import ar.edu.utn.frba.dds.notificaciones.cron.DiaSemana;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CuandoSucede implements ConfiguracionNotificacion{


    public CuandoSucede() {

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
