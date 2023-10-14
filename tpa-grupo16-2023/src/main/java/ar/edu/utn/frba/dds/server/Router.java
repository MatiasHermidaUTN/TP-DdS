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
            get("incidentes/{id}/editar", ((IncidentesController) FactoryController.controller("incidente"))::edit);
            //TODO f. Sugerencia de revisión de incidentes
            get("/incidentesCercanos", ((IncidentesController) FactoryController.controller("incidente"))::incidentesCercanos);

            // Registro y Login
            get("register", ((UsuariosController) FactoryController.controller("usuario"))::registrar);
            post("register", ((UsuariosController) FactoryController.controller("usuario"))::guardar_usuario);
            get("login", ((UsuariosController) FactoryController.controller("usuario"))::logear);
            post("login", ((UsuariosController) FactoryController.controller("usuario"))::procesar_login);

            // Perfiles
            get("/usuarios/{usuario_id}/perfiles", ((UsuariosController) FactoryController.controller("usuario"))::mostrar_perfiles);
            get("/usuarios/{usuario_id}/perfiles/crear", ((UsuariosController) FactoryController.controller("usuario"))::crear_perfil);
            post("/usuarios/{usuario_id}/perfiles/crear", ((UsuariosController) FactoryController.controller("usuario"))::procesar_creacion_perfil);
            get("/usuarios/{usuario_id}/perfiles/{perfil_id}", ((UsuariosController) FactoryController.controller("usuario"))::mostrar_perfil);

            // Comunidades
            get("/comunidades/{comunidad_id}", ((ComunidadController) FactoryController.controller("comunidad"))::show);

            //rankings
            //TODO h. Visualización de rankings de incidentes
            get("/rankings", ((IncidentesController) FactoryController.controller("incidente"))::rankings);

            //carga datos
            //TODO b. Carga masiva de datos de entidades prestadoras y organismos de control
            get("/cargarDatos", ((CargadorCSVController) FactoryController.controller("cargadorCSV"))::cargarDatos);

        });
    }
}
