package org.pou;

import jakarta.persistence.*;

@Entity
public class Pou {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Basic
    private String nombre;
    private int energia;
    private int suciedad;
    private int felicidad;
    private int hambre;

    public Pou(String nombre, int energia, int suciedad, int felicidad, int hambre) {
        this.nombre = nombre;
        this.energia = energia;
        this.suciedad = suciedad;
        this.felicidad = felicidad;
        this.hambre = hambre;
    }

    public Pou() {

    }

    public void mostrarStats() {
        System.out.println("Energia: " + energia);
        System.out.println("Suciedad: " + suciedad);
        System.out.println("Felicidad: " + felicidad);
        System.out.println("Hambre: " + hambre);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public int getSuciedad() {
        return suciedad;
    }

    public void setSuciedad(int suciedad) {
        this.suciedad = suciedad;
    }

    public int getFelicidad() {
        return felicidad;
    }

    public void setFelicidad(int felicidad) {
        this.felicidad = felicidad;
    }

    public int getHambre() {
        return hambre;
    }

    public void setHambre(int hambre) {
        this.hambre = hambre;
    }
}
