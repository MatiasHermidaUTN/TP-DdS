package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.comunidades.*;
import ar.edu.utn.frba.dds.models.converters.*;
import ar.edu.utn.frba.dds.models.georef.AdapterGeoref;
import ar.edu.utn.frba.dds.models.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.incidentes.Observacion;
import ar.edu.utn.frba.dds.models.localizacion.*;
import ar.edu.utn.frba.dds.models.notificaciones.Horario;
import ar.edu.utn.frba.dds.models.notificaciones.cron.DiaSemana;
import ar.edu.utn.frba.dds.models.notificaciones.estrategias.ConfiguracionNotificacion;
import ar.edu.utn.frba.dds.models.notificaciones.medios.Notificador;
import ar.edu.utn.frba.dds.models.repositorios.*;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Servicio;
import ar.edu.utn.frba.dds.models.validador.Resultado;
import ar.edu.utn.frba.dds.models.validador.Validador;
import ar.edu.utn.frba.dds.utils.Logueo;
import io.javalin.http.Context;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.lang.Thread.sleep;

public class UsuariosController {
    private RepoUsuario repoUsuario;
    private RepoLocalidad repoLocalidad;
    private RepoComunidad repoComunidad;
    private RepoPerfil repoPerfil;
    private RepoProvincia repoProvincia;
    private RepoServicio repoServicio;

    private RepoHorario repoHorario;
    private RepoEntidad repoEntidad;
    private AdapterGeoref adapterGeoref;
    private Validador validador;

    public UsuariosController(RepoUsuario repoUsuario, RepoLocalidad repoLocalidad, RepoComunidad repoComunidad, RepoPerfil repoPerfil, RepoHorario repoHorario, AdapterGeoref adapterGeoref, Validador validador, RepoProvincia repoProvincia, RepoEntidad repoEntidad, RepoServicio repoServicio) {
        this.repoUsuario = repoUsuario;
        this.validador = validador;
        this.repoLocalidad = repoLocalidad;
        this.adapterGeoref = adapterGeoref;
        this.repoComunidad = repoComunidad;
        this.repoPerfil = repoPerfil;
        this.repoHorario = repoHorario;
        this.repoProvincia = repoProvincia;
        this.repoEntidad = repoEntidad;
        this.repoServicio = repoServicio;
    }

    public void registrar(Context context){
        Map<String, Object> model = new HashMap<>();
        model.put("tipoUsuarios", TipoUsuario.values());
        context.render("usuarios/register.hbs", model);
    }

    public void guardar_usuario(Context context) throws InterruptedException {

        String usuario = context.formParam("usuario");
        TipoUsuario tipoUsuario = TipoUsuarioConverter.convertirAObjeto(context.formParam("tipoUsuario"));
        String contrasenia = context.formParam("contrasenia");
        String email = context.formParam("email");
        validador.cargarConfig1();
        Resultado resultado = validador.logear(usuario, contrasenia);
        if(resultado.isValor()){
            repoUsuario.guardar(new Usuario(email, usuario, contrasenia, tipoUsuario));
            String redirectScript = """
                    <script>
                    window.alert(\"El Usuario ha sido creado correctamente.\");
                    setTimeout(function() { window.location.href = '/login'; }, 0);
                    </script>
                    """;

            context.html(redirectScript);
        }
        else {
            String redirectScript =
                    "<script> window.alert(\"" + resultado.getMensajeError() + ".\");" +
                    "setTimeout(function() { window.location.href = '/register'; }, 0); </script>";
            context.html(redirectScript);
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
//            context.result("No existe el usuario, por favor volve a intentarlo.");
            //Thread.sleep(4000);
//            context.redirect("/login"); //si se ejecuta esto, no se muestra el mensaje de error
            String redirectScript = """
                    <script>
                    window.alert(\"No existe el usuario, por favor volve a intentarlo.\");
                    setTimeout(function() { window.location.href = '/login'; }, 0);
                    </script>
                    """;

            context.html(redirectScript);
        }
        else{
//            context.result("Usuario logeado correctamente.");
            //Thread.sleep(4000);

            // Guardo el id del usuario en una cookie
            context.cookie("usuario_id", String.valueOf(user.getId()));
            String redirectScript = """
                    <script>
                    window.alert(\"Usuario logeado correctamente.\");
                    """ +
                    "setTimeout(function() { window.location.href = '/usuarios/perfiles'; }, 0); </script>";
            context.html(redirectScript);
        }
    }

