package ar.edu.utn.frba.dds.models.repositorios;

import ar.edu.utn.frba.dds.models.comunidades.Perfil;
import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import ar.edu.utn.frba.dds.models.persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoPerfil implements WithSimplePersistenceUnit {
    public void guardar(Perfil unPerfil) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(unPerfil);
        EntityManagerHelper.commit();
    }

    public void eliminar(Perfil unPerfil) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().remove(unPerfil);
        EntityManagerHelper.commit();
    }

    public void modificar(Perfil unPerfil) {
        this.guardar(unPerfil);
    }

    public Perfil buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Perfil.class, id);
    }

    public List<Perfil> buscarTodos() {
        return EntityManagerHelper.createQuery("from " + Perfil.class.getName()).getResultList();
    }

    public RepoPerfil() {
    }
}
