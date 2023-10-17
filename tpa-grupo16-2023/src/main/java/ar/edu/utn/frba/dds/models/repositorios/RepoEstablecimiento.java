package ar.edu.utn.frba.dds.models.repositorios;

import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoEstablecimiento implements WithSimplePersistenceUnit {

    public void guardar(Establecimiento unEstablecimiento) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(unEstablecimiento);
        tx.commit();
    }

    public void eliminar(Establecimiento unEstablecimiento) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(unEstablecimiento);
        tx.commit();
    }

    public void modificar(Establecimiento unEstablecimiento) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(unEstablecimiento);
        tx.commit();
    }

    public Establecimiento buscarPorId(Integer id) {
        return entityManager().find(Establecimiento.class, id);
    }

    public List<Establecimiento> buscarTodos() {
        return entityManager().createQuery("from " + Establecimiento.class.getName()).getResultList();
    }

    public List<Establecimiento> buscarPorEntidad(Integer entidad) {
        return entityManager().createQuery("from " + Establecimiento.class.getName() + " where entidad_id = " + entidad).getResultList();
    }
}

