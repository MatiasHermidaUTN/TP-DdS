package ar.edu.utn.frba.dds.models.converters;

import ar.edu.utn.frba.dds.models.notificaciones.medios.AdapterMailSender;
import ar.edu.utn.frba.dds.models.notificaciones.medios.AdapterWhatsappSender;
import ar.edu.utn.frba.dds.models.notificaciones.medios.Notificador;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class NotificadorConverter implements AttributeConverter<Notificador, String> {
    @Override
    public String convertToDatabaseColumn(Notificador notificador) {
        if(notificador == null)
            return "";
        if(notificador.getClass().getSimpleName().equals("AdapterMailSender"))
            return "mail";
        else if(notificador.getClass().getSimpleName().equals("AdapterWhatsappSender"))
            return "wpp";
        return null;
    }

    @Override
    public Notificador convertToEntityAttribute(String s) {
        if(s.equals("mail"))
            return new AdapterMailSender();
        else if(s.equals("wpp"))
            return new AdapterWhatsappSender();
        return null;
    }
}
