package ar.edu.utn.frba.dds.ranking;

import ar.edu.utn.frba.dds.incidentes.Incidente;

import java.util.List;

public class ImpactoProblematicas implements Ranking{
    @Override
    public void generarRanking(List<Incidente> listaDeIncidentes) {
        // a mayor cantidad de miembros en una comunidad -> mayor impacto de un incidente sobre esa comunidad
        // tenemos que distinguir entre usuarios y observadores.
    }
}
