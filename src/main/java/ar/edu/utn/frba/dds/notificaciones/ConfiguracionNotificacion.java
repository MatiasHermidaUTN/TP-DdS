package ar.edu.utn.frba.dds.notificaciones;

import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.incidentes.Incidente;
import ar.edu.utn.frba.dds.notificaciones.cron.DiaSemana;
import ar.edu.utn.frba.dds.ranking.InformeSemanal;

import java.util.List;

public interface ConfiguracionNotificacion {
    public Notificador getNotificador();
    public void setNotificador(Notificador notificador);
    public Usuario getUsuario();
    public void setUsuario(Usuario usuario);

    //Estos son los metodos para notificar:
    public void notificarIncidenteNuevo(Incidente incidente);

    public void notificarConclusionDeIncidente(Incidente incidente);

    public void agregarHorario(DiaSemana diaDeLaSemana, String hora, String minuto);

    public void notificarIncidentesCercanos(List<Incidente> incidentesCercanos);

    public void notificarInformeSemanal(String msjInformeSemanal);
}
