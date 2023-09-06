package ar.edu.utn.frba.dds.notificaciones;

import ar.edu.utn.frba.dds.incidentes.Incidente;

import java.util.List;

public class FactoryNotificacion {

    public static Notificacion crearNotificacionDeIncidenteNuevo(Incidente incidente) {

        String titulo = "Incidente en " + incidente.getEstablecimiento().getNombre();
        String cuerpo = "Ocurrió un incidente con el/la " + incidente.getServicio().getNombre()
                + " de " + incidente.getEstablecimiento().getNombre() + ". El/la " + incidente.getServicio().getNombre()
                + " ahora no se encuentra disponible. Origen: " + incidente.getNombreComunidad();

        return new Notificacion(titulo, cuerpo);
    }

    public static Notificacion crearNotificacionDeConclusionDeIncidente(Incidente incidente) {

        String titulo = "Resolución de incidente en " + incidente.getEstablecimiento().getNombre();
        String cuerpo = "El incidente con el/la " + incidente.getServicio().getNombre()
                + " de " + incidente.getEstablecimiento().getNombre() + " ya fue resuelto. El/la " + incidente.getServicio().getNombre()
                + " ahora se encuentra disponible. Origen: " + incidente.getNombreComunidad();

        return new Notificacion(titulo, cuerpo);
    }

    public static Notificacion crearNotificacionDeResumenDeIncidentes(List<Incidente> incidentesNuevos, List<Incidente> incidentesConcluidos) {

        String titulo = "Incidentes";
        StringBuilder str = new StringBuilder();
        str.append("Ocurrieron los siguientes incidentes:<ul>");
        if(!incidentesNuevos.isEmpty()) {
            incidentesNuevos.stream().forEach(i -> str.append("<li>El/la " + i.getServicio().getNombre()
                    + " de " + i.getEstablecimiento().getNombre() + " no se encuentra disponible. Origen: "+ i.getNombreComunidad() + "</li>"));
        }
        if(!incidentesConcluidos.isEmpty()) {
            incidentesConcluidos.stream().forEach(i -> str.append("<li>El/la " + i.getServicio().getNombre()
                    + " de " + i.getEstablecimiento().getNombre() + " ya se encuentra disponible. Origen: "+ i.getNombreComunidad() + "</li>"));
        }
        str.append("</ul>Eso es todo. Lo mantendremos informado en sus horarios elegidos.");
        String cuerpo = str.toString();

        return new Notificacion(titulo, cuerpo);
    }

    public static Notificacion crearNotificacionDeRevisionDeIncidentesCercanos(List<Incidente> incidentes) {

        String titulo = "Incidentes cercanos";
        StringBuilder str = new StringBuilder();
        str.append("Los siguientes incidentes se encuentran en su radio de proximidad:<ul>");
        if(!incidentes.isEmpty()) {
            incidentes.stream().forEach(i -> str.append("<li>El/la " + i.getServicio().getNombre()
                    + " de " + i.getEstablecimiento().getNombre() + " presenta un incidente y se encuentra en sus cercanias."
                    + " Le sugerimos que se acerce a revisar el estado del mismo. Origen: "+ i.getNombreComunidad() + "</li>"));
        }
        str.append("</ul>Eso es todo. Muchas Gracias.");
        String cuerpo = str.toString();

        return new Notificacion(titulo, cuerpo);
    }

    public static Notificacion crearNotificacionDeInformeSemanal(String msjInformeSemanal) {

        String titulo = "Informe semanal";
        String cuerpo = msjInformeSemanal;

        return new Notificacion(titulo, cuerpo);
    }

}
