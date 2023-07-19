package ar.edu.utn.frba.dds.incidentes;

import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.serviciosPublicos.Servicio;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.Duration;
import java.util.Map;

@Getter
@Setter
public class Incidente {
    private Integer idIncidente;
    private String nombreComunidad;
    private Establecimiento establecimiento;
    private Servicio servicio;
    private Usuario usuarioApertura;
    private Usuario usuarioCierre;
    private LocalDateTime horarioApertura;
    private LocalDateTime horarioCierre;
    private EstadoIncidente estado;

    private static Integer id = 0;
    private Integer obtenerID() {
        return id++;
    }

    public Incidente(Establecimiento establecimiento, String nombreComunidad, Servicio servicio, Usuario usuarioApertura) {
        this.establecimiento = establecimiento;
        this.nombreComunidad = nombreComunidad;
        this.servicio = servicio;
        this.usuarioApertura = usuarioApertura;
        this.horarioApertura = LocalDateTime.now();
        this.estado = EstadoIncidente.ABIERTO;
        this.idIncidente = obtenerID(); //TODO: esto genera autoincremental?
    }

    public Incidente(){}

    public Long diferenciaCierreApertura(){
        LocalDateTime tempDateTime = LocalDateTime.from( horarioApertura );

        long years = tempDateTime.until( horarioCierre, ChronoUnit.YEARS );
        tempDateTime = tempDateTime.plusYears( years );

        long months = tempDateTime.until( horarioCierre, ChronoUnit.MONTHS );
        tempDateTime = tempDateTime.plusMonths( months );

        long days = tempDateTime.until( horarioCierre, ChronoUnit.DAYS );
        tempDateTime = tempDateTime.plusDays( days );

        long hours = tempDateTime.until( horarioCierre, ChronoUnit.HOURS );
        tempDateTime = tempDateTime.plusHours( hours );

        long minutes = tempDateTime.until( horarioCierre, ChronoUnit.MINUTES );
        tempDateTime = tempDateTime.plusMinutes( minutes );

        long seconds = tempDateTime.until( horarioCierre, ChronoUnit.SECONDS );

        long diferencia = tempDateTime.truncatedTo(ChronoUnit.DAYS).until(tempDateTime, ChronoUnit.MINUTES);

        return diferencia;
    }
}