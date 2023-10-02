package ar.edu.utn.frba.dds.models.ranking;

import ar.edu.utn.frba.dds.models.repositorios.reposDeprecados.RepoEntidadDeprecado;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Entidad;

import java.time.LocalDateTime;
import java.util.List;

public class MayorPromedioCierre implements GeneradorRanking {

    public List<Entidad> generarRanking(LocalDateTime fechaDeSemana) {
        List<Entidad> listaDeEntidades = RepoEntidadDeprecado.getInstancia().getListaEntidades();

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

        return listaDeEntidades;
    }
}
















