package main;

import estructuras.Estudiante;
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
            if(estudiante.getApoyo()){
                System.out.println("El estudiante tiene cupo asignado");
            }else{
                System.out.println("El estudiante no tiene cupo asignado");
            }
        }
        if(Consultar cupos){
            Minheap.getPlaces();
        }
        */

         
        int cupos = 5;
        Estudiante estudiante1 = new Estudiante(1025534286, "Sara Fajardo", 1);
        Estudiante estudiante2 = new Estudiante(1564654286, "Luisa Rosero", 5);
        Estudiante estudiante3 = new Estudiante(1157984694, "Thomas Contreras", 8);
        Estudiante estudiante4 = new Estudiante(1352454618, "Isabella Fajardo", 3);
        Estudiante estudiante5 = new Estudiante(1387684618, "Nelson Mandela", 3);
        Estudiante estudiante6 = new Estudiante(1368764618, "Pepito Perez", 9);
        Estudiante estudiante7 = new Estudiante(1354646618, "Reinaldo Rueda", 7);
        

        MinHeap heap = new MinHeap(100000);
        heap.insert(estudiante1);
        heap.insert(estudiante2);
        heap.insert(estudiante3);
        heap.insert(estudiante4);
        heap.insert(estudiante5);
        heap.insert(estudiante6);
        heap.insert(estudiante7);

        heap.remove(estudiante5);

        heap.print();

        heap.changePriority(estudiante3, 1);
        heap.changePriority(estudiante2, 10);

        heap.print();

        for(int i=0;i<cupos && heap.getSize()>0; i++){
            
            Estudiante e = heap.extractMin();
            e.tieneApoyo = true;
            System.out.println(e.getID() + "-" + e.getNombre() + " - " + e.getPuntaje() + " -" + e.getApoyo());
            
        }
    }
}

