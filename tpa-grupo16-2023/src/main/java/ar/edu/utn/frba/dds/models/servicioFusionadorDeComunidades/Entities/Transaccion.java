package ar.edu.utn.frba.dds.models.servicioFusionadorDeComunidades.Entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Transaccion {
    List<ComunidadDTO> comunidades;
    List<PropuestaFusionDTO> propuestasFusion;

    public Transaccion() {
        this.comunidades = new ArrayList<ComunidadDTO>();
        this.propuestasFusion = new ArrayList<PropuestaFusionDTO>();
    }
}
