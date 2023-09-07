package ar.edu.utn.frba.dds.ranking;

import ar.edu.utn.frba.dds.serviciosPublicos.Entidad;

import java.time.LocalDateTime;
import java.util.List;

public interface GeneradorRanking {
    public Ranking generarRanking(LocalDateTime fechaDeSemana);
}
