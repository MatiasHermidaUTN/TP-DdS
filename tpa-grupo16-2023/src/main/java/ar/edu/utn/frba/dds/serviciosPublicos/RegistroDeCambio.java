package ar.edu.utn.frba.dds.serviciosPublicos;

import ar.edu.utn.frba.dds.comunidades.Usuario;

import ar.edu.utn.frba.dds.persistencia.Persistente;

import ar.edu.utn.frba.dds.converters.LocalDateTimeConverter;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class RegistroDeCambio extends Persistente {

    @Getter
    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "fecha_cambio", columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaDeCambio;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_servicio")
    private EstadoServicio estadoServicio;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    private Usuario usuarioQueLoCambio;

    public RegistroDeCambio(EstadoServicio nuevoEstadoServicio, LocalDateTime now, Usuario usuario) {
        this.fechaDeCambio = now;
        this.estadoServicio = nuevoEstadoServicio;
        this.usuarioQueLoCambio = usuario;
    }

    public RegistroDeCambio() {

    }
}
