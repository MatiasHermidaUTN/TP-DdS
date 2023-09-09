package ar.edu.utn.frba.dds.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

// no me acuerdo como hacer este converter (y la clase anterior no esta subida todavia xd)
@Converter(autoApply = true)
public class DateConverter implements AttributeConverter<LocalDateTime, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDateTime localDateTime) {
        return null;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Date date) {
        return null;
    }
}
