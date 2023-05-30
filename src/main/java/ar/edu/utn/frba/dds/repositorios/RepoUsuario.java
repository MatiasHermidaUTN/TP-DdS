package ar.edu.utn.frba.dds.repositorios;

import ar.edu.utn.frba.dds.comunidades.Usuario;


import java.util.ArrayList;
import java.util.List;

public class RepoUsuario {

    private static RepoUsuario instancia = null;
    private static List<Usuario> listaUsuarios;

    public static void agregarUsuario(Usuario usuario) {
        listaUsuarios.add(usuario);
    }

    private RepoUsuario() {
        listaUsuarios = new ArrayList<>();
    }

    public static RepoUsuario getInstancia() {
        if (instancia == null) {
            instancia = new RepoUsuario();
        }
        return instancia;
    }
}
