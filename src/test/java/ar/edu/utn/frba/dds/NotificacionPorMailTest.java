package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.comunidades.Perfil;
import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.incidentes.Incidente;
import ar.edu.utn.frba.dds.localizacion.Localizacion;
import ar.edu.utn.frba.dds.localizacion.Ubicacion;
import ar.edu.utn.frba.dds.notificaciones.AdapterMailSender;
import ar.edu.utn.frba.dds.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.serviciosPublicos.Servicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static ar.edu.utn.frba.dds.localizacion.AdapterCercaniaLocalizacion.filtrarIncidentesCercanos;

public class NotificacionPorMailTest {

    AdapterMailSender mailSender = new AdapterMailSender();
    Usuario usuario = new Usuario();
    Perfil perfil = new Perfil();
    Establecimiento establecimientoA = new Establecimiento();
    Establecimiento establecimientoB = new Establecimiento();
    Servicio servicioA = new Servicio();
    Servicio servicioB = new Servicio();
    Servicio servicioC = new Servicio();
    Incidente incidenteA = new Incidente();
    Incidente incidenteB = new Incidente();
    Incidente incidenteC = new Incidente();

    @BeforeEach
    public void init(){
        this.usuario.setEmail("mhermida@frba.utn.edu.ar"); //email que recibe la notificación
        this.perfil.setUsuario(usuario);
        this.establecimientoA.setNombre("Estación A");
        establecimientoA.setLocalizacion(new Localizacion(new Ubicacion(-34.60781914239126, -58.38929918544316)));
        this.establecimientoB.setNombre("Estación B");
        establecimientoB.setLocalizacion(new Localizacion(new Ubicacion(-34.6083140245063, -58.39782535899123)));
        this.servicioA.setNombre("Baño");
        this.servicioB.setNombre("Rampa");
        this.servicioC.setNombre("Baño");
        this.incidenteA.setEstablecimiento(establecimientoA);
        this.incidenteA.setServicio(servicioA);
        this.incidenteB.setEstablecimiento(establecimientoA);
        this.incidenteB.setServicio(servicioB);
        this.incidenteC.setEstablecimiento(establecimientoB);
        this.incidenteC.setServicio(servicioC);
    }

    @Test
    public void enviarMailSobreIncidente() throws Exception {
        mailSender.mandarNotificacionDeIncidenteNuevo(incidenteA, usuario);
    }

    @Test
    public void enviarResumenDeIncidentes() throws  Exception {
        List<Incidente> incidentesNuevos = new ArrayList<>();
        List<Incidente> incidentesConcluidos = new ArrayList<>();
        incidentesNuevos.add(incidenteA);
        incidentesNuevos.add(incidenteB);
        incidentesConcluidos.add(incidenteC);
        mailSender.mandarResumenDeIncidentes(incidentesNuevos, incidentesConcluidos, usuario);
    }

    @Test
    public void enviarNotificacionIncidentesCercanos() {
        //TODO
        List<Incidente> incidentes = new ArrayList<>();
        incidentes.add(incidenteA); //Estación A
        incidentes.add(incidenteB); //Estación A
        incidentes.add(incidenteC); //Estación B
        //Estacion B <-----400m-----> puntoMedio <-----400m-----> Estacion A
        double lat = -34.60805915947694;    //en el medio de las dos estaciones
        double lon = -58.393507608910674;
        int radio = 500;
        List<Incidente> incidentesCercanos = filtrarIncidentesCercanos(incidentes, lat, lon, radio);
        Assertions.assertEquals(3, incidentesCercanos.size());
        lat = -34.60781914239126;   //Estación A
        lon = -58.38929918544316;
        List<Incidente> incidentesCercanos2 = filtrarIncidentesCercanos(incidentes, lat, lon, radio);
        Assertions.assertEquals(2, incidentesCercanos2.size());
        lat = -34.6083140245063;   //Estación B
        lon = -58.39782535899123;
        List<Incidente> incidentesCercanos3 = filtrarIncidentesCercanos(incidentes, lat, lon, radio);
        Assertions.assertEquals(1, incidentesCercanos3.size());
        radio = 1000;               //radio mas grande
        List<Incidente> incidentesCercanos4 = filtrarIncidentesCercanos(incidentes, lat, lon, radio);
        Assertions.assertEquals(3, incidentesCercanos4.size());

        mailSender.mandarNotificacionRevisionDeIncidentesCercano(incidentesCercanos2, usuario);
    }
}
