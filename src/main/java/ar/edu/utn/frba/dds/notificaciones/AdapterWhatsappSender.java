package ar.edu.utn.frba.dds.notificaciones;

import ar.edu.utn.frba.dds.comunidades.Perfil;
import ar.edu.utn.frba.dds.incidentes.Incidente;
import lombok.Getter;
import lombok.Setter;


import java.util.List;


@Setter @Getter
public class AdapterWhatsappSender implements Notificador {


    private WhatsappSender whatsappSender;
    @Override
    public Boolean mandarNotificacionDeIncidenteNuevo(Incidente incidente, Perfil perfil) {
        return whatsappSender.mandarNotificacion(incidente, perfil);
    }

    @Override
    public Boolean mandarNotificacionDeConclusionDeIncidente(Incidente incidente, Perfil perfil) {
        return whatsappSender.mandarNotificacion(incidente, perfil);
    }

    @Override
    public Boolean mandarResumenDeIncidentes(List<Incidente> incidentesNuevos, List<Incidente> incidentesConcluidos, Perfil perfil) {
        return true;
    }

}

