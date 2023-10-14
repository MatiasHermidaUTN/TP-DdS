package ar.edu.utn.frba.dds.models.repositorios;

import ar.edu.utn.frba.dds.models.serviciosPublicos.OrganismoDeControl;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoOrganismoDeControl implements WithSimplePersistenceUnit {
    public void guardar(OrganismoDeControl unaOrganismoDeControl) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(unaOrganismoDeControl);
        tx.commit();
    }

    public void eliminar(OrganismoDeControl unaOrganismoDeControl) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(unaOrganismoDeControl);
        tx.commit();
    }

    public void modificar(OrganismoDeControl unaOrganismoDeControl) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(unaOrganismoDeControl);
        tx.commit();
    }

    public OrganismoDeControl buscarPorId(Integer id) {
        return entityManager().find(OrganismoDeControl.class, id);
    }

    public List<OrganismoDeControl> buscarTodos() {
        return entityManager().createQuery("from " + OrganismoDeControl.class.getName()).getResultList();
    }
}
