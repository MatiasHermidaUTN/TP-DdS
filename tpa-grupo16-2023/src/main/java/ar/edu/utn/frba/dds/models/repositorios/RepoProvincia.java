package ar.edu.utn.frba.dds.models.repositorios;

import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import ar.edu.utn.frba.dds.models.localizacion.Provincia;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoProvincia implements WithSimplePersistenceUnit {

    public void guardar(Provincia unaProvincia) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(unaProvincia);
        tx.commit();
    }

    public void guardarMuchas(List<Provincia> listaProvincia) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        for(Provincia unaProvincia : listaProvincia){
            entityManager().persist(unaProvincia);
        }
        tx.commit();
    }

    public void eliminar(Provincia unaProvincia) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(unaProvincia);
        tx.commit();
    }

    public void modificar(Provincia unaProvincia) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(unaProvincia);
        tx.commit();
    }

    public Provincia buscarPorId(Integer id) {
        return entityManager().find(Provincia.class, id);
    }

    public List<Provincia> buscarTodos() {
        return entityManager().createQuery("from " + Provincia.class.getName()).getResultList();
    }

    public boolean existeElemento(Provincia provincia) {
        return entityManager().contains(provincia);
    }
}
