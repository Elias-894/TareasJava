package Clases;

public class Edificio {
    public enum Tipos {
        MARAVILLA,
        PLANTA_ENERGETICA,
        PLANTA_DE_AGUA,
        SEGURIDAD,
        INCENDIOS,
        CAMINOS,
        ECOLOGIA
    }

    public String nombre;
    public Tipos tipo;
    public int seguridad, incendio, felicidad, precio;


    public Edificio(String nombre, Tipos tipo, int seguridad, int incendio, int felicidad, int precio) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.seguridad = seguridad;
        this.incendio = incendio;
        this.felicidad = felicidad;
        this.precio = precio;
    }

}
