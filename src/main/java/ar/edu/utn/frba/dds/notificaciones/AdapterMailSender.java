package ar.edu.utn.frba.dds.notificaciones;

import ar.edu.utn.frba.dds.comunidades.Perfil;
import ar.edu.utn.frba.dds.incidentes.Incidente;


import java.util.List;


public class AdapterMailSender implements Notificador{

    MailSenderJavax mailSender = new MailSenderJavax();

    @Override
    public Boolean mandarNotificacionDeIncidenteNuevo(Incidente incidente, Perfil perfil) {

        return this.mailSender.mandarNotificacionDeIncidenteNuevo(incidente, perfil);

    }

    @Override
    public Boolean mandarNotificacionDeConclusionDeIncidente(Incidente incidente, Perfil perfil) {

        return this.mailSender.mandarNotificacionDeConclusionDeIncidente(incidente, perfil);

    }

    @Override
    public Boolean mandarResumenDeIncidentes(List<Incidente> incidentesNuevos, List<Incidente> incidentesConcluidos, Perfil perfil) {

        return this.mailSender.mandarResumenDeIncidentes(incidentesNuevos, incidentesConcluidos, perfil);

    }

}
