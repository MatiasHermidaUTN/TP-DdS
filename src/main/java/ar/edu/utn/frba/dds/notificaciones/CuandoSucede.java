package ar.edu.utn.frba.dds.notificaciones;

import ar.edu.utn.frba.dds.comunidades.Perfil;
import ar.edu.utn.frba.dds.incidentes.Incidente;
import lombok.Getter;
import lombok.Setter;

public class CuandoSucede implements ConfiguracionNotificacion{

    @Getter @Setter
    Notificador notificador = null;
    @Getter @Setter
    Perfil perfil = null;

    @Override
    public void notificarIncidenteNuevo(Incidente incidente) {

        notificador.mandarNotificacionDeIncidenteNuevo(incidente, perfil);

    }

    @Override
    public void notificarConclusionDeIncidente(Incidente incidente) {

        notificador.mandarNotificacionDeConclusionDeIncidente(incidente, perfil);

    }

}
