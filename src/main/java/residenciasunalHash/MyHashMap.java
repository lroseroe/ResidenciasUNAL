package residenciasunalhash;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Jthom
 */
public class MyHashMap {

    // Clase interna para representar un par clave-estudiante
    static class HashNode {
        long llave;
        Estudiante estudiante;
        HashNode siguiente;

        HashNode(long llave, Estudiante estudiante) {
            this.llave = llave;
            this.estudiante = estudiante;
            this.siguiente = null;
        }
    }

    private final int SIZE = 100; // Tamaño del array de buckets
    private HashNode[] table;
    private int tamano;

    public MyHashMap() {
        table = new HashNode[SIZE];
        tamano=0;
    }

    /*Hash con potencias de primos */
    private int hash(long key) {
        String llave = String.valueOf(key);
        int hash = 0;
        int primo = 31; // número primo pequeño
        long power = 1L;

        for (int i = 0; i < llave.length(); i++) {
            char c = llave.charAt(i);
            hash = (int)((hash + (c*power)) % SIZE);
            power = (power * primo) % SIZE;
        }

        return Math.abs(hash);
    }

    public void erase(long llave){
        int index = hash(llave);
        HashNode head = table[index];
        HashNode prev = null;

        // Verificar si la clave ya existe
        while (head != null) {
            if (head.llave==llave) {
                if(prev==null){
                    table[index]=head.siguiente;
                }else{
                    prev.siguiente=head.siguiente;
                }
                tamano--;
                return;
            }
            prev=head;
            head = head.siguiente;
        }
    }

    // Agregar o actualizar un estudiante
    public void put(long llave, Estudiante estudiante) {
        int index = hash(llave);
        HashNode head = table[index];

        // Verificar si la clave ya existe
        while (head != null) {
            if (head.llave==llave) {
                head.estudiante = estudiante;
                return;
            }
            head = head.siguiente;
        }

        // Insertar nuevo Entry al inicio
        tamano++;
        HashNode newEntry = new HashNode(llave, estudiante);
        newEntry.siguiente = table[index];
        table[index] = newEntry;
    }

    // Obtener el estudiante asociado a una clave
    public Estudiante get(long key) {
        int index = hash(key);
        HashNode head = table[index];

        while (head != null) {
            if (head.llave==key) {
                return head.estudiante;
            }
            head = head.siguiente;
        }
        return null; // No encontrado
    }

    // Verificar si existe una clave
    public boolean containsKey(long llave) {
        return get(llave) != null;
    }
    
     public void guardarComoCSV(String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (int i = 0; i < SIZE; i++) {
                HashNode current = table[i];
                while (current != null) {
                    writer.write(current.estudiante.toCSV());
                    writer.newLine();
                    current = current.siguiente;
                }
            }
            System.out.println("Datos guardados en: " + nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getTamano(){
        return tamano;
    }
            
}