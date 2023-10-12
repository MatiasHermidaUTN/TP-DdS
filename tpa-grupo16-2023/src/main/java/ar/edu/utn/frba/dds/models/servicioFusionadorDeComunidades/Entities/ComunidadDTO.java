package ar.edu.utn.frba.dds.models.servicioFusionadorDeComunidades.Entities;

import ar.edu.utn.frba.dds.models.comunidades.GradoDeConfianza;
import ar.edu.utn.frba.dds.models.comunidades.Perfil;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Servicio;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ComunidadDTO {
    Integer id;
    String nombre;
    List<Integer> idUsuarios;
    List<Integer> idEstablecimientos;
    List<Integer> idServicios;
    GradoDeConfianza gradoDeConfianza;
    Boolean activa;


    public ComunidadDTO(){
        this.idUsuarios = new ArrayList<>();
        this.idEstablecimientos = new ArrayList<>();
        this.idServicios = new ArrayList<>();
    }
    public ComunidadDTO(String nombre, GradoDeConfianza gradoDeConfianza, Boolean activa){
        this.nombre = nombre;
        this.idUsuarios = new ArrayList<>();
        this.idEstablecimientos = new ArrayList<>();
        this.idServicios = new ArrayList<>();
        this.gradoDeConfianza = gradoDeConfianza;
        this.activa = activa;
    }

    public List<Perfil> getPerfiles(List<Perfil> perfilesC1, List<Perfil> perfilesC2) {
        List<Integer> idUsuarios = this.getIdUsuarios();
        List<Perfil> miembros = new ArrayList<>();
        List<Perfil> perfiles = new ArrayList<>();
        perfiles.addAll(perfilesC1);
        perfiles.addAll(perfilesC2);

        for(Integer idUsuario : idUsuarios){;
            miembros.add(perfiles
                    .stream()
                    .filter(perfil -> perfil.getId().equals(idUsuario))
                    .findFirst()
                    .get());
        }
        return miembros;
    }

    public List<Establecimiento> getEstablecimientos(List<Establecimiento> establecimientosC1, List<Establecimiento> establecimientosC2) {
        List<Integer> idEstablecimientos = this.getIdEstablecimientos();
        List<Establecimiento> establecimientos = new ArrayList<>();
        List<Establecimiento> establecimientosTotales = new ArrayList<>();
        establecimientosTotales.addAll(establecimientosC1);
        establecimientosTotales.addAll(establecimientosC2);

        for(Integer idEstablecimiento : idEstablecimientos){
            establecimientos.add(establecimientosTotales
                    .stream()
                    .filter(establecimiento -> establecimiento.getId().equals(idEstablecimiento))
                    .findFirst()
                    .get());
        }
        return establecimientos;
    }

    public List<Servicio> getServicios(List<Servicio> serviciosC1, List<Servicio> serviciosC2) {
        List<Integer> idServicios = this.getIdServicios();
        List<Servicio> servicios = new ArrayList<>();
        List<Servicio> serviciosTotales = new ArrayList<>();
        serviciosTotales.addAll(serviciosC1);
        serviciosTotales.addAll(serviciosC2);

        for(Integer idServicio : idServicios){
            servicios.add(serviciosTotales
                    .stream()
                    .filter(servicio -> servicio.getId().equals(idServicio))
                    .findFirst()
                    .get());
        }
        return servicios;
    }
}
