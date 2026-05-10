package Clases;
import java.util.ArrayList;

public class Ciudad {
    public String nombre;
    public Alcalde alcalde;
    public ArrayList<Edificio> edificios;

    public int seguridad;
    public int incendios;
    public int felicidad;

    public void Actualizar_Estadisticas() {
        for (Edificio edificio : this.edificios) {
            seguridad += edificio.seguridad;
            incendios += edificio.incendio;
            felicidad += edificio.felicidad;
        }
    }


    public Ciudad(String u_nombre, Alcalde u_alcalde) {
        nombre = u_nombre;
        alcalde = u_alcalde;
        edificios = new ArrayList<>();
    }

}

