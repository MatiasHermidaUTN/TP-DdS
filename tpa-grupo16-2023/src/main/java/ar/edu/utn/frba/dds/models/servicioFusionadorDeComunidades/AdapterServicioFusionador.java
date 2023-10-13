package ar.edu.utn.frba.dds.models.servicioFusionadorDeComunidades;

import ar.edu.utn.frba.dds.models.comunidades.Comunidad;
import ar.edu.utn.frba.dds.models.servicioFusionadorDeComunidades.Entities.ComunidadDTO;
import ar.edu.utn.frba.dds.models.servicioFusionadorDeComunidades.Entities.PropuestaFusion;
import ar.edu.utn.frba.dds.models.servicioFusionadorDeComunidades.Entities.PropuestaFusionDTO;
import ar.edu.utn.frba.dds.models.servicioFusionadorDeComunidades.Entities.Transaccion;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AdapterServicioFusionador {
    private static AdapterServicioFusionador instance = null;
    private static ServicioFusionadorRetrofit servicioFusionadorRetrofit;

    private AdapterServicioFusionador(){
        servicioFusionadorRetrofit = servicioFusionadorRetrofit.instancia();
    }

    public static AdapterServicioFusionador instancia(){
        if(instance == null){
            instance = new AdapterServicioFusionador();
        }
        return instance;
    }

    public List<PropuestaFusion> proponerFusiones(List<Comunidad> comunidades, List<PropuestaFusion> propuestasFusions) throws IOException {
        Transaccion transaccion = new Transaccion();
        List<ComunidadDTO> comunidadesDTO = new ArrayList<>();
        List<PropuestaFusionDTO> propuestasFusionDTO = new ArrayList<>();

        for (Comunidad comunidad : comunidades){
            ComunidadDTO comunidadDTO = this.comunidadToComunidadDTO(comunidad);

            comunidadesDTO.add(comunidadDTO);

//            System.out.println("comunidadDTO: " + comunidadDTO + "\n");
        }

        for(PropuestaFusion propuestaFusion : propuestasFusions){
            if(propuestaFusion.getFechaDePropuesta().isAfter(LocalDateTime.now().minusMonths(6))){
                PropuestaFusionDTO propuestaFusionDTO = this.propuestaFusionToPropuestaFusionDTO(propuestaFusion);

                propuestasFusionDTO.add(propuestaFusionDTO);

//                System.out.println("propuestaFusionDTO: " + propuestaFusionDTO + "\n");
            }
        }

        transaccion.setComunidades(comunidadesDTO);
        transaccion.setPropuestasFusion(propuestasFusionDTO);

        System.out.println("transaccion: " + transaccion + "\n");

        List<PropuestaFusionDTO> propuestasFusionDTONuevas = servicioFusionadorRetrofit.sugerirFusiones(transaccion);

        List<PropuestaFusion> propuestasFusionNuevas = new ArrayList<>();

        for (PropuestaFusionDTO propuestaFusionDTO : propuestasFusionDTONuevas){
            PropuestaFusion propuestaFusion = this.propuestaFusionDTOToPropuestaFusion(propuestaFusionDTO, comunidades);

            propuestasFusionNuevas.add(propuestaFusion);
        }

//        System.out.println("listPropuestasFusion: " + propuestasFusionNuevas + "\n");

        return propuestasFusionNuevas;
    }

    private PropuestaFusionDTO propuestaFusionToPropuestaFusionDTO(PropuestaFusion propuestaFusion) {
        PropuestaFusionDTO propuestaFusionDTO = new PropuestaFusionDTO();

        propuestaFusionDTO.setComunidad1(this.comunidadToComunidadDTO(propuestaFusion.getComunidad1()));
        propuestaFusionDTO.setComunidad2(this.comunidadToComunidadDTO(propuestaFusion.getComunidad2()));

        if (propuestaFusion.getComunidadFusionada() != null)
            propuestaFusionDTO.setComunidadFusionada(this.comunidadToComunidadDTO(propuestaFusion.getComunidadFusionada()));
        if (propuestaFusion.getComunidad1desactivada() != null)
            propuestaFusionDTO.setComunidad1desactivada(this.comunidadToComunidadDTO(propuestaFusion.getComunidad1desactivada()));
        if (propuestaFusion.getComunidad2desactivada() != null)
            propuestaFusionDTO.setComunidad2desactivada(this.comunidadToComunidadDTO(propuestaFusion.getComunidad2desactivada()));

        propuestaFusionDTO.setEstadoPropuestaFusion(propuestaFusion.getEstado());
        propuestaFusionDTO.setFechaPropuestaFusion(propuestaFusion.getFechaDePropuesta());

        return propuestaFusionDTO;
    }

    private PropuestaFusion propuestaFusionDTOToPropuestaFusion(PropuestaFusionDTO propuestaFusionDTO, List<Comunidad> comunidades) {
        PropuestaFusion propuestaFusion = new PropuestaFusion();

        propuestaFusion.setComunidad1(this.comunidadDTOToComunidadExistente(propuestaFusionDTO.getComunidad1(), comunidades));
        propuestaFusion.setComunidad2(this.comunidadDTOToComunidadExistente(propuestaFusionDTO.getComunidad2(), comunidades));

        propuestaFusion.setComunidadFusionada(this.comunidadDTOToComunidadNoExistente(
                propuestaFusionDTO.getComunidadFusionada(),
                propuestaFusion.getComunidad1(),
                propuestaFusion.getComunidad2()));

        propuestaFusion.setComunidad1desactivada(this.comunidadDTOToComunidadExistente(propuestaFusionDTO.getComunidad1desactivada(), comunidades));
        propuestaFusion.setComunidad2desactivada(this.comunidadDTOToComunidadExistente(propuestaFusionDTO.getComunidad2desactivada(), comunidades));
        propuestaFusion.setEstado(propuestaFusionDTO.getEstadoPropuestaFusion());
        propuestaFusion.setFechaDePropuesta(propuestaFusionDTO.getFechaPropuestaFusion());

        return propuestaFusion;
    }

    public ComunidadDTO comunidadToComunidadDTO(Comunidad comunidad) {
        ComunidadDTO comunidadDTO = new ComunidadDTO();

        comunidadDTO.setId(comunidad.getId());
        comunidadDTO.setNombre(comunidad.getNombre());
        comunidadDTO.setGradoDeConfianza(comunidad.getGradoDeConfianza());
        comunidadDTO.setActiva(comunidad.getActiva());

        comunidadDTO.setIdUsuarios(comunidad.getIdUsuarios());
        comunidadDTO.setIdEstablecimientos(comunidad.getIdEstablecimientos());
        comunidadDTO.setIdServicios(comunidad.getIdServicios());

        return comunidadDTO;
    }

    public Comunidad comunidadDTOToComunidadExistente(ComunidadDTO comunidadDTO, List<Comunidad> comunidades) {
        Comunidad comunidadDeLista = comunidades.stream().filter(comunidad1 -> comunidad1.getNombre().equals(comunidadDTO.getNombre())).findFirst().get();
        Comunidad comunidad = new Comunidad();

        comunidad.setId(comunidadDeLista.getId());
        comunidad.setNombre(comunidadDeLista.getNombre());
        comunidad.setGradoDeConfianza(comunidadDeLista.getGradoDeConfianza());

        comunidad.setMiembros(comunidadDeLista.getMiembros());
        comunidad.setEstablecimientosDeComunidad(comunidadDeLista.getEstablecimientosDeComunidad());
        comunidad.setServiciosDeComunidad(comunidadDeLista.getServiciosDeComunidad());

        comunidad.setActiva(comunidadDTO.getActiva());

        return comunidad;
    }

    public Comunidad comunidadDTOToComunidadNoExistente(ComunidadDTO comunidadDTO, Comunidad comunidad1, Comunidad comunidad2) {
        Comunidad comunidad = new Comunidad();

        comunidad.setNombre(comunidadDTO.getNombre());
        comunidad.setGradoDeConfianza(comunidadDTO.getGradoDeConfianza());
        comunidad.setActiva(comunidadDTO.getActiva());

        comunidad.setMiembros(comunidadDTO.getPerfiles(comunidad1.getMiembros(), comunidad2.getMiembros()));
        comunidad.setEstablecimientosDeComunidad(comunidadDTO.getEstablecimientos(comunidad1.getEstablecimientosDeComunidad(), comunidad2.getEstablecimientosDeComunidad()));
        comunidad.setServiciosDeComunidad(comunidadDTO.getServicios(comunidad1.getServiciosDeComunidad(), comunidad2.getServiciosDeComunidad()));


        return comunidad;
    }


}
