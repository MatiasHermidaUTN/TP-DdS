package ar.edu.utn.frba.dds.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PropuestaFusion {
    private Long id;
    private Comunidad comunidad1;
    private Comunidad comunidad2;
    private Comunidad comunidadFusionada;
    private EstadoPropuestaFusion estadoPropuestaFusion;
    private LocalDate fechaPropuestaFusion;

    public PropuestaFusion() {
        this.estadoPropuestaFusion = EstadoPropuestaFusion.PENDIENTE;
        this.fechaPropuestaFusion = LocalDate.now();
        this.comunidadFusionada = new Comunidad();
    }

    public void fusionarComunidades() {
        comunidadFusionada = new Comunidad();
        comunidadFusionada.setNombre(comunidad1.getNombre() + "-" + comunidad2.getNombre());
        comunidadFusionada.setGradoDeConfianza(comunidad1.getGradoDeConfianza());
        comunidadFusionada.setActiva(true);

        List<Long> listaIdUsuarios = new ArrayList<>();
        listaIdUsuarios.addAll(comunidad1.getIdUsuarios());
        listaIdUsuarios.addAll(comunidad2.getIdUsuarios());
        comunidadFusionada.setIdUsuarios(listaIdUsuarios
                .stream()
                .distinct()
                .toList());

        List<Long> listaIdEstablecimientos = new ArrayList<>();
        listaIdEstablecimientos.addAll(comunidad1.getIdEstablecimientos());
        listaIdEstablecimientos.addAll(comunidad2.getIdEstablecimientos());
        comunidadFusionada.setIdEstablecimientos(listaIdEstablecimientos
                .stream()
                .distinct()
                .toList());

        List<Long> listaIdServicios = new ArrayList<>();
        listaIdServicios.addAll(comunidad1.getIdServicios());
        listaIdServicios.addAll(comunidad2.getIdServicios());
        comunidadFusionada.setIdServicios(listaIdServicios
                .stream()
                .distinct()
                .toList());

        comunidad1.setActiva(false);
        comunidad2.setActiva(false);
    }

    public boolean estaAceptada() {
        return this.estadoPropuestaFusion == EstadoPropuestaFusion.ACEPTADA;
    }

    public boolean estaRechazada() {
        return this.estadoPropuestaFusion == EstadoPropuestaFusion.RECHAZADA;
    }

    @Override
    public String toString() {
        return "PropuestaFusion{ " + this.hashCode() + "\n" +
                "    " + "id=" + id + "\n" +
                "    " + ", comunidad1=" + comunidad1.toString() + "\n" +
                "    " + ", comunidad2=" + comunidad2.toString() + "\n" +
                "    " + ", comunidadFusionada=" + comunidadFusionada.toString() + "\n" +
                "    " + ", estadoPropuestaFusion=" + estadoPropuestaFusion + "\n" +
                "    " + ", fechaPropuestaFusion=" + fechaPropuestaFusion + "\n" +
                '}';
    }
}
