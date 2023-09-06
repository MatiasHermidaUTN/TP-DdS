package ar.edu.utn.frba.dds.comunidades;

import ar.edu.utn.frba.dds.incidentes.Incidente;
import ar.edu.utn.frba.dds.notificaciones.ConfiguracionNotificacion;
import ar.edu.utn.frba.dds.ranking.InformeSemanal;
import ar.edu.utn.frba.dds.repositorios.RepoUsuario;
import ar.edu.utn.frba.dds.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.serviciosPublicos.Servicio;
import ar.edu.utn.frba.dds.localizacion.Localizacion;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Usuario {
    private String email;
    private String usuario;
    private String contrasenia;
    private Integer telefono;
    private List<Perfil> perfiles;
    private Localizacion localizacion;
    private List<Entidad> entidadesInteres;
    private List<Servicio> serviciosInteres;
    private TipoUsuario tipoUsuario;
    private ConfiguracionNotificacion configuracionNotificacion;

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
    public Usuario() {
        this.perfiles = new ArrayList<>();
        this.entidadesInteres = new ArrayList<>();
        this.serviciosInteres = new ArrayList<>();
        RepoUsuario.getInstancia().agregarUsuario(this);
    }

    public Usuario(String mail, String usuario, String contrasenia) {
        this.email = mail;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.perfiles = new ArrayList<>();
        this.entidadesInteres = new ArrayList<>();
        this.serviciosInteres = new ArrayList<>();
        RepoUsuario.getInstancia().agregarUsuario(this);
    }

    public void agregarPerfil(Perfil perfil){
        this.perfiles.add(perfil);
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

    //Estos son los metodos para notificar:
    public void recibirNotificacionDeAperturaDeIncidente(Incidente incidente) {
        configuracionNotificacion.notificarIncidenteNuevo(incidente);
    }

    public void recibirNotificacionDeCierreDeIncidente(Incidente incidente) {
        configuracionNotificacion.notificarConclusionDeIncidente(incidente);
    }

    public void recibirNotificacionDeIncidentesCercanos(List<Incidente> incidentes) {
        configuracionNotificacion.notificarIncidentesCercanos(incidentes);
    }

    public void recibirInformeSemanal(String msjInformeSemanal) {
        configuracionNotificacion.notificarInformeSemanal(msjInformeSemanal);
    }
}
