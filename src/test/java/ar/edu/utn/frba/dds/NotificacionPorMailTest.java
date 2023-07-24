package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.comunidades.Perfil;
import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.incidentes.Incidente;
import ar.edu.utn.frba.dds.notificaciones.AdapterMailSender;
import ar.edu.utn.frba.dds.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.serviciosPublicos.Servicio;
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
        this.usuario.setEmail("adalessandro@frba.utn.edu.ar"); //email que recibe la notificación
        this.perfil.setUsuario(usuario);
        this.establecimientoA.setNombre("Estación A");
        this.establecimientoB.setNombre("Estación B");
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
        incidentes.add(incidenteA);
        incidentes.add(incidenteB);
        incidentes.add(incidenteC);
        double lat = -34.60806241126338;
        double lon = -58.39352197717003;
        int radio = 500;
        List<Incidente> incidentesCercanos = filtrarIncidentesCercanos(incidentes, lat, lon, radio);
        mailSender.mandarNotificacionRevisionDeIncidentesCercano(incidentesCercanos, usuario);
    }
}
