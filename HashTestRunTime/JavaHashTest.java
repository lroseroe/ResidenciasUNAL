package com.mycompany.residenciasunalhash;

import java.io.FileWriter;
import java.io.IOException;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

interface Operation {
    void apply(int i);
}

public class JavaHashTest {

    static class Result {
        int size;
        String operation;
        long timeMs;

        Result(int size, String operation, long timeMs) {
            this.size = size;
            this.operation = operation;
            this.timeMs = timeMs;
        }
    }

    static List<Result> results = new ArrayList<>();

    public static void exec(int size, String method, Operation operation) {
        Instant start = Instant.now();
        for (int i = 0; i < size; i++) {
            operation.apply(i);
        }
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();

        results.add(new Result(size, method, timeElapsed));
        System.out.printf("Se ejecuta %s de %d elementos en: %d ms\n", method, size, timeElapsed);
    }

    public static void saveCSV(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("size,operation,time_milliseconds\n");
            for (Result r : results) {
                writer.write(String.format("%d,%s,%d\n", r.size, r.operation, r.timeMs));
            }
            System.out.println("Resultados guardados en " + filename);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo CSV: " + e.getMessage());
        }
    }

    public static String generarCadenaAlfanumerica(int longitud) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(longitud);
        for (int i = 0; i < longitud; i++) {
            int indice = random.nextInt(caracteres.length());
            sb.append(caracteres.charAt(indice));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Random rand = new Random();

        final int start = 10;
        final int end = 1000000;

        for (int size = start; size <= end; size *= 10) {
            for (int i = 0; i < 5; i++) {

                MyHashMap mapa = new MyHashMap();
                List<Long> claves = new ArrayList<>();

                // Medir inserciones
                exec(size, "put", (j) -> {
                    long id = rand.nextLong(99999999999L);
                    claves.addLast(id);
                    String nombre = "XXXXXX XXXXXXXXXXX XXXXXXXXX XXXXXXXXXXX";
                    int puntaje = rand.nextInt(100);
                    Estudiante e = new Estudiante(id, nombre, puntaje);
                    mapa.put(id, e);
                });

                // Medir búsquedas (get)
                exec(size, "get", (j) -> {
                    if (!claves.isEmpty()) {
                        long idBuscar = claves.get(rand.nextInt(claves.size()));
                        mapa.get(idBuscar);
                    }
                });

                // Medir containsKey
                exec(size, "containsKey", (j) -> {
                    if (!claves.isEmpty()) {
                        long idBuscar = claves.get(rand.nextInt(claves.size()));
                        mapa.containsKey(idBuscar);
                    }
                });

                // Medir eliminaciones (erase)
                exec(size, "erase", (j) -> {
                    if (!claves.isEmpty()) {
                        long idBorrar = claves.get(rand.nextInt(claves.size()));
                        mapa.erase(idBorrar);
                    }
                });
            }
        }

        saveCSV("resultados_hashmap.csv");
    }
}
