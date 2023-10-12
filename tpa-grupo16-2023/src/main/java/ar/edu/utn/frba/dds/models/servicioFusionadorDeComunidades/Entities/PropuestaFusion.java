package ar.edu.utn.frba.dds.models.servicioFusionadorDeComunidades.Entities;

import ar.edu.utn.frba.dds.models.comunidades.Comunidad;
import ar.edu.utn.frba.dds.models.converters.LocalDateTimeConverter;
import ar.edu.utn.frba.dds.models.persistencia.Persistente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "propuesta_fusion")
public class PropuestaFusion extends Persistente {
    @ManyToOne
    @JoinColumn(name = "comunidad1_id", referencedColumnName = "id")
    Comunidad comunidad1;

    @ManyToOne
    @JoinColumn(name = "comunidad2_id", referencedColumnName = "id")
    Comunidad comunidad2;

    @ManyToOne
    @JoinColumn(name = "comunidad_fusionada_id", referencedColumnName = "id")
    Comunidad comunidadFusionada;

    @ManyToOne
    @JoinColumn(name = "comunidad1_desactivada_id", referencedColumnName = "id")
    Comunidad comunidad1desactivada;

    @ManyToOne
    @JoinColumn(name = "comunidad2_desactivada_id", referencedColumnName = "id")
    Comunidad comunidad2desactivada;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_propuesta_fusion")
    EstadoPropuestaFusion estado;

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "fecha_de_propuesta", columnDefinition = "TIMESTAMP")
    LocalDateTime fechaDePropuesta;


    public void aceptar() {
        this.estado = EstadoPropuestaFusion.ACEPTADA;
    }

    public void rechazar() {
        this.estado = EstadoPropuestaFusion.RECHAZADA;
    }
}
