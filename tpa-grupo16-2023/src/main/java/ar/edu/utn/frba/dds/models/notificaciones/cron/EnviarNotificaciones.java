package ar.edu.utn.frba.dds.models.notificaciones.cron;

import ar.edu.utn.frba.dds.models.comunidades.Usuario;

import ar.edu.utn.frba.dds.models.repositorios.reposDeprecados.RepoUsuarioDeprecado;
import ar.edu.utn.frba.dds.models.repositorios.RepoUsuario;

import ar.edu.utn.frba.dds.models.notificaciones.estrategias.SinApuros;

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

        RepoUsuario repoUsuario = new RepoUsuario();

        System.out.print(LocalDateTime.now());

        // Solo aquellos usuarios que tengan la configuración SinApuros
        List<Usuario> usuariosSinApuros =
                repoUsuario.buscarTodos().stream()
                        .filter(u -> u.getConfiguracionNotificacion() instanceof SinApuros)
                        .collect(Collectors.toList());

        // Solo aquellos usuarios que tengan un horario que coincida con el actual, tanto día como hora
        List<Usuario> usuariosANotificar =
                usuariosSinApuros.stream()
                        .filter(u -> !(u.getHorarios().stream()
                                .filter(h -> (h.diaSemanaComoDayOfWeek() == LocalDateTime.now().getDayOfWeek())
                                && (h.getHora() == LocalDateTime.now().getHour()))
                                .collect(Collectors.toList())
                                .isEmpty()))
                        .collect(Collectors.toList());

        // Solo aquellos usuarios que tengan notificaciones pendientes
        List<Usuario> usuariosConNotificacionesPendientes = usuariosANotificar.stream()
                .filter(u -> !(u.getIncidentesNuevos().isEmpty()
                && u.getIncidentesConcluidos().isEmpty()))
                .collect(Collectors.toList());

        // Notificar
        usuariosConNotificacionesPendientes.stream()
                .forEach(u -> u.getNotificador()
                .mandarResumenDeIncidentes(
                        u.getIncidentesNuevos().stream().filter(
                                i -> ChronoUnit.HOURS.between(i.getHorarioApertura(), LocalDateTime.now()) < 24
                        ).collect(Collectors.toList()),
                        u.getIncidentesConcluidos(),
                        u));

        // Limpiar los incidentes notificados
        usuariosConNotificacionesPendientes.stream()
                .forEach(u -> u.incidentesNotificados());
    }
}
