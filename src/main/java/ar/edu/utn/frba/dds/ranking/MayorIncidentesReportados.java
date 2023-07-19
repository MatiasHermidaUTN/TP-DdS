package ar.edu.utn.frba.dds.ranking;

import ar.edu.utn.frba.dds.incidentes.Incidente;

import java.util.List;

public class MayorIncidentesReportados implements Ranking{
    @Override
    public void generarRanking(List<Incidente> listaDeIncidentes) {
        // tambien va distribuido por las entidades.
        // entidades con mayor cantidad de incidentes reportados en la semana
        // no se considera otro incidente, si el mismo esta dentro del plazo de 24 horas y continua abierto.
        // incidente nuevo -> si
        // incidente visto -> no continua abierto -> si
        // incidente visto -> continua abierto -> (hora incidente visto) > (hora incidente nuevo) + 1440(min) -> si
        // otro, no se considera el incidente para la cantidad dentro del reporte
        //

    }
}
