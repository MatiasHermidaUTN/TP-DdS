package ar.edu.utn.frba.dds.notificaciones;

import ar.edu.utn.frba.dds.comunidades.Perfil;
import ar.edu.utn.frba.dds.incidentes.Incidente;

public class AdapterWhatsappSender implements Notificador {
    @Override
    public void mandarNotificacion(Incidente incidente, Perfil perfil) {
        //if(perfil.getTipoPerfil() == CUANDO_SUCEDE)

    }
}
