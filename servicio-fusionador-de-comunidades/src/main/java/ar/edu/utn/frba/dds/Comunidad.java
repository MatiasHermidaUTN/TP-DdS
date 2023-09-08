package ar.edu.utn.frba.dds;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Comunidad {
    private Long id;
    private Long idComunidadModelo;
    private String nombre;
    private List<Long> idUsuarios;
    private List<Long> idEstablecimientos;
    private List<Long> idServicios;
    private GradoDeConfianza gradoDeConfianza;
    private Boolean activo;

    public Comunidad() {
        this.idUsuarios = new ArrayList<Long>();
        this.idEstablecimientos = new ArrayList<Long>();
        this.idServicios = new ArrayList<Long>();
    }

    @Override
    public String toString() {
        return "Comunidad{ " + this.hashCode() + "\n" +
                "      " + "id=" + id + "\n" +
                "      " + ", nombre=" + nombre + "\n" +
                "      " + ", idUsuarios=" + idUsuarios + "\n" +
                "      " + ", idEstablecimientos=" + idEstablecimientos + "\n" +
                "      " + ", idServicios=" + idServicios + "\n" +
                "      " + ", gradoDeConfianza=" + gradoDeConfianza + "\n" +
                "      " + ", activo=" + activo + "\n" +
                '}';
    }
}
