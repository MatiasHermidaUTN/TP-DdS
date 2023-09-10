package ar.edu.utn.frba.dds.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class SugeridorDeFusiones {
    private Long id;

    private Double coincidenciaEnEstablecimientos = 0.75;

    private Double coincidenciaEnServicios = 0.75;

    private Double coincidenciaEnUsuarios = 0.05;

    public List<PropuestaFusion> sugerirFusiones(List<Comunidad> comunidades, List<PropuestaFusion> propuestasFusionesAntiguas) {
        List<PropuestaFusion> nuevasPropuestas = new ArrayList<>();

        for(Comunidad comunidad1 : comunidades) {
            for(Comunidad comunidad2 : comunidades) {
                if (comunidad1.equals(comunidad2)) continue;

                if ( !this.estaEnNuevasPropuestas(comunidad1, nuevasPropuestas)
                        && !this.estaEnNuevasPropuestas(comunidad2, nuevasPropuestas)
                        && this.esPosibleFusion(comunidad1, comunidad2))
                {
                    PropuestaFusion propuesta = new PropuestaFusion();
                    propuesta.setComunidad1(comunidad1);
                    propuesta.setComunidad2(comunidad2);
                    nuevasPropuestas.add(propuesta);
                }
            }
        }

        return nuevasPropuestas
                .stream()
                .filter(prop -> !this.propuestaRealizadaRecientemente(prop, propuestasFusionesAntiguas))
                .toList();
    }

    public Boolean propuestaRealizadaRecientemente(PropuestaFusion propuesta, List<PropuestaFusion> propuestasFusionesAntiguas) {
        List<PropuestaFusion> propuestasRecientes = propuestasFusionesAntiguas
                .stream()
                .filter(prop -> prop.getFechaPropuestaFusion().isAfter(LocalDate.now().minusMonths(6)))
                .toList();

        return propuestasRecientes
                .stream()
                .anyMatch(prop ->
                        (prop.getComunidad1().getId().equals(propuesta.getComunidad1().getId())
                                && prop.getComunidad2().getId().equals(propuesta.getComunidad2().getId()))
                                || (prop.getComunidad1().getId().equals(propuesta.getComunidad2().getId())
                                && prop.getComunidad2().getId().equals(propuesta.getComunidad1().getId())));
    }

    public Boolean estaEnNuevasPropuestas(Comunidad comunidad, List<PropuestaFusion> nuevasPropuestas) {
        return nuevasPropuestas.stream().anyMatch(prop -> prop.getComunidad1().getId().equals(comunidad.getId())
                || prop.getComunidad2().getId().equals(comunidad.getId()));
    }

    public Boolean esPosibleFusion(Comunidad comunidad1, Comunidad comunidad2) {
        Boolean b1 = coincideEnEstablecimientos(comunidad1, comunidad2);
        Boolean b2 = coincideEnGradoConfianza(comunidad1, comunidad2);
        Boolean b3 = coincideEnServicios(comunidad1, comunidad2);
        Boolean b4 = coincideEnUsuarios(comunidad1, comunidad2);
        return b1 && b2 && b3 && b4;
    }

    private Boolean coincideEnEstablecimientos(Comunidad comunidad1, Comunidad comunidad2) {
        List<Long> listaIdEstablecimientosEnComun = new ArrayList<>();
        listaIdEstablecimientosEnComun.addAll(comunidad1.getIdEstablecimientos());
        listaIdEstablecimientosEnComun.retainAll(comunidad2.getIdEstablecimientos());

        Integer cantidadEstablecimientosEnComun = listaIdEstablecimientosEnComun.size();
        Integer cantidadEstablecimientosComunidad1 = comunidad1.getIdEstablecimientos().size();
        Integer cantidadEstablecimientosComunidad2 = comunidad2.getIdEstablecimientos().size();

        Boolean hayCoincidenciaComunidad1 = ((double) cantidadEstablecimientosEnComun /cantidadEstablecimientosComunidad1) >= coincidenciaEnEstablecimientos;
        Boolean hayCoincidenciaComunidad2 = ((double) cantidadEstablecimientosEnComun /cantidadEstablecimientosComunidad2) >= coincidenciaEnEstablecimientos;
        return hayCoincidenciaComunidad1 && hayCoincidenciaComunidad2;
    }

    private Boolean coincideEnGradoConfianza(Comunidad comunidad1, Comunidad comunidad2) {
        return comunidad1.getGradoDeConfianza().equals(comunidad2.getGradoDeConfianza());
    }

    private Boolean coincideEnServicios(Comunidad comunidad1, Comunidad comunidad2) {
        List<Long> listaIdEstablecimientosEnComun = new ArrayList<>();
        listaIdEstablecimientosEnComun.addAll(comunidad1.getIdServicios());
        listaIdEstablecimientosEnComun.retainAll(comunidad2.getIdServicios());

        Integer cantidadServiciosEnComun = listaIdEstablecimientosEnComun.size();
        Integer cantidadServiciosComunidad1 = comunidad1.getIdServicios().size();
        Integer cantidadServiciosComunidad2 = comunidad2.getIdServicios().size();

        Boolean hayCoincidenciaComunidad1 = ((double) cantidadServiciosEnComun /cantidadServiciosComunidad1) >= coincidenciaEnEstablecimientos;
        Boolean hayCoincidenciaComunidad2 = ((double) cantidadServiciosEnComun /cantidadServiciosComunidad2) >= coincidenciaEnEstablecimientos;
        return hayCoincidenciaComunidad1 && hayCoincidenciaComunidad2;
    }

    private Boolean coincideEnUsuarios(Comunidad comunidad1, Comunidad comunidad2) {
        List<Long> listaIdEstablecimientosEnComun = new ArrayList<>();
        listaIdEstablecimientosEnComun.addAll(comunidad1.getIdUsuarios());
        listaIdEstablecimientosEnComun.retainAll(comunidad2.getIdUsuarios());

        Integer cantidadUsuariosEnComun = listaIdEstablecimientosEnComun.size();
        Integer cantidadUsuariosComunidad1 = comunidad1.getIdUsuarios().size();
        Integer cantidadUsuariosComunidad2 = comunidad2.getIdUsuarios().size();

        Boolean hayCoincidenciaComunidad1 = ((double) cantidadUsuariosEnComun /cantidadUsuariosComunidad1) >= coincidenciaEnEstablecimientos;
        Boolean hayCoincidenciaComunidad2 = ((double) cantidadUsuariosEnComun /cantidadUsuariosComunidad2) >= coincidenciaEnEstablecimientos;
        return hayCoincidenciaComunidad1 && hayCoincidenciaComunidad2;
    }
}
