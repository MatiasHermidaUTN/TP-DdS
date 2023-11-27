package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.comunidades.Comunidad;
import ar.edu.utn.frba.dds.models.comunidades.GradoDeConfianza;
import ar.edu.utn.frba.dds.models.comunidades.Perfil;
import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import ar.edu.utn.frba.dds.models.servicioFusionadorDeComunidades.AdapterServicioFusionador;
import ar.edu.utn.frba.dds.models.servicioFusionadorDeComunidades.Entities.EstadoPropuestaFusion;
import ar.edu.utn.frba.dds.models.servicioFusionadorDeComunidades.Entities.PropuestaFusion;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Servicio;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Ignore
public class IntegracionServicioPropio {

    List<PropuestaFusion> propuestasViejas = new ArrayList<>();

    Usuario usuario1 = new Usuario();
    Usuario usuario2 = new Usuario();
    Usuario usuario3 = new Usuario();
    Usuario usuario4 = new Usuario();
    Usuario usuario5 = new Usuario();
    Usuario usuario6 = new Usuario();

    Perfil perfil1 = new Perfil();
    Perfil perfil2 = new Perfil();
    Perfil perfil3 = new Perfil();
    Perfil perfil4 = new Perfil();
    Perfil perfil5 = new Perfil();
    Perfil perfil6 = new Perfil();

    Establecimiento establecimiento1 = new Establecimiento();
    Establecimiento establecimiento2 = new Establecimiento();
    Establecimiento establecimiento3 = new Establecimiento();
    Establecimiento establecimiento4 = new Establecimiento();
    Establecimiento establecimiento5 = new Establecimiento();
    Establecimiento establecimiento6 = new Establecimiento();

    Servicio servicio1 = new Servicio();
    Servicio servicio2 = new Servicio();
    Servicio servicio3 = new Servicio();
    Servicio servicio4 = new Servicio();
    Servicio servicio5 = new Servicio();
    Servicio servicio6 = new Servicio();

    Comunidad comunidadA = new Comunidad();
    Comunidad comunidadB = new Comunidad();
    Comunidad comunidadC = new Comunidad();


