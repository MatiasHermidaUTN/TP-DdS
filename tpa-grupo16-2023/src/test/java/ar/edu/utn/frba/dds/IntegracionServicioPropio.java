package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.calculadorImpactoDeIncidentes.AdapterCalcNivImpacto;
import ar.edu.utn.frba.dds.models.comunidades.Comunidad;
import ar.edu.utn.frba.dds.models.comunidades.GradoDeConfianza;
import ar.edu.utn.frba.dds.models.comunidades.Perfil;
import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import ar.edu.utn.frba.dds.models.repositorios.*;
import ar.edu.utn.frba.dds.models.servicioFusionadorDeComunidades.AdapterServicioFusionador;
import ar.edu.utn.frba.dds.models.servicioFusionadorDeComunidades.Entities.PropuestaFusion;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Servicio;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IntegracionServicioPropio {
    Usuario usuarioA = new Usuario();
    Usuario usuarioB = new Usuario();
    Usuario usuarioC = new Usuario();
    Usuario usuarioD = new Usuario();
    Usuario usuarioE = new Usuario();

    Perfil perfilA = new Perfil();
    Perfil perfilB = new Perfil();
    Perfil perfilC = new Perfil();
    Perfil perfilD = new Perfil();
    Perfil perfilE = new Perfil();

    Establecimiento establecimientoA = new Establecimiento();
    Establecimiento establecimientoB = new Establecimiento();
    Establecimiento establecimientoC = new Establecimiento();
    Establecimiento establecimientoD = new Establecimiento();
    Establecimiento establecimientoE = new Establecimiento();

    Servicio servicioA = new Servicio();
    Servicio servicioB = new Servicio();
    Servicio servicioC = new Servicio();
    Servicio servicioD = new Servicio();
    Servicio servicioE = new Servicio();

    Comunidad comunidadA = new Comunidad();
    Comunidad comunidadB = new Comunidad();
    Comunidad comunidadC = new Comunidad();
    Comunidad comunidadD = new Comunidad();


    public List<Comunidad> initIntegracion() {
        List<Perfil> perfilesA = new ArrayList<>();
        perfilA.setId(1);
        perfilesA.add(perfilA);
        usuarioA.setPerfiles(perfilesA);
        usuarioA.setId(1);
        perfilA.setUsuario(usuarioA);

        List<Perfil> perfilesB = new ArrayList<>();
        perfilB.setId(2);
        perfilesB.add(perfilB);
        usuarioB.setPerfiles(perfilesB);
        usuarioB.setId(2);
        perfilB.setUsuario(usuarioB);

        List<Perfil> perfilesC = new ArrayList<>();
        perfilC.setId(3);
        perfilesC.add(perfilC);
        usuarioC.setPerfiles(perfilesC);
        usuarioC.setId(3);
        perfilC.setUsuario(usuarioC);

        List<Perfil> perfilesD = new ArrayList<>();
        perfilD.setId(4);
        perfilesD.add(perfilD);
        usuarioD.setPerfiles(perfilesD);
        usuarioD.setId(4);
        perfilD.setUsuario(usuarioD);

        List<Perfil> perfilesE = new ArrayList<>();
        perfilE.setId(5);
        perfilesE.add(perfilE);
        usuarioE.setPerfiles(perfilesE);
        usuarioE.setId(5);
        perfilE.setUsuario(usuarioE);

        List<Servicio> serviciosA = new ArrayList<>();
        servicioA.setId(1);
        serviciosA.add(servicioA);

        List<Servicio> serviciosB = new ArrayList<>();
        servicioB.setId(2);
        serviciosB.add(servicioB);

        List<Servicio> serviciosC = new ArrayList<>();
        servicioC.setId(3);
        serviciosC.add(servicioC);

        List<Servicio> serviciosD = new ArrayList<>();
        servicioD.setId(4);
        serviciosD.add(servicioD);

        List<Servicio> serviciosE = new ArrayList<>();
        servicioE.setId(5);
        serviciosE.add(servicioE);

        List<Establecimiento> establecimientosA = new ArrayList<>();
        establecimientoA.setId(1);
        establecimientosA.add(establecimientoA);

        List<Establecimiento> establecimientosB = new ArrayList<>();
        establecimientoB.setId(2);
        establecimientosB.add(establecimientoB);

        List<Establecimiento> establecimientosC = new ArrayList<>();
        establecimientoC.setId(3);
        establecimientosC.add(establecimientoC);

        List<Establecimiento> establecimientosD = new ArrayList<>();
        establecimientoD.setId(4);
        establecimientosD.add(establecimientoD);

        List<Establecimiento> establecimientosE = new ArrayList<>();
        establecimientoE.setId(5);
        establecimientosE.add(establecimientoE);

        comunidadA.setNombre("Comunidad A");
        comunidadA.setMiembros(perfilesA);
        comunidadA.setMiembros(perfilesB);
        comunidadA.setMiembros(perfilesC);
        comunidadA.setMiembros(perfilesD);
        comunidadA.setMiembros(perfilesE);
        comunidadA.setServiciosDeComunidad(serviciosA);
        comunidadA.setEstablecimientosDeComunidad(establecimientosA);
        comunidadA.setGradoDeConfianza(GradoDeConfianza.CONFIABLE_NIVEL_1);
        comunidadA.setActiva(true);

        comunidadB.setNombre("Comunidad B");
        comunidadB.setMiembros(perfilesA);
        comunidadB.setMiembros(perfilesB);
        comunidadB.setMiembros(perfilesC);
        comunidadB.setMiembros(perfilesD);
        comunidadB.setMiembros(perfilesE);
        comunidadB.setServiciosDeComunidad(serviciosA);
        comunidadB.setEstablecimientosDeComunidad(establecimientosA);
        comunidadB.setGradoDeConfianza(GradoDeConfianza.CONFIABLE_NIVEL_1);
        comunidadB.setActiva(true);

        List<Comunidad> comunidades = new ArrayList<>();
        comunidades.add(comunidadA);
        comunidades.add(comunidadB);

        return comunidades;
    }
    @Test
    public void testIntegracion() {
        List<Comunidad> comunidades = initIntegracion();
        List<PropuestaFusion> propuestasViejas = new ArrayList<>();
        List<PropuestaFusion> propuestas = new ArrayList<>();


        try {
            AdapterServicioFusionador adapterServicioFusionador = AdapterServicioFusionador.instancia();
            propuestas = adapterServicioFusionador.proponerFusiones(comunidades, propuestasViejas);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(PropuestaFusion propuestaFusion : propuestas) {
            System.out.println(propuestaFusion);
        }
    }
}
