package ar.edu.utn.frba.dds.comunidades;

import ar.edu.utn.frba.dds.repositorios.RepoUsuario;
import ar.edu.utn.frba.dds.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.serviciosPublicos.localizacion.Localizacion;
import ar.edu.utn.frba.dds.serviciosPublicos.Servicio;
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
    private List<Perfil> perfiles;
    @Setter
    private Localizacion localizacion;
    private List<Entidad> entidadesInteres;
    private List<Servicio> serviciosInteres;
    @Setter
    private TipoUsuario tipoUsuario;

    public Usuario(String email, String usuario, String contrasenia, Localizacion localizacion, TipoUsuario tipoUsuario) {
        this.email = email;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.perfiles = new ArrayList<>();
        this.localizacion = localizacion;
        this.entidadesInteres = new ArrayList<>();
        this.serviciosInteres = new ArrayList<>();
        this.tipoUsuario = tipoUsuario;
        RepoUsuario.getInstancia().agregarUsuario(this);
    }
}
