package ar.edu.utn.frba.dds.models.repositorios;

import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import ar.edu.utn.frba.dds.models.incidentes.Prestacion;
import ar.edu.utn.frba.dds.models.persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoPrestacion implements WithSimplePersistenceUnit {

    public void guardar(Prestacion unaPrestacion) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(unaPrestacion);
        EntityManagerHelper.commit();
    }

    public void eliminar(Prestacion unaPrestacion) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().remove(unaPrestacion);
        EntityManagerHelper.commit();
    }

    public void modificar(Prestacion unaPrestacion) {
        this.guardar(unaPrestacion);
    }

    public Prestacion buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Prestacion.class, id);
    }

    public List<Prestacion> buscarTodos() {
        return EntityManagerHelper.createQuery("from " + Prestacion.class.getName()).getResultList();
    }
}
