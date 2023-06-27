package ar.edu.utn.frba.dds.incidentes;

import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.serviciosPublicos.Servicio;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class Incidente {
    private Establecimiento establecimiento;
    private Servicio servicio;
    private Usuario usuarioApertura;
    private Usuario usuarioCierre;
    private LocalDateTime horarioApartura;
    private LocalDateTime horarioCierre;
    private EstadoIncidente estado;
}