package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.controllers.*;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Router {

    public static void init() {
        Server.app().get("/", ctx -> {
            ctx.redirect("/login");

        });

        Server.app().routes(() -> {

            // Incidentes
            get("comunidades/{id}/incidentes", ((IncidentesController) FactoryController.controller("incidente"))::index);
            get("incidentes/crear", ((IncidentesController) FactoryController.controller("incidente"))::crear);
            post("incidentes/crear", ((IncidentesController) FactoryController.controller("incidente"))::procesar_creacion);
            get("incidentes/{id}/cerrar", ((IncidentesController) FactoryController.controller("incidente"))::cerrar);
            get("incidentes/{id}", ((IncidentesController) FactoryController.controller("incidente"))::show);
            get("incidentes/{id}/observaciones", ((IncidentesController) FactoryController.controller("incidente"))::observaciones);
            get("incidentes/{id}/comentar", ((IncidentesController) FactoryController.controller("incidente"))::agregar_observacion);
            post("incidentes/{id}/comentar", ((IncidentesController) FactoryController.controller("incidente"))::procesar_observacion);
            //TODO f. Sugerencia de revisión de incidentes
            get("/incidentesCercanos", ((IncidentesController) FactoryController.controller("incidente"))::incidentesCercanos);

            // Registro y Login
            get("register", ((UsuariosController) FactoryController.controller("usuario"))::registrar);
            post("register", ((UsuariosController) FactoryController.controller("usuario"))::guardar_usuario);
            get("login", ((UsuariosController) FactoryController.controller("usuario"))::logear);
            post("login", ((UsuariosController) FactoryController.controller("usuario"))::procesar_login);
            post("logout", ((UsuariosController) FactoryController.controller("usuario"))::logout);

            // Perfiles
            get("/usuarios/perfiles", ((UsuariosController) FactoryController.controller("usuario"))::mostrar_perfiles);
            get("/usuarios/perfiles/crear", ((UsuariosController) FactoryController.controller("usuario"))::crear_perfil);
            post("/usuarios/perfiles/crear", ((UsuariosController) FactoryController.controller("usuario"))::procesar_creacion_perfil);
            get("/usuarios/perfiles/{perfil_id}", ((UsuariosController) FactoryController.controller("usuario"))::mostrar_perfil);

            // Usuarios (configuración)
            get("/usuarios/usuario", ((UsuariosController) FactoryController.controller("usuario"))::configuracion_de_usuario);

            get("/usuarios/usuario/editar", ((UsuariosController) FactoryController.controller("usuario"))::editar_datos);
            post("/usuarios/usuario/editar", ((UsuariosController) FactoryController.controller("usuario"))::procesar_edicion_datos);

            get("/usuarios/usuario/password", ((UsuariosController) FactoryController.controller("usuario"))::cambiar_contrasenia);
            post("/usuarios/usuario/password", ((UsuariosController) FactoryController.controller("usuario"))::procesar_cambio_contrasenia);

            get("/usuarios/usuario/intereses", ((UsuariosController) FactoryController.controller("usuario"))::cargar_intereses);
            post("/usuarios/usuario/entidad_interes", ((UsuariosController) FactoryController.controller("usuario"))::agregar_entidad_interes);
            post("/usuarios/usuario/servicio_interes", ((UsuariosController) FactoryController.controller("usuario"))::agregar_servicio_interes);

            get("/usuarios/usuario/notificaciones", ((UsuariosController) FactoryController.controller("usuario"))::configurar_envio_notificaciones);
            post("/usuarios/usuario/notificaciones", ((UsuariosController) FactoryController.controller("usuario"))::procesar_configuracion_notificaciones);
            get("/usuarios/usuario/notificaciones/horarios", ((UsuariosController) FactoryController.controller("usuario"))::horarios);
            get("/usuarios/usuario/notificaciones/horarios/agregar", ((UsuariosController) FactoryController.controller("usuario"))::agregar_horario);
            post("/usuarios/usuario/notificaciones/horarios/agregar", ((UsuariosController) FactoryController.controller("usuario"))::procesar_horario);
            get("/usuarios/usuario/notificaciones/horarios/{id}/quitar", ((UsuariosController) FactoryController.controller("usuario"))::quitar_horario);

            // Comunidades
            get("/comunidades/{comunidad_id}", ((ComunidadController) FactoryController.controller("comunidad"))::show);
//            get("/comunidades/crear", ((ComunidadController) FactoryController.controller("comunidad"))::create);
//            post("/comunidades/crear", ((ComunidadController) FactoryController.controller("comunidad"))::procesar_creacion);


            //rankings
            get("/rankings", ((InformesController) FactoryController.controller("informes"))::mostrar_informe_reciente);

            //carga datos
            get("/cargarDatos", ((CargadorCSVController) FactoryController.controller("cargadorCSV"))::index);
            post("/cargarDatos", ((CargadorCSVController) FactoryController.controller("cargadorCSV"))::cargarDatos);

            // Entidades, establecimientos y servicios. Devuelven jsons
            get("/entidades", ((EntidadesController) FactoryController.controller("entidades"))::entidades); // NO SE USA
            get("/establecimientos", ((EntidadesController) FactoryController.controller("entidades"))::establecimientos); // Pueden filtrarse por entidad con query param
            get("/servicios", ((EntidadesController) FactoryController.controller("entidades"))::servicios); // Pueden filtrarse por establecimiento con query param

            // Departamentos y localidades. Devuelven jsons // NO SE USA
            get("/departamentos", ((LocalizacionController) FactoryController.controller("localizacion"))::departamentos); // Pueden filtrarse por entidad con query param
            get("/localidades", ((LocalizacionController) FactoryController.controller("localizacion"))::localidades); // Pueden filtrarse por establecimiento con query param

        });

        Server.app().error(404, ctx -> {
            ctx.redirect("https://http.dog/404.jpg");
        });

        Server.app().error(500, ctx -> {
            ctx.redirect("https://http.garden/500.jpg");
        });
    }
}
