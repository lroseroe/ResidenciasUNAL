/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.residenciasunalhash;

/**
 *
 * @author Jthom
 */
import java.util.Scanner;


public class Estudiante{
    long id;
    String nombre;
    int puntaje;
    boolean tieneApoyo;

    public Estudiante(){
        this.id=9999999999L;
        this.nombre="XXXXXX XXXXX XXXXXX XXXXX";
        this.puntaje=0;
        this.tieneApoyo=false;
    }

    public Estudiante(long id, String nombre, int puntaje){
        this.id=id;
        this.nombre=nombre;
        this.puntaje=puntaje;
        this.tieneApoyo=false;
    }

    public String getNombre(){
        return this.nombre;
    }
    
    public long getID(){
        return this.id;
    }

    public void setPuntaje(int n){
        this.puntaje=n;
    }
    public int getPuntaje(){
        return this.puntaje;
    }

    public void setApoyo(boolean b){
        this.tieneApoyo=b;
    }

    public boolean getApoyo(){
        return this.tieneApoyo;
    }

     // Método para convertir a CSV
    public String toCSV() {
        return id + "," + nombre + "," + puntaje + "," + tieneApoyo;
    }

    // Método para crear estudiante desde CSV
    public static Estudiante fromCSV(String linea) {
        String[] partes = linea.split(",", -1);
        if (partes.length < 4) return null;
        long id = Long.parseLong(partes[0]);
        String nombre = partes[1];
        int puntaje = Integer.parseInt(partes[2]);
        boolean tieneApoyo = Boolean.parseBoolean(partes[3]);

        Estudiante est = new Estudiante(id, nombre, puntaje);
        est.setApoyo(tieneApoyo);
        return est;
    }
    
    
}