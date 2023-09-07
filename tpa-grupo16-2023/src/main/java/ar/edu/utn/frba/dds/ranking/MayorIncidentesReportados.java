package ar.edu.utn.frba.dds.ranking;

import ar.edu.utn.frba.dds.incidentes.Prestacion;
import ar.edu.utn.frba.dds.repositorios.RepoEntidad;
import ar.edu.utn.frba.dds.repositorios.RepoPrestacion;
import ar.edu.utn.frba.dds.serviciosPublicos.Entidad;

import java.time.LocalDateTime;
import java.util.List;

public class MayorIncidentesReportados implements GeneradorRanking {

    public List<Entidad> generarRanking(LocalDateTime fechaDeSemana) {
        List<Prestacion> listaDePrestaciones = RepoPrestacion.getInstancia().getListaPrestaciones();
        List<Entidad> listaDeEntidades = RepoEntidad.getInstancia().getListaEntidades();

        listaDeEntidades.sort((entidad1, entidad2) -> {
            int cantIncidentesEntidad1 = entidad1.cantIncidentesEnLaSemana(listaDePrestaciones, fechaDeSemana);
            int cantIncidentesEntidad2 = entidad2.cantIncidentesEnLaSemana(listaDePrestaciones, fechaDeSemana);
            if (cantIncidentesEntidad1 < cantIncidentesEntidad2) {
                return 1;
            } else if (cantIncidentesEntidad1 > cantIncidentesEntidad2) {
                return -1;
            } else {
                return 0;
            }
        });

        return listaDeEntidades;
    }
}
