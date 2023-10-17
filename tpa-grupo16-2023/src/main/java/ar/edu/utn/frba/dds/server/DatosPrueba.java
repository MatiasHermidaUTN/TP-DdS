package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.models.comunidades.*;
import ar.edu.utn.frba.dds.models.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.incidentes.Observacion;
import ar.edu.utn.frba.dds.models.incidentes.Prestacion;
import ar.edu.utn.frba.dds.models.repositorios.*;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Servicio;

public class DatosPrueba {
    static RepoIncidente repoIncidente = new RepoIncidente();
    static RepoPrestacion repoPrestacion = new RepoPrestacion();
    static RepoComunidad repoComunidad = new RepoComunidad();
    static RepoPerfil repoPerfil = new RepoPerfil();
    static RepoUsuario repoUsuario = new RepoUsuario();
    static RepoEntidad repoEntidad = new RepoEntidad();
    static RepoEstablecimiento repoEstablecimiento = new RepoEstablecimiento();
    static RepoServicio repoServicio = new RepoServicio();

    public static void cargarDatos() {
        //usuarios
        Usuario usr1 = new Usuario("usr1@mail.com", "Usuario1", "passUsr1!");
        repoUsuario.guardar(usr1);

        Usuario usr2 = new Usuario("usr2@mail.com", "Usuario2", "passUsr2!");
        repoUsuario.guardar(usr2);

        Usuario usr3 = new Usuario("usr3@mail.com", "Usuario3", "passUsr3!");
        repoUsuario.guardar(usr3);

        //comunidades
        Comunidad comu1 = new Comunidad("Comunidad1");
        comu1.setActiva(true);
        comu1.setGradoDeConfianza(GradoDeConfianza.CONFIABLE_NIVEL_2);
        repoComunidad.guardar(comu1);

        Comunidad comu2 = new Comunidad("Comunidad2");
        comu2.setActiva(true);
        comu2.setGradoDeConfianza(GradoDeConfianza.CONFIABLE_NIVEL_1);
        repoComunidad.guardar(comu2);

        Comunidad comu3 = new Comunidad("Comunidad3");
        comu3.setActiva(true);
        comu3.setGradoDeConfianza(GradoDeConfianza.CON_RESERVAS);
        repoComunidad.guardar(comu3);

        //perfiles
        Perfil prf1 = new Perfil("Perfil1", comu1, TipoPerfil.NORMAL);
        prf1.setTipoMiembro(TipoMiembro.AFECTADO);
        prf1.setUsuario(usr1);
        usr1.agregarPerfil(prf1);
        comu1.agregarMiembros(prf1);
        repoPerfil.guardar(prf1);

        Perfil prf2 = new Perfil("Perfil2", comu2, TipoPerfil.NORMAL);
        prf2.setTipoMiembro(TipoMiembro.AFECTADO);
        prf2.setUsuario(usr2);
        usr2.agregarPerfil(prf2);
        comu2.agregarMiembros(prf2);
        repoPerfil.guardar(prf2);

        Perfil prf3 = new Perfil("Perfil3", comu3, TipoPerfil.NORMAL);
        prf3.setTipoMiembro(TipoMiembro.AFECTADO);
        prf3.setUsuario(usr3);
        usr3.agregarPerfil(prf3);
        comu3.agregarMiembros(prf3);
        repoPerfil.guardar(prf3);

        //entidades
        Entidad ent1 = new Entidad("Entidad1");
        repoEntidad.guardar(ent1);

        Entidad ent2 = new Entidad("Entidad2");
        repoEntidad.guardar(ent2);

        Entidad ent3 = new Entidad("Entidad3");
        repoEntidad.guardar(ent3);

        //establecimientos
        Establecimiento est1 = new Establecimiento("Establecimiento1");
        est1.setEntidad(ent1);
        ent1.agregarEstablecimientos(est1);
        repoEstablecimiento.guardar(est1);

        Establecimiento est2 = new Establecimiento("Establecimiento2");
        est2.setEntidad(ent2);
        ent2.agregarEstablecimientos(est2);
        repoEstablecimiento.guardar(est2);

        Establecimiento est3 = new Establecimiento("Establecimiento3");
        est3.setEntidad(ent3);
        ent3.agregarEstablecimientos(est3);
        repoEstablecimiento.guardar(est3);

        //servicios
        Servicio serv1 = new Servicio("Servicio1");
        serv1.setEstablecimiento(est1);
        est1.agregarServicios(serv1);
        repoServicio.guardar(serv1);

        Servicio serv2 = new Servicio("Servicio2");
        serv2.setEstablecimiento(est2);
        est2.agregarServicios(serv2);
        repoServicio.guardar(serv2);

        Servicio serv3 = new Servicio("Servicio3");
        serv3.setEstablecimiento(est3);
        est3.agregarServicios(serv3);
        repoServicio.guardar(serv3);

        Servicio serv4 = new Servicio("Servicio4");
        serv4.setEstablecimiento(est1);
        est1.agregarServicios(serv4);
        repoServicio.guardar(serv4);

        Servicio serv5 = new Servicio("Servicio5");
        serv5.setEstablecimiento(est2);
        est2.agregarServicios(serv5);
        repoServicio.guardar(serv5);

        //prestaciones
        Prestacion prestacion1 = new Prestacion(est1, serv1);
        repoPrestacion.guardar(prestacion1);

        Prestacion prestacion2 = new Prestacion(est2, serv2);
        repoPrestacion.guardar(prestacion2);

        Prestacion prestacion3 = new Prestacion(est3, serv3);
        repoPrestacion.guardar(prestacion3);

        Prestacion prestacion4 = new Prestacion(est1, serv4);
        repoPrestacion.guardar(prestacion4);

        Prestacion prestacion5 = new Prestacion(est2, serv5);
        repoPrestacion.guardar(prestacion5);

        //incidentes
        Incidente incidente1 = new Incidente(comu1, usr1, prestacion1);
        incidente1.agregarObservacion(new Observacion(usr1, "Incidente1"));
        prestacion1.agregarIncidente(incidente1);
        repoIncidente.guardar(incidente1);

        Incidente incidente2 = new Incidente(comu2, usr2, prestacion2);
        incidente2.agregarObservacion(new Observacion(usr2, "Incidente2"));
        prestacion2.agregarIncidente(incidente2);
        repoIncidente.guardar(incidente2);

        Incidente incidente3 = new Incidente(comu3, usr3, prestacion3);
        incidente3.agregarObservacion(new Observacion(usr3, "Incidente3"));
        prestacion3.agregarIncidente(incidente3);
        repoIncidente.guardar(incidente3);

        Incidente incidente4 = new Incidente(comu1, usr2, prestacion4);
        incidente4.agregarObservacion(new Observacion(usr2, "Incidente4"));
        prestacion4.agregarIncidente(incidente4);
        repoIncidente.guardar(incidente4);

        Incidente incidente5 = new Incidente(comu2, usr3, prestacion5);
        incidente5.agregarObservacion(new Observacion(usr3, "Incidente5"));
        prestacion5.agregarIncidente(incidente5);
        repoIncidente.guardar(incidente5);

        Incidente incidente6 = new Incidente(comu3, usr1, prestacion1);
        incidente6.agregarObservacion(new Observacion(usr1, "Incidente6"));
        prestacion1.agregarIncidente(incidente6);
        repoIncidente.guardar(incidente6);

        Incidente incidente7 = new Incidente(comu1, usr3, prestacion2);
        incidente7.agregarObservacion(new Observacion(usr3, "Incidente7"));
        prestacion2.agregarIncidente(incidente7);
        repoIncidente.guardar(incidente7);

        Incidente incidente8 = new Incidente(comu2, usr1, prestacion3);
        incidente8.agregarObservacion(new Observacion(usr1, "Incidente8"));
        prestacion3.agregarIncidente(incidente8);
        repoIncidente.guardar(incidente8);

        Incidente incidente9 = new Incidente(comu3, usr2, prestacion4);
        incidente9.agregarObservacion(new Observacion(usr2, "Incidente9"));
        prestacion4.agregarIncidente(incidente9);
        repoIncidente.guardar(incidente9);
    }
}
