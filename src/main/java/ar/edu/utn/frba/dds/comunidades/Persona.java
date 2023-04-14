package ar.edu.utn.frba.dds.comunidades;

import lombok.Getter;
import lombok.Setter;

public class Persona {
    @Setter @Getter
    private String nombre;

    @Setter @Getter
    private String tel;

    @Setter @Getter
    private String mail;
    private Usuario usuario;

    public Persona(String nombre, String tel, String mail) {
        this.nombre = nombre;
        this.tel = tel;
        this.mail = mail;
        this.usuario = null; // todavia no esta creado
    }

    public void registrarse(String usuario, String contrasenia) {
        //TODO
    }
}
