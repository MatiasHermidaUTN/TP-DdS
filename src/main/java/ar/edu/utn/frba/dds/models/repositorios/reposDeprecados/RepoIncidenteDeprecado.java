package ar.edu.utn.frba.dds.models.repositorios.reposDeprecados;

import ar.edu.utn.frba.dds.models.incidentes.Incidente;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class RepoIncidenteDeprecado {
    private static RepoIncidenteDeprecado instancia = null;
    @Getter
    private static List<Incidente> listaIncidentes;

    public static void agregarIncidente(Incidente incidente) {
        listaIncidentes.add(incidente);
    }

    private RepoIncidenteDeprecado() {
        listaIncidentes = new ArrayList<>();
    }

    public static RepoIncidenteDeprecado getInstancia() {
        if (instancia == null) {
            instancia = new RepoIncidenteDeprecado();
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
