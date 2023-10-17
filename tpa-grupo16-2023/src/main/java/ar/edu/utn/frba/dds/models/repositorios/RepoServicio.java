package ar.edu.utn.frba.dds.models.repositorios;

import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Servicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoServicio implements WithSimplePersistenceUnit {
    public void guardar(Servicio unServicio) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(unServicio);
        tx.commit();
    }

    public void eliminar(Servicio unServicio) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(unServicio);
        tx.commit();
    }

    public void modificar(Servicio unServicio) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(unServicio);
        tx.commit();
    }

    public Servicio buscarPorId(Integer id) {
        return entityManager().find(Servicio.class, id);
    }

    public List<Servicio> buscarTodos() {
        return entityManager().createQuery("from " + Servicio.class.getName()).getResultList();
    }

    public List<Servicio> buscarPorEstablecimiento(Integer establecimiento) {
        return entityManager().createQuery("from " + Servicio.class.getName() + " where establecimiento_id = " + establecimiento).getResultList();
    }
}
