package org.pou;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntManager {
    private static EntityManagerFactory emf;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("miUnidad");
        } catch (Throwable ex) {
            System.err.println("Fallo en creacion de Entity Manager Factory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManager obtenerEntityManager() {
        return emf.createEntityManager();
    }

    public static void cerrar() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
