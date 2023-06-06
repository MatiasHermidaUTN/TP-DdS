package ar.edu.utn.frba.dds.serviciosPublicos;

import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.repositorios.RepoEntidad;
import lombok.Getter;
import lombok.Setter;

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
        RepoEntidad.getInstancia().agregarEntidad(this);
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

}
