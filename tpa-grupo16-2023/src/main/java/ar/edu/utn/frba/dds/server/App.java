package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import ar.edu.utn.frba.dds.models.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.notificaciones.cron.CronGlobal;
import ar.edu.utn.frba.dds.models.repositorios.RepoComunidad;
import ar.edu.utn.frba.dds.models.repositorios.RepoIncidente;
import ar.edu.utn.frba.dds.models.repositorios.RepoUsuario;

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
