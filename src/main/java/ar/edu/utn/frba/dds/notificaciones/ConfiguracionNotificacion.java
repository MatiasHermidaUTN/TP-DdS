package ar.edu.utn.frba.dds.notificaciones;

import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.incidentes.Incidente;
import ar.edu.utn.frba.dds.notificaciones.cron.DiaSemana;

public interface ConfiguracionNotificacion {

    public void notificarIncidenteNuevo(Incidente incidente);

    public void notificarConclusionDeIncidente(Incidente incidente);

    public Notificador getNotificador();

    public Usuario getUsuario();

    public void setNotificador(Notificador notificador);

    public void setUsuario(Usuario usuario);

    public void agregarHorario(DiaSemana diaDeLaSemana, String hora, String minuto);
}
