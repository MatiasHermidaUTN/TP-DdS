package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.comunidades.*;
import ar.edu.utn.frba.dds.models.incidentes.EstadoIncidente;
import ar.edu.utn.frba.dds.models.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.incidentes.Prestacion;
import ar.edu.utn.frba.dds.models.notificaciones.medios.AdapterMailSender;
import ar.edu.utn.frba.dds.models.notificaciones.estrategias.ConfiguracionNotificacion;
import ar.edu.utn.frba.dds.models.notificaciones.estrategias.CuandoSucede;
import ar.edu.utn.frba.dds.models.ranking.InformeSemanal;
import ar.edu.utn.frba.dds.models.ranking.MayorImpactoProblematicas;
import ar.edu.utn.frba.dds.models.ranking.MayorIncidentesReportados;
import ar.edu.utn.frba.dds.models.ranking.MayorPromedioCierre;
import ar.edu.utn.frba.dds.models.repositorios.*;
import ar.edu.utn.frba.dds.models.repositorios.reposDeprecados.RepoEntidadDeprecado;
import ar.edu.utn.frba.dds.models.repositorios.reposDeprecados.RepoIncidenteDeprecado;
import ar.edu.utn.frba.dds.models.repositorios.reposDeprecados.RepoPrestacionDeprecado;
import ar.edu.utn.frba.dds.models.repositorios.reposDeprecados.RepoUsuarioDeprecado;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Servicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class RankingTest {
    RepoPrestacionDeprecado repoPrestacion = RepoPrestacionDeprecado.getInstancia();
    RepoEntidadDeprecado repoEntidad = RepoEntidadDeprecado.getInstancia();
    RepoIncidenteDeprecado repoIncidente = RepoIncidenteDeprecado.getInstancia();

    RepoEntidad repoEntidad2 = new RepoEntidad();
    RepoEstablecimiento repoEstablecimientos2 = new RepoEstablecimiento();
    //RepoServicios repoServicios2 = new RepoServicios();
    RepoPrestacion repoPrestacion2 = new RepoPrestacion();
    RepoIncidente repoIncidente2 = new RepoIncidente();



    Entidad entidadA = new Entidad("Entidad A");
    Entidad entidadB = new Entidad("Entidad B");
    Entidad entidadC = new Entidad("Entidad C");
    Establecimiento establecimientoA = new Establecimiento();
    Establecimiento establecimientoB = new Establecimiento();
    Establecimiento establecimientoC = new Establecimiento();
    Servicio servicioA1 = new Servicio();
    Servicio servicioA2 = new Servicio();
    Servicio servicioB1 = new Servicio();
    Servicio servicioB2 = new Servicio();
    Servicio servicioC1 = new Servicio();
    Incidente incidenteA1 = new Incidente();
    Incidente incidenteA2 = new Incidente();
    Incidente incidenteA3 = new Incidente();
    Incidente incidenteA4 = new Incidente();
    Incidente incidenteB1 = new Incidente();
    Incidente incidenteB2 = new Incidente();
    Incidente incidenteB3 = new Incidente();
    Incidente incidenteC1 = new Incidente();
    Incidente incidenteC2 = new Incidente();
    Prestacion prestacionA1 = new Prestacion(establecimientoA, servicioA1);
    Prestacion prestacionA2 = new Prestacion(establecimientoA, servicioA2);
    Prestacion prestacionB1 = new Prestacion(establecimientoB, servicioB1);
    Prestacion prestacionB2 = new Prestacion(establecimientoB, servicioB2);
    Prestacion prestacionC1 = new Prestacion(establecimientoC, servicioC1);

    @BeforeEach
    public void initGeneral(){
    }

    public void initRankingMayorIncidentesReportados(){
        RepoPrestacionDeprecado.agregarPrestacion(prestacionA1);
        RepoPrestacionDeprecado.agregarPrestacion(prestacionA2);
        RepoPrestacionDeprecado.agregarPrestacion(prestacionB1);
        RepoPrestacionDeprecado.agregarPrestacion(prestacionB2);
        RepoPrestacionDeprecado.agregarPrestacion(prestacionC1);

        RepoEntidadDeprecado.agregarEntidad(entidadC);
        RepoEntidadDeprecado.agregarEntidad(entidadA);
        RepoEntidadDeprecado.agregarEntidad(entidadB);

        //establecimiento A
        this.establecimientoA.setEntidad(entidadA);
        this.establecimientoA.setNombre("Estación A");

        this.servicioA1.setNombre("Baño");
        this.servicioA2.setNombre("Ascensor");
        this.prestacionA1.agregarIncidente(incidenteA1);
        this.prestacionA1.agregarIncidente(incidenteA2);
        this.prestacionA2.agregarIncidente(incidenteA3);
        this.prestacionA2.agregarIncidente(incidenteA4);

//        this.incidenteA1.setEstablecimiento(establecimientoA);
//        this.incidenteA2.setEstablecimiento(establecimientoA);
//        this.incidenteA3.setEstablecimiento(establecimientoA);
//        this.incidenteA4.setEstablecimiento(establecimientoA);
//        this.incidenteA1.setServicio(servicioA1);
//        this.incidenteA2.setServicio(servicioA1);
//        this.incidenteA3.setServicio(servicioA2);
//        this.incidenteA4.setServicio(servicioA2);
        this.incidenteA1.setHorarioApertura(LocalDateTime.of(2023, 7, 26, 12, 0));  //Miercoles 26/7 (prestacionA1)
        this.incidenteA1.setHorarioCierre(LocalDateTime.of(2023, 7, 26, 12, 30));
        this.incidenteA1.setEstado(EstadoIncidente.RESUELTO);
        this.incidenteA2.setHorarioApertura(LocalDateTime.of(2023, 7, 26, 15, 0));  //Miercoles 26/7 (prestacionA1)(menos de 24hs pero anterior RESUELTO)
        this.incidenteA3.setHorarioApertura(LocalDateTime.of(2023, 7, 26, 12, 0));  //Miercoles 26/7 (prestacionA2)
        this.incidenteA4.setHorarioApertura(LocalDateTime.of(2023, 7, 30, 12, 0));  //Domingo 30/7 (prestacionA2)(misma semana)

        //establecimiento B
        this.establecimientoB.setEntidad(entidadB);
        this.establecimientoB.setNombre("Estación B");

        this.servicioB1.setNombre("Rampa");
        this.servicioB2.setNombre("Escalera Mecanica");
        this.prestacionB1.agregarIncidente(incidenteB1);
        this.prestacionB2.agregarIncidente(incidenteB2);
        this.prestacionB2.agregarIncidente(incidenteB3);

//        this.incidenteB1.setEstablecimiento(establecimientoB);
//        this.incidenteB2.setEstablecimiento(establecimientoB);
//        this.incidenteB3.setEstablecimiento(establecimientoB);
//        this.incidenteB1.setServicio(servicioB1);
//        this.incidenteB2.setServicio(servicioB2);
//        this.incidenteB3.setServicio(servicioB2);
        this.incidenteB1.setHorarioApertura(LocalDateTime.of(2023, 7, 25, 12, 0));  //Martes 25/7 (prestacionB1)
        this.incidenteB2.setHorarioApertura(LocalDateTime.of(2023, 7, 25, 12, 0));  //Martes 25/7 (prestacionB2)
        this.incidenteB3.setHorarioApertura(LocalDateTime.of(2023, 7, 18, 12, 0));  //Martes 18/7 (prestacionB2)(en otra semana)

        //establecimiento C
        this.establecimientoC.setEntidad(entidadC);
        this.establecimientoC.setNombre("Estación C");

        this.servicioC1.setNombre("Puerta Emergencia");
        this.prestacionC1.agregarIncidente(incidenteC1);
        this.prestacionC1.agregarIncidente(incidenteC2);

//        this.incidenteC1.setEstablecimiento(establecimientoC);
//        this.incidenteC2.setEstablecimiento(establecimientoC);
//        this.incidenteC1.setServicio(servicioC1);
//        this.incidenteC2.setServicio(servicioC1);
        this.incidenteC1.setHorarioApertura(LocalDateTime.of(2023, 7, 24, 12, 0));  //Lunes 24/7 (prestacionC1)
        this.incidenteC2.setHorarioApertura(LocalDateTime.of(2023, 7, 24, 20, 0));  //Lunes 24/7 (prestacionC1)(menos de 24hs del anterior ABIERTO)
    }

    public void initRankingPromedio(){
        RepoIncidenteDeprecado.agregarIncidente(incidenteA1);
        RepoIncidenteDeprecado.agregarIncidente(incidenteA2);
        RepoIncidenteDeprecado.agregarIncidente(incidenteA3);
        RepoIncidenteDeprecado.agregarIncidente(incidenteA4);
        RepoIncidenteDeprecado.agregarIncidente(incidenteB1);
        RepoIncidenteDeprecado.agregarIncidente(incidenteB2);
        RepoIncidenteDeprecado.agregarIncidente(incidenteB3);
        RepoIncidenteDeprecado.agregarIncidente(incidenteC1);
        RepoIncidenteDeprecado.agregarIncidente(incidenteC2);

        RepoEntidadDeprecado.agregarEntidad(entidadB);
        RepoEntidadDeprecado.agregarEntidad(entidadC);
        RepoEntidadDeprecado.agregarEntidad(entidadA);

        //establecimiento A
        this.establecimientoA.setEntidad(entidadA);
        this.establecimientoA.setNombre("Estación A");

//        this.incidenteA1.setEstablecimiento(establecimientoA);
//        this.incidenteA2.setEstablecimiento(establecimientoA);
//        this.incidenteA3.setEstablecimiento(establecimientoA);
//        this.incidenteA4.setEstablecimiento(establecimientoA);

        this.incidenteA1.setHorarioApertura(LocalDateTime.of(2023, 7, 26, 12, 0));
        this.incidenteA1.setHorarioCierre(LocalDateTime.of(2023, 7, 26, 13, 30));

        this.incidenteA2.setHorarioApertura(LocalDateTime.of(2023, 7, 26, 15, 0));
        this.incidenteA2.setHorarioCierre(LocalDateTime.of(2023, 7, 26, 16, 30));

        this.incidenteA3.setHorarioApertura(LocalDateTime.of(2023, 7, 27, 12, 0));
        this.incidenteA3.setHorarioCierre(LocalDateTime.of(2023, 7, 27, 13, 30));

        this.incidenteA4.setHorarioApertura(LocalDateTime.of(2023, 7, 30, 12, 0));
        this.incidenteA4.setHorarioCierre(LocalDateTime.of(2023, 7, 30, 13, 30));

        //establecimiento B
        this.establecimientoB.setEntidad(entidadB);
        this.establecimientoB.setNombre("Estación B");

        this.servicioB1.setNombre("Rampa");
        this.servicioB2.setNombre("Escalera Mecanica");
        this.prestacionB1.agregarIncidente(incidenteB1);
        this.prestacionB2.agregarIncidente(incidenteB2);
        this.prestacionB2.agregarIncidente(incidenteB3);

//        this.incidenteB1.setEstablecimiento(establecimientoB);
//        this.incidenteB2.setEstablecimiento(establecimientoB);
//        this.incidenteB3.setEstablecimiento(establecimientoB);

        this.incidenteB1.setHorarioApertura(LocalDateTime.of(2023, 7, 25, 12, 0));
        this.incidenteB1.setHorarioCierre(LocalDateTime.of(2023, 7, 25, 12, 30));

        this.incidenteB2.setHorarioApertura(LocalDateTime.of(2023, 7, 25, 15, 0));
        this.incidenteB2.setHorarioCierre(LocalDateTime.of(2023, 7, 25, 16, 30));

        this.incidenteB3.setHorarioApertura(LocalDateTime.of(2023, 7, 18, 12, 0));
        this.incidenteB3.setHorarioCierre(LocalDateTime.of(2023, 7, 18, 13, 0));

        //establecimiento C
        this.establecimientoC.setEntidad(entidadC);
        this.establecimientoC.setNombre("Estación C");

        this.servicioC1.setNombre("Puerta Emergencia");
        this.prestacionC1.agregarIncidente(incidenteC1);
        this.prestacionC1.agregarIncidente(incidenteC2);

//        this.incidenteC1.setEstablecimiento(establecimientoC);
//        this.incidenteC2.setEstablecimiento(establecimientoC);

        this.incidenteC1.setHorarioApertura(LocalDateTime.of(2023, 7, 24, 12, 0));
        this.incidenteC1.setHorarioCierre(LocalDateTime.of(2023, 7, 24, 12, 15));

        this.incidenteC2.setHorarioApertura(LocalDateTime.of(2023, 7, 24, 20, 0));
        this.incidenteC2.setHorarioCierre(LocalDateTime.of(2023, 7, 24, 20, 45));
    }

    public void initRankingImpacto(){
        RepoPrestacionDeprecado.agregarPrestacion(prestacionA1);
        RepoPrestacionDeprecado.agregarPrestacion(prestacionA2);
        RepoPrestacionDeprecado.agregarPrestacion(prestacionB1);
        RepoPrestacionDeprecado.agregarPrestacion(prestacionB2);
        RepoPrestacionDeprecado.agregarPrestacion(prestacionC1);

        RepoEntidadDeprecado.agregarEntidad(entidadA);
        RepoEntidadDeprecado.agregarEntidad(entidadB);
        RepoEntidadDeprecado.agregarEntidad(entidadC);

        //seteos de ID, TODO borrar si se usan los repos de hibernate
        entidadA.setId(1);
        entidadB.setId(2);
        entidadC.setId(3);

        //comunidades y miembros para ranking
        Comunidad comunidadA1 = new Comunidad("Comunidad A1");
        Comunidad comunidadA2 = new Comunidad("Comunidad A2");
        Comunidad comunidadB1 = new Comunidad("Comunidad B1");
        Comunidad comunidadC1 = new Comunidad("Comunidad C1");

        Perfil perfilA1 = new Perfil("Perfil A1", comunidadA1, TipoPerfil.NORMAL);
        Perfil perfilA2 = new Perfil("Perfil A2", comunidadA2, TipoPerfil.NORMAL);
        Perfil perfilB1 = new Perfil("Perfil B1", comunidadB1, TipoPerfil.NORMAL);
        Perfil perfilB2 = new Perfil("Perfil B2", comunidadB1, TipoPerfil.NORMAL);
        Perfil perfilC1 = new Perfil("Perfil C1", comunidadC1, TipoPerfil.NORMAL);
        Perfil perfilC2 = new Perfil("Perfil C2", comunidadC1, TipoPerfil.NORMAL);

        perfilA1.setTipoMiembro(TipoMiembro.AFECTADO);
        perfilA2.setTipoMiembro(TipoMiembro.AFECTADO);
        perfilB1.setTipoMiembro(TipoMiembro.AFECTADO);
        perfilB2.setTipoMiembro(TipoMiembro.OBSERVADOR);
        perfilC1.setTipoMiembro(TipoMiembro.AFECTADO);
        perfilC2.setTipoMiembro(TipoMiembro.AFECTADO);

        comunidadA1.agregarMiembros(perfilA1);
        comunidadA2.agregarMiembros(perfilA2);
        comunidadB1.agregarMiembros(perfilB1);
        comunidadB1.agregarMiembros(perfilB2);
        comunidadC1.agregarMiembros(perfilC1);
        comunidadC1.agregarMiembros(perfilC2);

        //establecimiento A
        this.establecimientoA.setEntidad(entidadA);
        this.establecimientoA.setNombre("Estación A");

        this.servicioA1.setNombre("Baño");
        this.servicioA2.setNombre("Ascensor");

//        this.incidenteA1.setObservaciones("El cubiculo 3 del baño no funciona");
//        this.incidenteA2.setObservaciones("El lavabo del baño no funciona");
//        this.incidenteA3.setObservaciones("El ascensor no frena en el piso 2");
//        this.incidenteA4.setObservaciones("El ascensor no sube hasta el piso 3");

        this.prestacionA1.agregarIncidente(incidenteA1);
        this.prestacionA1.agregarIncidente(incidenteA2);
        this.prestacionA2.agregarIncidente(incidenteA3);
        this.prestacionA2.agregarIncidente(incidenteA4);

        this.incidenteA1.setHorarioApertura(LocalDateTime.of(2023, 7, 26, 12, 0));  //Miercoles 26/7 (prestacionA1)
        this.incidenteA1.setHorarioCierre(LocalDateTime.of(2023, 7, 26, 12, 30));
        this.incidenteA1.setEstado(EstadoIncidente.RESUELTO);
        this.incidenteA2.setHorarioApertura(LocalDateTime.of(2023, 7, 26, 15, 0));  //Miercoles 26/7 (prestacionA1)(menos de 24hs pero anterior RESUELTO)
        this.incidenteA3.setHorarioApertura(LocalDateTime.of(2023, 7, 26, 12, 0));  //Miercoles 26/7 (prestacionA2)
        this.incidenteA3.setHorarioCierre(LocalDateTime.of(2023, 7, 26, 13, 0));
        this.incidenteA3.setEstado(EstadoIncidente.RESUELTO);
        this.incidenteA4.setHorarioApertura(LocalDateTime.of(2023, 7, 30, 12, 0));  //Domingo 30/7 (prestacionA2)(misma semana)

        incidenteA1.setComunidad(comunidadA1);  //comunidad.hbs con 1 afectado
        incidenteA2.setComunidad(comunidadA1);  //comunidad.hbs con 1 afectado
        incidenteA3.setComunidad(comunidadA2);  //comunidad.hbs con 1 afectado
        incidenteA4.setComunidad(comunidadA2);  //comunidad.hbs con 1 afectado
        // 4 afectados * [ (30 + 60) t resolucion de incidentes + 2 incidentes no resueltos * CNF{1.5*60} ] = 1080

        //establecimiento B
        this.establecimientoB.setEntidad(entidadB);
        this.establecimientoB.setNombre("Estación B");

        this.servicioB1.setNombre("Rampa");
        this.servicioB2.setNombre("Escalera Mecanica");

//        this.incidenteB1.setObservaciones("La rampa no se puede acceder");
//        this.incidenteB2.setObservaciones("La escalera mecanica no funciona");
//        this.incidenteB3.setObservaciones("La escalera mecanica esta parada");

        this.prestacionB1.agregarIncidente(incidenteB1);
        this.prestacionB2.agregarIncidente(incidenteB2);
        this.prestacionB2.agregarIncidente(incidenteB3);

        this.incidenteB1.setHorarioApertura(LocalDateTime.of(2023, 7, 25, 12, 0));  //Martes 25/7 (prestacionB1)
        this.incidenteB2.setHorarioApertura(LocalDateTime.of(2023, 7, 25, 12, 0));  //Martes 25/7 (prestacionB2)
        this.incidenteB3.setHorarioApertura(LocalDateTime.of(2023, 7, 18, 12, 0));  //Martes 18/7 (prestacionB2)(en otra semana)

        incidenteB1.setComunidad(comunidadB1);  //comunidad.hbs con 1 afectado y 1 observador
        incidenteB2.setComunidad(comunidadB1);  //comunidad.hbs con 1 afectado y 1 observador
        incidenteB3.setComunidad(comunidadB1);  //comunidad.hbs con 1 afectado y 1 observador (pero este es de otra semana)
        // 2 afectados * [ (0) t resolucion de incidentes + 2 incidentes no resueltos * CNF{1.5*60} ] = 360


        //establecimiento C
        this.establecimientoC.setEntidad(entidadC);
        this.establecimientoC.setNombre("Estación C");

        this.servicioC1.setNombre("Puerta Emergencia");

//        this.incidenteC1.setObservaciones("La puerta de emergencia no abre");
//        this.incidenteC2.setObservaciones("La puerta de emergencia no cierra");

        this.prestacionC1.agregarIncidente(incidenteC1);
        this.prestacionC1.agregarIncidente(incidenteC2);

        this.incidenteC1.setHorarioApertura(LocalDateTime.of(2023, 7, 24, 12, 0));  //Lunes 24/7 (prestacionC1)
        this.incidenteC2.setHorarioApertura(LocalDateTime.of(2023, 7, 24, 20, 0));  //Lunes 24/7 (prestacionC1)(menos de 24hs del anterior ABIERTO)

        incidenteC1.setComunidad(comunidadC1);  //comunidad.hbs con 2 afectados
        incidenteC2.setComunidad(comunidadC1);  //comunidad.hbs con 2 afectados
        // 4 afectados * [ (0) t resolucion de incidentes + 2 incidentes no resueltos * CNF{1.5*60} ] = 720

        //TODO guardo en el repo
//        repoServicios2.guardar(servicioA1);
//        repoServicios2.guardar(servicioA2);
//        repoServicios2.guardar(servicioB1);
//        repoServicios2.guardar(servicioB2);
//        repoServicios2.guardar(servicioC1);
//
//        repoEstablecimientos2.guardar(establecimientoA);
//        repoEstablecimientos2.guardar(establecimientoB);
//        repoEstablecimientos2.guardar(establecimientoC);
//
//        repoEntidad2.guardar(entidadA);
//        repoEntidad2.guardar(entidadB);
//        repoEntidad2.guardar(entidadC);

//        repoPrestacion2.guardar(prestacionA1);
//        repoPrestacion2.guardar(prestacionA2);
//        repoPrestacion2.guardar(prestacionB1);
//        repoPrestacion2.guardar(prestacionB2);
//        repoPrestacion2.guardar(prestacionC1);
    }

    @Test
    public void primerRankingMayorPromedioCierre() {
        this.initRankingPromedio();
        LocalDateTime fechaDeSemana = LocalDateTime.of(2023, 7, 25, 12, 0);
        MayorPromedioCierre generadorRanking = new MayorPromedioCierre();

        List<Entidad> rankingEntidades = generadorRanking.generarRanking(fechaDeSemana);
        System.out.println("rankingMayorPromedioCierre: ");
        for (Entidad entidad : rankingEntidades) {
            Integer posicionEnRankingMayorIncidentesReportados = rankingEntidades.indexOf(entidad);
            System.out.println("Estas son las posiciones en las que la entidad " + entidad.getNombre() +
                    " se encuentra en rankingMayorPromedioCierre en la posicion: " + (posicionEnRankingMayorIncidentesReportados+1) + "º. ");
        }
    }

    @Test
    public void segundoRankingMayorIncidentesReportados() {
        this.initRankingMayorIncidentesReportados();
        LocalDateTime fechaDeSemana = LocalDateTime.of(2023, 7, 25, 12, 0);
        MayorIncidentesReportados generadorRanking = new MayorIncidentesReportados();

        List<Entidad> rankingEntidades = generadorRanking.generarRanking(fechaDeSemana);
        System.out.println("rankingMayorIncidentesReportados: ");
        for (Entidad entidad : rankingEntidades) {
            Integer posicionEnRankingMayorIncidentesReportados = rankingEntidades.indexOf(entidad);
            System.out.println("Estas son las posiciones en las que la entidad " + entidad.getNombre() +
                    " se encuentra en rankingMayorIncidentesReportados en la posicion: " + posicionEnRankingMayorIncidentesReportados + "º. ");
        }
    }

    @Test
    public void tercerRankingMayorImpactoProblematicas() {
        this.initRankingImpacto();
        LocalDateTime fechaDeSemana = LocalDateTime.of(2023, 7, 25, 12, 0);
        MayorImpactoProblematicas generadorRanking = new MayorImpactoProblematicas();

        List<Entidad> rankingEntidades = generadorRanking.generarRanking(fechaDeSemana);
        System.out.println("rankingMayorImpactoProblematicas (TODO en 4ta entrega): ");
        for (Entidad entidad : rankingEntidades) {
            Integer posicionEnRankingMayorIncidentesReportados = rankingEntidades.indexOf(entidad) + 1;
            System.out.println("Esta es la posicion en las que la entidad " + entidad.getNombre() +
                    " se encuentra en rankingMayorImpactoProblematicas: " + posicionEnRankingMayorIncidentesReportados + "º. ");
        }
    }

    @Test
    public void enviarInforme() {
        initRankingMayorIncidentesReportados();
        InformeSemanal informeSemanal = new InformeSemanal(LocalDateTime.of(2023, 7, 25, 12, 0));

        Usuario juanPerez = new Usuario("mhermida@frba.utn.edu.ar", "juanPerez", "1234");
        juanPerez.setTipoUsuario(TipoUsuario.ENTIDAD_PRESTADORA);
        juanPerez.agregarEntidadInteres(entidadA);
        ConfiguracionNotificacion configuracionNotificacion = new CuandoSucede();
        juanPerez.setNotificador(new AdapterMailSender());
        juanPerez.setConfiguracionNotificacion(configuracionNotificacion);

        Usuario juanPerez2 = new Usuario("mhermida@frba.utn.edu.ar", "juanPerez2", "1234");
        juanPerez2.setTipoUsuario(TipoUsuario.ORGANISMO_CONTROL);
        juanPerez2.agregarEntidadInteres(entidadB);
        juanPerez2.agregarEntidadInteres(entidadC);
        ConfiguracionNotificacion configuracionNotificacion2 = new CuandoSucede();
        juanPerez2.setNotificador(new AdapterMailSender());
        juanPerez2.setConfiguracionNotificacion(configuracionNotificacion2);

        RepoUsuarioDeprecado.agregarUsuario(juanPerez);
        RepoUsuarioDeprecado.agregarUsuario(juanPerez2);

        List<Usuario> usuarioList = RepoUsuarioDeprecado.getInstancia().getListaUsuarios(); //TODO este RepoUsuario se deberia hacer con Hibernate
        for (Usuario usuario : usuarioList) {
            String msjInformeSemanal;
            //TODO hacerlo polimorfico con composicion? (por si se agreagan mas tipos de usuarios)
            TipoUsuario tipoUsuario = usuario.getTipoUsuario();
            if (tipoUsuario.equals(TipoUsuario.ENTIDAD_PRESTADORA) || tipoUsuario.equals(TipoUsuario.ORGANISMO_CONTROL)) {
                msjInformeSemanal = new String();
                for (Entidad entidad : usuario.getEntidadesInteres()) {
                    msjInformeSemanal = msjInformeSemanal + informeSemanal.posicionesParaEntidad(entidad);
                }
                usuario.recibirInformeSemanal(msjInformeSemanal);
            }
        }
    }

    @Test
    public void mostrarInforme() {
        initRankingMayorIncidentesReportados();
        InformeSemanal informeSemanal = new InformeSemanal(LocalDateTime.of(2023, 7, 25, 12, 0));

        RepoInforme repoInforme = new RepoInforme();
        repoInforme.guardar(informeSemanal);

        System.out.println(informeSemanal.getRankingMayorImpactoProblematicasString());
        System.out.println(informeSemanal.getRankingMayorIncidentesReportadosString());
        System.out.println(informeSemanal.getRankingMayorPromedioCierreString());
    }

    @Test
    public void mostrarInformeListaStrings() {
        initRankingMayorIncidentesReportados();
        InformeSemanal informeSemanal = new InformeSemanal(LocalDateTime.of(2023, 7, 25, 12, 0));

        System.out.println(informeSemanal.generarListaDeStrings(informeSemanal.getRankingMayorImpactoProblematicasString()));
        System.out.println(informeSemanal.generarListaDeStrings(informeSemanal.getRankingMayorIncidentesReportadosString()));
        System.out.println(informeSemanal.generarListaDeStrings(informeSemanal.getRankingMayorPromedioCierreString()));
    }

    @Test
    public void crearInformeConDatosActualizados() {

        InformeSemanal informeSemanal = new InformeSemanal(LocalDateTime.now());

        RepoInforme repoInforme = new RepoInforme();
        repoInforme.guardar(informeSemanal);

        System.out.println(informeSemanal.getRankingMayorImpactoProblematicasString());
        System.out.println(informeSemanal.getRankingMayorIncidentesReportadosString());
        System.out.println(informeSemanal.getRankingMayorPromedioCierreString());
    }
}
