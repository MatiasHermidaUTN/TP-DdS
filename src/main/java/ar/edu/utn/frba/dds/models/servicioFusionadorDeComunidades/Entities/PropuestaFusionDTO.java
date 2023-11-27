package ar.edu.utn.frba.dds.models.servicioFusionadorDeComunidades.Entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PropuestaFusionDTO {
    public Integer id;
    public ComunidadDTO comunidad1;
    public ComunidadDTO comunidad2;
    public ComunidadDTO comunidadFusionada;
    public ComunidadDTO comunidad1desactivada;
    public ComunidadDTO comunidad2desactivada;
    public EstadoPropuestaFusion estadoPropuestaFusion;
    public LocalDateTime fechaPropuestaFusion;

    public PropuestaFusionDTO() {
    }

    @Override
    public String toString() {
        return "PropuestaFusionDTO{" +
                "id=" + id +
                ", comunidad1=" + comunidad1 +
                ", comunidad2=" + comunidad2 +
                ", comunidadFusionada=" + comunidadFusionada +
                ", comunidad1desactivada=" + comunidad1desactivada +
                ", comunidad2desactivada=" + comunidad2desactivada +
                ", estadoPropuestaFusion=" + estadoPropuestaFusion +
                ", fechaPropuestaFusion=" + fechaPropuestaFusion +
                '}';
    }
}

