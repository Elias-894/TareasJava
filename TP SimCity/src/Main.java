import Clases.Alcalde;
import Clases.Ciudad;
import Clases.Edificio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Cargar todos los edificios del archivo
        ArrayList<Edificio> edificios_disponibles;

        File archivo = new File("src/datos.txt");
        edificios_disponibles = Utils.LeerEdificios(archivo);

        System.out.println("¡Bienvenido/a a SimCity!");

        // Crear el alcalde
        System.out.print("Ingrese su nombre: ");
        String nom = scanner.nextLine();
        System.out.print("Ingrese su apellido: ");
        String ape = scanner.nextLine();
        Alcalde alcalde = new Alcalde(nom, ape);

        // Crear la ciudad
        System.out.println("Hola " + alcalde.nombre + ",");
        System.out.print("Ingrese el nombre de su ciudad: ");
        nom = scanner.nextLine();
        Ciudad ciudad = new Ciudad(nom, alcalde);

        // Compra de edificios
        Utils.Espaciolol();
        boolean continuar = true;
        // que horrible pero no se me ocurre otra forma
        boolean seg,inc,eco,agu,pod,cam;
        seg=inc=eco=agu=pod=cam=false;
        do {
            System.out.println("--- TIENDA DE EDIFICIOS ---");
            var ed = edificios_disponibles;
            for (int i = 0; i < edificios_disponibles.size(); i++) {
                System.out.println((i+1) + ". " + ed.get(i).nombre + " - " + ed.get(i).tipo + " - Seguridad: " + ed.get(i).seguridad + " - Prevencion de Incendios: " + ed.get(i).incendio + " - Felicidad: " + ed.get(i).felicidad + " - $" + ed.get(i).precio);
            }
            System.out.print("\n");
            System.out.println("0. Continuar");
            String entrada = scanner.nextLine();
            if (Objects.equals(entrada, "TORG")) {
                ciudad.alcalde.dinero += 1000000;
                continue;
            }

            int input;
            try {
                input = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                Utils.Espaciolol();
                System.out.println("\u001B[31mComando Invalido\u001B[0m");
                continue;
            }

            boolean encontrado = false;
            for (int i = 0; i < ed.size(); i++) {
                if (input - 1 == i) {
                    // SI SE ENCUENTRA EL EDIFICIO
                    encontrado = true;
                    if (edificios_disponibles.get(i).precio <= ciudad.alcalde.dinero) {
                        // SI TE ALCANZA LA PLATA
                        ciudad.alcalde.dinero -= edificios_disponibles.get(i).precio;
                        ciudad.edificios.add(edificios_disponibles.get(i));
                        Edificio.Tipos tipoaeliminar = edificios_disponibles.get(i).tipo;
                        switch (tipoaeliminar) {
                            case SEGURIDAD -> seg = true;
                            case CAMINOS -> cam = true;
                            case ECOLOGIA -> eco = true;
                            case INCENDIOS -> inc = true;
                            case PLANTA_DE_AGUA -> agu = true;
                            case PLANTA_ENERGETICA -> pod = true;
                        }

                        edificios_disponibles.remove(i);
                        Utils.Espaciolol();
                        System.out.println("Edificio comprado con exito.");

                        for (int j = 0; j < edificios_disponibles.size(); j++) {
                            // ELIMINAR EDIFICIOS DEL MISMO TIPO...
                            if (tipoaeliminar == Edificio.Tipos.MARAVILLA) {
                                // ...al menos que sea una maravilla
                                break;
                            }
                            if (edificios_disponibles.get(j).tipo == tipoaeliminar) {
                                edificios_disponibles.remove(j);
                                j -= 1;
                            }
                        }

                        break;
                    } else {
                        // SI SOS POBRE
                        Utils.Espaciolol();
                        System.out.println("\u001B[31mDinero Insuficiente...\u001B[0m");
                    }
                }
            }
            if (input == 0 && seg && inc && eco && agu && pod && cam) {
                break;
            }

            // SI NO EXISTE EL EDIFICIO BUSCADO
            if (!encontrado) {
                Utils.Espaciolol();
                System.out.println("\u001B[31mEdificio no encontrado...\u001B[0m");
            }

        } while (true);
        ciudad.Actualizar_Estadisticas();

        // Menu
        Utils.Espaciolol();
        boolean salir = false;
        do {
            System.out.println("--- MENU CIUDAD ---");
            System.out.println("1. Mostrar Datos");
            System.out.println("2. Promedio de Seguridad");
            System.out.println("3. Promedio de Prevención de Incendios");
            System.out.println("4. Promedio de Felicidad");
            System.out.println("5. Edificio de mayor y menor valor");
            System.out.println("6. Dinero desenfundado");
            System.out.println("7. Edificios de mayor y menor característica.");
            System.out.print("\n");
            System.out.println("8. Salir");
            int input = scanner.nextInt();

            switch (input) {
                case 1:
                    Utils.Espaciolol();
                    System.out.println("- Ciudad: " + ciudad.nombre);
                    System.out.println("- Alcalde: " + ciudad.alcalde.nombre + " " + ciudad.alcalde.apellido);
                    System.out.println("- Edificios: ");
                    for (int i = 0; i < ciudad.edificios.size(); i++) {
                        System.out.println("      - " + ciudad.edificios.get(i).nombre);
                    }
                    break;
                case 2:
                    // Pide un promedio pero más de la mitad de los edificios proporcionan 0 de cada estadistica. Es imposible que llegue a 10.
                    Utils.Espaciolol();
                    System.out.println("Seguridad total: " + Utils.LimitarNum(ciudad.seguridad, 10, 0));
                    break;
                case 3:
                    Utils.Espaciolol();
                    System.out.println("Prevención de Incendios: " + Utils.LimitarNum(ciudad.incendios, 10, 0));
                    break;
                case 4:
                    Utils.Espaciolol();
                    System.out.println("Felicidad total: " + Utils.LimitarNum(ciudad.felicidad, 10, 0));
                    break;
                case 5:
                    Utils.Espaciolol();
                    Edificio edificioMasCaro = ciudad.edificios.get(1);
                    Edificio edificioMasBarato = ciudad.edificios.get(1);
                    for (int i = 0; i < ciudad.edificios.size(); i++) {
                        if (edificioMasCaro.precio < ciudad.edificios.get(i).precio) edificioMasCaro = ciudad.edificios.get(i);
                        if (edificioMasBarato.precio > ciudad.edificios.get(i).precio) edificioMasBarato = ciudad.edificios.get(i);
                    }
                    System.out.println("Edificio más costoso: " + edificioMasCaro.nombre + " - $" + edificioMasCaro.precio);
                    System.out.println("Edificio menos costoso: " + edificioMasBarato.nombre + " - $" + edificioMasBarato.precio);
                    break;
                case 6:
                    Utils.Espaciolol();
                    int total = 0;
                    for (int i = 0; i < ciudad.edificios.size(); i++) {
                        total += ciudad.edificios.get(i).precio;
                    }
                    System.out.println("Total gastado en la ciudad: $" + total);
                    break;
                case 7:
                    // Horrible
                    Utils.Espaciolol();
                    Edificio FelicidadMax = ciudad.edificios.get(1);
                    Edificio FelicidadMin = ciudad.edificios.get(1);
                    Edificio SeguridadMax = ciudad.edificios.get(1);
                    Edificio SeguridadMin = ciudad.edificios.get(1);
                    Edificio IncendiosMax = ciudad.edificios.get(1);
                    Edificio IncendiosMin = ciudad.edificios.get(1);
                    for (Edificio ed: ciudad.edificios) {
                        if (FelicidadMax.felicidad < ed.felicidad) FelicidadMax = ed;
                        if (FelicidadMin.felicidad > ed.felicidad && ed.felicidad != 0) FelicidadMin = ed;
                        if (SeguridadMax.seguridad < ed.seguridad) SeguridadMax = ed;
                        if (SeguridadMin.seguridad > ed.seguridad && ed.felicidad != 0) SeguridadMin = ed;
                        if (IncendiosMax.incendio < ed.incendio) IncendiosMax = ed;
                        if (IncendiosMin.incendio > ed.incendio && ed.felicidad != 0) IncendiosMin = ed;
                    }
                    System.out.println("Edificio de mayor felicidad: " + FelicidadMax.nombre + ", Aporta:" + FelicidadMax.felicidad);
                    System.out.println("Edificio de menor felicidad: " + FelicidadMin.nombre + ", Aporta:" + FelicidadMin.felicidad);
                    System.out.println("Edificio de mayor seguridad: " + SeguridadMax.nombre + ", Aporta:" + SeguridadMax.seguridad);
                    System.out.println("Edificio de menor seguridad: " + SeguridadMin.nombre + ", Aporta:" + SeguridadMin.seguridad);
                    System.out.println("Edificio de mayor prevencion de incendios: " + IncendiosMax.nombre + ", Aporta:" + IncendiosMax.incendio);
                    System.out.println("Edificio de menor prevencion de incendios: " + IncendiosMin.nombre + ", Aporta:" + IncendiosMin.incendio);
                    break;
                case 8:
                    Utils.Espaciolol();
                    salir = true;
                    break;
                default:
                    Utils.Espaciolol();
                    System.out.println("\u001B[31mOPCION INVALIDA.\u001B[0m");
                    break;
            }
        } while (!salir);
        scanner.close();
    }
}