    public void mostrar_perfiles(Context context){
        if(Logueo.comprobarLogueo(context)) {
            Map<String, Object> model = new HashMap<>();
            List<Perfil> perfiles = this.repoUsuario.buscarPorId(Integer.valueOf(context.cookie("usuario_id"))).getPerfiles();
            model.put("perfiles", perfiles);
            model.put("usuario_id", context.cookie("usuario_id"));
            context.render("usuarios/perfiles.hbs", model);
        }
    }

    public void mostrar_perfil(Context context){
        if(Logueo.comprobarLogueo(context)) {
            String perfil_id = context.pathParam("perfil_id");
            context.cookie("perfil_id", perfil_id);
            Perfil perfil = this.repoPerfil.buscarPorId(Integer.valueOf(perfil_id));
            Map<String, Object> model = new HashMap<>();
            model.put("perfil", perfil);
            model.put("usuario_id", context.cookie("usuario_id")); // funciona la cookie :)
            context.render("usuarios/perfil.hbs", model);
        }
    }

    public void crear_perfil(Context context){
        if(Logueo.comprobarLogueo(context)) {
            Map<String, Object> model = new HashMap<>();
            model.put("usuario_id", context.cookie("usuario_id"));
            model.put("comunidades", this.repoComunidad.buscarTodos());
            model.put("tipoMiembros", TipoMiembro.values());
            model.put("tipoPerfiles", TipoPerfil.values());
            context.render("usuarios/crear_perfil.hbs", model);
        }
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
        Usuario usuario = this.repoUsuario.buscarPorId(Integer.valueOf(context.cookie("usuario_id")));
        nuevoPerfil.setUsuario(usuario);
        usuario.agregarPerfil(nuevoPerfil);
        this.repoUsuario.modificar(usuario);

        // Guardo el Perfil en la comunidad.hbs
        comunidad.agregarMiembros(nuevoPerfil);
        this.repoComunidad.modificar(comunidad);

        context.redirect("/usuarios/perfiles");
    }


    public void agregarLocalizacionUsuario(Integer idUsuario, String direccion, String localidad) throws IOException {
        Localidad unaLocalidad = repoLocalidad.buscarPorNombre(localidad);
        Ubicacion unaUbicacion = adapterGeoref.obtenerUbicacion(direccion, unaLocalidad);

        Localizacion localizacion = new Localizacion(unaLocalidad, unaUbicacion);

        Usuario usuario = repoUsuario.buscarPorId(idUsuario);

        usuario.setLocalizacion(localizacion);

        repoUsuario.modificar(usuario);
    }

    public void logout(Context context) {
        context.removeCookie("usuario_id");
        context.removeCookie("perfil_id");
        context.redirect("/login");
    }

    public void configuracion_de_usuario(Context context) {
        if(Logueo.comprobarLogueo(context)) {
            Map<String, Object> model = new HashMap<>();
            Usuario usuario = repoUsuario.buscarPorId(Integer.valueOf(context.cookie("usuario_id")));
            model.put("usuario", usuario);
            context.render("usuarios/usuario.hbs", model);
        }
    }

