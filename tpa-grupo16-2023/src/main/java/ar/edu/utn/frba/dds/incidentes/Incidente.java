package ar.edu.utn.frba.dds.incidentes;

import ar.edu.utn.frba.dds.comunidades.Comunidad;
import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.converters.LocalDateTimeAttributeConverter;
import ar.edu.utn.frba.dds.persistencia.Persistente;
import ar.edu.utn.frba.dds.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.serviciosPublicos.Servicio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

@Entity
@Table
@Getter
@Setter
public class Incidente extends Persistente {

    @ManyToOne
    @JoinColumn(name = "comunidad_id", referencedColumnName = "comunidad_id")
    private Comunidad comunidad; // esto lo agregue porque no le gustaba al inteliij que use el nombreComunidad

    @Transient
    private String nombreComunidad;

    @Column
    public String observaciones;

    @Transient
    private Establecimiento establecimiento;

    @Transient
    private Servicio servicio;

    @ManyToOne
    @JoinColumn(name = "usuario_apertura", referencedColumnName = "usuario_id")
    private Usuario usuarioApertura;

    @ManyToOne
    @JoinColumn(name = "usuario_cierre", referencedColumnName = "usuario_id")
    private Usuario usuarioCierre;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "horario_apertura", columnDefinition = "TIMESTAMP")
    private LocalDateTime horarioApertura;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "horario_cierre", columnDefinition = "TIMESTAMP")
    private LocalDateTime horarioCierre;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_incidente")
    private EstadoIncidente estado;

    @ManyToOne
    @JoinColumn(name = "prestacion_id", referencedColumnName = "prestacion_id")
    private Prestacion prestacion;

    // private static Integer id = 0;
    // private Integer obtenerID() {
        // return id++;
    // }

    public Incidente(Establecimiento establecimiento, String nombreComunidad, Servicio servicio, Usuario usuarioApertura) {
        this.establecimiento = establecimiento;
        this.nombreComunidad = nombreComunidad;
        this.servicio = servicio;
        this.usuarioApertura = usuarioApertura;
        this.horarioApertura = LocalDateTime.now();
        this.estado = EstadoIncidente.ABIERTO;
        // this.idIncidente = obtenerID();
    }

    public Incidente(){
        this.estado = EstadoIncidente.ABIERTO;
        // this.idIncidente = obtenerID();
    }

    public double minutosEntreAperturaYCierre() {
        return ChronoUnit.MINUTES.between(horarioApertura, horarioCierre);
    }

    public boolean seReportoEnLaSemanaDeLaFecha(LocalDateTime unaFecha) {

        // Calculate the start of the previous week (Monday at 00:00)
        LocalDateTime inicioDeSemana;
        if (unaFecha.getDayOfWeek() != DayOfWeek.MONDAY) {
            inicioDeSemana = unaFecha.with(TemporalAdjusters.previous(DayOfWeek.MONDAY)).withHour(0).withMinute(0).withSecond(0).withNano(0);
        } else {
            inicioDeSemana = unaFecha.withHour(0).withMinute(0).withSecond(0).withNano(0);
        }

        // Calculate the end of the next week (Sunday at 23:59)
        LocalDateTime finDeSemana;
        if (unaFecha.getDayOfWeek() != DayOfWeek.SUNDAY) {
            finDeSemana = unaFecha.with(TemporalAdjusters.next(DayOfWeek.SUNDAY)).withHour(23).withMinute(59).withSecond(59).withNano(999999999);
        } else {
            finDeSemana = unaFecha.withHour(23).withMinute(59).withSecond(59).withNano(999999999);
        }

        // Check if the date is between the start and end of the previous week
        return horarioApertura.isAfter(inicioDeSemana) && horarioApertura.isBefore(finDeSemana);
    }

    public boolean seCerroEnLaSemanaDeLaFecha(LocalDateTime unaFecha) {

        // Calculate the start of the previous week (Monday at 00:00)
        LocalDateTime inicioDeSemana;
        if (unaFecha.getDayOfWeek() != DayOfWeek.MONDAY) {
            inicioDeSemana = unaFecha.with(TemporalAdjusters.previous(DayOfWeek.MONDAY)).withHour(0).withMinute(0).withSecond(0).withNano(0);
        } else {
            inicioDeSemana = unaFecha.withHour(0).withMinute(0).withSecond(0).withNano(0);
        }

        // Calculate the end of the next week (Sunday at 23:59)
        LocalDateTime finDeSemana;
        if (unaFecha.getDayOfWeek() != DayOfWeek.SUNDAY) {
            finDeSemana = unaFecha.with(TemporalAdjusters.next(DayOfWeek.SUNDAY)).withHour(23).withMinute(59).withSecond(59).withNano(999999999);
        } else {
            finDeSemana = unaFecha.withHour(23).withMinute(59).withSecond(59).withNano(999999999);
        }

        // Check if the date is between the start and end of the previous week
        return horarioCierre.isAfter(inicioDeSemana) && horarioCierre.isBefore(finDeSemana);
    }

    public boolean seOriginoEnEntidad(Entidad entidad) {
        return this.establecimiento.getEntidad().equals(entidad);
    }
}