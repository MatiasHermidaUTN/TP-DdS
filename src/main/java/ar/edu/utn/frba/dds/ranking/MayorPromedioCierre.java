package ar.edu.utn.frba.dds.ranking;

import ar.edu.utn.frba.dds.incidentes.Incidente;
import ar.edu.utn.frba.dds.incidentes.Prestacion;
import ar.edu.utn.frba.dds.repositorios.RepoPrestacion;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MayorPromedioCierre {


    public List<Prestacion> generarRanking() {
        List<Prestacion> listaDePrestaciones = RepoPrestacion.getInstancia().getListaPrestaciones();

        listaDePrestaciones.sort((prestacion1, prestacion2) -> {
            if (prestacion1.getPromedioCierre() > prestacion2.getPromedioCierre()) {
                return 1;
            } else if (prestacion1.getPromedioCierre() < prestacion2.getPromedioCierre()) {
                return -1;
            } else {
                return 0;
            }
        });

        return listaDePrestaciones;
    }
}
















