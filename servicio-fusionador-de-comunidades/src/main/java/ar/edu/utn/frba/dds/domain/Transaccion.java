package ar.edu.utn.frba.dds.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Transaccion {
    private List<ComunidadDTO> comunidades;
    private List<PropuestaFusionDTO> propuestasFusion;

    public Transaccion() {
        this.comunidades = new ArrayList<ComunidadDTO>();
        this.propuestasFusion = new ArrayList<PropuestaFusionDTO>();
    }


}

