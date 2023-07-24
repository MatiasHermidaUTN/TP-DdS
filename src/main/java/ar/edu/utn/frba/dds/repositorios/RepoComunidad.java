package ar.edu.utn.frba.dds.repositorios;

import ar.edu.utn.frba.dds.comunidades.Comunidad;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class RepoComunidad {
    private static RepoComunidad instancia = null;
    @Getter
    private static List<Comunidad> listaComunidad;

    public static void agregarComunidad(Comunidad comunidad) {
        listaComunidad.add(comunidad);
    }

    private RepoComunidad() {
        listaComunidad = new ArrayList<>();
    }

    public static RepoComunidad getInstancia() {
        if (instancia == null) {
            instancia = new RepoComunidad();
        }
        return instancia;
    }
}