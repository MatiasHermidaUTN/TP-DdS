package ar.edu.utn.frba.dds.models.repositorios;

import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import ar.edu.utn.frba.dds.models.localizacion.Localizacion;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoLocalizacion implements WithSimplePersistenceUnit {

    public void guardar(Localizacion unaLocalizacion) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(unaLocalizacion);
        tx.commit();
    }

    public void eliminar(Localizacion unaLocalizacion) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(unaLocalizacion);
        tx.commit();
    }

    public void modificar(Localizacion unaLocalizacion) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(unaLocalizacion);
        tx.commit();
    }

    public Localizacion buscarPorId(Integer id) {
        return entityManager().find(Localizacion.class, id);
    }

    public List<Localizacion> buscarTodos() {
        return entityManager().createQuery("from " + Localizacion.class.getName()).getResultList();
    }
}
