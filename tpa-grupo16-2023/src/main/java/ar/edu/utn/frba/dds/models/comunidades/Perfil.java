package ar.edu.utn.frba.dds.models.comunidades;


import ar.edu.utn.frba.dds.models.persistencia.Persistente;
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


    public boolean esAfectado() {
        return this.tipoMiembro == TipoMiembro.AFECTADO;
    }

    @Override
    public String toString() {
        return "{" +
                "\"nickname\":\"" + nickname + '\"' +
//                ", \"comunidad\":" + comunidad +
                ", \"tipoPerfil\":" + tipoPerfil +
                ", \"tipoMiembro\":" + tipoMiembro +
                ", \"usuario\":" + usuario +
                '}';
    }

}
