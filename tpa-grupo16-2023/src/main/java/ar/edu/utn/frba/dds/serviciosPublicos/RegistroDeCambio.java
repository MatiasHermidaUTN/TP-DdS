package ar.edu.utn.frba.dds.serviciosPublicos;

import ar.edu.utn.frba.dds.comunidades.Usuario;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegistroDeCambio {
    @Getter
    private LocalDateTime fechaDeCambio;
    private EstadoServicio estadoServicio;
    private Usuario usuarioQueLoCambio;

    public RegistroDeCambio(EstadoServicio nuevoEstadoServicio, LocalDateTime now, Usuario usuario) {
        this.fechaDeCambio = now;
        this.estadoServicio = nuevoEstadoServicio;
        this.usuarioQueLoCambio = usuario;
    }
}
