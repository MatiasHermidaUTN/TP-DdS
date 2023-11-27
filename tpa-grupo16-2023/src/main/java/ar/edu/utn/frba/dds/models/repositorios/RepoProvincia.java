package ar.edu.utn.frba.dds.models.repositorios;

import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import ar.edu.utn.frba.dds.models.localizacion.Provincia;
import ar.edu.utn.frba.dds.models.persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoProvincia implements WithSimplePersistenceUnit {

    public void guardar(Provincia unaProvincia) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(unaProvincia);
        EntityManagerHelper.commit();
    }

    public void guardarMuchas(List<Provincia> listaProvincia) {
        EntityManagerHelper.beginTransaction();
        for(Provincia unaProvincia : listaProvincia){
            EntityManagerHelper.getEntityManager().persist(unaProvincia);
        }
        EntityManagerHelper.commit();
    }

    public void eliminar(Provincia unaProvincia) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().remove(unaProvincia);
        EntityManagerHelper.commit();
    }

    public void modificar(Provincia unaProvincia) {
        this.guardar(unaProvincia);
    }

    public Provincia buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Provincia.class, id);
    }

    public List<Provincia> buscarTodos() {
        return EntityManagerHelper.createQuery("from " + Provincia.class.getName()).getResultList();
    }

    public Provincia buscarPorNombre(String nombre) {
        return  (Provincia) EntityManagerHelper
                .createQuery("from " + Provincia.class.getName() + " where nombre = :nombre")
                .setParameter("nombre", nombre)
                .getResultList().get(0);
    }

    public boolean existeElemento(Provincia provincia) {
        return entityManager().contains(provincia);
    }
}
