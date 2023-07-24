package ar.edu.utn.frba.dds.ranking;

import ar.edu.utn.frba.dds.incidentes.Prestacion;
import ar.edu.utn.frba.dds.repositorios.RepoEntidad;
import ar.edu.utn.frba.dds.repositorios.RepoPrestacion;
import ar.edu.utn.frba.dds.serviciosPublicos.Entidad;

import java.util.List;

public class MayorPromedioCierre {

    public List<Entidad> generarRanking() {
        List<Prestacion> listaDePrestaciones = RepoPrestacion.getInstancia().getListaPrestaciones();
        List<Entidad> listaDeEntidades = RepoEntidad.getInstancia().getListaEntidades();

        listaDeEntidades.sort((entidad1, entidad2) -> {
            if (entidad1.getPromedioCierre(listaDePrestaciones) > entidad2.getPromedioCierre(listaDePrestaciones)) {
                return 1;
            } else if (entidad1.getPromedioCierre(listaDePrestaciones) < entidad2.getPromedioCierre(listaDePrestaciones)) {
                return -1;
            } else {
                return 0;
            }
        });

        return listaDeEntidades;
    }
}
















