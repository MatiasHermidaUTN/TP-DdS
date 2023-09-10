package ar.edu.utn.frba.dds.comunidades;

import ar.edu.utn.frba.dds.incidentes.Incidente;
import ar.edu.utn.frba.dds.notificaciones.estrategias.ConfiguracionNotificacion;
import ar.edu.utn.frba.dds.repositorios.RepoUsuario;
import ar.edu.utn.frba.dds.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.serviciosPublicos.Servicio;
import ar.edu.utn.frba.dds.localizacion.Localizacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue
    private Integer usuario_id;

    @Column
    private String email;

    @Column
    private String usuario;

    @Column
    private String contrasenia;

    @Column
    private Integer telefono;

    @Transient
    private List<Perfil> perfiles;

    @OneToOne
    @JoinColumn(name = "localizacion_id", referencedColumnName = "localizacion_id")
    private Localizacion localizacion;

    @Transient
    private List<Entidad> entidadesInteres;

    @ManyToMany
    private List<Servicio> serviciosInteres;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario")
    private TipoUsuario tipoUsuario;

    @Transient
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
