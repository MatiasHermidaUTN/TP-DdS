package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.controllers.ComunidadController;
import ar.edu.utn.frba.dds.controllers.FactoryController;
import ar.edu.utn.frba.dds.controllers.IncidentesController;
import ar.edu.utn.frba.dds.controllers.UsuariosController;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Router {

    public static void init() {
        Server.app().get("/", ctx -> {
            ctx.sessionAttribute("item1", "Cosa 1");
            ctx.result("Hola mundo");
        });
        Server.app().get("/saluda", ctx -> {
            ctx.result("Hola "
                    + ctx.queryParam("nombre")
                    + ", " + ctx.sessionAttribute("item1")
            );
        });
        Server.app().get("/saludo-para/{nombre}", ctx -> ctx.result("Hola "
                + ctx.pathParam("nombre")
        ));


        Server.app().routes(() -> {

            get("comunidades/{id}/incidentes", ((IncidentesController) FactoryController.controller("incidente"))::index);
            get("incidentes/{id}/cerrar", ((IncidentesController) FactoryController.controller("incidente"))::cerrar);
            get("register", ((UsuariosController) FactoryController.controller("usuario"))::registrar);
        });
    }
}
