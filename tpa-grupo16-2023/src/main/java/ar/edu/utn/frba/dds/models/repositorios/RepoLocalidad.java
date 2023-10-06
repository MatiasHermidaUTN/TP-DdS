package ar.edu.utn.frba.dds.models.repositorios;

import ar.edu.utn.frba.dds.models.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.localizacion.Localidad;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import net.bytebuddy.asm.Advice;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoLocalidad implements WithSimplePersistenceUnit {

    public void guardar(Localidad unaLocalidad) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(unaLocalidad);
        tx.commit();
    }

    public void guardarMuchas(List<Localidad> listaLocalidad) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        for(Localidad unaLocalidad : listaLocalidad){
            entityManager().persist(unaLocalidad);
        }
        tx.commit();
    }

    public void eliminar(Localidad unaLocalidad) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(unaLocalidad);
        tx.commit();
    }

    public void modificar(Localidad unaLocalidad) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(unaLocalidad);
        tx.commit();
    }

    public Localidad buscarPorNombre(String nombre) {
        return  (Localidad) entityManager()
                .createQuery("from " + Localidad.class.getName() + " where localidad_nombre = :nombre")
                .setParameter("nombre", nombre)
                .getSingleResult();
    }
    public Localidad buscarPorId(Integer id) {
        return entityManager().find(Localidad.class, id);
    }

    public List<Localidad> buscarTodos() {
        return entityManager().createQuery("from " + Localidad.class.getName()).getResultList();
    }
}
