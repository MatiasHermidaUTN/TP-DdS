package ar.edu.utn.frba.dds.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class SugeridorDeFusiones {
    private Long id;

    private Double coincidenciaEnEstablecimientos = 0.75;

    private Double coincidenciaEnServicios = 0.75;

    private Double coincidenciaEnUsuarios = 0.05;

    public List<PropuestaFusionDTO> sugerirFusiones(List<ComunidadDTO> comunidades, List<PropuestaFusionDTO> propuestasFusionesAntiguas) {
        List<PropuestaFusionDTO> nuevasPropuestas = new ArrayList<>();

        for(ComunidadDTO comunidad1 : comunidades) {
            for(ComunidadDTO comunidad2 : comunidades) {

                if (comunidad1.equals(comunidad2)) continue;

                if ( !this.estaEnNuevasPropuestas(comunidad1, nuevasPropuestas)
                        && !this.estaEnNuevasPropuestas(comunidad2, nuevasPropuestas)
                        && this.esPosibleFusion(comunidad1, comunidad2))
                {
                    PropuestaFusionDTO propuesta = new PropuestaFusionDTO();
                    propuesta.setComunidad1(comunidad1);
                    propuesta.setComunidad2(comunidad2);

                    if(this.propuestaRealizadaRecientemente(propuesta, propuestasFusionesAntiguas))
                        continue;

                    nuevasPropuestas.add(propuesta);
                }
            }
        }

        return nuevasPropuestas;
    }

    public Boolean propuestaRealizadaRecientemente(PropuestaFusionDTO propuesta, List<PropuestaFusionDTO> propuestasFusionesAntiguas) {
        List<PropuestaFusionDTO> propuestasRecientes = propuestasFusionesAntiguas
                .stream()
                .filter(prop -> prop.getFechaPropuestaFusion().isAfter(LocalDateTime.now().minusMonths(6)))
                .toList();

        return propuestasRecientes
                .stream()
                .anyMatch(prop ->
                        (prop.getComunidad1().getId().equals(propuesta.getComunidad1().getId())
                                && prop.getComunidad2().getId().equals(propuesta.getComunidad2().getId()))
                                || (prop.getComunidad1().getId().equals(propuesta.getComunidad2().getId())
                                && prop.getComunidad2().getId().equals(propuesta.getComunidad1().getId())));
    }

    public Boolean estaEnNuevasPropuestas(ComunidadDTO comunidad, List<PropuestaFusionDTO> nuevasPropuestas) {
        return nuevasPropuestas.stream().anyMatch(prop -> prop.getComunidad1().getId().equals(comunidad.getId())
                || prop.getComunidad2().getId().equals(comunidad.getId()));
    }

    public Boolean esPosibleFusion(ComunidadDTO comunidad1, ComunidadDTO comunidad2) {
        Boolean b1 = coincideEnEstablecimientos(comunidad1, comunidad2);
        Boolean b2 = coincideEnGradoConfianza(comunidad1, comunidad2);
        Boolean b3 = coincideEnServicios(comunidad1, comunidad2);
        Boolean b4 = coincideEnUsuarios(comunidad1, comunidad2);
        return b1 && b2 && b3 && b4;
    }

    private Boolean coincideEnEstablecimientos(ComunidadDTO comunidad1, ComunidadDTO comunidad2) {
        List<Integer> listaIdEstablecimientosEnComun = new ArrayList<>();
        listaIdEstablecimientosEnComun.addAll(comunidad1.getIdEstablecimientos());
        listaIdEstablecimientosEnComun.retainAll(comunidad2.getIdEstablecimientos());

        Integer cantidadEstablecimientosEnComun = listaIdEstablecimientosEnComun.size();
        Integer cantidadEstablecimientosComunidad1 = comunidad1.getIdEstablecimientos().size();
        Integer cantidadEstablecimientosComunidad2 = comunidad2.getIdEstablecimientos().size();

        Boolean hayCoincidenciaComunidad1 = ((double) cantidadEstablecimientosEnComun /cantidadEstablecimientosComunidad1) >= coincidenciaEnEstablecimientos;
        Boolean hayCoincidenciaComunidad2 = ((double) cantidadEstablecimientosEnComun /cantidadEstablecimientosComunidad2) >= coincidenciaEnEstablecimientos;
        return hayCoincidenciaComunidad1 && hayCoincidenciaComunidad2;
    }

    private Boolean coincideEnGradoConfianza(ComunidadDTO comunidad1, ComunidadDTO comunidad2) {
        return comunidad1.getGradoDeConfianza().equals(comunidad2.getGradoDeConfianza());
    }

    private Boolean coincideEnServicios(ComunidadDTO comunidad1, ComunidadDTO comunidad2) {
        List<Integer> listaIdEstablecimientosEnComun = new ArrayList<>();
        listaIdEstablecimientosEnComun.addAll(comunidad1.getIdServicios());
        listaIdEstablecimientosEnComun.retainAll(comunidad2.getIdServicios());

        Integer cantidadServiciosEnComun = listaIdEstablecimientosEnComun.size();
        Integer cantidadServiciosComunidad1 = comunidad1.getIdServicios().size();
        Integer cantidadServiciosComunidad2 = comunidad2.getIdServicios().size();

        Boolean hayCoincidenciaComunidad1 = ((double) cantidadServiciosEnComun /cantidadServiciosComunidad1) >= coincidenciaEnEstablecimientos;
        Boolean hayCoincidenciaComunidad2 = ((double) cantidadServiciosEnComun /cantidadServiciosComunidad2) >= coincidenciaEnEstablecimientos;
        return hayCoincidenciaComunidad1 && hayCoincidenciaComunidad2;
    }

    private Boolean coincideEnUsuarios(ComunidadDTO comunidad1, ComunidadDTO comunidad2) {
        List<Integer> listaIdEstablecimientosEnComun = new ArrayList<>();
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
