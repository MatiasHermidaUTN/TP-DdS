package ar.edu.utn.frba.dds.incidentes;

import ar.edu.utn.frba.dds.ranking.Ranking;

import java.util.List;

public class IncidenteRepository {
    private List<Incidente> listaIncidentes;
    private Ranking ranking;

    public void generarRanking() {
        ranking.generarRanking(listaIncidentes);
    }
}