    public void editar_datos(Context context) {
        if(Logueo.comprobarLogueo(context)) {
            Map<String, Object> model = new HashMap<>();
            Usuario usuario = repoUsuario.buscarPorId(Integer.valueOf(context.cookie("usuario_id")));
            List<Provincia> provincias = repoProvincia.buscarTodos();
            model.put("usuario", usuario);
            model.put("provincias", provincias);
            context.render("usuarios/editar_usuario.hbs", model);
        }
    }

    public void procesar_edicion_datos(Context context) {
        if(Logueo.comprobarLogueo(context)) {
            Usuario usuario = repoUsuario.buscarPorId(Integer.valueOf(context.cookie("usuario_id")));
            usuario.setUsuario(context.formParam("usuario"));
            usuario.setEmail(context.formParam("email"));
            usuario.setTelefono(Integer.valueOf(context.formParam("telefono")));

            if(!Objects.equals(context.formParam("direccion"), "")){
                // agregar localizacion al usuario
                Integer provincia = Integer.valueOf(context.formParam("provincia"));
                Integer departamento = Integer.valueOf(context.formParam("departamento"));
                Long localidad = Long.valueOf(context.formParam("localidad"));
                String direccion = context.formParam("direccion");

                String stringLocalidad = repoLocalidad.buscarPorId(localidad).nombre;

                Localizacion localizacion = null;

                try {
                    agregarLocalizacionUsuario(usuario.getId(), direccion, stringLocalidad);
//                localizacion = adapterGeoref.obtenerLocalizacionIds(provincia, departamento, localidad, direccion);
                } catch(RuntimeException | IOException e){
                    String poneBienLaDirec = "<script> window.alert(\"Coloque una direccion Valida.\");"
                            + "setTimeout(function() { window.location.href = '/usuarios/usuario/editar'; }, 500); </script>";
                    context.html(poneBienLaDirec);
                }
            }

//            usuario.setLocalizacion(localizacion);

            repoUsuario.modificar(usuario);

            context.redirect("/usuarios/usuario");
        }
    }

    public void cambiar_contrasenia(Context context) {
        if(Logueo.comprobarLogueo(context)) {
            Map<String, Object> model = new HashMap<>();
            context.render("usuarios/cambiar_contrasenia.hbs", model);
        }
    }

    public void procesar_cambio_contrasenia(Context context) {
        Usuario usuario = repoUsuario.buscarPorId(Integer.valueOf(context.cookie("usuario_id")));
        String contrasenia = context.formParam("contrasenia");
        validador.cargarConfig1();
        Resultado resultado = validador.logear(usuario.getUsuario(), contrasenia);
        if(resultado.isValor()){
            usuario.setContrasenia(contrasenia);
            repoUsuario.modificar(usuario);
            String redirectScript = """
                    <script>
                    window.alert(\"La contraseña se cambio exitosamente.\");
                    setTimeout(function() { window.location.href = '/usuarios/usuario'; }, 500);
                    </script>
                    """;

            context.html(redirectScript);
        }
        else {
            String redirectScript =
                    "<script> window.alert(\"" + resultado.getMensajeError() + ".\");" +
                            "setTimeout(function() { window.location.href = '/usuarios/usuario/password'; }, 500); </script>";
            context.html(redirectScript);
        }
    }

    public void configurar_envio_notificaciones(Context context) {
        if(Logueo.comprobarLogueo(context)) {
            Map<String, Object> model = new HashMap<>();
            Usuario usuario = repoUsuario.buscarPorId(Integer.valueOf(context.cookie("usuario_id")));
            model.put("usuario", usuario);
            context.render("usuarios/configuracion_notificaciones.hbs", model);
        }
    }

