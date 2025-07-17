package main;

import estructuras.Estudiante;
import estructuras.MinHeap;
import estructuras.HeapSort;
//import ResidenciasUnalHash;

public class Main {
    public static void main(String[] args) {
        // Conectar con botones en la interfaz
        
        /*
        if(boton == "Registrar estudiante"){
            Minheap.insert(estudiante);
        }
        if(boton == "Eliminar estudiante"){
            MinHeap.remove(estudiante);
        }
        if(boton == "Cambiar Puntaje"){
            Minheap.changePriority(estudiante, nuevoPuntaje);
        }
        */

        Estudiante estudiante1 = new Estudiante(1025534286, "Sara Fajardo", 1);
        Estudiante estudiante2 = new Estudiante(1564654286, "Luisa Rosero", 5);
        Estudiante estudiante3 = new Estudiante(1157984694, "Thomas Contreras", 8);
        Estudiante estudiante4 = new Estudiante(1352454618, "Isabella Fajardo", 3);
        Estudiante estudiante5 = new Estudiante(1387684618, "Nelson Mandela", 3);
        Estudiante estudiante6 = new Estudiante(1368764618, "Pepito Perez", 9);
        Estudiante estudiante7 = new Estudiante(1354646618, "Reinaldo Rueda", 7);
        Estudiante estudiante8 = new Estudiante(1576746618, "Giovanni Pulido",2);
        

        MinHeap heap = new MinHeap(100000);
        heap.insert(estudiante1);
        heap.insert(estudiante2);
        heap.insert(estudiante3);
        heap.insert(estudiante4);
        heap.insert(estudiante5);
        heap.insert(estudiante6);
        heap.insert(estudiante7);
        
        heap.printHeap();
        
        int i = estudiante4.getHeapIndex();
        System.out.println("Indice: " + i);

        Estudiante[] lista = heap.array();
        HeapSort arrayOrdenado = new HeapSort(lista);

        arrayOrdenado.heapSort(2);
        arrayOrdenado.printEstudiantes();

        heap.changePriority(estudiante6, 10);
        heap.changePriority(estudiante2, 9);
        heap.remove(estudiante1);
        heap.insert(estudiante8); //Mirar si se puede insertar y hacer HeapSort sin tener que volver a crear el objeto HeapSort(lista)

        arrayOrdenado.heapSort(6);
        
        arrayOrdenado.printEstudiantes();
    }        
}

