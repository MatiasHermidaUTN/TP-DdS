package ar.edu.utn.frba.dds.notificaciones.estrategias;

import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.incidentes.Incidente;
import ar.edu.utn.frba.dds.notificaciones.Horario;
import ar.edu.utn.frba.dds.notificaciones.medios.Notificador;
import ar.edu.utn.frba.dds.notificaciones.cron.DiaSemana;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class SinApuros implements ConfiguracionNotificacion{

    @Getter @Setter
    Notificador notificador = null;
    @Getter @Setter
    Usuario usuario = null;

    //List<Cron> crones = new ArrayList<Cron>();
    @Getter
    List<Horario> horarios = new ArrayList<Horario>();
    @Getter
    List<Incidente> incidentesNuevos = new ArrayList<Incidente>();
    @Getter
    List<Incidente> incidentesConcluidos = new ArrayList<Incidente>();

    public SinApuros(Usuario usuario) {
        this.usuario = usuario;
    }

    /*
    public void agregarHorario(DiaSemana diaDeLaSemana, String hora, String minuto){
        Cron cron = new Cron();
        cron.enviarConfigurable(diaDeLaSemana, hora, minuto, this);
        crones.add(cron);
    }
    */

    public void agregarHorario(DiaSemana diaDeLaSemana, Integer hora){
        Horario horario = new Horario(diaDeLaSemana, hora);
        horarios.add(horario);
    }

    @Override
    public void notificarIncidenteNuevo(Incidente incidente) {
        incidentesNuevos.add(incidente);
    }

    @Override
    public void notificarConclusionDeIncidente(Incidente incidente) {
        if(incidentesNuevos.contains(incidente)) {
            incidentesNuevos.remove(incidente);
        } else {
            incidentesConcluidos.add(incidente);
        }
    }

    public void incidentesNotificados() {
        incidentesNuevos.clear();
        incidentesConcluidos.clear();
    }

    @Override
    public void notificarIncidentesCercanos(List<Incidente> incidentesCercanos) {
        notificador.mandarNotificacionRevisionDeIncidentesCercano(incidentesCercanos, usuario);
    }

    @Override
    public void notificarInformeSemanal(String msjInformeSemanal) {
        notificador.mandarNotificacionInformeSemanal(msjInformeSemanal, usuario);
    }
}
