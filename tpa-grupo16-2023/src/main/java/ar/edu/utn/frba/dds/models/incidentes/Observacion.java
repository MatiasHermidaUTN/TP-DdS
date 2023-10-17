package ar.edu.utn.frba.dds.models.incidentes;

import ar.edu.utn.frba.dds.models.comunidades.Perfil;
import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import ar.edu.utn.frba.dds.models.persistencia.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Observacion extends Persistente {

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @Column(name="observado")
    private String observado;

    public Observacion(Usuario usuario, String observado) {
        this.usuario = usuario;
        this.observado = observado;
    }

    public Observacion() {
    }
}
