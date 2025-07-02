
package com.mycompany.residenciasunalhash;

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

    public MyHashMap() {
        table = new HashNode[SIZE];
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
                return;
            }
            prev=head;
            head = head.siguiente;
        }
    }

    // Agregar o actualizar un estudiante
    public void put(long llave, String estudiante) {
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
        HashNode newEntry = new HashNode(llave, estudiante);
        newEntry.siguiente = table[index];
        table[index] = newEntry;
    }

    // Obtener el estudiante asociado a una clave
    public String get(long key) {
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
}
