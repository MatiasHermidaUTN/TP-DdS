package ar.edu.utn.frba.dds.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PropuestaFusionDTO {
    private Integer id;
    private ComunidadDTO comunidad1;
    private ComunidadDTO comunidad2;
    private ComunidadDTO comunidadFusionada = null;
    private ComunidadDTO comunidad1desactivada;
    private ComunidadDTO comunidad2desactivada;
    private EstadoPropuestaFusion estadoPropuestaFusion;
    private LocalDateTime fechaPropuestaFusion;

    public PropuestaFusionDTO() {
        this.estadoPropuestaFusion = EstadoPropuestaFusion.PENDIENTE;
        this.fechaPropuestaFusion = LocalDateTime.now();
    }

    public void fusionarComunidades() {
        comunidadFusionada = new ComunidadDTO();
        comunidadFusionada.setNombre(comunidad1.getNombre() + "-" + comunidad2.getNombre());
        comunidadFusionada.setGradoDeConfianza(comunidad1.getGradoDeConfianza());
        comunidadFusionada.setActiva(true);

        List<Integer> listaIdUsuarios = new ArrayList<>();
        listaIdUsuarios.addAll(comunidad1.getIdUsuarios());
        listaIdUsuarios.addAll(comunidad2.getIdUsuarios());
        comunidadFusionada.setIdUsuarios(listaIdUsuarios
                .stream()
                .distinct()
                .toList());

        List<Integer> listaIdEstablecimientos = new ArrayList<>();
        listaIdEstablecimientos.addAll(comunidad1.getIdEstablecimientos());
        listaIdEstablecimientos.addAll(comunidad2.getIdEstablecimientos());
        comunidadFusionada.setIdEstablecimientos(listaIdEstablecimientos
                .stream()
                .distinct()
                .toList());

        List<Integer> listaIdServicios = new ArrayList<>();
        listaIdServicios.addAll(comunidad1.getIdServicios());
        listaIdServicios.addAll(comunidad2.getIdServicios());
        comunidadFusionada.setIdServicios(listaIdServicios
                .stream()
                .distinct()
                .toList());

        comunidad1desactivada = new ComunidadDTO();
        comunidad1desactivada.setId(comunidad1.getId());
        comunidad1desactivada.setNombre(comunidad1.getNombre());
        comunidad1desactivada.setIdUsuarios(comunidad1.getIdUsuarios());
        comunidad1desactivada.setIdEstablecimientos(comunidad1.getIdEstablecimientos());
        comunidad1desactivada.setIdServicios(comunidad1.getIdServicios());
        comunidad1desactivada.setGradoDeConfianza(comunidad1.getGradoDeConfianza());
        comunidad1desactivada.setActiva(false);

        comunidad2desactivada = new ComunidadDTO();
        comunidad2desactivada.setId(comunidad2.getId());
        comunidad2desactivada.setNombre(comunidad2.getNombre());
        comunidad2desactivada.setIdUsuarios(comunidad2.getIdUsuarios());
        comunidad2desactivada.setIdEstablecimientos(comunidad2.getIdEstablecimientos());
        comunidad2desactivada.setIdServicios(comunidad2.getIdServicios());
        comunidad2desactivada.setGradoDeConfianza(comunidad2.getGradoDeConfianza());
        comunidad2desactivada.setActiva(false);
    }

    public boolean estaAceptada() {
        return this.estadoPropuestaFusion == EstadoPropuestaFusion.ACEPTADA;
    }

    public boolean estaRechazada() {
        return this.estadoPropuestaFusion == EstadoPropuestaFusion.RECHAZADA;
    }

}
