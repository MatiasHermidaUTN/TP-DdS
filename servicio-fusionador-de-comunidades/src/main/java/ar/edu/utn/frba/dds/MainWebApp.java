package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.controllers.FinalizarTransaccionController;
import ar.edu.utn.frba.dds.controllers.IniciarTransaccionController;
import io.javalin.Javalin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class MainWebApp {

    public static void main(String[] args) {
        // Crear un ObjectMapper con el módulo JSR-310
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());


        Integer port = Integer.parseInt(
                System.getProperty("port", "8080"));
        Javalin app = Javalin.create().start(port);

        app.post("/api/transacciones", new IniciarTransaccionController());
        app.patch("/api/transacciones", new FinalizarTransaccionController());
    }

}