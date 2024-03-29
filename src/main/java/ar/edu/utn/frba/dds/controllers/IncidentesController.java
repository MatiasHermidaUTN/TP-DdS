package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.comunidades.Comunidad;
import ar.edu.utn.frba.dds.models.comunidades.Perfil;
import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import ar.edu.utn.frba.dds.models.incidentes.EstadoIncidente;
import ar.edu.utn.frba.dds.models.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.incidentes.Observacion;
import ar.edu.utn.frba.dds.models.incidentes.Prestacion;
import ar.edu.utn.frba.dds.models.localizacion.AdapterCercaniaLocalizacion;
import ar.edu.utn.frba.dds.models.localizacion.Localizacion;
import ar.edu.utn.frba.dds.models.ranking.MayorImpactoProblematicas;
import ar.edu.utn.frba.dds.models.ranking.MayorIncidentesReportados;
import ar.edu.utn.frba.dds.models.ranking.MayorPromedioCierre;
import ar.edu.utn.frba.dds.models.repositorios.*;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Servicio;
import ar.edu.utn.frba.dds.utils.Logueo;
import io.javalin.http.Context;

import java.time.LocalDateTime;
import java.util.*;


public class IncidentesController {
    private final Integer radioCercaniaMetros = 2000;

    private RepoIncidente repoIncidente;
    private RepoPerfil repoPerfil;
    private RepoEntidad repoEntidad;
    private RepoEstablecimiento repoEstablecimiento;
    private RepoServicio repoServicio;
    private RepoUsuario repoUsuario;
    private RepoComunidad repoComunidad;

    public IncidentesController(RepoIncidente repoIncidente,
                                RepoPerfil repoPerfil,
                                RepoEntidad repoEntidad,
                                RepoEstablecimiento repoEstablecimiento,
                                RepoServicio repoServicio,
                                RepoUsuario repoUsuario,
                                RepoComunidad repoComunidad) {
        this.repoIncidente = repoIncidente;
        this.repoPerfil = repoPerfil;
        this.repoEntidad = repoEntidad;
        this.repoServicio = repoServicio;
        this.repoEstablecimiento = repoEstablecimiento;
        this.repoUsuario = repoUsuario;
        this.repoComunidad = repoComunidad;
    }

    public void index(Context context) {
        if(Logueo.comprobarLogueo(context)) {
            Map<String, Object> model = new HashMap<>();
            List<Incidente> incidentes = this.repoIncidente.buscarPorComunidad(Integer.parseInt(context.pathParam("id")));

            model.put("incidentes", incidentes);
            context.render("incidentes/incidentes.hbs", model);
        }
    }

    public void show(Context context) {
        if(Logueo.comprobarLogueo(context)) {
            Incidente incidente = this.repoIncidente.buscarPorId(Integer.valueOf(context.pathParam("id")));
            Map<String, Object> model = new HashMap<>();
            model.put("incidente", incidente);
            context.render("incidentes/incidente.hbs", model);
        }
    }

    public void crear(Context context) {
        if(Logueo.comprobarLogueo(context)) {
            Map<String, Object> model = new HashMap<>();
            List<Entidad> entidades = this.repoEntidad.buscarTodos();
            List<Establecimiento> establecimientos = this.repoEstablecimiento.buscarTodos();
            List<Servicio> servicios = this.repoServicio.buscarTodos();
            model.put("entidades", entidades);
            model.put("establecimientos", establecimientos);
            model.put("servicios", servicios);
            context.render("incidentes/crear.hbs", model);
        }
    }

    public void procesar_creacion(Context context) {
        Perfil perfilApertura = this.repoPerfil.buscarPorId(Integer.valueOf(context.cookie("perfil_id")));
        Usuario usuarioApertura = this.repoUsuario.buscarPorId(Integer.valueOf(context.cookie("usuario_id")));
        Establecimiento establecimiento = this.repoEstablecimiento.buscarPorId(Integer.valueOf(context.formParam("establecimiento")));
        Servicio servicio = this.repoServicio.buscarPorId(Integer.valueOf(context.formParam("servicio")));
        Observacion observacion = new Observacion(usuarioApertura, context.formParam("observaciones"));
        Incidente incidente = crearIncidenteParaPerfil(establecimiento, servicio, perfilApertura, observacion);
        this.repoIncidente.guardar(incidente);

        context.redirect("/comunidades/" + perfilApertura.getComunidad().getId() + "/incidentes");
    }


