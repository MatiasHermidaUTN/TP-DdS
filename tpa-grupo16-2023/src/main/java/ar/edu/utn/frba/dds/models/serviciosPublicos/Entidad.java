package ar.edu.utn.frba.dds.models.serviciosPublicos;

import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import ar.edu.utn.frba.dds.models.incidentes.EstadoIncidente;
import ar.edu.utn.frba.dds.models.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.incidentes.Prestacion;
import ar.edu.utn.frba.dds.models.localizacion.Localizacion;
import ar.edu.utn.frba.dds.models.persistencia.Persistente;
import ar.edu.utn.frba.dds.models.repositorios.RepoIncidente;
import ar.edu.utn.frba.dds.models.repositorios.reposDeprecados.RepoIncidenteDeprecado;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table
@Getter
@Setter
public class Entidad extends Persistente {

    @Column
    private String nombre;

    @OneToOne(cascade = { CascadeType.ALL})
    @JoinColumn(name = "localizacion_id", referencedColumnName = "id")
    private Localizacion localizacion;

    @Transient
    private List<Establecimiento> establecimientos;

    @ManyToMany
    private List<AtributoVariable> atributosVariables;

    @Transient
    private List<Usuario> usuariosAsignados;


    public Entidad() {

    }

    public List<Servicio> servicios() {
        return this.establecimientos.stream().
                map(establecimiento -> establecimiento.getServicios()).
                flatMap(List::stream).
                collect(Collectors.toList());
    }

    public Entidad(String nombre) {
        this.nombre = nombre;
        this.establecimientos = new ArrayList<>();
        this.atributosVariables = new ArrayList<>() ;
        this.usuariosAsignados = new ArrayList<>();
    }

    public void agregarEstablecimientos(Establecimiento ... establecimientos) {
        Collections.addAll(this.establecimientos, establecimientos);
    }

    public void eliminarEstablecimiento(Establecimiento establecimiento){
        this.establecimientos.remove(establecimiento);
    }

    public void agregarAtributoVar(String nombre, String valor) {
        AtributoVariable nuevoAtributo = new AtributoVariable(nombre, valor);
        this.atributosVariables.add(nuevoAtributo);
    }

    public void eliminarAtributoVar(String nombre){
        this.atributosVariables.removeIf(atributoVariable -> atributoVariable.getNombre_atributo() == nombre);
    }

    public void avisar_a_usuarios() {}

    public double getPromedioCierreRanking(LocalDateTime fechaDeSemana) {
        List<Incidente> todosLosIncidentes = new RepoIncidente().buscarTodos();
        List<Incidente> incidentesDeEntidad = todosLosIncidentes.stream()
                .filter(unIncidete -> unIncidete.seOriginoEnEntidad(this))
                .toList();
        List<Incidente> incidentesCerrados = incidentesDeEntidad.stream()
                .filter(incidente -> incidente.seCerroEnLaSemanaDeLaFecha(fechaDeSemana))
                .toList();

        if (incidentesCerrados.isEmpty()) return 0;

        return incidentesCerrados.stream().mapToDouble(incidente -> incidente.minutosEntreAperturaYCierre()).sum() / incidentesCerrados.size();
    }

    public int cantIncidentesEnLaSemana(List<Prestacion> listaDePrestacionesGlobal, LocalDateTime fechaDeSemana) {
        //Filtrar las prestacionesDeEntidad de la entidad
        List<Prestacion> prestacionesDeEntidad = listaDePrestacionesGlobal.stream().
                filter(prestacion -> prestacion.getEstablecimiento().getEntidad().equals(this)).toList();

        List<Incidente> todosLosIncidentes = new RepoIncidente().buscarTodos();
        List<Incidente> incidentesDeLaSemana = todosLosIncidentes.stream().
            filter(incidente -> incidente.seReportoEnLaSemanaDeLaFecha(fechaDeSemana)).
            toList();

        Integer cantidadIncidentesNoRepetidosDeLaSemana = 0;

        // Recorremos la lista de prestaciones con los incidentes que se abrieron en la ultima semana
        for (Prestacion prestacion : prestacionesDeEntidad) {
            // Filtramos los incidentes que se abrieron en la ultima semana y que corresponden a la prestacion actual
            List<Incidente> incidentesDeLaPrestacion = incidentesDeLaSemana.stream().
                    filter(incidente -> incidente.getPrestacion().getId().equals(prestacion.getId())).toList();

            while (incidentesDeLaPrestacion.size() > 0) {
                Incidente primerIncidente = incidentesDeLaPrestacion.get(0);

                cantidadIncidentesNoRepetidosDeLaSemana++;
                incidentesDeLaPrestacion = incidentesDeLaPrestacion.stream().
                        filter(otroIncidente -> !otroIncidente.equals(primerIncidente)).toList();

                if (primerIncidente.estaResuelto()) continue;

                incidentesDeLaPrestacion = incidentesDeLaPrestacion.stream().filter(otroIncidente ->
                        Duration.between(primerIncidente.getHorarioApertura(), otroIncidente.getHorarioApertura()).toHours() > 24).
                        toList();
            }
        }

        return cantidadIncidentesNoRepetidosDeLaSemana;
    }

    public double calcularImpactoProblematicas(LocalDateTime fechaDeSemana) {
        //TODO entrega 4
        return 0;
    }

    @Override
    public String toString() {
        return "{" +
                "\"nombre\":\"" + nombre + "\"" +
                ", \"localizacion\":" + localizacion +
                ", \"establecimientos\":" + establecimientos +
                ", \"atributosVariables\":" + atributosVariables +
                ", \"usuariosAsignados\":" + usuariosAsignados +
                '}';
    }
}
