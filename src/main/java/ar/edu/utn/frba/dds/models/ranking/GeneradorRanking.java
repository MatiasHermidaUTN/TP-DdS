package ar.edu.utn.frba.dds.models.ranking;

import ar.edu.utn.frba.dds.models.serviciosPublicos.Entidad;

import java.time.LocalDateTime;
import java.util.List;

public interface GeneradorRanking {
    public List<Entidad> generarRanking(LocalDateTime fechaDeSemana);
}
