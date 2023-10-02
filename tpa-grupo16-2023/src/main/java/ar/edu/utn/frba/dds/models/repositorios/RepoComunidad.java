package ar.edu.utn.frba.dds.models.repositorios;

import ar.edu.utn.frba.dds.models.comunidades.Comunidad;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoComunidad implements WithSimplePersistenceUnit {

    public void guardar(Comunidad unaComunidad) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(unaComunidad);
        tx.commit();
    }

    public void eliminar(Comunidad unaComunidad) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(unaComunidad);
        tx.commit();
    }

    public void modificar(Comunidad unaComunidad) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(unaComunidad);
        tx.commit();
    }

    public Comunidad buscarPorId(Integer id) {
        return entityManager().find(Comunidad.class, id);
    }

    public List<Comunidad> buscarTodos() {
        return entityManager().createQuery("from " + Comunidad.class.getName()).getResultList();
    }
}
