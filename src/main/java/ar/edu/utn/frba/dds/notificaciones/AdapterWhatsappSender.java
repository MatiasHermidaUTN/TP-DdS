package ar.edu.utn.frba.dds.notificaciones;

import ar.edu.utn.frba.dds.comunidades.Perfil;
import ar.edu.utn.frba.dds.incidentes.Incidente;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class AdapterWhatsappSender implements Notificador {

    private WhatsappSender whatsappSender;
    @Override
    public Boolean mandarNotificacion(Incidente incidente, Perfil perfil) {
        return whatsappSender.mandarNotificacion(incidente, perfil);
    }
}

