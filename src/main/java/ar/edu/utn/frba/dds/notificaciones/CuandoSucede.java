package ar.edu.utn.frba.dds.notificaciones;

import ar.edu.utn.frba.dds.comunidades.Perfil;
import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.incidentes.Incidente;
import lombok.Getter;
import lombok.Setter;

public class CuandoSucede implements ConfiguracionNotificacion{

    @Getter @Setter
    Notificador notificador = null;
    @Getter @Setter
    Usuario usuario = null;

    public CuandoSucede(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void notificarIncidenteNuevo(Incidente incidente) {

        notificador.mandarNotificacionDeIncidenteNuevo(incidente, usuario);

    }

    @Override
    public void notificarConclusionDeIncidente(Incidente incidente) {

        notificador.mandarNotificacionDeConclusionDeIncidente(incidente, usuario);

    }

}
