package ar.edu.utn.frba.dds.repositorios;

import ar.edu.utn.frba.dds.incidentes.Incidente;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class RepoIncidente {
    private static RepoIncidente instancia = null;
    @Getter
    private static List<Incidente> listaIncidentes;

    public static void agregarIncidente(Incidente incidente) {
        listaIncidentes.add(incidente);
    }

    private RepoIncidente() {
        listaIncidentes = new ArrayList<>();
    }

    public static RepoIncidente getInstancia() {
        if (instancia == null) {
            instancia = new RepoIncidente();
        }
        return instancia;
    }

    /*private MayorImpactoProblematicas rankingImpactoProblematicas;
    private MayorIncidentesReportados rankingMayorIncidentesReportados;
    private MayorPromedioCierre rankingMayorPromedioCierre;

    public void generarRankingImpactoProblematicas() {
        rankingImpactoProblematicas.generarRanking(listaIncidentes);
    }
    public void generarRankingMayorIncidentesReportados() {
        rankingMayorIncidentesReportados.generarRanking(listaIncidentes);
    }
    public void generarRankingMayorPromedioCierre() {
        rankingMayorPromedioCierre.generarRanking();
    }*/
}