    public void cerrar(Context context) {
        Incidente incidenteACerrar = this.repoIncidente.buscarPorId(Integer.valueOf(context.pathParam("id")));
        Perfil perfil = this.repoPerfil.buscarPorId(Integer.valueOf(context.cookie("perfil_id")));

        incidenteACerrar.setUsuarioCierre(this.repoUsuario.buscarPorId(perfil.getUsuario().getId()));
        incidenteACerrar.setHorarioCierre(LocalDateTime.now());
        incidenteACerrar.setEstado(EstadoIncidente.RESUELTO);

        // notificar a cada miembro
        perfil.getComunidad().getMiembros()
                .forEach(miembro -> miembro.getUsuario().recibirNotificacionDeCierreDeIncidente(incidenteACerrar));


        repoIncidente.modificar(incidenteACerrar);
//        context.redirect("/comunidades/" + perfil.getComunidad().getId() + "/incidentes");
        context.redirect("/comunidades/" + incidenteACerrar.getComunidad().getId() + "/incidentes");
    }

    public void asignarParametros(Incidente incidente, Context context) {
        // TODO
    }

    public void crear_o_agregar_prestacion(Establecimiento establecimiento_a_buscar, Servicio servicio_a_buscar, Incidente incidente) {
        RepoPrestacion repoPrestacion = new RepoPrestacion();
        List<Prestacion> listaPrestaciones = repoPrestacion.buscarTodos();

        List<Prestacion> listaPrestacionesDelEstablecimiento = listaPrestaciones.stream()
                .filter(prestacion -> prestacion.getEstablecimiento().getNombre() == establecimiento_a_buscar.getNombre())
                .toList();

        if (listaPrestacionesDelEstablecimiento.isEmpty()) {
            Prestacion nuevaPrestacion = new Prestacion(establecimiento_a_buscar, servicio_a_buscar);
            nuevaPrestacion.agregarIncidente(incidente);
            repoPrestacion.guardar(nuevaPrestacion);
            incidente.setPrestacion(nuevaPrestacion);
        } else {
            Prestacion prestacionDelServicioDelEstablecimiento = listaPrestacionesDelEstablecimiento.stream()
                    .filter(prestacion -> prestacion.getServicio().getNombre() == servicio_a_buscar.getNombre())
                    .findAny()
                    .orElse(null);

            if (prestacionDelServicioDelEstablecimiento == null) {
                Prestacion nuevaPrestacion = new Prestacion(establecimiento_a_buscar, servicio_a_buscar);
                nuevaPrestacion.agregarIncidente(incidente);
                incidente.setPrestacion(nuevaPrestacion);
                repoPrestacion.guardar(nuevaPrestacion);
            } else {
                prestacionDelServicioDelEstablecimiento.agregarIncidente(incidente);
                incidente.setPrestacion(prestacionDelServicioDelEstablecimiento);
            }
        }
    }

    public Incidente crearIncidenteParaPerfil(Establecimiento establecimiento, Servicio servicio, Perfil perfil, Observacion observacion) {

        Comunidad unaComunidad = perfil.getComunidad();
        Incidente incidente = new Incidente(establecimiento, unaComunidad, servicio, perfil.getUsuario());
        incidente.agregarObservacion(observacion);
        incidente.setComunidad(unaComunidad);
        unaComunidad.agregarIncidente(incidente);

        crear_o_agregar_prestacion(establecimiento, servicio, incidente);

        // notificar a cada miembro
        System.out.println("Está por notificar a los miembros de la comunidad");
        unaComunidad.getMiembros()
                .forEach(miembro -> miembro.getUsuario().recibirNotificacionDeAperturaDeIncidente(incidente));

//        List<Usuario> usuarioList = this.repoUsuario.buscarTodos();
//        usuarioList.stream()
//                .filter(
//                        unUsuario -> unUsuario.getServiciosInteres().contains(servicio) &&
//                                unUsuario.getEntidadesInteres()
//                                        .contains(establecimiento.getEntidad())
//                )
//                // se lo mandamos a un solo perfil de cada usuario para que no reciba notificaciones repetidas (por cada uno de sus perfiles)
//                .forEach(usuario -> usuario.
//                        recibirNotificacionDeAperturaDeIncidente(new Incidente(establecimiento, new Comunidad("Servicio Interes Particular"), servicio, perfil.getUsuario())));

        return incidente;
    }

