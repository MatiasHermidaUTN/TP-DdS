package ar.edu.utn.frba.dds.serviciosPublicos;

import ar.edu.utn.frba.dds.comunidades.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Servicio {
    private String nombre;
    private Map<String,String> atributosVariables;

    private EstadoServicio estado;
    private List<RegistroDeCambio> registro;

    public Servicio() {}

    public Servicio(String nombre) {
        this.nombre = nombre;
        this.atributosVariables = new HashMap<String, String>();
        this.estado = EstadoServicio.DISPONIBLE;
        this.registro = new ArrayList<>();
        // guardamos fecha de creacion del servicio
        this.registrarCambio(EstadoServicio.DISPONIBLE, LocalDateTime.now(), new Usuario("default@mail.com", "default", "default"));
    }

    public void setEstado(EstadoServicio estado, Usuario usuario) {
        this.estado = estado;
        registrarCambio(estado, LocalDateTime.now(), usuario);
    }

    private void registrarCambio(EstadoServicio estadoServicio, LocalDateTime now, Usuario usuario) {
        RegistroDeCambio registroDeCambio = new RegistroDeCambio(estadoServicio, now, usuario);
        this.registro.add(registroDeCambio);
    }

    public void agregarAtributoVar(String nombre, String valor) {
        this.atributosVariables.put(nombre, valor);
    }

    public void eliminarAtributoVar(String nombre){
        this.atributosVariables.remove(nombre);
    }
}
