package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.models.notificaciones.cron.CronGlobal;

import java.io.IOException;

public class App {

    public static void main(String[] args) {
        try {
            new DatosPrueba().cargaDatosGeoref();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        new DatosPrueba().cargarDatos();

        Server.init();

        CronGlobal cron = new CronGlobal();
        cron.iniciar();

    }
}
