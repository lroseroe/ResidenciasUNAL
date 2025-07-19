package pruebasComplejidad;

import java.time.Duration;
import java.time.Instant;

import estructuras.Estudiante;
import estructuras.HeapSort;
import estructuras.MinHeap;

public class Complejidad {

    public interface Operation {
    void apply(int value);
    
    }

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
        final int end = 1000000; //Poner la cantidad de datos a probar segun la preferencia
     
        System.out.println("===== MinHeap =====");
        for(int size = start; size<=end;size*=10){
            MinHeap minHeap = new MinHeap(size);
            Estudiante estudiantePrueba1 = new Estudiante(1012574489, "David Herrera", 50);
  
            System.out.println("=== Tamaño de prueba: " + size + " ===");
            
            exec(size, "MinHeap - insert", _-> minHeap.insert(estudiantePrueba1));
            exec(size, "MinHeap - changePriority", _-> minHeap.changePriority(estudiantePrueba1, 80));
            exec(size, "MinHeap - remove", _-> minHeap.remove(estudiantePrueba1));
            System.out.println();
        }

        System.out.println("===== HeapSort =====");
        for(int size = start; size<=end;size*=10){
            MinHeap minHeap = new MinHeap(size);
            int i = size;

            Estudiante estudiantePrueba1 = new Estudiante(1012574489, "David Herrera", 50);

            for(int j=0;j<size;j++){
                minHeap.insert(estudiantePrueba1);
            }

            HeapSort heapSort = new HeapSort(minHeap.filtrarEstudiantes());
            System.out.println("=== Tamaño de prueba: " + size + " ===");

            exec(size, "HeapSort", _-> heapSort.heapSort());
            exec(size, "AsignarCupos", _->heapSort.asignarCupos(i));

            System.out.println();
        }

    }
}
