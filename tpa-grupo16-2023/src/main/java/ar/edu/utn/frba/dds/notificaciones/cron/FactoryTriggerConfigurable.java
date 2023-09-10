package ar.edu.utn.frba.dds.notificaciones.cron;

import org.quartz.CronScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

//Se usa en el Cron, deprecado
public class FactoryTriggerConfigurable {

    public static Trigger crearTriggerConfigurable(DiaSemana diaDeLaSemana, String hora, String minuto) {

        String dia = switch (diaDeLaSemana) {
            case LUNES -> "MON";
            case MARTES -> "TUE";
            case MIERCOLES -> "WED";
            case JUEVES -> "THU";
            case VIERNES -> "FRI";
            case SABADO -> "SAT";
            case DOMINGO -> "SUN";
        };

        return TriggerBuilder.newTrigger()
                .withIdentity("trigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 "+minuto+" "+hora+" ? * "+dia))
                .build();

    }
}
