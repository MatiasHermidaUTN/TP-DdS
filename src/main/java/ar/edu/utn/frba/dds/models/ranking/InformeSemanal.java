package ar.edu.utn.frba.dds.models.ranking;

import ar.edu.utn.frba.dds.models.converters.LocalDateTimeConverter;
import ar.edu.utn.frba.dds.models.persistencia.Persistente;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.models.serviciosPublicos.OrganismoDeControl;
import lombok.Getter;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Getter
public class InformeSemanal extends Persistente {

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "fecha_creacion", columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaCreacion;

    @Transient
    private List<Entidad> rankingMayorPromedioCierre;

    @Transient
    private List<Entidad> rankingMayorIncidentesReportados;

    @Transient
    private List<Entidad> rankingMayorImpactoProblematicas;

    @Column(name = "ranking_mayor_promedio_cierre")
    private String rankingMayorPromedioCierreString;

    @Column(name = "ranking_mayor_incidentes_reportados")
    private String rankingMayorIncidentesReportadosString;

    @Column(name = "ranking_mayor_impacto_problematicas")
    private String rankingMayorImpactoProblematicasString;


    public InformeSemanal(LocalDateTime fechaDeLaSemana) {
        rankingMayorPromedioCierre = new MayorPromedioCierre().generarRanking(fechaDeLaSemana);
        rankingMayorIncidentesReportados = new MayorIncidentesReportados().generarRanking(fechaDeLaSemana);
        rankingMayorImpactoProblematicas = new MayorImpactoProblematicas().generarRanking(fechaDeLaSemana);
        fechaCreacion = fechaDeLaSemana;
        this.rankingMayorPromedioCierreString = generarStringsEntidades(rankingMayorPromedioCierre);
        this.rankingMayorIncidentesReportadosString = generarStringsEntidades(rankingMayorIncidentesReportados);
        this.rankingMayorImpactoProblematicasString = generarStringsEntidades(rankingMayorImpactoProblematicas);
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

    public String generarStringsEntidades(List<Entidad> entidades){
        String entidadesString = "";
        for(int i=0; i<entidades.size(); i++){
            entidadesString += i+1 + ". " + entidades.get(i).getNombre() + ", ";
        }

        entidadesString = entidadesString.substring(0, entidadesString.length() - 2);
        return entidadesString;
    }

    public List<String> generarListaDeStrings(String input) {
        List<String> result = new ArrayList<>();

        // Define el patrón de expresión regular para buscar los elementos en el formato dado.
        Pattern pattern = Pattern.compile("\\d+\\.\\s*([^,]+)");
        Matcher matcher = pattern.matcher(input);

        // Encuentra todos los elementos que coinciden con el patrón y agrégalos a la lista.
        while (matcher.find()) {
            result.add(matcher.group(1).trim());
        }

        return result;
    }
}
