package ar.edu.utn.frba.dds.models.repositorios;

import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import ar.edu.utn.frba.dds.models.incidentes.Prestacion;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoPrestacion implements WithSimplePersistenceUnit {

    public void guardar(Prestacion unaPrestacion) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(unaPrestacion);
        tx.commit();
    }

    public void eliminar(Prestacion unaPrestacion) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(unaPrestacion);
        tx.commit();
    }

    public void modificar(Prestacion unaPrestacion) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(unaPrestacion);
        tx.commit();
    }

    public Prestacion buscarPorId(Integer id) {
        return entityManager().find(Prestacion.class, id);
    }

    public List<Prestacion> buscarTodos() {
        return entityManager().createQuery("from " + Prestacion.class.getName()).getResultList();
    }
}
