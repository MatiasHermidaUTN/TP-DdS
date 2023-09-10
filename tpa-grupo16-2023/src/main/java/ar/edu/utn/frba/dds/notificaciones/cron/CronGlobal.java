package ar.edu.utn.frba.dds.notificaciones.cron;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class CronGlobal {

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

    Trigger trigger = TriggerBuilder.newTrigger()
            .withIdentity("trigger")
            .withSchedule(CronScheduleBuilder.cronSchedule("0 0 * * * ?"))
            .build();

    public void enviarConfigurable() {
        try {
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            System.out.print("Error al ejecutar scheduler");
        }
    }

}
