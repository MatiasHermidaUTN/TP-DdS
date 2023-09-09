package ar.edu.utn.frba.dds.comunidades;

import ar.edu.utn.frba.dds.incidentes.Incidente;
import ar.edu.utn.frba.dds.notificaciones.ConfiguracionNotificacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Setter
@Getter
public class Perfil {

    @Id
    @GeneratedValue
    private Integer perfil_id;

    @Column
    private String nickname;
    // foto

    @ManyToOne
    @JoinColumn(name = "comunidad_id", referencedColumnName = "comunidad_id")
    private Comunidad comunidad;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_perfil")
    private TipoPerfil tipoPerfil;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_miembro")
    private TipoMiembro tipoMiembro;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    private Usuario usuario;

    public Perfil(String nickname, Comunidad comunidad, TipoPerfil tipoPerfil) {
        this.nickname = nickname;
        this.comunidad = comunidad;
        this.tipoPerfil = tipoPerfil;
    }
    public Perfil() {
    }



}
