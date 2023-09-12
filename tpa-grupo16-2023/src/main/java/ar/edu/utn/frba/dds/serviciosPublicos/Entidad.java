package ar.edu.utn.frba.dds.serviciosPublicos;

import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.incidentes.EstadoIncidente;
import ar.edu.utn.frba.dds.incidentes.Incidente;
import ar.edu.utn.frba.dds.incidentes.Prestacion;
import ar.edu.utn.frba.dds.localizacion.Localizacion;
import ar.edu.utn.frba.dds.persistencia.Persistente;
import ar.edu.utn.frba.dds.repositorios.reposDeprecados.RepoIncidenteDeprecado;
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
public class Entidad extends Persistente {

    @Column
    private String nombre;

    @OneToOne
    @JoinColumn(name = "localizacion_id", referencedColumnName = "id")
    @Setter
    private Localizacion localizacion;

    @Transient
    private List<Establecimiento> establecimientos;

    @ManyToMany
    private List<AtributoVariable> atributosVariables;

    @ManyToMany
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
        List<Incidente> incidentes = RepoIncidenteDeprecado.getInstancia().getListaIncidentes().stream()
                .filter(unIncidete -> unIncidete.seOriginoEnEntidad(this))
                .filter(incidente -> incidente.seCerroEnLaSemanaDeLaFecha(fechaDeSemana))
                .toList();

        return incidentes.stream().mapToDouble(incidente -> incidente.minutosEntreAperturaYCierre()).sum() / incidentes.size();
    }

    public int cantIncidentesEnLaSemana(List<Prestacion> listaDePrestacionesGlobal, LocalDateTime fechaDeSemana) {
        //Filtrar las prestacionesDeEntidad de la entidad
        List<Prestacion> prestacionesDeEntidad = listaDePrestacionesGlobal.stream().
                filter(prestacion -> prestacion.getEstablecimiento().getEntidad().equals(this)).toList();

        Integer cantidadIncidentesNoRepetidosDeLaSemana = 0;

        // Recorremos la lista de prestaciones con los incidentes que se abrieron en la ultima semana
        for (Prestacion prestacion : prestacionesDeEntidad) {
            List<Incidente> incidentesDeLaSemana = prestacion.getIncidentes().stream().
                filter(incidente -> incidente.seReportoEnLaSemanaDeLaFecha(fechaDeSemana)).
                toList();

            while (incidentesDeLaSemana.size() > 0) {
                Incidente primerIncidente = incidentesDeLaSemana.get(0);

                cantidadIncidentesNoRepetidosDeLaSemana++;
                incidentesDeLaSemana = incidentesDeLaSemana.stream().
                        filter(otroIncidente -> !otroIncidente.equals(primerIncidente)).toList();

                if (primerIncidente.getEstado().equals(EstadoIncidente.RESUELTO)) continue;

                incidentesDeLaSemana = incidentesDeLaSemana.stream().filter(otroIncidente ->
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
}
