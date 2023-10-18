package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.ranking.InformeSemanal;
import ar.edu.utn.frba.dds.models.repositorios.RepoInforme;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InformesController {

    private RepoInforme repoInforme;

    public InformesController(RepoInforme repoInforme) {
        this.repoInforme = repoInforme;
    }

    public void mostrar_informe_reciente(Context context){
        InformeSemanal ultimoInforme = this.repoInforme.buscarTodos().get(0);
        Map<String, Object> model = new HashMap<>();

        List<String> rankingMayorPromedioCierre = ultimoInforme.generarListaDeStrings(ultimoInforme.getRankingMayorPromedioCierreString());
        List<String> rankingMayorIncidentesReportados = ultimoInforme.generarListaDeStrings(ultimoInforme.getRankingMayorIncidentesReportadosString());
        List<String> rankingMayorImpactoProblematicas = ultimoInforme.generarListaDeStrings(ultimoInforme.getRankingMayorImpactoProblematicasString());

        model.put("fecha", ultimoInforme.getFechaCreacion());
        model.put("rankingMayorPromedioCierre", rankingMayorPromedioCierre);
        model.put("rankingMayorIncidentesReportados", rankingMayorIncidentesReportados);
        model.put("rankingMayorImpactoProblematicas", rankingMayorImpactoProblematicas);

        context.render("/rankings/rankings.hbs", model);
    }
}
