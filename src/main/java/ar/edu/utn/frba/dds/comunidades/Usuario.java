package ar.edu.utn.frba.dds.comunidades;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Usuario {
    private String usuario;
    private String constrasenia;

    public Usuario(String usuario, String constrasenia) {
        this.usuario = usuario;
        this.constrasenia = constrasenia;
    }
}
