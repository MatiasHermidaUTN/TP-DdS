package ar.edu.utn.frba.dds.serviciosPublicos;

import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.converters.LocalDateTimeAttributeConverter;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class RegistroDeCambio {

    @Id
    @GeneratedValue
    private Integer registro_id;

    @Getter
    @Convert(converter = LocalDateTimeAttributeConverter.class)
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
