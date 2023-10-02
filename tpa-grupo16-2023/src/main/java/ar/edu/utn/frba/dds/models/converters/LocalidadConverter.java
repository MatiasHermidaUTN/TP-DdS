package ar.edu.utn.frba.dds.models.converters;

import ar.edu.utn.frba.dds.models.localizacion.Localidad;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

// no le den bola a esto, era para guardar solo el nombre de la localidad
// pero si persistimos toda (localidad + departamento + provincia) esta clase deja de tener sentido
@Converter(autoApply = true)
public class LocalidadConverter implements AttributeConverter<Localidad, String> {
    @Override
    public String convertToDatabaseColumn(Localidad localidad) {
        //TODO
        return null;
    }

    @Override
    public Localidad convertToEntityAttribute(String s) {
        //TODO
        return null;
    }
}
