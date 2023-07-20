package ar.edu.utn.frba.dds.notificaciones;

import ar.edu.utn.frba.dds.comunidades.Perfil;
import ar.edu.utn.frba.dds.incidentes.Incidente;

import java.util.List;

public class AdapterMailSender implements Notificador{

    MailSenderJavax mailSender = new MailSenderJavax();

    @Override
    public void mandarNotificacionDeIncidenteNuevo(Incidente incidente, Perfil perfil) {

        this.mailSender.mandarNotificacionDeIncidenteNuevo(incidente, perfil);

    }

    @Override
    public void mandarNotificacionDeConclusionDeIncidente(Incidente incidente, Perfil perfil) {

        this.mailSender.mandarNotificacionDeConclusionDeIncidente(incidente, perfil);

    }

    @Override
    public void mandarResumenDeIncidentes(List<Incidente> incidentesNuevos, List<Incidente> incidentesConcluidos, Perfil perfil) {

        this.mailSender.mandarResumenDeIncidentes(incidentesNuevos, incidentesConcluidos, perfil);

    }

}