    public List<Comunidad> initIntegracion() {
        // cargo los prefiles y los usuarios
        List<Perfil> perfiles1 = new ArrayList<>();
        perfil1.setId(1);
        perfiles1.add(perfil1);
        usuario1.setPerfiles(perfiles1);
        usuario1.setId(1);
        perfil1.setUsuario(usuario1);

        List<Perfil> perfiles2 = new ArrayList<>();
        perfil2.setId(2);
        perfiles2.add(perfil2);
        usuario2.setPerfiles(perfiles2);
        usuario2.setId(2);
        perfil2.setUsuario(usuario2);

        List<Perfil> perfiles3 = new ArrayList<>();
        perfil3.setId(3);
        perfiles3.add(perfil3);
        usuario3.setPerfiles(perfiles3);
        usuario3.setId(3);
        perfil3.setUsuario(usuario3);

        List<Perfil> perfiles4 = new ArrayList<>();
        perfil4.setId(4);
        perfiles4.add(perfil4);
        usuario4.setPerfiles(perfiles4);
        usuario4.setId(4);
        perfil4.setUsuario(usuario4);

        List<Perfil> perfiles5 = new ArrayList<>();
        perfil5.setId(5);
        perfiles5.add(perfil5);
        usuario5.setPerfiles(perfiles5);
        usuario5.setId(5);
        perfil5.setUsuario(usuario5);

        List<Perfil> perfiles6 = new ArrayList<>();
        perfil6.setId(6);
        perfiles6.add(perfil6);
        usuario6.setPerfiles(perfiles6);
        usuario6.setId(6);
        perfil6.setUsuario(usuario6);

        // cargo los servicios y los establecimientos
        servicio1.setId(1);
        servicio2.setId(2);
        servicio3.setId(3);
        servicio4.setId(4);
        servicio5.setId(5);
        servicio6.setId(6);

        List<Servicio> serviciosA = new ArrayList<>();
        List<Servicio> serviciosB = new ArrayList<>();
        List<Servicio> serviciosC = new ArrayList<>();

        serviciosA.addAll(List.of(servicio1, servicio2, servicio3, servicio4));
        serviciosB.addAll(List.of(servicio1, servicio2, servicio3, servicio5));
        serviciosC.addAll(List.of(servicio1, servicio2, servicio3, servicio6));

        establecimiento1.setId(1);
        establecimiento2.setId(2);
        establecimiento3.setId(3);
        establecimiento4.setId(4);
        establecimiento5.setId(5);
        establecimiento6.setId(6);

        List<Establecimiento> establecimientosA = new ArrayList<>();
        List<Establecimiento> establecimientosB = new ArrayList<>();
        List<Establecimiento> establecimientosC = new ArrayList<>();

        establecimientosA.addAll(List.of(establecimiento1, establecimiento2, establecimiento3, establecimiento4));
        establecimientosB.addAll(List.of(establecimiento1, establecimiento2, establecimiento3, establecimiento5));
        establecimientosC.addAll(List.of(establecimiento1, establecimiento2, establecimiento3, establecimiento6));


        // cargo las comunidades
        comunidadA.setId(1);
        comunidadA.setNombre("Comunidad A");
        comunidadA.agregarMiembros(perfil1, perfil2, perfil3, perfil4);
        comunidadA.setServiciosDeComunidad(serviciosA);
        comunidadA.setEstablecimientosDeComunidad(establecimientosA);
        comunidadA.setGradoDeConfianza(GradoDeConfianza.CONFIABLE_NIVEL_1);
        comunidadA.setActiva(true);

        comunidadB.setId(2);
        comunidadB.setNombre("Comunidad B");
        comunidadB.agregarMiembros(perfil1, perfil2, perfil3, perfil5);
        comunidadB.setServiciosDeComunidad(serviciosB);
        comunidadB.setEstablecimientosDeComunidad(establecimientosB);
        comunidadB.setGradoDeConfianza(GradoDeConfianza.CONFIABLE_NIVEL_1);
        comunidadB.setActiva(true);

        comunidadC.setId(3);
        comunidadC.setNombre("Comunidad C");
        comunidadC.agregarMiembros(perfil1, perfil2, perfil3, perfil6);
        comunidadC.setServiciosDeComunidad(serviciosC);
        comunidadC.setEstablecimientosDeComunidad(establecimientosC);
        comunidadC.setGradoDeConfianza(GradoDeConfianza.CONFIABLE_NIVEL_1);
        comunidadC.setActiva(true);

        List<Comunidad> comunidades = new ArrayList<>();
        comunidades.add(comunidadA);
        comunidades.add(comunidadB);
        comunidades.add(comunidadC);


        // cargo las propuestas viejas
        PropuestaFusion propuestaFusion1 = new PropuestaFusion();
        propuestaFusion1.setComunidad1(comunidadA);
        propuestaFusion1.setComunidad2(comunidadB);
        propuestaFusion1.setEstado(EstadoPropuestaFusion.RECHAZADA);
        propuestaFusion1.setFechaDePropuesta(LocalDateTime.now().minusMonths(5));
        //los campos comunidadFusionada, comunidad1desactivada y comunidad2desactivada no importan

        PropuestaFusion propuestaFusion2 = new PropuestaFusion();
        propuestaFusion2.setComunidad1(comunidadA);
        propuestaFusion2.setComunidad2(comunidadC);
        propuestaFusion2.setEstado(EstadoPropuestaFusion.ACEPTADA);
        propuestaFusion2.setFechaDePropuesta(LocalDateTime.now().minusMonths(4));

        propuestasViejas.addAll(List.of(propuestaFusion1, propuestaFusion2));

        return comunidades;
    }
    @Test
    public void testIntegracion() {
        List<Comunidad> comunidades = initIntegracion();
        List<PropuestaFusion> propuestasViejasTest = propuestasViejas;
        List<PropuestaFusion> propuestas = new ArrayList<>();


        try {
            AdapterServicioFusionador adapterServicioFusionador = AdapterServicioFusionador.instancia();
            propuestas = adapterServicioFusionador.proponerFusiones(comunidades, propuestasViejasTest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(PropuestaFusion propuestaFusion : propuestas) {
            System.out.println(propuestaFusion);
        }
    }
}
