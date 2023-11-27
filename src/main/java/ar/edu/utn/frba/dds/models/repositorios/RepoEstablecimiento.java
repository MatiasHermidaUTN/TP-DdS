package ar.edu.utn.frba.dds.models.repositorios;

import ar.edu.utn.frba.dds.models.persistencia.EMFConVariablesDeBaseDeDatos;
import ar.edu.utn.frba.dds.models.persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoEstablecimiento implements WithSimplePersistenceUnit {

    public void guardar(Establecimiento unEstablecimiento) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(unEstablecimiento);
        EntityManagerHelper.commit();
    }

    public void eliminar(Establecimiento unEstablecimiento) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().remove(unEstablecimiento);
        EntityManagerHelper.commit();
    }

    public void modificar(Establecimiento unEstablecimiento) {
        this.guardar(unEstablecimiento);
    }

    public Establecimiento buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Establecimiento.class, id);
    }

    public List<Establecimiento> buscarTodos() {
        return EntityManagerHelper.createQuery("from " + Establecimiento.class.getName()).getResultList();
    }

    public List<Establecimiento> buscarPorEntidad(Integer entidad) {
        return EntityManagerHelper.createQuery("from " + Establecimiento.class.getName() + " where entidad_id = " + entidad).getResultList();
    }
}

