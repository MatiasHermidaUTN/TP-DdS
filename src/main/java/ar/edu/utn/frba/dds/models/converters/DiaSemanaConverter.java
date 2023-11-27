package ar.edu.utn.frba.dds.models.converters;

import ar.edu.utn.frba.dds.models.notificaciones.cron.DiaSemana;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class DiaSemanaConverter implements AttributeConverter<DiaSemana, String> {

    @Override
    public String convertToDatabaseColumn(DiaSemana dia) {
        String day = null;
        switch (dia) {
            case LUNES: day = "Lunes"; break;
            case MARTES: day = "Martes"; break;
            case MIERCOLES: day = "Miercoles"; break;
            case JUEVES: day = "Jueves"; break;
            case VIERNES: day = "Viernes"; break;
            case SABADO: day = "Sabado"; break;
            case DOMINGO: day = "Domingo"; break;
        }
        return day;
    }

    @Override
    public DiaSemana convertToEntityAttribute(String dia) {
        DiaSemana day = null;
        switch (dia) {
            case "Lunes": day = DiaSemana.LUNES; break;
            case "Martes": day = DiaSemana.MARTES; break;
            case "Miercoles": day = DiaSemana.MIERCOLES; break;
            case "Jueves": day = DiaSemana.JUEVES; break;
            case "Viernes": day = DiaSemana.VIERNES; break;
            case "Sabado": day = DiaSemana.SABADO; break;
            case "Domingo": day = DiaSemana.DOMINGO; break;
        }
        return day;
    }
}
