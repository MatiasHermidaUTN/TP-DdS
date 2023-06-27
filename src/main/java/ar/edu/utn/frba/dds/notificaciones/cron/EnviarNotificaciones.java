package ar.edu.utn.frba.dds.notificaciones.cron;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class EnviarNotificaciones implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("notificacion enviada xd");
    }
}
