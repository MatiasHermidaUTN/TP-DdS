package ar.edu.utn.frba.dds.repositorios.reposDeprecados;

import ar.edu.utn.frba.dds.comunidades.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.Getter;


import java.util.ArrayList;
import java.util.List;

public class RepoUsuarioDeprecado implements WithSimplePersistenceUnit {

    private static RepoUsuarioDeprecado instancia = null;

    @Getter
    private static List<Usuario> listaUsuarios;

    public static void agregarUsuario(Usuario usuario) {
        listaUsuarios.add(usuario);
    }

    private RepoUsuarioDeprecado() {
        listaUsuarios = new ArrayList<>();
    }

    public static List<Usuario> buscarTodos() {
        return listaUsuarios;
    }

    public static RepoUsuarioDeprecado getInstancia() {
        if (instancia == null) {
            instancia = new RepoUsuarioDeprecado();
        }
        return instancia;
    }
}
