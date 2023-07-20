package ar.edu.utn.frba.dds.notificaciones;

import ar.edu.utn.frba.dds.comunidades.Perfil;
import ar.edu.utn.frba.dds.incidentes.Incidente;

import java.util.List;

public class AdapterWhatsappSender implements Notificador {

    @Override
    public void mandarNotificacionDeIncidenteNuevo(Incidente incidente, Perfil perfil) {
        //if(perfil.getTipoPerfil() == CUANDO_SUCEDE)

    }

    @Override
    public void mandarNotificacionDeConclusionDeIncidente(Incidente incidente, Perfil perfil) {

    }

    @Override
    public void mandarResumenDeIncidentes(List<Incidente> incidentesNuevos, List<Incidente> incidentesConcluidos, Perfil perfil) {

    }

}
