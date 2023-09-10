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

    @Getter @Setter
    Notificador notificador = null;
    @Getter @Setter
    Usuario usuario = null;

    public CuandoSucede(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void notificarIncidenteNuevo(Incidente incidente) {
        notificador.mandarNotificacionDeIncidenteNuevo(incidente, usuario);
    }

    @Override
    public void notificarConclusionDeIncidente(Incidente incidente) {
        notificador.mandarNotificacionDeConclusionDeIncidente(incidente, usuario);
    }

    @Override
    public void notificarIncidentesCercanos(List<Incidente> incidentesCercanos) {
        notificador.mandarNotificacionRevisionDeIncidentesCercano(incidentesCercanos, usuario);
    }

    @Override
    public void notificarInformeSemanal(String msjInformeSemanal) {
        notificador.mandarNotificacionInformeSemanal(msjInformeSemanal, usuario);
    }

    @Override
    public void agregarHorario(DiaSemana diaDeLaSemana, Integer hora) {}

    @Override
    public List<Horario> getHorarios() {
        return null;
    }

    @Override
    public List<Incidente> getIncidentesNuevos() {
        return null;
    }

    @Override
    public List<Incidente> getIncidentesConcluidos() {
        return null;
    }

}
