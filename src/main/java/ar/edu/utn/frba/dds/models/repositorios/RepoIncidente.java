package ar.edu.utn.frba.dds.models.repositorios;

import ar.edu.utn.frba.dds.models.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoIncidente implements WithSimplePersistenceUnit {

    public void guardar(Incidente unIncidente) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(unIncidente);
        EntityManagerHelper.commit();
    }

    public void eliminar(Incidente unIncidente) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().remove(unIncidente);
        EntityManagerHelper.commit();
    }

    public void modificar(Incidente unIncidente) {
        this.guardar(unIncidente);
    }

    public Incidente buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Incidente.class, id);
    }

    public List<Incidente> buscarTodos() {
        return EntityManagerHelper.createQuery("from " + Incidente.class.getName()).getResultList();
    }

    public List buscarPorComunidad(Integer id) {
        return EntityManagerHelper
                .createQuery("from " + Incidente.class.getName() + " where comunidad_id = :comunidad")
                .setParameter("comunidad", id)
                .getResultList();
    }
}
