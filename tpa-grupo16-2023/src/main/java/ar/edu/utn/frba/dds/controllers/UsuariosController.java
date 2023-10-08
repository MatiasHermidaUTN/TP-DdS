package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import ar.edu.utn.frba.dds.models.georef.AdapterGeoref;
import ar.edu.utn.frba.dds.models.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.localizacion.Localidad;
import ar.edu.utn.frba.dds.models.localizacion.Localizacion;
import ar.edu.utn.frba.dds.models.localizacion.Ubicacion;
import ar.edu.utn.frba.dds.models.repositorios.RepoIncidente;
import ar.edu.utn.frba.dds.models.repositorios.RepoLocalidad;
import ar.edu.utn.frba.dds.models.repositorios.RepoUsuario;
import ar.edu.utn.frba.dds.models.validador.Resultado;
import ar.edu.utn.frba.dds.models.validador.Validador;
import io.javalin.http.Context;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UsuariosController {
    private RepoUsuario repoUsuario;
    private RepoLocalidad repoLocalidad;
    private AdapterGeoref adapterGeoref;
    private Validador validador;

    public UsuariosController(RepoUsuario repoUsuario, RepoLocalidad repoLocalidad, AdapterGeoref adapterGeoref, Validador validador) {
        this.repoUsuario = repoUsuario;
        this.validador = validador;
        this.repoLocalidad = repoLocalidad;
        this.adapterGeoref = adapterGeoref;
    }

    public void index(Context context){
        // TODO
    }

    public void show(Context context){
        // TODO
    }

    public void registrar(Context context){
        Map<String, Object> model = new HashMap<>();
        context.render("usuarios/register.hbs", model);
    }

    public void guardar_usuario(Context context) throws InterruptedException {

        String usuario = context.formParam("usuario");
        String contrasenia = context.formParam("contrasenia");
        String email = context.formParam("email");
        validador.cargarConfig1();
        Resultado resultado = validador.logear(usuario, contrasenia);
        if(resultado.isValor()){
            crearUsuario(email, usuario, contrasenia);
            context.result("El usuario ha sido registrado correctamente");
//            Thread.sleep(4000);
//            context.redirect("/login");
        }
        else {
            context.result(resultado.getMensajeError());
//            Thread.sleep(4000);
//            context.redirect("/register");
        }
    }

    public void logear(Context context){
        Map<String, Object> model = new HashMap<>();
        context.render("usuarios/login.hbs", model);
    }

    public void procesar_login(Context context) throws InterruptedException {
        String usuario = context.formParam("usuario");
        String contrasenia = context.formParam("contrasenia");
        if(!existeUsuario(usuario, contrasenia)){
            context.result("No existe el usuario, por favor volve a intentarlo.");
            Thread.sleep(4000);
            //context.redirect("/login");
        }
        else{
            context.result("Usuario logeado correctamente.");
            Thread.sleep(4000);
            //context.redirect("/perfiles_menu");
        }
    }

    public Boolean existeUsuario(String usuario, String contrasenia){
        List<Usuario> usuarios = this.repoUsuario.buscarTodos();
        for(Usuario user : usuarios){
            if(Objects.equals(user.getUsuario(), usuario) && Objects.equals(user.getContrasenia(), contrasenia))
                return true;
        }
        return false;
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

    public void crearUsuario(String mail, String usuario, String contrasenia) {
        repoUsuario.guardar(new Usuario(mail, usuario, contrasenia));
    }

    public void agregarLocalizacionUsuario(Integer idUsuario, String direccion, String localidad) throws IOException {
        Localidad unaLocalidad = repoLocalidad.buscarPorNombre(localidad);
        Ubicacion unaUbicacion = adapterGeoref.obtenerUbicacion(direccion, unaLocalidad);

        Localizacion localizacion = new Localizacion(unaLocalidad, unaUbicacion);

        Usuario usuario = repoUsuario.buscarPorId(idUsuario);

        usuario.setLocalizacion(localizacion);

        repoUsuario.modificar(usuario);
    }
}
