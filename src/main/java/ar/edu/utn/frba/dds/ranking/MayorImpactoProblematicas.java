package ar.edu.utn.frba.dds.ranking;

import ar.edu.utn.frba.dds.repositorios.RepoEntidad;
import ar.edu.utn.frba.dds.serviciosPublicos.Entidad;

import java.time.LocalDateTime;
import java.util.List;

public class MayorImpactoProblematicas implements GeneradorRanking {

    public List<Entidad> generarRanking(LocalDateTime fechaDeSemana) {
        //TODO entrega 4
        // a mayor cantidad de miembros en una comunidad -> mayor impacto de un incidente sobre esa comunidad
        // tenemos que distinguir entre usuarios y observadores.
        List<Entidad> listaDeEntidades = RepoEntidad.getInstancia().getListaEntidades();

        return listaDeEntidades;
    }
}
