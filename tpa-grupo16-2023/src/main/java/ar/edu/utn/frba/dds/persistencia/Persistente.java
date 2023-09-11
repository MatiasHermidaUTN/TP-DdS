package ar.edu.utn.frba.dds.persistencia;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class Persistente {
    @Id
    @GeneratedValue
    private Long id;
}
