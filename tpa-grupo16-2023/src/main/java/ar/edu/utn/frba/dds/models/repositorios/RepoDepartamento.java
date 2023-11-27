package ar.edu.utn.frba.dds.models.repositorios;

import ar.edu.utn.frba.dds.models.localizacion.Departamento;
import ar.edu.utn.frba.dds.models.persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoDepartamento implements WithSimplePersistenceUnit {

    public void guardar(Departamento unDepartamento) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(unDepartamento);
        EntityManagerHelper.commit();
    }

    public void guardarMuchos(List<Departamento> listaDepartamento) {
        EntityManagerHelper.beginTransaction();
        for(Departamento unDepartamento : listaDepartamento){
            EntityManagerHelper.getEntityManager().persist(unDepartamento);
        }
        EntityManagerHelper.commit();
    }

    public void eliminar(Departamento unDepartamento) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().remove(unDepartamento);
        EntityManagerHelper.commit();
    }

    public void modificar(Departamento unDepartamento) {
        this.guardar(unDepartamento);
    }

    public Departamento buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Departamento.class, id);
    }

    public List<Departamento> buscarTodos() {
        return EntityManagerHelper.createQuery("from " + Departamento.class.getName()).getResultList();
    }

     public List<Departamento> buscarPorProvincia(Integer provincia){
         return EntityManagerHelper.createQuery("from " + Departamento.class.getName() + " where provincia_id = " + provincia).getResultList();
     }
}
