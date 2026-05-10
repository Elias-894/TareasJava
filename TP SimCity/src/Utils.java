import Clases.Ciudad;
import Clases.Edificio;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
    public static void Espaciolol() {
        // muchos newlines para que la consola no sea un desastre
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public static boolean EdificiosRequeridos(Ciudad ciudad) {
        // Verifica si la ciudad tiene todos los edificios necesarios
        ciudad.edificios.get(0);

        return true;
    }

    public static Edificio.Tipos StringATipo(String string) {
        switch (string) {
            case "Maravilla":
                return Edificio.Tipos.MARAVILLA;
            case "Planta energética":
                return Edificio.Tipos.PLANTA_ENERGETICA;
            case "Planta de agua":
                return Edificio.Tipos.PLANTA_DE_AGUA;
            case "Seguridad":
                return Edificio.Tipos.SEGURIDAD;
            case "Incendios":
                return Edificio.Tipos.INCENDIOS;
            case "Caminos":
                return Edificio.Tipos.CAMINOS;
            case "Ecología":
                return Edificio.Tipos.ECOLOGIA;
        }
        return Edificio.Tipos.MARAVILLA;
    }

    public static ArrayList<Edificio> LeerEdificios(File archivo) {
        ArrayList<Edificio> edificios = new ArrayList<>();
        try {
            Scanner lector = new Scanner(archivo);
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                if (linea.endsWith(",")) {
                    linea = linea.substring(1, linea.length() - 2);
                } else {
                    linea = linea.substring(1, linea.length() - 1);
                }
                String[] contenido = linea.split(",");
                for (int i = 0; i < contenido.length; i++) {
                    contenido[i] = contenido[i].substring(1, contenido[i].length() - 1);
                }

                edificios.add(new Edificio(contenido[0],StringATipo(contenido[1]),Integer.parseInt(contenido[2]),Integer.parseInt(contenido[3]),Integer.parseInt(contenido[4]),Integer.parseInt(contenido[5])));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return edificios;
    }

    public static int LimitarNum(int a, int max, int min) {
        if (a > max) return max;
        return Math.max(a, min);
    }

}