    public void procesar_configuracion_notificaciones(Context context) {
        if(Logueo.comprobarLogueo(context)) {
            ConfiguracionNotificacionConverter converterConfigNotific = new ConfiguracionNotificacionConverter();
            NotificadorConverter converterNotificacion = new NotificadorConverter();
            Usuario usuario = repoUsuario.buscarPorId(Integer.valueOf(context.cookie("usuario_id")));
            String configNotific = context.formParam("configuracion");
            String medioNotific = context.formParam("medio");
            ConfiguracionNotificacion configuracion = converterConfigNotific.convertToEntityAttribute(configNotific);
            Notificador notificador = converterNotificacion.convertToEntityAttribute((medioNotific));
            usuario.setConfiguracionNotificacion(configuracion);
            usuario.setNotificador(notificador);
            this.repoUsuario.modificar(usuario);

            context.redirect("/usuarios/usuario");
        }
    }

    public void horarios(Context context) {
        if(Logueo.comprobarLogueo(context)) {
            Map<String, Object> model = new HashMap<>();
            Usuario usuario = repoUsuario.buscarPorId(Integer.valueOf(context.cookie("usuario_id")));
            model.put("usuario", usuario);
            context.render("usuarios/horarios.hbs", model);
        }
    }

    public void quitar_horario(Context context) {
        if(Logueo.comprobarLogueo(context)) {
            Horario horario = repoHorario.buscarPorId(Integer.valueOf(context.pathParam("id")));
            Usuario usuario = repoUsuario.buscarPorId(Integer.valueOf(context.cookie("usuario_id")));
            usuario.eliminarHorario(horario);
            this.repoUsuario.modificar(usuario);
            this.repoHorario.eliminar(horario);

            context.html("<script> setTimeout(function() { window.location.href = '/usuarios/usuario/notificaciones/horarios'; }, 500); </script>");
        }
    }

    public void agregar_horario(Context context) {
        if(Logueo.comprobarLogueo(context)) {
            Map<String, Object> model = new HashMap<>();
            context.render("usuarios/agregar_horario.hbs", model);
        }
    }

    public void procesar_horario(Context context) {
        if(Logueo.comprobarLogueo(context)) {
            DiaSemanaConverter diaConverter = new DiaSemanaConverter();
            DiaSemana dia = diaConverter.convertToEntityAttribute(context.formParam("dia"));
            Horario horario = new Horario(dia, Integer.valueOf(context.formParam("hora")));
            Usuario usuario = repoUsuario.buscarPorId(Integer.valueOf(context.cookie("usuario_id")));
            usuario.agregarHorario(horario);
            repoUsuario.modificar(usuario);

            context.redirect("/usuarios/usuario/notificaciones/horarios");
        }
    }

    public void cargar_intereses(Context context){
        if(Logueo.comprobarLogueo(context)) {
            Map<String, Object> model = new HashMap<>();
            Usuario usuario = repoUsuario.buscarPorId(Integer.valueOf(context.cookie("usuario_id")));

            model.put("entidadesInteres", usuario.getEntidadesInteres());
            model.put("serviciosInteres", usuario.getServiciosInteres());
            model.put("todasLasEntidades", this.repoEntidad.buscarTodos());
            model.put("todosLosServicios", this.repoServicio.buscarTodos());
            model.put("usuario", usuario);

            context.render("usuarios/intereses.hbs", model);
        }
    }

    public void agregar_entidad_interes(Context context){
        Entidad entidadNueva = this.repoEntidad.buscarPorId(Integer.valueOf(context.formParam("entidad")));
        Usuario usuario = this.repoUsuario.buscarPorId(Integer.valueOf(context.cookie("usuario_id")));

        usuario.agregarEntidadInteres(entidadNueva);

        this.repoUsuario.guardar(usuario);

        context.redirect("/usuarios/usuario");
    }

    public void agregar_servicio_interes(Context context){
        Servicio servicioNuevo = this.repoServicio.buscarPorId(Integer.valueOf(context.formParam("servicio")));
        Usuario usuario = this.repoUsuario.buscarPorId(Integer.valueOf(context.cookie("usuario_id")));

        usuario.agregarServicioInteres(servicioNuevo);

        this.repoUsuario.guardar(usuario);

        context.redirect("/usuarios/usuario");
    }
}
