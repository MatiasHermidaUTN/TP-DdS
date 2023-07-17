package ar.edu.utn.frba.dds.notificaciones;

import ar.edu.utn.frba.dds.comunidades.Perfil;
import ar.edu.utn.frba.dds.incidentes.Incidente;

public interface Notificador {
    public Boolean mandarNotificacion(Incidente incidente, Perfil perfil);
}
