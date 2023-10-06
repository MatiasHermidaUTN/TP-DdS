package ar.edu.utn.frba.dds.models.repositorios;

import ar.edu.utn.frba.dds.models.localizacion.Departamento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoDepartamento implements WithSimplePersistenceUnit {

    public void guardar(Departamento unDepartamento) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(unDepartamento);
        tx.commit();
    }

    public void guardarMuchos(List<Departamento> listaDepartamento) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        for(Departamento unDepartamento : listaDepartamento){
            entityManager().persist(unDepartamento);
        }
        tx.commit();
    }

    public void eliminar(Departamento unDepartamento) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(unDepartamento);
        tx.commit();
    }

    public void modificar(Departamento unDepartamento) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(unDepartamento);
        tx.commit();
    }

    public Departamento buscarPorId(Integer id) {
        return entityManager().find(Departamento.class, id);
    }

    public List<Departamento> buscarTodos() {
        return entityManager().createQuery("from " + Departamento.class.getName()).getResultList();
    }
}
