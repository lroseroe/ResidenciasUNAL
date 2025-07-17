package estructuras;

import java.time.Instant;
import java.time.Duration;


interface Operation {
    void apply(int value);
    
}

public class Complejidad {

    public static void exec(int size, String method, Operation operation) {
        Instant start = Instant.now();        
        
        for (int i = 0; i < size; i++){
            operation.apply(i);
        }
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        System.out.printf("Se ejecutó %s de %d elementos en: %d milisegundos\n", method, size, timeElapsed);
    }

    public static void main(String[] args) {
        final int start = 10;
        final int end = 100; //Poner la cantidad de datos a probar segun la preferencia
     
        System.out.println("===== MinHeap =====");
        for(int size = start; size<=end;size*=10){
            MinHeap minHeap = new MinHeap(size);
            
            Estudiante estudiantePrueba1 = new Estudiante(1012574489, "David Herrera", 50);
            Estudiante estudiantePrueba2 = new Estudiante(1025468372, "Diana Chicuasuque", 100);

            System.out.println("=== Tamaño de prueba: " + size + " ===");
            
            exec(size-1, "MinHeap - insert", value -> minHeap.insert(estudiantePrueba1));
            exec(1, "MinHeap - insert", value -> minHeap.insert(estudiantePrueba2));
            exec(size, "MinHeap - changePriority", value -> minHeap.changePriority(estudiantePrueba2, 80));
            //exec(size, "MinHeap - findIndex", value -> minHeap.findIndex(estudiantePrueba2));
            exec(size-1, "MinHeap - remove", value -> minHeap.remove(estudiantePrueba1));
            exec(1, "MinHeap - remove", value -> minHeap.remove(estudiantePrueba2));
            System.out.println();
        }
    }
}