    public void incidentesCercanos(Context context) {
        Map<String, Object> model = new HashMap<>();
        List<Incidente> incidentes = this.repoIncidente.buscarTodos();

        RepoUsuario repoUsuario = new RepoUsuario();

        Usuario usuario = repoUsuario.buscarPorId(Integer.valueOf(Objects.requireNonNull(context.cookie("usuario_id"))));

        try {
            Localizacion locaUsuario = usuario.getLocalizacion();
            List<Incidente> incidenteCercanos = AdapterCercaniaLocalizacion.filtrarIncidentesCercanos(
                    incidentes,
                    locaUsuario.getUbicacion().getLat(),
                    locaUsuario.getUbicacion().getLon(),
                    radioCercaniaMetros);

            model.put("incidentes", incidenteCercanos);
            context.render("incidentes/incidentesCercanos.hbs", model);
        } catch (NullPointerException e) {
            String redirect = "<script> window.alert(\"El usuario no tiene localizacion, asignela en la seccion 'editar datos'.\");"
                    + "setTimeout(function() { window.location.href = '/usuarios/perfiles'; }, 0); </script>";
            context.html(redirect);
        }


    }

    public void incidenteCercano(Context context) {
        if(Logueo.comprobarLogueo(context)) {
            Incidente incidente = this.repoIncidente.buscarPorId(Integer.valueOf(context.pathParam("id")));
            Map<String, Object> model = new HashMap<>();
            model.put("incidente", incidente);
            context.render("incidentes/unIncidenteCercano.hbs", model);
        }
    }

    public void incidenteCercanoCerrar(Context context) {
        Incidente incidenteACerrar = this.repoIncidente.buscarPorId(Integer.valueOf(context.pathParam("id")));
        Usuario usuario = this.repoUsuario.buscarPorId(Integer.valueOf(context.cookie("usuario_id")));

        incidenteACerrar.setUsuarioCierre(this.repoUsuario.buscarPorId(usuario.getId()));
        incidenteACerrar.setHorarioCierre(LocalDateTime.now());
        incidenteACerrar.setEstado(EstadoIncidente.RESUELTO);

        // notificar a cada miembro
        incidenteACerrar.getComunidad().getMiembros()
                .forEach(miembro -> miembro.getUsuario().recibirNotificacionDeCierreDeIncidente(incidenteACerrar));


        repoIncidente.modificar(incidenteACerrar);

        String redirectScript = """
                <script>
                setTimeout(function() { window.location.href = '/incidentesCercanos'; }, 500);
                </script>
                """;

        context.html(redirectScript);
    }

    public void observaciones(Context context) {
        if(Logueo.comprobarLogueo(context)) {
            Map<String, Object> model = new HashMap<>();
            Incidente incidente = repoIncidente.buscarPorId(Integer.valueOf(context.pathParam("id")));
            List<Observacion> observaciones = incidente.getObservaciones();
            model.put("incidente", incidente);
            model.put("observaciones", observaciones);
            context.render("incidentes/observaciones.hbs", model);
        }
    }

    public void agregar_observacion(Context context) {
        if(Logueo.comprobarLogueo(context)) {
            Map<String, Object> model = new HashMap<>();
            Incidente incidente = repoIncidente.buscarPorId(Integer.valueOf(context.pathParam("id")));
            model.put("incidente", incidente);
            context.render("incidentes/agregar_observacion.hbs", model);
        }
    }

    public void procesar_observacion(Context context) {
        Usuario usuarioQueAgregaObservacion = this.repoUsuario.buscarPorId(Integer.valueOf(context.cookie("usuario_id")));
        Incidente incidente = this.repoIncidente.buscarPorId(Integer.valueOf(context.pathParam("id")));
        Observacion observacion = new Observacion(usuarioQueAgregaObservacion, context.formParam("observaciones"));
        incidente.agregarObservacion(observacion);
        this.repoIncidente.modificar(incidente);
        context.redirect("/incidentes/" + incidente.getId());
    }
}
