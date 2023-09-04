package ar.edu.utn.frba.dds.notificaciones.cron;

import ar.edu.utn.frba.dds.notificaciones.SinApuros;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class Cron {
    private SchedulerFactory crearScheduler = new StdSchedulerFactory();
    private Scheduler scheduler;

    {
        try {
            scheduler = crearScheduler.getScheduler();
        } catch (SchedulerException e) {
            System.out.print("Error al crear scheduler");
        }
    }

    JobDetail job = JobBuilder.newJob(EnviarNotificacionesDeIncidentes.class)
            .withIdentity("enviarNotificacion")
            .build();

    public void enviarConfigurable(DiaSemana diaDeLaSemana, String hora, String minuto, SinApuros sinApuros) {
        try {
            job.getJobDataMap().put("sinApuros", sinApuros);
            scheduler.start();
            scheduler.scheduleJob(job, FactoryTriggerConfigurable.crearTriggerConfigurable(diaDeLaSemana, hora, minuto));
        } catch (SchedulerException e) {
            System.out.print("Error al ejecutar scheduler");
        }
    }

}
