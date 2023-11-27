package ar.edu.utn.frba.dds.models.repositorios;

import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import ar.edu.utn.frba.dds.models.persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Servicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoServicio implements WithSimplePersistenceUnit {
    public void guardar(Servicio unServicio) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(unServicio);
        EntityManagerHelper.commit();
    }

    public void eliminar(Servicio unServicio) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().remove(unServicio);
        EntityManagerHelper.commit();
    }

    public void modificar(Servicio unServicio) {
        this.guardar(unServicio);
    }

    public Servicio buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Servicio.class, id);
    }

    public List<Servicio> buscarTodos() {
        return EntityManagerHelper.createQuery("from " + Servicio.class.getName()).getResultList();
    }

    public List<Servicio> buscarPorEstablecimiento(Integer establecimiento) {
        return EntityManagerHelper.createQuery("from " + Servicio.class.getName() + " where establecimiento_id = " + establecimiento).getResultList();
    }
}
