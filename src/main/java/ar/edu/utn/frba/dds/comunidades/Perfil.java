package ar.edu.utn.frba.dds.comunidades;

import ar.edu.utn.frba.dds.incidentes.Incidente;
import ar.edu.utn.frba.dds.notificaciones.ConfiguracionNotificacion;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Perfil {
    private String nickname;
    // foto
    private Comunidad comunidad;
    private TipoPerfil tipoPerfil;
    private TipoMiembro tipoMiembro;
    private Usuario usuario;


    public Perfil(String nickname, Comunidad comunidad, TipoPerfil tipoPerfil) {
        this.nickname = nickname;
        this.comunidad = comunidad;
        this.tipoPerfil = tipoPerfil;
    }
    public Perfil() {
    }

    /*public void recibirNotificacionDeAperturaDeIncidente(Incidente incidente) {

        usuario.getConfiguracionNotificacion().notificarIncidenteNuevo(incidente);

    }

    public void recibirNotificacionDeCierreDeIncidente(Incidente incidente) {

        usuario.getConfiguracionNotificacion().notificarConclusionDeIncidente(incidente);

    }*/



}
