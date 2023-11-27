package ar.edu.utn.frba.dds.models.repositorios;

import ar.edu.utn.frba.dds.models.localizacion.Localidad;
import ar.edu.utn.frba.dds.models.persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoEntidad implements WithSimplePersistenceUnit {

    public void guardar(Entidad unaEntidad) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(unaEntidad);
        EntityManagerHelper.commit();
    }

    public void eliminar(Entidad unaEntidad) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().remove(unaEntidad);
        EntityManagerHelper.commit();
    }

    public void modificar(Entidad unaEntidad) {
        this.guardar(unaEntidad);
    }

    public Entidad buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Entidad.class, id);
    }

    public Entidad buscarPorNombre(String nombre) {
        return  (Entidad) EntityManagerHelper
                .createQuery("from " + Entidad.class.getName() + " where nombre = :nombre")
                .setParameter("nombre", nombre)
                .getResultList().get(0);
    }
    public List<Entidad> buscarTodos() {
        return EntityManagerHelper.createQuery("from " + Entidad.class.getName()).getResultList();
    }
}
