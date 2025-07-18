/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package residenciasunalhash;

/**
 *
 * @author Jthom
 */
import java.util.Scanner;

public class ResidenciasUnalHash {
    
    public MyHashMap usuarios = new MyHashMap();
    
    public boolean registrarUsuario(long id, String nombre, int puntaje) {
        if (!usuarios.containsKey(id)) {
            Estudiante miEstudiante = new Estudiante(id,nombre,puntaje);
            usuarios.put(id, miEstudiante);
            usuarios.guardarComoCSV("estudiantes");
            /*System.out.println("Estudiante registrado con éxito.");*/
            /*Mensaje provisional */
            return true;
        }
        return false;
        /*System.out.println("El estudiante ya existe.");*/
        /*Mensaje provisional */
        
    }

    public boolean borrarUsuario(long id) {
        if (usuarios.containsKey(id)) {
            Estudiante miEstudiante = usuarios.get(id);
            usuarios.erase(id);
            usuarios.guardarComoCSV("estudiantes");
            /*System.out.println("Estudiante registrado con éxito.");*/
            /*Mensaje provisional */
            return true;
        }
        return false;
        /*System.out.println("El estudiante no existe.");*/
        /*Mensaje provisional */
        
    }
    
    public int getEstudiantes() {
        return usuarios.getTamano();
        /*System.out.println("El estudiante no existe.");*/
        /*Mensaje provisional */
        
    }
    
    public boolean cambiarPuntaje(long id, int puntaje) {
        if (usuarios.containsKey(id)) {
            Estudiante miEstudiante = usuarios.get(id);
            miEstudiante.setPuntaje(puntaje);
            usuarios.put(id, miEstudiante);
            /*System.out.println("Estudiante modificado con éxito.");*/
            /*Mensaje provisional */
            return true;
        }
        return false;
        /*System.out.println("El estudiante no existe.");*/
        /*Mensaje provisional */
        
    }

    public Estudiante buscarUsuario(long id){
        Estudiante miEstudiante;
        if(usuarios.containsKey(id)){
            /*Caso en que se encontro el estudiante */
            miEstudiante = usuarios.get(id);
        }else{
            /*Caso en que no se encontro el estudiante */
            miEstudiante = null;
        }
        /*Se deja a implementacion del equipo de trabajo del UI mostrar los valores en pantalla */
        return miEstudiante;
    }

    /* 
    public static void authenticate(String username, String password) {
        if (!usuarios.containsKey(username)) {
            System.out.println("Estudiante no encontrado.");
        } else if (usuarios.get(username).equals(password)) {
            System.out.println("Autenticación exitosa.");
        } else {
            System.out.println("Contraseña incorrecta.");
        }
    }
    */

}