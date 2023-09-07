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

        listaDeEntidades.sort((entidad1, entidad2) -> {
            double promedioCierreEntidad1 = entidad1.calcularImpactoProblematicas(fechaDeSemana);
            double promedioCierreEntidad2 = entidad2.calcularImpactoProblematicas(fechaDeSemana);
            if (promedioCierreEntidad1 < promedioCierreEntidad2) {
                return 1;
            } else if (promedioCierreEntidad1 > promedioCierreEntidad2) {
                return -1;
            } else {
                return 0;
            }
        });

        return listaDeEntidades;
    }
}
