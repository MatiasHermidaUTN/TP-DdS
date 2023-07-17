package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.comunidades.Perfil;
import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.incidentes.Incidente;
import ar.edu.utn.frba.dds.notificaciones.Notificador;
import ar.edu.utn.frba.dds.notificaciones.WhatsappSender;
import ar.edu.utn.frba.dds.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.serviciosPublicos.Servicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class NotificacionPorWhatsappTest {

    private Notificador whatsappSender;
    private Usuario usuario = new Usuario();
    private Perfil perfil = new Perfil();
    private Establecimiento establecimiento = new Establecimiento();
    private Servicio servicio = new Servicio();
    private Incidente incidente = new Incidente();

    @BeforeEach
    public void init() {
        this.perfil.setUsuario(usuario);
        this.establecimiento.setNombre("Estación A");
        this.servicio.setNombre("Baño");
        this.incidente.setEstablecimiento(establecimiento);
        this.incidente.setServicio(servicio);
        this.whatsappSender = mock(WhatsappSender.class);
    }

    @Test
    public void enviarNotificacionPorWhatsapp() {
        // el mockeo tira error
        // when(whatsappSender.mandarNotificacion(incidente, perfil)).thenReturn(Boolean.TRUE);
        System.out.println("Enviando mensaje de whatsapp\n");
        whatsappSender.mandarNotificacion(incidente, perfil);
        System.out.println("Mensaje whatsapp enviado\n");
    }
}
