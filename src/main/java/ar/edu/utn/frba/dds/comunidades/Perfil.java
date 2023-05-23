package ar.edu.utn.frba.dds.comunidades;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Perfil {
    private String nickname;
    // foto
    private Comunidad comunidad;
    private TipoPerfil tipoPerfil;

    public Perfil(String nickname, Comunidad comunidad, TipoPerfil tipoPerfil) {
        this.nickname = nickname;
        this.comunidad = comunidad;
        this.tipoPerfil = tipoPerfil;
    }
}
