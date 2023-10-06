package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import ar.edu.utn.frba.dds.models.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.repositorios.RepoIncidente;
import ar.edu.utn.frba.dds.models.repositorios.RepoUsuario;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuariosController {
    private RepoUsuario repoUsuario;

    public UsuariosController(RepoUsuario repoUsuario) {
        this.repoUsuario = repoUsuario;
    }

    public void index(Context context){
        // TODO
    }

    public void show(Context context){
        // TODO
    }

    public void registrar(Context context){
        Usuario usuario = null;
        Map<String, Object> model = new HashMap<>();
        model.put("usuario", usuario);
        context.render("usuarios/register.hbs", model);
    }

    public void save(Context context){
        // TODO
    }

    public void edit(Context context){
        // TODO
    }

    public void delete(Context context){
        // TODO
    }

    public void cerrar(Context context){
        // TODO
    }

    public void asignarParametros(Usuario usuario, Context context){
        // TODO
    }
}
