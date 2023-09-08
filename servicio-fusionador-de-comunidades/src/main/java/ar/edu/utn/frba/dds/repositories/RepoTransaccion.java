package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.Transaccion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RepoTransaccion {
    private static long secuencia = 0;
    private long nextId() {
        secuencia = secuencia + 1;
        return secuencia ; }

    private Collection<Transaccion> transacciones;

    public RepoTransaccion() {
        this.transacciones = new ArrayList<Transaccion>();
    }

    public void add(Transaccion transaccion) {
        transaccion.setId(nextId());
        this.transacciones.add(transaccion);
    }

    public void remove(Transaccion transaccion) {
        this.transacciones = this.transacciones.stream().filter(
                x -> !x.getId().equals(
                        transaccion.getId())).toList();
    }

    public boolean exists(Long id) {
        return this.transacciones.stream().anyMatch(
                x -> x.getId().equals(id));
    }

    public Transaccion findById(Long id) {
        return this.transacciones.stream().filter(
                x -> x.getId().equals(id)
        ).findFirst().get();
    }

    public Collection<Transaccion> all() {
        return this.transacciones;
    }

}
