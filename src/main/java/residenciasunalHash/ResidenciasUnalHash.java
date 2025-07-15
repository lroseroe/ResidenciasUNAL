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
    
    public static MyHashMap usuarios = new MyHashMap();

    public static boolean cargarMapa() {
        usuarios.cargarDesdeCSV("estudiantes");
        return true;
    }
    
    public static boolean registrarUsuario(long id, String nombre, int puntaje) {
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

    public static boolean borrarUsuario(long id) {
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
    
    public static int getEstudiantes() {
        return usuarios.getTamano();
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

    public static void main(String[] args) {
        cargarMapa();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Mostrar Info");
            System.out.println("3. Eliminar usuario");
            System.out.println("4. Ver cantidad usuarios");
            System.out.println("9. Salir");
            
            System.out.print("Selecciona una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("ID: ");
                    long newUser = scanner.nextLong();
                    System.out.print("nombre: ");
                    scanner.nextLine();
                    String newPass = scanner.nextLine();
                    System.out.print("puntaje: ");
                    int punt = scanner.nextInt();
                    registrarUsuario(newUser, newPass,punt);
                    
                    break;

                case 2:
                    
                    System.out.print("ID: ");
                    newUser = scanner.nextLong();
                    Estudiante miE = buscarUsuario(newUser);
                    System.out.println(miE.getNombre());
                    System.out.println(miE.getPuntaje());
                    break;

                case 3:
                    System.out.print("ID: ");
                    newUser = scanner.nextLong();
                    borrarUsuario(newUser);
                    
                    break;
                case 4:
                    System.out.print("Hay: ");
                    System.out.print(getEstudiantes());
                    
                    break;

                case 9:
                    System.out.println("Hasta luego.");
                    return;
                    
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}