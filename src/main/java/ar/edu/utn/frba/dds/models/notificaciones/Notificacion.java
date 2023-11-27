package ar.edu.utn.frba.dds.models.notificaciones;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notificacion {

    private String titulo;
    private String cuerpo;

    public Notificacion(String titulo, String cuerpo) {
        this.titulo = titulo;
        this.cuerpo = cuerpo;
    }
}
