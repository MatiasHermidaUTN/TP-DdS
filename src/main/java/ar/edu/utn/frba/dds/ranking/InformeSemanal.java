package ar.edu.utn.frba.dds.ranking;

import ar.edu.utn.frba.dds.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.serviciosPublicos.OrganismoDeControl;
import lombok.Getter;

import java.util.List;

@Getter
public class InformeSemanal {
    private List<Entidad> rankingMayorPromedioCierre;
    private List<Entidad> rankingMayorIncidentesReportados;
    private List<Entidad> rankingMayorImpactoProblematicas;

    public InformeSemanal(/*LocalDateTime fechaDeLaSemana*/) {
        rankingMayorPromedioCierre = new MayorPromedioCierre().generarRanking(/*fechaDeLaSemana*/);
        rankingMayorIncidentesReportados = new MayorIncidentesReportados().generarRanking(/*fechaDeLaSemana*/);
        rankingMayorImpactoProblematicas = new MayorImpactoProblematicas().generarRanking(/*fechaDeLaSemana*/);
    }

    private Integer posicionEnRankingMayorPromedioCierre(Entidad entidad) {
        return rankingMayorPromedioCierre.indexOf(entidad);
    }

    private Integer posicionEnRankingMayorIncidentesReportados(Entidad entidad) {
        return rankingMayorIncidentesReportados.indexOf(entidad);
    }

    private Integer posicionEnRankingMayorImpactoProblematicas(Entidad entidad) {
        return rankingMayorImpactoProblematicas.indexOf(entidad);
    }

    public String posicionesParaEntidad(Entidad entidad) {
        StringBuilder str = new StringBuilder();
        str.append("Estas son las posiciones en las que la entidad " + entidad.getNombre() + " se encuentra en los rankings: ");
        str.append("rankingMayorPromedioCierre: " + posicionEnRankingMayorPromedioCierre(entidad) + ", ");
        str.append("rankingMayorIncidentesReportados: " + posicionEnRankingMayorIncidentesReportados(entidad) + ", ");
        str.append("rankingMayorImpactoProblematicas: " + posicionEnRankingMayorImpactoProblematicas(entidad) + ". ");
        String _body = str.toString();
        return _body;
    }

    public String posicionesParaOrganismoDeControl(OrganismoDeControl organismoDeControl) {
        List<Entidad> entidadesControladas = organismoDeControl.getEntidadesControladas();
        StringBuilder str = new StringBuilder();
        str.append("Estas son las posiciones en los rankings de las entidades controladas por el organismo de control " + organismoDeControl.getNombre() + ": ");
        for (Entidad entidad : entidadesControladas) {
            str.append(posicionesParaEntidad(entidad));
        }
        String _body = str.toString();
        return _body;
    }

}
