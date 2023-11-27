package ar.edu.utn.frba.dds.models.repositorios;

import ar.edu.utn.frba.dds.models.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.localizacion.Localidad;
import ar.edu.utn.frba.dds.models.persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import net.bytebuddy.asm.Advice;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoLocalidad implements WithSimplePersistenceUnit {

    public void guardar(Localidad unaLocalidad) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(unaLocalidad);
        EntityManagerHelper.commit();
    }

    public void guardarMuchas(List<Localidad> listaLocalidad) {
        EntityManagerHelper.beginTransaction();
        for(Localidad unaLocalidad : listaLocalidad){
            EntityManagerHelper.getEntityManager().persist(unaLocalidad);
        }
        EntityManagerHelper.commit();
    }

    public void eliminar(Localidad unaLocalidad) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().remove(unaLocalidad);
        EntityManagerHelper.commit();
    }

    public void modificar(Localidad unaLocalidad) {
        this.guardar(unaLocalidad);
    }

    public Localidad buscarPorNombre(String nombre) {
        return  (Localidad) EntityManagerHelper
                .createQuery("from " + Localidad.class.getName() + " where localidad_nombre = :nombre")
                .setParameter("nombre", nombre)
                .getResultList().get(0);
    }
    public Localidad buscarPorId(Long id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Localidad.class, id);
    }

    public List<Localidad> buscarTodos() {
        return EntityManagerHelper.createQuery("from " + Localidad.class.getName()).getResultList();
    }

    public List<Localidad> buscarPorDepartamento(Integer departamento) {
        return EntityManagerHelper.createQuery("from " + Localidad.class.getName() + " where departamento_id = " + departamento).getResultList();
    }
}
