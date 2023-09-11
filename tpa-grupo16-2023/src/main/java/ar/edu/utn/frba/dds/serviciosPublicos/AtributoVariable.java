package ar.edu.utn.frba.dds.serviciosPublicos;

import ar.edu.utn.frba.dds.persistencia.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class AtributoVariable extends Persistente {

    @Column
    private String nombre_atributo;

    @Column
    private String valor_atributo;

    public AtributoVariable(String nombre_atributo, String valor_atributo) {
        this.nombre_atributo = nombre_atributo;
        this.valor_atributo = valor_atributo;
    }

    public AtributoVariable() {

    }
}
