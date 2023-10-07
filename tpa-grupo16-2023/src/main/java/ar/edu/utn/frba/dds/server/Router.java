package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.controllers.ComunidadController;
import ar.edu.utn.frba.dds.controllers.FactoryController;
import ar.edu.utn.frba.dds.controllers.IncidentesController;
import ar.edu.utn.frba.dds.controllers.UsuariosController;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Router {

    public static void init() {
        Server.app().get("/", ctx -> {
            ctx.redirect("/register");

        });

        Server.app().routes(() -> {

            get("comunidades/{id}/incidentes", ((IncidentesController) FactoryController.controller("incidente"))::index);
            get("incidentes/{id}/cerrar", ((IncidentesController) FactoryController.controller("incidente"))::cerrar);
            get("register", ((UsuariosController) FactoryController.controller("usuario"))::registrar);
            post("register", ((UsuariosController) FactoryController.controller("usuario"))::guardar_usuario);
            get("login", ((UsuariosController) FactoryController.controller("usuario"))::logear);
            post("login", ((UsuariosController) FactoryController.controller("usuario"))::procesar_login);
        });
    }
}
