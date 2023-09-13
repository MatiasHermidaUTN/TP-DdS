package ar.edu.utn.frba.dds.converters;

import ar.edu.utn.frba.dds.notificaciones.estrategias.ConfiguracionNotificacion;
import ar.edu.utn.frba.dds.notificaciones.estrategias.CuandoSucede;
import ar.edu.utn.frba.dds.notificaciones.estrategias.SinApuros;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ConfiguracionNotificacionConverter implements AttributeConverter<ConfiguracionNotificacion, String> {
    @Override
    public String convertToDatabaseColumn(ConfiguracionNotificacion configuracionNotificacion) {
        return configuracionNotificacion.getClass().getSimpleName();
    }

    @Override
    public ConfiguracionNotificacion convertToEntityAttribute(String s) {
        if(s.equals("CuandoSucede"))
            return new CuandoSucede();
        else if(s.equals("SinApuros"))
            return new SinApuros();
        return null;
    }
}
