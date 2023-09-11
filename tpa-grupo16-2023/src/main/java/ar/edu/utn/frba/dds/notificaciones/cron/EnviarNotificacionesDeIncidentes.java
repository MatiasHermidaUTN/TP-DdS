package ar.edu.utn.frba.dds.notificaciones.cron;

import ar.edu.utn.frba.dds.incidentes.Incidente;
import ar.edu.utn.frba.dds.notificaciones.estrategias.SinApuros;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

//Fue reemplazado por EnviarNotificaciones. Deprecado.
//Además se rompió tdo después de hacer stateless a SinApuros.
public class EnviarNotificacionesDeIncidentes implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {/*

        System.out.print(LocalDateTime.now());

        JobDataMap data = jobExecutionContext.getJobDetail().getJobDataMap();
        SinApuros sinApuros = (SinApuros) data.get("sinApuros");

        List<Incidente> incidentesAbiertosEnLapsoDeVeinticuatroHs = sinApuros.getIncidentesNuevos().stream()
                .filter(i -> ChronoUnit.HOURS.between(i.getHorarioApertura(), LocalDateTime.now()) < 24)
                .collect(Collectors.toList());

        if(!(incidentesAbiertosEnLapsoDeVeinticuatroHs.isEmpty() && sinApuros.getIncidentesConcluidos().isEmpty())) {

            sinApuros.getNotificador().mandarResumenDeIncidentes(
                    incidentesAbiertosEnLapsoDeVeinticuatroHs,
                    sinApuros.getIncidentesConcluidos(),
                    sinApuros.getUsuario());

            sinApuros.incidentesNotificados();

        }*/

    }

}
