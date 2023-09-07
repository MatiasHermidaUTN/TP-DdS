package ar.edu.utn.frba.dds.ranking;

import ar.edu.utn.frba.dds.incidentes.Prestacion;
import ar.edu.utn.frba.dds.repositorios.RepoEntidad;
import ar.edu.utn.frba.dds.repositorios.RepoPrestacion;
import ar.edu.utn.frba.dds.serviciosPublicos.Entidad;

import java.time.LocalDateTime;
import java.util.List;

public class MayorPromedioCierre implements GeneradorRanking {

    public Ranking generarRanking(LocalDateTime fechaDeSemana) {
        List<Entidad> listaDeEntidades = RepoEntidad.getInstancia().getListaEntidades();

        listaDeEntidades.sort((entidad1, entidad2) -> {
            double promedioCierreEntidad1 = entidad1.getPromedioCierreRanking(fechaDeSemana);
            double promedioCierreEntidad2 = entidad2.getPromedioCierreRanking(fechaDeSemana);
            if (promedioCierreEntidad1 < promedioCierreEntidad2) {
                return 1;
            } else if (promedioCierreEntidad1 > promedioCierreEntidad2) {
                return -1;
            } else {
                return 0;
            }
        });

        return new Ranking(listaDeEntidades, fechaDeSemana.toLocalDate());
    }
}
















