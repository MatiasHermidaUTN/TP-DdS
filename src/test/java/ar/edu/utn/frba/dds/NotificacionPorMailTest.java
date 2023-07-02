package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.comunidades.Perfil;
import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.incidentes.Incidente;
import ar.edu.utn.frba.dds.notificaciones.AdapterMailSender;
import ar.edu.utn.frba.dds.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.serviciosPublicos.Servicio;
import ar.edu.utn.frba.dds.validador.Validador;
import ar.edu.utn.frba.dds.validador.reglas.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NotificacionPorMailTest {

    AdapterMailSender mailSender = new AdapterMailSender();
    Usuario usuario = new Usuario();
    Perfil perfil = new Perfil();
    Establecimiento establecimiento = new Establecimiento();
    Servicio servicio = new Servicio();
    Incidente incidente = new Incidente();

    @BeforeEach
    public void init(){

        this.usuario.setEmail("leofierens@frba.utn.edu.ar");//email que recibe la notificación
        this.perfil.setUsuario(usuario);
        this.establecimiento.setNombre("Estación A");
        this.servicio.setNombre("Baño");
        this.incidente.setEstablecimiento(establecimiento);
        this.incidente.setServicio(servicio);

    }

    @Test
    public void enviarMailSobreIncidente() throws Exception {
        mailSender.mandarNotificacion(incidente, perfil);
    }
}
