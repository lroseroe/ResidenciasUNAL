package main;

import estructuras.Estudiante;
import estructuras.HeapSort;
import estructuras.MinHeap;
//import ResidenciasUnalHash;

public class Main {
    public static void main(String[] args) {

        Estudiante estudiante1 = new Estudiante(1025534286, "Sara Fajardo", 1);
        Estudiante estudiante2 = new Estudiante(1564654286, "Luisa Rosero", 5);
        Estudiante estudiante3 = new Estudiante(1157984694, "Thomas Contreras", 8);
        Estudiante estudiante4 = new Estudiante(1352454618, "Isabella Fajardo", 3);
        Estudiante estudiante5 = new Estudiante(1387684618, "Nelson Mandela", 3);
        Estudiante estudiante6 = new Estudiante(1368764618, "Pepito Perez", 9);
        Estudiante estudiante7 = new Estudiante(1354646618, "Reinaldo Rueda", 7);
        Estudiante estudiante8 = new Estudiante(1576746618, "Giovanni Pulido",2);
        
        //Crear un MinHeap de tamaño 100000
        MinHeap heap = new MinHeap(100000);
        heap.insert(estudiante1);
        heap.insert(estudiante2);
        heap.insert(estudiante3);
        heap.insert(estudiante4);
        heap.insert(estudiante5);
        heap.insert(estudiante6);
        heap.insert(estudiante7);
        heap.printHeap();
        
        //Obtener el indice de un estudiante
        int i = estudiante7.getHeapIndex();
        System.out.println("Indice del estudiante numero 7: " + i);

        //Pasar el MinHep al HeapSort
        Estudiante[] lista = heap.filtrarEstudiantes();
        HeapSort arrayOrdenado = new HeapSort(lista);                       
        
        //Ordenar y asignar cupos
        heap.remove(estudiante1);        
        arrayOrdenado.heapSort();
        arrayOrdenado.asignarCupos(4);
        arrayOrdenado.printEstudiantes();

        /* 
        //Cambiar prioridad remover e insertar un estudiante, ordenar nuevamente reinciando el apoyo de los estudiantes
        heap.changePriority(estudiante6, 10);
        heap.changePriority(estudiante2, 9);
        heap.remove(estudiante1);  
        heap.insert(estudiante8);
        arrayOrdenado.heapSort2();
        arrayOrdenado.asignarCupos(4);                    
        arrayOrdenado.printEstudiantes();*/
    }        
}

