package ar.edu.utn.frba.dds.models.repositorios.reposDeprecados;

import ar.edu.utn.frba.dds.models.comunidades.Comunidad;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class RepoComunidadDeprecado {
    private static RepoComunidadDeprecado instancia = null;
    @Getter
    private static List<Comunidad> listaComunidad;

    public static void agregarComunidad(Comunidad comunidad) {
        listaComunidad.add(comunidad);
    }

    private RepoComunidadDeprecado() {
        listaComunidad = new ArrayList<>();
    }

    public static RepoComunidadDeprecado getInstancia() {
        if (instancia == null) {
            instancia = new RepoComunidadDeprecado();
        }
        return instancia;
    }
}