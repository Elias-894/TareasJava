package org.pou;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManager em = EntManager.obtenerEntityManager();
        EntityTransaction tran = em.getTransaction();
        Scanner scan = new Scanner(System.in);


        tran.begin();
        System.out.println("Hola, ingrese el nombre de su pou: ");
        Pou pou = new Pou(scan.nextLine(), 100, 0, 80, 50);
        em.persist(pou);
        tran.commit();

        boolean salir = false;
        do {
            System.out.print("Estado pou: ");
            pou.mostrarStats();
            System.out.print("\nIngrese una accion: ");
            System.out.print("1 - Dar de comer");
            System.out.print("2 - Bañar");
            System.out.print("3 - Jugar");
            System.out.print("4 - Dormir");
            System.out.print("5 - Acariciar");
            System.out.print("0 - Salir");
            int accion = scan.nextInt();
            switch (accion) {
                case 0:
                    salir = true;
                    break;
                case 1:
                    salir = true;
                    break;
                case 2:
                    salir = true;
                    break;
                case 3:
                    salir = true;
                    break;
                case 4:
                    salir = true;
                    break;
                case 5:
                    salir = true;
                    break;
            }

        } while (!salir);


        scan.close();
        em.close();
        EntManager.cerrar();
    }

}
