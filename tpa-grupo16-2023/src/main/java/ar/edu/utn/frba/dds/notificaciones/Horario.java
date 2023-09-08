package ar.edu.utn.frba.dds.notificaciones;

import ar.edu.utn.frba.dds.notificaciones.cron.DiaSemana;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;

@Getter
@Setter
public class Horario {

    private DiaSemana dia;
    private Integer hora;

    public Horario(DiaSemana dia, Integer hora) {
        this.dia = dia;
        this.hora = hora;
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
