package ar.edu.utn.frba.dds.comunidades;

public class Proveedor {

    public void designarAdmin(Persona persona, Comunidad comunidad) {
        // agrego a la persona a la lista de admins
        comunidad.agregarAdmin(persona);
    }
}
