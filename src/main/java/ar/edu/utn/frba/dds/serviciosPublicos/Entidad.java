package ar.edu.utn.frba.dds.serviciosPublicos;

import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.incidentes.Incidente;
import ar.edu.utn.frba.dds.incidentes.Prestacion;
import ar.edu.utn.frba.dds.localizacion.Localizacion;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Getter
public class Entidad {
    private String nombre;

    @Setter
    private Localizacion localizacion;
    private List<Establecimiento> establecimientos;
    private Map<String,String> atributosVariables;
    private List<Usuario> usuariosAsignados;

    // private Boolean servicioPublico;

    public List<Servicio> servicios() {
        return this.establecimientos.stream().
                map(establecimiento -> establecimiento.getServicios()).
                flatMap(List::stream).
                collect(Collectors.toList());
    }

    public Entidad(String nombre) {
        this.nombre = nombre;
        this.establecimientos = new ArrayList<>();
        this.atributosVariables = new HashMap<String, String>();
        this.usuariosAsignados = new ArrayList<>();
    }

    public void agregarEstablecimientos(Establecimiento ... establecimientos) {
        Collections.addAll(this.establecimientos, establecimientos);
    }

    public void eliminarEstablecimiento(Establecimiento establecimiento){
        this.establecimientos.remove(establecimiento);
    }

    public void agregarAtributoVar(String nombre, String valor) {
        this.atributosVariables.put(nombre, valor);
    }

    public void eliminarAtributoVar(String nombre){
        this.atributosVariables.remove(nombre);
    }

    public void avisar_a_usuarios() {}

    public double getPromedioCierre(List<Prestacion> listaDePrestaciones) { //se puede hacer desde el repo de incidentes tambien
        List<Incidente> incidentes = listaDePrestaciones.stream().
                filter(prestacion -> prestacion.getEstablecimiento().getEntidad().equals(this)).
                flatMap(prestacion -> prestacion.getIncidentes().stream()).
                toList();

        return incidentes.stream().mapToDouble(incidente -> {
            LocalDateTime fechaCierre = incidente.getHorarioCierre();
            LocalDateTime fechaApertura = incidente.getHorarioApertura();

            return ChronoUnit.MINUTES.between(fechaApertura, fechaCierre);
        }).count() / incidentes.size();
    }

    public int cantIncidentesEnLaSemana(List<Prestacion> listaDePrestaciones) {

        List<Prestacion> prestaciones = listaDePrestaciones.stream().
                filter(prestacion -> prestacion.getEstablecimiento().getEntidad().equals(this)).toList();

        // Filtramos las listas de incidentes de cada prestacion con los incidentes que se abrieron en la ultima semana
        prestaciones.forEach(prestacion ->
                prestacion.getIncidentes().stream().
                filter(incidente -> incidente.seReportoEnLaSemanaDeLaFecha(LocalDateTime.now())));

        // No tomamos en cuenta las prestaciones cuya lista de incidentes este vacia
        List<Prestacion> prestaciones_en_la_semana = prestaciones.stream().filter(prestacion -> prestacion.getIncidentes().size() > 0).toList();

        // Filtrar los incidentes que se hayan hecho en menos de 24 horas respecto del primer incidente
        List<Incidente> incidentesNoRepetidosDeLaSemana = new ArrayList();

        for (Prestacion unaPrestacion : prestaciones_en_la_semana){
            List<Incidente> incidentesDeUnaPrestacion = unaPrestacion.getIncidentes();

            while (incidentesDeUnaPrestacion.size() > 0) {
                Incidente primerIncidente = incidentesDeUnaPrestacion.get(0);
                incidentesDeUnaPrestacion.remove(0);

                incidentesNoRepetidosDeLaSemana.add(primerIncidente);

                incidentesDeUnaPrestacion.removeIf(otroIncidente ->
                        primerIncidente.getEstado().equals("ABIERTO") && ChronoUnit.HOURS.between(otroIncidente.getHorarioApertura(), primerIncidente.getHorarioApertura()) < 24);
            }
        }

        return incidentesNoRepetidosDeLaSemana.size();
    }

}
