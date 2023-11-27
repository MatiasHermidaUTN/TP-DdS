package ar.edu.utn.frba.dds.models.repositorios;

import ar.edu.utn.frba.dds.models.persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.models.serviciosPublicos.OrganismoDeControl;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoOrganismoDeControl implements WithSimplePersistenceUnit {
    public void guardar(OrganismoDeControl unaOrganismoDeControl) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(unaOrganismoDeControl);
        EntityManagerHelper.commit();
    }

    public void eliminar(OrganismoDeControl unaOrganismoDeControl) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().remove(unaOrganismoDeControl);
        EntityManagerHelper.commit();
    }

    public void modificar(OrganismoDeControl unaOrganismoDeControl) {
        this.guardar(unaOrganismoDeControl);
    }

    public OrganismoDeControl buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(OrganismoDeControl.class, id);
    }

    public OrganismoDeControl buscarPorNombre(String nombre) {
        return  (OrganismoDeControl) EntityManagerHelper
                .createQuery("from " + OrganismoDeControl.class.getName() + " where nombre = :nombre")
                .setParameter("nombre", nombre)
                .getResultList().get(0);
    }

    public List<OrganismoDeControl> buscarTodos() {
        return EntityManagerHelper.createQuery("from " + OrganismoDeControl.class.getName()).getResultList();
    }
}
