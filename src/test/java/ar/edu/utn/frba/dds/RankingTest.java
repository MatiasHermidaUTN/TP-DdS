package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.comunidades.TipoUsuario;
import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.incidentes.EstadoIncidente;
import ar.edu.utn.frba.dds.incidentes.Incidente;
import ar.edu.utn.frba.dds.incidentes.Prestacion;
import ar.edu.utn.frba.dds.notificaciones.AdapterMailSender;
import ar.edu.utn.frba.dds.notificaciones.ConfiguracionNotificacion;
import ar.edu.utn.frba.dds.notificaciones.CuandoSucede;
import ar.edu.utn.frba.dds.notificaciones.SinApuros;
import ar.edu.utn.frba.dds.ranking.InformeSemanal;
import ar.edu.utn.frba.dds.ranking.MayorImpactoProblematicas;
import ar.edu.utn.frba.dds.ranking.MayorIncidentesReportados;
import ar.edu.utn.frba.dds.ranking.MayorPromedioCierre;
import ar.edu.utn.frba.dds.repositorios.RepoEntidad;
import ar.edu.utn.frba.dds.repositorios.RepoIncidente;
import ar.edu.utn.frba.dds.repositorios.RepoPrestacion;
import ar.edu.utn.frba.dds.repositorios.RepoUsuario;
import ar.edu.utn.frba.dds.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.serviciosPublicos.Servicio;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class RankingTest {
    RepoPrestacion repoPrestacion = RepoPrestacion.getInstancia();
    RepoEntidad repoEntidad = RepoEntidad.getInstancia();
    RepoIncidente repoIncidente = RepoIncidente.getInstancia();

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

    public void initRankingMayorIncidentesReportados(){
        RepoPrestacion.agregarPrestacion(prestacionA1);
        RepoPrestacion.agregarPrestacion(prestacionA2);
        RepoPrestacion.agregarPrestacion(prestacionB1);
        RepoPrestacion.agregarPrestacion(prestacionB2);
        RepoPrestacion.agregarPrestacion(prestacionC1);

        RepoEntidad.agregarEntidad(entidadC);
        RepoEntidad.agregarEntidad(entidadA);
        RepoEntidad.agregarEntidad(entidadB);

        //establecimiento A
        this.establecimientoA.setEntidad(entidadA);
        this.establecimientoA.setNombre("Estación A");

        this.servicioA1.setNombre("Baño");
        this.servicioA2.setNombre("Ascensor");
        this.prestacionA1.agregarIncidente(incidenteA1);
        this.prestacionA1.agregarIncidente(incidenteA2);
        this.prestacionA2.agregarIncidente(incidenteA3);
        this.prestacionA2.agregarIncidente(incidenteA4);

        this.incidenteA1.setEstablecimiento(establecimientoA);
        this.incidenteA2.setEstablecimiento(establecimientoA);
        this.incidenteA3.setEstablecimiento(establecimientoA);
        this.incidenteA4.setEstablecimiento(establecimientoA);
        this.incidenteA1.setServicio(servicioA1);
        this.incidenteA2.setServicio(servicioA1);
        this.incidenteA3.setServicio(servicioA2);
        this.incidenteA4.setServicio(servicioA2);
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

        this.incidenteB1.setEstablecimiento(establecimientoB);
        this.incidenteB2.setEstablecimiento(establecimientoB);
        this.incidenteB3.setEstablecimiento(establecimientoB);
        this.incidenteB1.setServicio(servicioB1);
        this.incidenteB2.setServicio(servicioB2);
        this.incidenteB3.setServicio(servicioB2);
        this.incidenteB1.setHorarioApertura(LocalDateTime.of(2023, 7, 25, 12, 0));  //Martes 25/7 (prestacionB1)
        this.incidenteB2.setHorarioApertura(LocalDateTime.of(2023, 7, 25, 12, 0));  //Martes 25/7 (prestacionB2)
        this.incidenteB3.setHorarioApertura(LocalDateTime.of(2023, 7, 18, 12, 0));  //Martes 18/7 (prestacionB2)(en otra semana)

        //establecimiento C
        this.establecimientoC.setEntidad(entidadC);
        this.establecimientoC.setNombre("Estación C");

        this.servicioC1.setNombre("Puerta Emergencia");
        this.prestacionC1.agregarIncidente(incidenteC1);
        this.prestacionC1.agregarIncidente(incidenteC2);

        this.incidenteC1.setEstablecimiento(establecimientoC);
        this.incidenteC2.setEstablecimiento(establecimientoC);
        this.incidenteC1.setServicio(servicioC1);
        this.incidenteC2.setServicio(servicioC1);
        this.incidenteC1.setHorarioApertura(LocalDateTime.of(2023, 7, 24, 12, 0));  //Lunes 24/7 (prestacionC1)
        this.incidenteC2.setHorarioApertura(LocalDateTime.of(2023, 7, 24, 20, 0));  //Lunes 24/7 (prestacionC1)(menos de 24hs del anterior ABIERTO)
    }

    public void initRankingPromedio(){
        RepoIncidente.agregarIncidente(incidenteA1);
        RepoIncidente.agregarIncidente(incidenteA2);
        RepoIncidente.agregarIncidente(incidenteA3);
        RepoIncidente.agregarIncidente(incidenteA4);
        RepoIncidente.agregarIncidente(incidenteB1);
        RepoIncidente.agregarIncidente(incidenteB2);
        RepoIncidente.agregarIncidente(incidenteB3);
        RepoIncidente.agregarIncidente(incidenteC1);
        RepoIncidente.agregarIncidente(incidenteC2);

        RepoEntidad.agregarEntidad(entidadB);
        RepoEntidad.agregarEntidad(entidadC);
        RepoEntidad.agregarEntidad(entidadA);

        //establecimiento A
        this.establecimientoA.setEntidad(entidadA);
        this.establecimientoA.setNombre("Estación A");

        this.incidenteA1.setEstablecimiento(establecimientoA);
        this.incidenteA2.setEstablecimiento(establecimientoA);
        this.incidenteA3.setEstablecimiento(establecimientoA);
        this.incidenteA4.setEstablecimiento(establecimientoA);

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

        this.incidenteB1.setEstablecimiento(establecimientoB);
        this.incidenteB2.setEstablecimiento(establecimientoB);
        this.incidenteB3.setEstablecimiento(establecimientoB);

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

        this.incidenteC1.setEstablecimiento(establecimientoC);
        this.incidenteC2.setEstablecimiento(establecimientoC);

        this.incidenteC1.setHorarioApertura(LocalDateTime.of(2023, 7, 24, 12, 0));
        this.incidenteC1.setHorarioCierre(LocalDateTime.of(2023, 7, 24, 12, 15));

        this.incidenteC2.setHorarioApertura(LocalDateTime.of(2023, 7, 24, 20, 0));
        this.incidenteC2.setHorarioCierre(LocalDateTime.of(2023, 7, 24, 20, 45));
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
        LocalDateTime fechaDeSemana = LocalDateTime.of(2023, 7, 25, 12, 0);
        MayorImpactoProblematicas generadorRanking = new MayorImpactoProblematicas();

        List<Entidad> rankingEntidades = generadorRanking.generarRanking(fechaDeSemana);
        System.out.println("rankingMayorImpactoProblematicas (TODO en 4ta entrega): ");
        for (Entidad entidad : rankingEntidades) {
            Integer posicionEnRankingMayorIncidentesReportados = rankingEntidades.indexOf(entidad);
            System.out.println("Estas son las posiciones en las que la entidad " + entidad.getNombre() +
                    " se encuentra en rankingMayorImpactoProblematicas en la posicion: " + posicionEnRankingMayorIncidentesReportados + "º. ");
        }
    }

    @Test
    public void enviarInforme() {
        initRankingMayorIncidentesReportados();
        InformeSemanal informeSemanal = new InformeSemanal(LocalDateTime.of(2023, 7, 25, 12, 0));

        Usuario juanPerez = new Usuario("mhermida@frba.utn.edu.ar", "juanPerez", "1234");
        juanPerez.setTipoUsuario(TipoUsuario.ENTIDAD_PRESTADORA);
        juanPerez.agregarEntidadInteres(entidadA);
        ConfiguracionNotificacion configuracionNotificacion = new CuandoSucede(juanPerez);
        configuracionNotificacion.setNotificador(new AdapterMailSender());
        juanPerez.setConfiguracionNotificacion(configuracionNotificacion);

        Usuario juanPerez2 = new Usuario("mhermida@frba.utn.edu.ar", "juanPerez2", "1234");
        juanPerez2.setTipoUsuario(TipoUsuario.ORGANISMO_CONTROL);
        juanPerez2.agregarEntidadInteres(entidadB);
        juanPerez2.agregarEntidadInteres(entidadC);
        ConfiguracionNotificacion configuracionNotificacion2 = new CuandoSucede(juanPerez2);
        configuracionNotificacion2.setNotificador(new AdapterMailSender());
        juanPerez2.setConfiguracionNotificacion(configuracionNotificacion2);

        RepoUsuario.agregarUsuario(juanPerez);
        RepoUsuario.agregarUsuario(juanPerez2);

        List<Usuario> usuarioList = RepoUsuario.getInstancia().getListaUsuarios(); //TODO este RepoUsuario se deberia hacer con Hibernate
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
}
