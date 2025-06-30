
package com.mycompany.residenciasunalhash;

/**
 *
 * @author Jthom
 */
public class MyHashMap {

    // Clase interna para representar un par clave-valor
    static class HashNode {
        String llave;
        String valor;
        HashNode siguiente;

        HashNode(String llave, String valor) {
            this.llave = llave;
            this.valor = valor;
            this.siguiente = null;
        }
    }

    private final int SIZE = 100; // Tamaño del array de buckets
    private HashNode[] table;

    public MyHashMap() {
        table = new HashNode[SIZE];
    }

  
    private int hash(String llave) {
        int hash = 0;
        int primo = 31; // número primo pequeño
        long power = 1;

        for (int i = 0; i < llave.length(); i++) {
            char c = llave.charAt(i);
            hash = (int)((hash + (c - 'a' + 1) * power) % SIZE);
            power = (power * primo) % SIZE;
        }

        return Math.abs(hash);
    }

    // Agregar o actualizar un valor
    public void put(String llave, String valor) {
        int index = hash(llave);
        HashNode head = table[index];

        // Verificar si la clave ya existe
        while (head != null) {
            if (head.llave.equals(llave)) {
                head.valor = valor;
                return;
            }
            head = head.siguiente;
        }

        // Insertar nuevo Entry al inicio
        HashNode newEntry = new HashNode(llave, valor);
        newEntry.siguiente = table[index];
        table[index] = newEntry;
    }

    // Obtener el valor asociado a una clave
    public String get(String key) {
        int index = hash(key);
        HashNode head = table[index];

        while (head != null) {
            if (head.llave.equals(key)) {
                return head.valor;
            }
            head = head.siguiente;
        }
        return null; // No encontrado
    }

    // Verificar si existe una clave
    public boolean containsKey(String llave) {
        return get(llave) != null;
    }
}
