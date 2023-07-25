package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.comunidades.Perfil;
import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.incidentes.Incidente;
import ar.edu.utn.frba.dds.incidentes.Prestacion;
import ar.edu.utn.frba.dds.localizacion.Localizacion;
import ar.edu.utn.frba.dds.localizacion.Ubicacion;
import ar.edu.utn.frba.dds.notificaciones.AdapterMailSender;
import ar.edu.utn.frba.dds.ranking.GeneradorRanking;
import ar.edu.utn.frba.dds.ranking.MayorImpactoProblematicas;
import ar.edu.utn.frba.dds.ranking.MayorIncidentesReportados;
import ar.edu.utn.frba.dds.ranking.MayorPromedioCierre;
import ar.edu.utn.frba.dds.repositorios.RepoPrestacion;
import ar.edu.utn.frba.dds.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.serviciosPublicos.Servicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class RankingTest {
    RepoPrestacion repoPrestacion = RepoPrestacion.getInstancia();
    Usuario usuario = new Usuario();
    Entidad entidadA = new Entidad("Entidad A");
    Entidad entidadB = new Entidad("Entidad B");
    Entidad entidadC = new Entidad("Entidad C");
//    Entidad entidadD = new Entidad("Entidad D");
//    Entidad entidadE = new Entidad("Entidad E");
    Establecimiento establecimientoA = new Establecimiento();
    Establecimiento establecimientoB = new Establecimiento();
    Establecimiento establecimientoC = new Establecimiento();
//    Establecimiento establecimientoD = new Establecimiento();
//    Establecimiento establecimientoE = new Establecimiento();
    Servicio servicioA = new Servicio();
    Servicio servicioB = new Servicio();
    Servicio servicioC = new Servicio();
    Servicio servicioD = new Servicio();
    Servicio servicioE = new Servicio();
    Incidente incidenteA = new Incidente();
    Incidente incidenteB = new Incidente();
    Incidente incidenteC = new Incidente();
    Incidente incidenteD = new Incidente();
    Incidente incidenteE = new Incidente();
    Prestacion prestacionA = new Prestacion(establecimientoA, servicioA);
    Prestacion prestacionB = new Prestacion(establecimientoB, servicioB);
    Prestacion prestacionC = new Prestacion(establecimientoC, servicioC);
    Prestacion prestacionD = new Prestacion(establecimientoA, servicioD);
    Prestacion prestacionE = new Prestacion(establecimientoB, servicioE);

    @BeforeEach
    public void init(){
        this.establecimientoA.setEntidad(entidadA);
        this.establecimientoB.setEntidad(entidadB);
        this.establecimientoC.setEntidad(entidadC);

        this.establecimientoA.setNombre("Estación A");
        this.establecimientoB.setNombre("Estación B");
        this.establecimientoC.setNombre("Estación C");
//        this.establecimientoA.agregarServicios(servicioA, servicioD);
//        this.establecimientoB.agregarServicios(servicioB, servicioE);
//        this.establecimientoC.agregarServicios(servicioC);

        this.servicioA.setNombre("Baño");
        this.servicioB.setNombre("Rampa");
        this.servicioC.setNombre("Puerta Emergencia");
        this.servicioD.setNombre("Ascensor");
        this.servicioE.setNombre("Escalera Mecanica");

        this.incidenteA.setEstablecimiento(establecimientoA);
        this.incidenteB.setEstablecimiento(establecimientoA);
        this.incidenteC.setEstablecimiento(establecimientoC);
        this.incidenteD.setEstablecimiento(establecimientoA);
        this.incidenteE.setEstablecimiento(establecimientoB);
        this.incidenteA.setServicio(servicioA);
        this.incidenteB.setServicio(servicioB);
        this.incidenteC.setServicio(servicioC);
        this.incidenteD.setServicio(servicioD);
        this.incidenteE.setServicio(servicioE);
        this.prestacionA.agregarIncidente(incidenteA);
        this.prestacionB.agregarIncidente(incidenteB);
        this.prestacionC.agregarIncidente(incidenteC);
        this.prestacionD.agregarIncidente(incidenteD);
        this.prestacionE.agregarIncidente(incidenteE);

        this.incidenteA.setHorarioApertura(LocalDateTime.of(2023, 7, 25, 12, 0));
        this.incidenteB.setHorarioApertura(LocalDateTime.of(2023, 7, 25, 12, 0));
        this.incidenteC.setHorarioApertura(LocalDateTime.of(2023, 7, 25, 12, 0));
        this.incidenteD.setHorarioApertura(LocalDateTime.of(2023, 7, 25, 12, 0));
        this.incidenteE.setHorarioApertura(LocalDateTime.of(2023, 7, 25, 12, 0));

        this.incidenteA.setHorarioCierre(LocalDateTime.of(2023, 7, 25, 12, 30));
        this.incidenteB.setHorarioCierre(LocalDateTime.of(2023, 7, 25, 12, 30));
        this.incidenteC.setHorarioCierre(LocalDateTime.of(2023, 7, 25, 12, 30));
        this.incidenteD.setHorarioCierre(LocalDateTime.of(2023, 7, 25, 12, 30));
        this.incidenteE.setHorarioCierre(LocalDateTime.of(2023, 7, 25, 12, 30));

        repoPrestacion.agregarPrestacion(prestacionA);
        repoPrestacion.agregarPrestacion(prestacionB);
        repoPrestacion.agregarPrestacion(prestacionC);
        repoPrestacion.agregarPrestacion(prestacionD);
        repoPrestacion.agregarPrestacion(prestacionE);
    }

    @Test
    public void primerRanking() {
        MayorPromedioCierre generadorRanking = new MayorPromedioCierre();
        List<Entidad> rankingEntidades = generadorRanking.generarRanking();
    }

    @Test
    public void segundoRanking() {
        MayorIncidentesReportados generadorRanking = new MayorIncidentesReportados();
        List<Entidad> rankingEntidades = generadorRanking.generarRanking();

    }

    @Test
    public void tercerRanking() {
        MayorImpactoProblematicas generadorRanking = new MayorImpactoProblematicas();
        List<Entidad> rankingEntidades = generadorRanking.generarRanking();

    }
}
