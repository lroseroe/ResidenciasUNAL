/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package residenciasunalhash;

/**
 *
 * @author Jthom
 */
import java.io.File;
import java.util.Scanner;


public class Estudiante{
    long id;
    String nombre;
    int puntaje;
    boolean tieneApoyo;
    public boolean eliminado = false;

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
    
    public int compareTo(Estudiante otro){
        return Integer.compare(this.puntaje, otro.getPuntaje());
    }

    public boolean isRemove(){
        return eliminado;
    }

    public void setRemove(){
        this.eliminado = true;
        this.tieneApoyo = false;
    }
    
    public void setRemove(boolean bool){
        this.eliminado = bool;
    }

    
     // Método para convertir a CSV
    public String toCSV() {
        String idEnc = CriptoCode.encriptar(String.valueOf(id));
        String nombreEnc = CriptoCode.encriptar(nombre.replace(",", ";"));
        String puntajeEnc = CriptoCode.encriptar(String.valueOf(puntaje));
        String apoyoEnc = CriptoCode.encriptar(String.valueOf(tieneApoyo));
        return idEnc + "," + nombreEnc + "," + puntajeEnc + "," + apoyoEnc;
    }

    // Método para crear estudiante desde CSV
    public static Estudiante fromCSV(String linea) {
        String[] partes = linea.split(",", -1);
        if (partes.length < 4) return null;
        
        System.out.println("Encriptado: " + partes[0]);
        System.out.println("Desencriptadooo: " + CriptoCode.desencriptar(partes[0]));
        
        long id = Long.parseLong(CriptoCode.desencriptar(partes[0]));
        String nombre = CriptoCode.desencriptar(partes[1]).replace(";", ",");
        int puntaje = Integer.parseInt(CriptoCode.desencriptar(partes[2]));
        boolean apoyo = Boolean.parseBoolean(CriptoCode.desencriptar(partes[3]));

        Estudiante est = new Estudiante(id, nombre, puntaje);
        est.setApoyo(apoyo);
        return est;
    }
    
    //Para el indexHash en heap
    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
    
}