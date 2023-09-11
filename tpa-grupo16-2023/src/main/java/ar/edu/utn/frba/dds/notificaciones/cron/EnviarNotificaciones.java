package ar.edu.utn.frba.dds.notificaciones.cron;

import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.notificaciones.SinApuros;
import ar.edu.utn.frba.dds.repositorios.reposDeprecados.RepoUsuarioDeprecado;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class EnviarNotificaciones  implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        System.out.print(LocalDateTime.now());

        List<Usuario> usuariosSinApuros =
                RepoUsuarioDeprecado.buscarTodos().stream()
                        .filter(u -> u.getConfiguracionNotificacion() instanceof SinApuros)
                        .collect(Collectors.toList());

        List<Usuario> usuariosANotificar =
                usuariosSinApuros.stream()
                        .filter(u -> !(u.getConfiguracionNotificacion().getHorarios().stream()
                                .filter(h -> (h.diaSemanaComoDayOfWeek() == LocalDateTime.now().getDayOfWeek())
                                && (h.getHora() == LocalDateTime.now().getHour()))
                                .collect(Collectors.toList())
                                .isEmpty()))
                        .collect(Collectors.toList());

        List<Usuario> usuariosConNotificacionesPendientes = usuariosANotificar.stream()
                .filter(u -> !(u.getConfiguracionNotificacion().getIncidentesNuevos().isEmpty()
                && u.getConfiguracionNotificacion().getIncidentesConcluidos().isEmpty()))
                .collect(Collectors.toList());

        usuariosConNotificacionesPendientes.stream()
                .forEach(u -> u.getConfiguracionNotificacion().getNotificador()
                .mandarResumenDeIncidentes(
                        u.getConfiguracionNotificacion().getIncidentesNuevos().stream().filter(
                                i -> ChronoUnit.HOURS.between(i.getHorarioApertura(), LocalDateTime.now()) < 24
                        ).collect(Collectors.toList()),
                        u.getConfiguracionNotificacion().getIncidentesConcluidos(),
                        u));
    }
}
