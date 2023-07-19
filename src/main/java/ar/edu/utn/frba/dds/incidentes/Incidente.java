package ar.edu.utn.frba.dds.incidentes;

import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.serviciosPublicos.Servicio;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class Incidente {
    private int id;
    private Establecimiento establecimiento;
    private Servicio servicio;
    private Usuario usuarioApertura;
    private Usuario usuarioCierre;
    private LocalDateTime horarioApartura;
    private LocalDateTime horarioCierre;
    private EstadoIncidente estado;


    public Incidente(Establecimiento establecimiento, Servicio servicio, Usuario usuarioApertura, LocalDateTime horarioApartura) {
        this.establecimiento = establecimiento;
        this.servicio = servicio;
        this.usuarioApertura = usuarioApertura;
        this.horarioApartura = horarioApartura;
        this.estado = EstadoIncidente.ABIERTO;
    }

    public Incidente() {

    }

    public void crear() {
        usuarioApertura.getPerfiles()
                .stream()
                .map(perfil -> perfil.getComunidad())
                .forEach(comunidad -> comunidad.agregarIncidente(this));
    }

    public void cerrar(Usuario usuarioCierre, LocalDateTime horarioCierre) {
        this.usuarioCierre = usuarioCierre;
        this.horarioCierre = horarioCierre;
        this.estado = EstadoIncidente.RESUELTO;
    }

}