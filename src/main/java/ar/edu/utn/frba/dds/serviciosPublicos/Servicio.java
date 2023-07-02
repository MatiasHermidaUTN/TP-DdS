package ar.edu.utn.frba.dds.serviciosPublicos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Servicio {
    private String nombre;
    private Map<String,String> atributosVariables;

    private EstadoServicio estado;
    private RegistroDeCambio registro;

    public Servicio(String nombre) {
        this.nombre = nombre;
        this.atributosVariables = new HashMap<String, String>();
        this.estado = EstadoServicio.DISPONIBLE;
        this.registro = new RegistroDeCambio();
        // guardamos fecha de creacion del servicio
        this.registro.registrarCambio(EstadoServicio.DISPONIBLE, LocalDateTime.now());
    }

    public Servicio() {
    }

    public void agregarAtributoVar(String nombre, String valor) {
        this.atributosVariables.put(nombre, valor);
    }

    public void eliminarAtributoVar(String nombre){
        this.atributosVariables.remove(nombre);
    }
}
