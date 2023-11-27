package ar.edu.utn.frba.dds.models.servicioFusionadorDeComunidades.Entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Transaccion {
    public List<ComunidadDTO> comunidades;
    public List<PropuestaFusionDTO> propuestasFusion;

    public Transaccion() {
        this.comunidades = new ArrayList<ComunidadDTO>();
        this.propuestasFusion = new ArrayList<PropuestaFusionDTO>();
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "comunidades=" + comunidades +
                ", propuestasFusion=" + propuestasFusion +
                '}';
    }
}
