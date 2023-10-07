package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import ar.edu.utn.frba.dds.models.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.repositorios.RepoIncidente;
import ar.edu.utn.frba.dds.models.repositorios.RepoUsuario;
import ar.edu.utn.frba.dds.models.validador.Resultado;
import ar.edu.utn.frba.dds.models.validador.Validador;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuariosController {
    private RepoUsuario repoUsuario;
    private Validador validador;

    public UsuariosController(RepoUsuario repoUsuario, Validador validador) {
        this.repoUsuario = repoUsuario;
        this.validador = validador;
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

    public void guardar(Context context){

        String usuario = context.formParam("usuario");
        String contrasenia = context.formParam("contrasenia");
        String email = context.formParam("email");
        validador.cargarConfig1();
        Resultado resultado = validador.logear(usuario, contrasenia);
        if(resultado.isValor()){
            repoUsuario.guardar(new Usuario(email, usuario, contrasenia));
            context.result("El usuario ha sido registrado correctamente");
            //context.redirect("/login");
        }
        else {
            context.result(resultado.getMensajeError());
            //context.redirect("/register");
        }
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
