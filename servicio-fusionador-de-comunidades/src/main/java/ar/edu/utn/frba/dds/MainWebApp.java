package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.controllers.FinalizarTransaccionController;
import ar.edu.utn.frba.dds.controllers.IniciarTransaccionController;
import ar.edu.utn.frba.dds.repositories.RepoTransaccion;
import io.javalin.Javalin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class MainWebApp {

    public static void main(String[] args) {
        // Crear un ObjectMapper con el módulo JSR-310
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        RepoTransaccion repo = new RepoTransaccion();

        Integer port = Integer.parseInt(
                System.getProperty("port", "8080"));
        Javalin app = Javalin.create().start(port);

        app.post("/transaccionIniciar", new IniciarTransaccionController(repo));
        app.post("/transaccionFinalizar", new FinalizarTransaccionController(repo));
    }

}