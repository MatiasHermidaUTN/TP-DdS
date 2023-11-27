package ar.edu.utn.frba.dds.models.repositorios;

import ar.edu.utn.frba.dds.models.comunidades.Comunidad;
import ar.edu.utn.frba.dds.models.persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoComunidad implements WithSimplePersistenceUnit {

    public void guardar(Comunidad unaComunidad) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(unaComunidad);
        EntityManagerHelper.commit();
    }

    public void eliminar(Comunidad unaComunidad) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(unaComunidad);
        tx.commit();
    }

    public void modificar(Comunidad unaComunidad) {
        this.guardar(unaComunidad);
    }

    public Comunidad buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Comunidad.class, id);
    }

    public List<Comunidad> buscarTodos() {
        return EntityManagerHelper.createQuery("from " + Comunidad.class.getName()).getResultList();
    }
}
