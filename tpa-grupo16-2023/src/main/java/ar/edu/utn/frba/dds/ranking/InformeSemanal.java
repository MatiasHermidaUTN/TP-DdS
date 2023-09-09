package ar.edu.utn.frba.dds.ranking;

import ar.edu.utn.frba.dds.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.serviciosPublicos.OrganismoDeControl;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Getter
public class InformeSemanal {

    @Id
    private LocalDate fechaCreacion;

    @ManyToMany
    private List<Entidad> rankingMayorPromedioCierre;

    @ManyToMany
    private List<Entidad> rankingMayorIncidentesReportados;

    @ManyToMany
    private List<Entidad> rankingMayorImpactoProblematicas;


    public InformeSemanal(LocalDateTime fechaDeLaSemana) {
        rankingMayorPromedioCierre = new MayorPromedioCierre().generarRanking(fechaDeLaSemana);
        rankingMayorIncidentesReportados = new MayorIncidentesReportados().generarRanking(fechaDeLaSemana);
        rankingMayorImpactoProblematicas = new MayorImpactoProblematicas().generarRanking(fechaDeLaSemana);
        fechaCreacion = fechaDeLaSemana.toLocalDate();
    }

    public InformeSemanal() {

    }

    private Integer posicionEnRankingMayorPromedioCierre(Entidad entidad) {
        return rankingMayorPromedioCierre.indexOf(entidad) + 1;
    }

    private Integer posicionEnRankingMayorIncidentesReportados(Entidad entidad) {
        return rankingMayorIncidentesReportados.indexOf(entidad) + 1;
    }

    private Integer posicionEnRankingMayorImpactoProblematicas(Entidad entidad) {
        return rankingMayorImpactoProblematicas.indexOf(entidad) + 1;
    }

    public String posicionesParaEntidad(Entidad entidad) {
        StringBuilder str = new StringBuilder();
        str.append("<br>Estas son las posiciones en las que la entidad " + entidad.getNombre() + " se encuentra en los rankings: ");
        str.append("<br>- rankingMayorPromedioCierre: " + posicionEnRankingMayorPromedioCierre(entidad));
        str.append("<br>- rankingMayorIncidentesReportados: " + posicionEnRankingMayorIncidentesReportados(entidad));
        str.append("<br>- rankingMayorImpactoProblematicas: " + posicionEnRankingMayorImpactoProblematicas(entidad));
        str.append("<br><br>");
        return str.toString();
    }

    public String posicionesParaOrganismoDeControl(OrganismoDeControl organismoDeControl) {
        List<Entidad> entidadesControladas = organismoDeControl.getEntidadesControladas();
        StringBuilder str = new StringBuilder();
        str.append("Estas son las posiciones en los rankings de las entidades controladas por el organismo de control " + organismoDeControl.getNombre() + ": ");
        for (Entidad entidad : entidadesControladas) {
            str.append(posicionesParaEntidad(entidad));
        }
        return str.toString();
    }

}
