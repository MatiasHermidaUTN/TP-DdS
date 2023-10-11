package ar.edu.utn.frba.dds.models.incidentes;

import ar.edu.utn.frba.dds.models.comunidades.Comunidad;
import ar.edu.utn.frba.dds.models.comunidades.Usuario;

import ar.edu.utn.frba.dds.models.persistencia.Persistente;

import ar.edu.utn.frba.dds.models.converters.LocalDateTimeConverter;

import ar.edu.utn.frba.dds.models.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Servicio;
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
    @JoinColumn(name = "comunidad_id", referencedColumnName = "id")
    private Comunidad comunidad;

    @Column
    public String observaciones;

    // El establecimiento y servicio asociados estan en la prestacion. Esto quedaria deprecado.
    // @Transient
    // private Establecimiento establecimiento;

    // @Transient
    // private Servicio servicio;

    @ManyToOne
    @JoinColumn(name = "usuario_apertura", referencedColumnName = "id")
    private Usuario usuarioApertura;

    @ManyToOne
    @JoinColumn(name = "usuario_cierre", referencedColumnName = "id")
    private Usuario usuarioCierre;

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "horario_apertura", columnDefinition = "TIMESTAMP")
    private LocalDateTime horarioApertura;

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "horario_cierre", columnDefinition = "TIMESTAMP")
    private LocalDateTime horarioCierre;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_incidente")
    private EstadoIncidente estado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prestacion_id", referencedColumnName = "id")
    private Prestacion prestacion;

    public Incidente(Establecimiento establecimiento, String nombreComunidad, Servicio servicio, Usuario usuarioApertura) {
        // this.establecimiento = establecimiento;
        //this.nombreComunidad = nombreComunidad;
        // this.servicio = servicio;
        this.usuarioApertura = usuarioApertura;
        this.horarioApertura = LocalDateTime.now();
        this.estado = EstadoIncidente.ABIERTO;
    }

    public Incidente(){
        this.estado = EstadoIncidente.ABIERTO;
        // this.idIncidente = obtenerID();
    }

    public double minutosEntreAperturaYCierre() {
        if (horarioCierre == null) {
            return 0;
        } else {
            return ChronoUnit.MINUTES.between(horarioApertura, horarioCierre);
        }
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
        return this.getEstablecimiento().getEntidad().equals(entidad);
    }

    public String getEstablecimientoNombre() {
        return this.prestacion.getEstablecimiento().getNombre();
    }

    public String getServicioNombre() {
        return this.prestacion.getServicio().getNombre();
    }

    public Establecimiento getEstablecimiento() {
        return this.prestacion.getEstablecimiento();
    }

    public Servicio getServicio() {
        return this.prestacion.getServicio();
    }

    public Integer getMiembrosAfectados() {
        return this.comunidad.getCantPerfilesAfectados();
    }

    public Boolean estaResuelto() {
        return this.estado == EstadoIncidente.RESUELTO;
    }

    @Override
    public String toString() {
        return "{" +
                "\"comunidad.hbs\":" + comunidad +
                ", \"observaciones\":\"" + observaciones + '\"' +
                ", \"usuarioApertura\":" + usuarioApertura +
                ", \"usuarioCierre\":" + usuarioCierre +
                ", \"horarioApertura\":\"" + horarioApertura + '\"' +
                ", \"horarioCierre\":\"" + horarioCierre + '\"' +
                ", \"estado\":\"" + estado + '\"' +
                ", \"prestacion\":" + prestacion +
                '}';
    }
}