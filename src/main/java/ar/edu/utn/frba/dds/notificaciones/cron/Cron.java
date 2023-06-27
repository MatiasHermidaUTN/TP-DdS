package ar.edu.utn.frba.dds.notificaciones.cron;

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

    JobDetail job = JobBuilder.newJob(EnviarNotificaciones.class)
            .withIdentity("enviarNotificacion")
            .build();

    Trigger triggerD = TriggerBuilder.newTrigger()
            .withIdentity("trigger")
            .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
            .build();

    public void enviar() {
        try {
            scheduler.start();
            scheduler.scheduleJob(job, triggerD);
        } catch (SchedulerException e) {
            System.out.print("Error al ejecutar scheduler");
        }
    }
}
