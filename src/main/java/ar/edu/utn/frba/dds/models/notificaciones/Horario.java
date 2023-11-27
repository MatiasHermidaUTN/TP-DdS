package ar.edu.utn.frba.dds.models.notificaciones;

import ar.edu.utn.frba.dds.models.converters.DiaSemanaConverter;
import ar.edu.utn.frba.dds.models.persistencia.Persistente;
import ar.edu.utn.frba.dds.models.notificaciones.cron.DiaSemana;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;

@Entity
@Table
@Getter
@Setter
public class Horario extends Persistente {

    @Convert(converter = DiaSemanaConverter.class)
    @Column(name = "dia")
    private DiaSemana dia;

    @Column
    private Integer hora;

    public Horario(DiaSemana dia, Integer hora) {
        this.dia = dia;
        this.hora = hora;
    }

    public Horario() {

    }

    public DayOfWeek diaSemanaComoDayOfWeek() {
        DayOfWeek day = null;
        switch (dia) {
            case LUNES: day = DayOfWeek.MONDAY; break;
            case MARTES: day = DayOfWeek.TUESDAY; break;
            case MIERCOLES: day = DayOfWeek.WEDNESDAY; break;
            case JUEVES: day = DayOfWeek.THURSDAY; break;
            case VIERNES: day = DayOfWeek.FRIDAY; break;
            case SABADO: day = DayOfWeek.SATURDAY; break;
            case DOMINGO: day = DayOfWeek.SUNDAY; break;
        }
        return day;
    }
}
