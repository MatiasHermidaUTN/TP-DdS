package ar.edu.utn.frba.dds.models.notificaciones.estrategias;

import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import ar.edu.utn.frba.dds.models.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.notificaciones.Horario;
import ar.edu.utn.frba.dds.models.notificaciones.cron.DiaSemana;

import java.util.List;

public class SinApuros implements ConfiguracionNotificacion{


    public SinApuros() {

    }

    /* deprecado
    public void agregarHorario(DiaSemana diaDeLaSemana, String hora, String minuto){
        Cron cron = new Cron();
        cron.enviarConfigurable(diaDeLaSemana, hora, minuto, this);
        crones.add(cron);
    }*/

    public void agregarHorario(DiaSemana diaDeLaSemana, Integer hora, Usuario usuario){
        Horario horario = new Horario(diaDeLaSemana, hora);
        usuario.agregarHorario(horario);
    }

    @Override
    public void notificarIncidenteNuevo(Incidente incidente, Usuario usuario) {
        usuario.agregarIncidenteNuevo(incidente);
    }

    @Override
    public void notificarConclusionDeIncidente(Incidente incidente, Usuario usuario) {
        if(usuario.getIncidentesNuevos().contains(incidente)) {
            usuario.removerIncidenteNuevo(incidente);
        } else {
            usuario.agregarIncidenteConcluido(incidente);
        }
    }

    @Override
    public void notificarIncidentesCercanos(List<Incidente> incidentesCercanos, Usuario usuario) {
        usuario.getNotificador().mandarNotificacionRevisionDeIncidentesCercano(incidentesCercanos, usuario);
    }

    @Override
    public void notificarInformeSemanal(String msjInformeSemanal, Usuario usuario) {
        usuario.getNotificador().mandarNotificacionInformeSemanal(msjInformeSemanal, usuario);
    }
}
