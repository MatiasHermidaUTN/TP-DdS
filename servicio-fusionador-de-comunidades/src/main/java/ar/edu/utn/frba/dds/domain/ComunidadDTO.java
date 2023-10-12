package ar.edu.utn.frba.dds.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ComunidadDTO {
    private Integer id;
    private String nombre;
    private List<Integer> idUsuarios;
    private List<Integer> idEstablecimientos;
    private List<Integer> idServicios;
    private GradoDeConfianza gradoDeConfianza;
    private Boolean activa;

    public ComunidadDTO() {
        this.idUsuarios = new ArrayList<Integer>();
        this.idEstablecimientos = new ArrayList<Integer>();
        this.idServicios = new ArrayList<Integer>();
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
                "      " + ", activa=" + activa + "\n" +
                '}';
    }
}

