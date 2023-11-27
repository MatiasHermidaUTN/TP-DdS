package ar.edu.utn.frba.dds.models.ranking;

import ar.edu.utn.frba.dds.models.calculadorImpactoDeIncidentes.AdapterCalcNivImpacto;
import ar.edu.utn.frba.dds.models.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.incidentes.Prestacion;
import ar.edu.utn.frba.dds.models.repositorios.RepoEntidad;
import ar.edu.utn.frba.dds.models.repositorios.RepoIncidente;
import ar.edu.utn.frba.dds.models.repositorios.RepoPrestacion;
import ar.edu.utn.frba.dds.models.repositorios.reposDeprecados.RepoEntidadDeprecado;
import ar.edu.utn.frba.dds.models.repositorios.reposDeprecados.RepoPrestacionDeprecado;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Entidad;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class MayorImpactoProblematicas implements GeneradorRanking {

    public List<Entidad> generarRanking(LocalDateTime fechaDeSemana) {
        List<Entidad> listaDeEntidades = new RepoEntidad().buscarTodos();
        List<Prestacion> listaDePrestaciones = new RepoPrestacion().buscarTodos();

//        System.out.println("entidades del repo:" + listaDeEntidades);
//        System.out.println();
//        System.out.println("prestaciones del repo:" + listaDePrestaciones);
//        System.out.println();

        List<Entidad> entidadesRankeadas = null;
        try {
            AdapterCalcNivImpacto adapterCalcNivImpacto = AdapterCalcNivImpacto.instancia();
            entidadesRankeadas = adapterCalcNivImpacto.calcularNivelDeImpacto(listaDeEntidades, listaDePrestaciones, fechaDeSemana);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        entidadesRankeadas.sort((entidad1, entidad2) -> -1);
//        System.out.println("entidadesRankeadas: " + entidadesRankeadas);
//        System.out.println();

        return entidadesRankeadas;
    }
}
