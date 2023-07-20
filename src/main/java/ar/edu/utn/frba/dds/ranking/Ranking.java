package ar.edu.utn.frba.dds.ranking;

import ar.edu.utn.frba.dds.incidentes.Incidente;

import java.util.List;

public interface Ranking {

    public void generarRanking(List<Incidente> listaDeIncidentes);
}
