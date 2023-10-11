package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.comunidades.*;
import ar.edu.utn.frba.dds.models.converters.TipoMiembroConverter;
import ar.edu.utn.frba.dds.models.converters.TipoPerfilConverter;
import ar.edu.utn.frba.dds.models.georef.AdapterGeoref;
import ar.edu.utn.frba.dds.models.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.localizacion.Localidad;
import ar.edu.utn.frba.dds.models.localizacion.Localizacion;
import ar.edu.utn.frba.dds.models.localizacion.Ubicacion;
import ar.edu.utn.frba.dds.models.repositorios.RepoComunidad;
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
    private RepoComunidad repoComunidad;
    private AdapterGeoref adapterGeoref;
    private Validador validador;

    public UsuariosController(RepoUsuario repoUsuario, RepoLocalidad repoLocalidad, RepoComunidad repoComunidad, AdapterGeoref adapterGeoref, Validador validador) {
        this.repoUsuario = repoUsuario;
        this.validador = validador;
        this.repoLocalidad = repoLocalidad;
        this.adapterGeoref = adapterGeoref;
        this.repoComunidad = repoComunidad;
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
            repoUsuario.guardar(new Usuario(email, usuario, contrasenia));
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
        Usuario user = repoUsuario.buscarPorUsuarioYContrasenia(usuario, contrasenia);
        if(user == null){
            context.result("No existe el usuario, por favor volve a intentarlo.");
            Thread.sleep(4000);
            //context.redirect("/login"); si se ejecuta esto, no se muestra el mensaje de error
        }
        else{
            context.result("Usuario logeado correctamente.");
            Thread.sleep(4000);
            context.redirect("/usuarios/" + user.getId() + "/perfiles");
        }
    }

    public void mostrar_perfiles(Context context){
        Map<String, Object> model = new HashMap<>();
        List<Perfil> perfiles = this.repoUsuario.buscarPorId(Integer.valueOf(context.pathParam("usuario_id"))).getPerfiles();
        model.put("perfiles", perfiles);
        model.put("usuario_id",context.pathParam("usuario_id"));
        context.render("usuarios/perfiles.hbs", model);
    }

    public void crear_perfil(Context context){
        Map<String, Object> model = new HashMap<>();
        model.put("usuario_id",context.pathParam("usuario_id"));
        model.put("comunidades", this.repoComunidad.buscarTodos());
        model.put("tipoMiembros", TipoMiembro.values());
        model.put("tipoPerfiles", TipoPerfil.values());
        context.render("usuarios/crear_perfil.hbs", model);
    }

    public void procesar_creacion_perfil(Context context){
        // Obtengo los inputs del usuario en el formulario
        String nickname = context.formParam("nickname");
        TipoPerfil tipoPerfil = TipoPerfilConverter.convertirAObjeto(context.formParam("tipoPerfil"));
        TipoMiembro tipoMiembro = TipoMiembroConverter.convertirAObjeto(context.formParam("tipoMiembro"));
        Integer comunidad_id = Integer.valueOf(context.formParam("comunidad"));
        Comunidad comunidad = this.repoComunidad.buscarPorId(comunidad_id);

        // Los cargo en una nueva instancia de Perfil
        Perfil nuevoPerfil = new Perfil(nickname, comunidad, tipoPerfil);
        nuevoPerfil.setTipoMiembro(tipoMiembro);

        // Guardo el Perfil en la base (se lo asigno al usuario, no hay repoPerfil)
        Usuario usuario = this.repoUsuario.buscarPorId(Integer.valueOf(context.pathParam("usuario_id")));
        usuario.agregarPerfil(nuevoPerfil);
        this.repoUsuario.modificar(usuario);

        // Guardo el Perfil en la comunidad
        comunidad.agregarMiembros(nuevoPerfil);
        this.repoComunidad.modificar(comunidad);

        context.redirect("/usuarios/" + context.pathParam("usuario_id") +"/perfiles");
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

    public void agregarLocalizacionUsuario(Integer idUsuario, String direccion, String localidad) throws IOException {
        Localidad unaLocalidad = repoLocalidad.buscarPorNombre(localidad);
        Ubicacion unaUbicacion = adapterGeoref.obtenerUbicacion(direccion, unaLocalidad);

        Localizacion localizacion = new Localizacion(unaLocalidad, unaUbicacion);

        Usuario usuario = repoUsuario.buscarPorId(idUsuario);

        usuario.setLocalizacion(localizacion);

        repoUsuario.modificar(usuario);
    }
}
