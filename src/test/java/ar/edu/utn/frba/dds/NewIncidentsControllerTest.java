package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.comunidades.Comunidad;
import ar.edu.utn.frba.dds.comunidades.Perfil;
import ar.edu.utn.frba.dds.comunidades.TipoPerfil;
import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.incidentes.EstadoIncidente;
import ar.edu.utn.frba.dds.incidentes.Incidente;
import ar.edu.utn.frba.dds.localizacion.*;
import ar.edu.utn.frba.dds.services.georef.AdapterGeoref;
import ar.edu.utn.frba.dds.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.serviciosPublicos.Servicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


public class NewIncidentsControllerTest {
    //Hay que charlar un par de cosas


    public void crear(Incidente incidente) {
        incidente.getUsuarioApertura().getPerfiles()
                .stream()
                .map(perfil -> perfil.getComunidad());
                //.forEach(comunidad -> comunidad.agregarIncidente());
    }

    public void cerrarEnComunidad(Incidente incidente, Perfil perfilCierre, LocalDateTime horarioCierre) {
        incidente.setUsuarioCierre(perfilCierre.getUsuario());
        incidente.setHorarioCierre(horarioCierre);
        incidente.setEstado(EstadoIncidente.RESUELTO);
    }





    @Test
    public void controladorIncidentesTest() throws Exception {
        Establecimiento mcDonalds = new Establecimiento("McDonalds");

        mcDonalds.agregarServicios( new Servicio("banio"),
                                    new Servicio("escalera"),
                                    new Servicio("wifi"));

        Comunidad comunidad1 = new Comunidad("comunidad1");
        Comunidad comunidad2 = new Comunidad("comunidad2");


        Perfil perfil1 = new Perfil("JuanP", comunidad1, TipoPerfil.NORMAL);
        Perfil perfil2 = new Perfil("JuanP", comunidad2, TipoPerfil.NORMAL);
        Usuario jp = new Usuario("JuanP@gmail.com", "JP", "1234");
        jp.agregarPerfil(perfil1);
        jp.agregarPerfil(perfil2);
    }
}
