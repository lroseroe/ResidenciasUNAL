package main;

import estructuras.MinHeap;
//import ResidenciasUnalHash;

public class Main {
    public static void main(String[] args) {
        // Conectar con botones en la interfaz

        /*if(boton == "Editar Cupos"){
            MinHeap.newPlaces(nuevosCupos);
        }
        if(boton == "Registrar estudiante"){
            Minheap.insert(estudiante);
        }
        if(boton == "Eliminar estudiante"){
            MinHeap.remove(estudiante);
        }
        if(boton == "Cambiar Puntaje"){
            Minheap.changePriority(estudiante, nuevoPuntaje);
        }
        if(boton == "Consultar Asignacion"){
            if(MinHeap.find()){
                System.out.println("El estudiante tiene cupo asignado");
            }else{
                System.out.println("El estudiante no tiene cupo asignado");
            }
        }
        if(Consultar cupos){
            Minheap.getPlaces();
        }
        */

        // Se debe inicializar el minHeap con algun valor;
        MinHeap<Integer> heap = new MinHeap<>(100);
        heap.insert(7);
        heap.insert(10);
        heap.insert(4);
    }
}

