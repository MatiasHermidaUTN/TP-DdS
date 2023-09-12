package ar.edu.utn.frba.dds.comunidades;


import ar.edu.utn.frba.dds.incidentes.Incidente;
import ar.edu.utn.frba.dds.notificaciones.estrategias.ConfiguracionNotificacion;
import ar.edu.utn.frba.dds.persistencia.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Setter
@Getter
public class Perfil extends Persistente {

    @Column
    private String nickname;
    // foto

    @ManyToOne
    @JoinColumn(name = "comunidad_id", referencedColumnName = "id")
    private Comunidad comunidad;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_perfil")
    private TipoPerfil tipoPerfil;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_miembro")
    private TipoMiembro tipoMiembro;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    public Perfil(String nickname, Comunidad comunidad, TipoPerfil tipoPerfil) {
        this.nickname = nickname;
        this.comunidad = comunidad;
        this.tipoPerfil = tipoPerfil;
    }
    public Perfil() {
    }



}
