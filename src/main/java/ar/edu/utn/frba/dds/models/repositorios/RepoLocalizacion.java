package ar.edu.utn.frba.dds.models.repositorios;

import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import ar.edu.utn.frba.dds.models.localizacion.Localizacion;
import ar.edu.utn.frba.dds.models.persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoLocalizacion implements WithSimplePersistenceUnit {

    public void guardar(Localizacion unaLocalizacion) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(unaLocalizacion);
        EntityManagerHelper.commit();
    }

    public void eliminar(Localizacion unaLocalizacion) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().remove(unaLocalizacion);
        EntityManagerHelper.commit();
    }

    public void modificar(Localizacion unaLocalizacion) {
        this.guardar(unaLocalizacion);
    }

    public Localizacion buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Localizacion.class, id);
    }

    public List<Localizacion> buscarTodos() {
        return EntityManagerHelper.createQuery("from " + Localizacion.class.getName()).getResultList();
    }
}
