package ar.edu.utn.frba.dds.ranking;

import ar.edu.utn.frba.dds.incidentes.Incidente;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MayorPromedioCierre implements Ranking{

    @Override
    public void generarRanking(List<Incidente> listaDeIncidentes) {
        // sumMap(incidente  -> incidente.diferenciaCierreAper()) / listaDeIncidentes.size()


        // mayor promedio de tiempo de cierre de incidentes, diff entre cierre y apertura en la semana.
        // por cada incidente de cada entidad
        // hacer el promedio de la diferencia entre el cierre y la apertura del incidente
        // calculo para tiempo pasado -> +(hora cierre) -(hora apertura) +(dias pasados * 24(horas)) -> todo expresado en minutos
        //
    }

}
















