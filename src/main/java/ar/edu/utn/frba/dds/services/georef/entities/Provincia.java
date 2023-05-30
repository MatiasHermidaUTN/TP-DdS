package ar.edu.utn.frba.dds.services.georef.entities;

public class Provincia {
    public int id;
    public String nombre;
    public Float latitud;
    public Float longitud;

    public Float getLatitud(){
        return this.latitud;
    }
    public Float getLongitud(){
        return this.longitud;
    }
}