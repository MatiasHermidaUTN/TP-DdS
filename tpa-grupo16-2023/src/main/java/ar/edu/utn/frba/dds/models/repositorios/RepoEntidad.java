package ar.edu.utn.frba.dds.models.repositorios;

import ar.edu.utn.frba.dds.models.localizacion.Localidad;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Entidad;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoEntidad implements WithSimplePersistenceUnit {

    public void guardar(Entidad unaEntidad) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(unaEntidad);
        tx.commit();
    }

    public void eliminar(Entidad unaEntidad) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(unaEntidad);
        tx.commit();
    }

    public void modificar(Entidad unaEntidad) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(unaEntidad);
        tx.commit();
    }

    public Entidad buscarPorId(Integer id) {
        return entityManager().find(Entidad.class, id);
    }

    public Entidad buscarPorNombre(String nombre) {
        return  (Entidad) entityManager()
                .createQuery("from " + Entidad.class.getName() + " where nombre = :nombre")
                .setParameter("nombre", nombre)
                .getResultList().get(0);
    }
    public List<Entidad> buscarTodos() {
        return entityManager().createQuery("from " + Entidad.class.getName()).getResultList();
    }
}
