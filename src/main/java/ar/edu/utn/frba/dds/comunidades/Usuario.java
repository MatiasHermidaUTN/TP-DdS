package ar.edu.utn.frba.dds.comunidades;

import ar.edu.utn.frba.dds.repositorios.RepoUsuario;
import ar.edu.utn.frba.dds.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.serviciosPublicos.Servicio;
import ar.edu.utn.frba.dds.localizacion.Localizacion;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Usuario {
    @Setter
    private String email;
    @Setter
    private String usuario;
    @Setter
    private String contrasenia;
    @Setter
    private Integer telefono;
    private List<Perfil> perfiles;
    @Setter
    private Localizacion localizacion;
    private List<Entidad> entidadesInteres;
    private List<Servicio> serviciosInteres;
    private TipoUsuario tipoUsuario;

    public Usuario(String email, String usuario, String contrasenia, Localizacion localizacion) {
        this.email = email;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.perfiles = new ArrayList<>();
        this.localizacion = localizacion;
        this.entidadesInteres = new ArrayList<>();
        this.serviciosInteres = new ArrayList<>();
        RepoUsuario.getInstancia().agregarUsuario(this);
    }

    public void agregarEntidadInteres(Entidad entidad){
        this.entidadesInteres.add(entidad);
    }

    public void eliminarEntidadInteres(Entidad entidad){
        this.entidadesInteres.remove(entidad);
    }

    public void agregarServicioInteres(Servicio servicio){
        this.serviciosInteres.add(servicio);
    }

    public void eliminarServicioInteres(Servicio servicio){
        this.serviciosInteres.remove(servicio);
    }
}
