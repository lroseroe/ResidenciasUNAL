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

    public static void registerUser(String username, String password) {
        if (usuarios.containsKey(username)) {
            System.out.println("El usuario ya existe.");
        } else {
            usuarios.put(username, password);
            System.out.println("Usuario registrado con éxito.");
        }
    }

    public static void authenticate(String username, String password) {
        if (!usuarios.containsKey(username)) {
            System.out.println("Usuario no encontrado.");
        } else if (usuarios.get(username).equals(password)) {
            System.out.println("Autenticación exitosa.");
        } else {
            System.out.println("Contraseña incorrecta.");
        }
    }

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