package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.comunidades.Comunidad;
import ar.edu.utn.frba.dds.models.comunidades.Perfil;
import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import ar.edu.utn.frba.dds.models.incidentes.EstadoIncidente;
import ar.edu.utn.frba.dds.models.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.incidentes.Prestacion;
import ar.edu.utn.frba.dds.models.repositorios.RepoComunidad;
import ar.edu.utn.frba.dds.models.repositorios.RepoIncidente;
import ar.edu.utn.frba.dds.models.repositorios.RepoPrestacion;
import ar.edu.utn.frba.dds.models.repositorios.RepoUsuario;
import ar.edu.utn.frba.dds.models.repositorios.reposDeprecados.RepoComunidadDeprecado;
import ar.edu.utn.frba.dds.models.repositorios.reposDeprecados.RepoPrestacionDeprecado;
import ar.edu.utn.frba.dds.models.repositorios.reposDeprecados.RepoUsuarioDeprecado;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Servicio;
import io.javalin.http.Context;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class IncidentesController {
    private RepoIncidente repoIncidente;

    public IncidentesController(RepoIncidente repoIncidente) {
        this.repoIncidente = repoIncidente;
    }

    public void index(Context context){
        Map<String, Object> model = new HashMap<>();
        List<Incidente> incidentes = this.repoIncidente.buscarPorComunidad(Integer.parseInt(context.pathParam("id")));

        model.put("incidentes", incidentes);
        context.render("incidentes/incidentes.hbs", model);
    }

    public void show(Context context){
        // TODO
    }

    public void create(Context context){
        Incidente incidente = null;
        Map<String, Object> model = new HashMap<>();
        model.put("incidente", incidente);
        context.render("incidentes/incidente.hbs", model);
    }

    public void save(Context context){
        // TODO
    }

    public void edit(Context context){
        // TODO
    }

    public void delete(Context context){
        // TODO
    }

    public void cerrar(Context context){
        // TODO
        //cerrarEnComunidad(); como saco los parametros necesarios para este metodo ???
        //repoIncidente.modificar(incidente); aca tengo que ver de donde saco el incidente a cerrar
        context.redirect("/comunidades/1/incidentes"); // el comunidad_id aca esta hardcodeado, hay que cambiarlo
    }

    public void asignarParametros(Incidente incidente, Context context){
        // TODO
    }

    public void crear_o_agregar_prestacion(Establecimiento establecimiento_a_buscar, Servicio servicio_a_buscar, Incidente incidente){
        RepoPrestacion repoPrestacion = new RepoPrestacion();
        List<Prestacion> listaPrestaciones = repoPrestacion.buscarTodos();

        List <Prestacion> listaPrestacionesDelEstablecimiento = listaPrestaciones.stream()
                .filter(prestacion -> prestacion.getEstablecimiento().getNombre() == establecimiento_a_buscar.getNombre())
                .toList();

        if(listaPrestacionesDelEstablecimiento.isEmpty()){
            Prestacion nuevaPrestacion = new Prestacion(establecimiento_a_buscar, servicio_a_buscar);
            nuevaPrestacion.agregarIncidente(incidente);
            repoPrestacion.guardar(nuevaPrestacion);
        }
        else {
            Prestacion prestacionDelServicioDelEstablecimiento =  listaPrestacionesDelEstablecimiento.stream()
                    .filter(prestacion -> prestacion.getServicio().getNombre() == servicio_a_buscar.getNombre())
                    .findAny()
                    .orElse(null);

            if(prestacionDelServicioDelEstablecimiento == null){
                Prestacion nuevaPrestacion = new Prestacion(establecimiento_a_buscar, servicio_a_buscar);
                nuevaPrestacion.agregarIncidente(incidente);
                repoPrestacion.guardar(nuevaPrestacion);
            }
            else {
                prestacionDelServicioDelEstablecimiento.agregarIncidente(incidente);
            }
        }
    }
    public void crearIncidente(Establecimiento establecimiento, Servicio servicio, Usuario usuarioApertura) {

        RepoComunidadDeprecado.getInstancia();

        List<Comunidad> comunidades = usuarioApertura.getPerfiles()
                .stream()
                .map(perfil -> perfil.getComunidad()).toList();

        for(Comunidad unaComunidad : comunidades) {
            Incidente incidente = new Incidente(establecimiento, unaComunidad.getNombre(), servicio, usuarioApertura);
            unaComunidad.agregarIncidente(incidente);
            // TODO Esto lo tendria que hacer el prestacion controller (creo)
            crear_o_agregar_prestacion(establecimiento, servicio, incidente);

            // notificar a cada miembro
            unaComunidad.getMiembros()
                    .forEach(perfil -> perfil.getUsuario().recibirNotificacionDeAperturaDeIncidente(incidente));
        }

        List<Usuario> usuarioList = new RepoUsuario().buscarTodos(); // no se si esta bien
        usuarioList.stream()
                .filter(
                        unUsuario -> unUsuario.getServiciosInteres().contains(servicio) &&
                                unUsuario.getEntidadesInteres()
                                        .contains(establecimiento.getEntidad())
                )
                // se lo mandamos a un solo perfil de cada usuario para que no reciba notificaciones repetidas (por cada uno de sus perfiles)
                .forEach(usuario -> usuario.
                        recibirNotificacionDeAperturaDeIncidente(new Incidente(establecimiento, "Servicio Interes Particular", servicio, usuarioApertura)));
    }


    public void cerrarEnComunidad(Integer incidente_id, Integer usuario_cierre_id, Integer comunidad_id, LocalDateTime horarioCierre) {
        Incidente incidenteACerrar = repoIncidente.buscarPorId(incidente_id);

        RepoUsuario repoUsuario = new RepoUsuario(); // esto no se si esta bien
        incidenteACerrar.setUsuarioCierre(repoUsuario.buscarPorId(usuario_cierre_id));
        incidenteACerrar.setHorarioCierre(LocalDateTime.now());
        incidenteACerrar.setEstado(EstadoIncidente.RESUELTO);

        // notificar a cada miembro
        new RepoComunidad().buscarPorId(comunidad_id).getMiembros()
                .forEach(perfil -> perfil.getUsuario().recibirNotificacionDeCierreDeIncidente(incidenteACerrar));

        // guardarlo en la base
        repoIncidente.merge(incidenteACerrar);
    }

}
