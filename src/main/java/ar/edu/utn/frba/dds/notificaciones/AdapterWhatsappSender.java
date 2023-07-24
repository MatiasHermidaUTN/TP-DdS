package ar.edu.utn.frba.dds.notificaciones;

import ar.edu.utn.frba.dds.comunidades.Perfil;
import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.incidentes.Incidente;
import lombok.Getter;
import lombok.Setter;


import java.util.List;


@Setter @Getter
public class AdapterWhatsappSender implements Notificador {
    private WhatsappSender whatsappSender;

    @Override
    public Boolean mandarNotificacionDeIncidenteNuevo(Incidente incidente, Usuario usuario) {
        return whatsappSender.mandarNotificacionDeIncidenteNuevo(incidente, usuario);
    }

    @Override
    public Boolean mandarNotificacionDeConclusionDeIncidente(Incidente incidente, Usuario usuario) {
        return whatsappSender.mandarNotificacionDeConclusionDeIncidente(incidente, usuario);
    }

    @Override
    public Boolean mandarResumenDeIncidentes(List<Incidente> incidentesNuevos, List<Incidente> incidentesConcluidos, Usuario usuario) {
        return whatsappSender.mandarResumenDeIncidentes(incidentesNuevos, incidentesConcluidos, usuario);
    }

    @Override
    public Boolean mandarNotificacionRevisionDeIncidentesCercano(List<Incidente> incidente, Usuario usuario) {
        return whatsappSender.mandarNotificacionRevisionDeIncidentesCercano(incidente, usuario);
    }
}

