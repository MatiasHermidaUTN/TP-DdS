package ar.edu.utn.frba.dds.models.comunidades;

import ar.edu.utn.frba.dds.models.converters.ConfiguracionNotificacionConverter;
import ar.edu.utn.frba.dds.models.converters.NotificadorConverter;
import ar.edu.utn.frba.dds.models.incidentes.Incidente;

import ar.edu.utn.frba.dds.models.localizacion.Localizacion;
import ar.edu.utn.frba.dds.models.notificaciones.Horario;
import ar.edu.utn.frba.dds.models.notificaciones.medios.Notificador;
import ar.edu.utn.frba.dds.models.persistencia.Persistente;

import ar.edu.utn.frba.dds.models.notificaciones.estrategias.ConfiguracionNotificacion;

import ar.edu.utn.frba.dds.models.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Servicio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Usuario extends Persistente {

    @Column
    private String email;

    @Column
    private String usuario;

    @Column
    private String contrasenia;

    @Column
    private Integer telefono;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Perfil> perfiles;

    @OneToOne(cascade = { CascadeType.ALL})
    @JoinColumn(name = "localizacion_id", referencedColumnName = "id")
    private Localizacion localizacion;

    @ManyToMany(cascade = { CascadeType.ALL})
    private List<Entidad> entidadesInteres;

    @ManyToMany(cascade = { CascadeType.ALL})
    private List<Servicio> serviciosInteres;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario")
    private TipoUsuario tipoUsuario;

    @Convert(converter = ConfiguracionNotificacionConverter.class)
    @Column(name = "configuracion_notificacion")
    private ConfiguracionNotificacion configuracionNotificacion;

    @Convert(converter = NotificadorConverter.class)
    @Column
    private Notificador notificador;

    @ManyToMany(cascade = { CascadeType.ALL})
    List<Horario> horarios = new ArrayList<Horario>();

    @ManyToMany
    @JoinTable(name="usuario_incidentes_nuevos")
    List<Incidente> incidentesNuevos = new ArrayList<Incidente>();

    @ManyToMany
    @JoinTable(name="usuario_incidentes_concluidos")
    List<Incidente> incidentesConcluidos = new ArrayList<Incidente>();

    public Usuario(String email, String usuario, String contrasenia, Localizacion localizacion) {
        this.email = email;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.perfiles = new ArrayList<>();
        this.localizacion = localizacion;
        this.entidadesInteres = new ArrayList<>();
        this.serviciosInteres = new ArrayList<>();
    }
    public Usuario() {
        this.perfiles = new ArrayList<>();
        this.entidadesInteres = new ArrayList<>();
        this.serviciosInteres = new ArrayList<>();
    }

    public Usuario(String mail, String usuario, String contrasenia) {
        this.email = mail;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.perfiles = new ArrayList<>();
        this.entidadesInteres = new ArrayList<>();
        this.serviciosInteres = new ArrayList<>();
        //RepoUsuarioDeprecado.getInstancia().agregarUsuario(this);
    }

    public Usuario(String mail, String usuario, String contrasenia, TipoUsuario tipoUsuario) {
        this.email = mail;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.perfiles = new ArrayList<>();
        this.entidadesInteres = new ArrayList<>();
        this.serviciosInteres = new ArrayList<>();
        this.tipoUsuario = tipoUsuario;
        //RepoUsuarioDeprecado.getInstancia().agregarUsuario(this);
    }

    public Boolean tieneApuro() {
        return configuracionNotificacion.apuro();
    }

    public Boolean notificarPorMail() {
        return notificador.porMail();
    }

    public void agregarIncidenteNuevo(Incidente incidente) {
        this.incidentesNuevos.add(incidente);
    }

    public void removerIncidenteNuevo(Incidente incidente) {
        this.incidentesNuevos.remove(incidente);
    }

    public void agregarIncidenteConcluido(Incidente incidente) {
        this.incidentesNuevos.add(incidente);
    }

    public void incidentesNotificados() {
        incidentesNuevos.clear();
        incidentesConcluidos.clear();
    }

    public void agregarHorario(Horario horario) {
        this.horarios.add(horario);
    }
    public void eliminarHorario(Horario horario) {
        this.horarios.remove(horario);
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
        if(configuracionNotificacion != null)
            configuracionNotificacion.notificarIncidenteNuevo(incidente, this);
    }

    public void recibirNotificacionDeCierreDeIncidente(Incidente incidente) {
        if(configuracionNotificacion != null)
            configuracionNotificacion.notificarConclusionDeIncidente(incidente, this);
    }

    public void recibirNotificacionDeIncidentesCercanos(List<Incidente> incidentes) {
        if(configuracionNotificacion != null)
            configuracionNotificacion.notificarIncidentesCercanos(incidentes, this);
    }

    public void recibirInformeSemanal(String msjInformeSemanal) {
        if(configuracionNotificacion != null)
            configuracionNotificacion.notificarInformeSemanal(msjInformeSemanal, this);
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + this.getId() +
//                ", \"email\":\"" + email + '\"' +
                ", \"usuario\":\"" + usuario + '\"' +
//                ", \"contrasenia\":\"" + contrasenia + '\"' +
//                ", \"telefono\":" + telefono +
//                ", \"perfiles\":" + perfiles +
//                ", \"localizacion\":" + localizacion +
//                ", \"entidadesInteres\":" + entidadesInteres +
//                ", \"serviciosInteres\":" + serviciosInteres +
//                ", \"tipoUsuario\":" + tipoUsuario +
//                ", \"configuracionNotificacion\":" + configuracionNotificacion +
//                ", \"notificador\":" + notificador +
//                ", \"horarios\":" + horarios +
//                ", \"incidentesNuevos\":" + incidentesNuevos +
//                ", \"incidentesConcluidos\":" + incidentesConcluidos +
                '}';
    }
}
