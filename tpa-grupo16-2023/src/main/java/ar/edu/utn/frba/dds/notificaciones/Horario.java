package ar.edu.utn.frba.dds.notificaciones;

import ar.edu.utn.frba.dds.converters.DiaSemanaConverter;
import ar.edu.utn.frba.dds.notificaciones.cron.DiaSemana;
import ar.edu.utn.frba.dds.persistencia.Persistente;
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

    @Transient
    private Integer hora; // aca como se haria el mapeo del string? Hay un tipo de dato HOUR o algo asi?

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
