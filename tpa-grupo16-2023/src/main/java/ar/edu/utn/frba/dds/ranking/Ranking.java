package ar.edu.utn.frba.dds.ranking;

import ar.edu.utn.frba.dds.serviciosPublicos.Entidad;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter @Getter
public class Ranking {
    private List<Entidad> topEntidades;

    private LocalDate fechaCreacion;

    public Ranking(List<Entidad> topEntidades, LocalDate fechaCreacion) {
        this.topEntidades = topEntidades;
        this.fechaCreacion = fechaCreacion;
    }
}
