/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.residenciasunalhash;

/**
 *
 * @author Jthom
 */
import java.util.Scanner;

public class ResidenciasUnalHash {
    
    public static MyHashMap usuarios = new MyHashMap();

    public static boolean registrarUsuario(long id, String nombre, int puntaje) {
        if (!usuarios.containsKey(id)) {
            Estudiante miEstudiante = new Estudiante(id,nombre,puntaje);
            usuarios.put(id, miEstudiante);
            /*System.out.println("Estudiante registrado con éxito.");*/
            /*Mensaje provisional */
            return true;
        }
        return false;
        /*System.out.println("El estudiante ya existe.");*/
        /*Mensaje provisional */
        
    }

    public static boolean borrarUsuario(long id) {
        if (usuarios.containsKey(id)) {
            Estudiante miEstudiante = usuarios.get(id);
            usuarios.erase(id);
            /*System.out.println("Estudiante registrado con éxito.");*/
            /*Mensaje provisional */
            return true;
        }
        return false;
        /*System.out.println("El estudiante no existe.");*/
        /*Mensaje provisional */
        
    }

    public static boolean cambiarPuntaje(long id, int puntaje) {
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

    public static Estudiante buscarUsuario(long id){
        Estudiante miEstudiante;
        if(usuarios.containsKey(id)){
            /*Caso en que se encontro el estudiante */
            miEstudiante = usuarios.get(id);
        }else{
            /*Caso en que no se encontro el estudiante */
            miEstudiante=new Estudiante();
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Nombre de usuario: ");
                    String newUser = scanner.nextLine();
                    System.out.print("Contraseña: ");
                    String newPass = scanner.nextLine();
                    registerUser(newUser, newPass);
                    break;

                case 2:
                    System.out.print("Usuario: ");
                    String user = scanner.nextLine();
                    System.out.print("Contraseña: ");
                    String pass = scanner.nextLine();
                    authenticate(user, pass);
                    break;

                case 3:
                    System.out.println("Hasta luego.");
                    return;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}