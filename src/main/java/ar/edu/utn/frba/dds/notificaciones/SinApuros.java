package ar.edu.utn.frba.dds.notificaciones;

import ar.edu.utn.frba.dds.comunidades.Perfil;
import ar.edu.utn.frba.dds.incidentes.Incidente;
import ar.edu.utn.frba.dds.notificaciones.cron.Cron;
import ar.edu.utn.frba.dds.notificaciones.cron.DiaSemana;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class SinApuros implements ConfiguracionNotificacion{

    @Getter @Setter
    Notificador notificador = null;
    @Getter @Setter
    Perfil perfil = null;

    List<Cron> crones = new ArrayList<Cron>();
    @Getter
    List<Incidente> incidentesNuevos = new ArrayList<Incidente>();
    @Getter
    List<Incidente> incidentesConcluidos = new ArrayList<Incidente>();

    public void agregarHorario(DiaSemana diaDeLaSemana, String hora, String minuto){
        Cron cron = new Cron();
        cron.enviarConfigurable(diaDeLaSemana, hora, minuto, this);
        crones.add(cron);
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
        }
    }

    public void incidentesNotificados() {

        incidentesNuevos.clear();
        incidentesConcluidos.clear();

    }

}
