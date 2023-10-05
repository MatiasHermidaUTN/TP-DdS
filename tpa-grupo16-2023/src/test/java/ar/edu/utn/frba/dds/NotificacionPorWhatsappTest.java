package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.comunidades.Perfil;
import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import ar.edu.utn.frba.dds.models.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.notificaciones.medios.Notificador;
import ar.edu.utn.frba.dds.models.notificaciones.medios.WhatsappSender;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Servicio;
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
//        this.incidente.setEstablecimiento(establecimiento);
//        this.incidente.setServicio(servicio);
        this.whatsappSender = (Notificador) mock(WhatsappSender.class);
    }

    @Test
    public void enviarNotificacionPorWhatsapp() {
        when(whatsappSender.mandarNotificacionDeIncidenteNuevo(incidente, usuario)).thenReturn(Boolean.TRUE);
        System.out.println("Enviando mensaje de whatsapp \n");

        if(whatsappSender.mandarNotificacionDeIncidenteNuevo(incidente, usuario))
            System.out.println("Mensaje whatsapp enviado \n");
        else
            System.out.println("Error al enviar el mensaje de whatsapp \n");

    }
}
