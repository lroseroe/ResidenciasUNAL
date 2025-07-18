package main;

import estructuras.HeapSort;
import estructuras.MinHeap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import residenciasunalhash.Estudiante;
import residenciasunalhash.ResidenciasUnalHash;

public class SystemControl {
    MainPanel panel;
    
    public boolean arrayOrdenado;
    public boolean needToReasign = false;
    int MAX_SIZE;
    
    ResidenciasUnalHash hashMap;
    MinHeap heap;
    HeapSort heapSort;
    
    public SystemControl(int capacidad_max, MainPanel panel){
        this.panel = panel;
        
        MAX_SIZE = capacidad_max;
        arrayOrdenado = true;
        
        hashMap = new ResidenciasUnalHash();
        heap = new MinHeap(MAX_SIZE);
        heapSort = new HeapSort(heap.getEstudiantesArray(), panel);
        
        //Iniciar datos
        cargarDesdeCSV("estudiantes");
    }
    
    
    
    public void cambiarNumCupos(int num){
        if(num <= MAX_SIZE){
            panel.totalResidences = num;
            panel.availableResidences = panel.totalResidences - panel.takenResidences; //Actualizar variable
            if(panel.availableResidences < 0){
                panel.availableResidences = 0;
            }
        }
    }
    
    public boolean insertarEstudiante(long id, String nombre, int puntaje){
        boolean insertado = hashMap.registrarUsuario(id, nombre, puntaje);
        //Verificar que el estudiante no este en el sistema con el hash y proceder a insertar en heap
        if(insertado){
            heap.insert(hashMap.buscarUsuario(id));
        }
        
        arrayOrdenado = false;
        return insertado;
    }
    
    public boolean eliminarEstudiante(long id){
        Estudiante estudiante = buscarEstudiante(id);
        boolean eliminado = hashMap.borrarUsuario(id);
        
        if(eliminado){
            heap.remove(estudiante);
        } 
        
        arrayOrdenado = false;
        return eliminado;
    }
    
    public Estudiante buscarEstudiante(long id){
        return hashMap.buscarUsuario(id);
    }
    
    public void actualizarPuntaje(long id, int puntaje){
        boolean puntajeCambiado = hashMap.cambiarPuntaje(id, puntaje);
        if(puntajeCambiado){
            Estudiante estudiante = buscarEstudiante(id);
            heap.changePriority(estudiante, puntaje);
        }  
    }
    
    public void ordenarEstudiantes(){
        if(!arrayOrdenado){
            heapSort.setEstudiantes(heap.getEstudiantesArray()); //Actualizar el array por si hubo cambios en el heap
            heapSort.heapSort();
            heapSort.printEstudiantes();
        }
    }
    
    public void asignarCupos(){
        ordenarEstudiantes();
        heapSort.asignarCupos(panel.totalResidences);
    }
    
    // Importar estudiantes desde un archivo CSV
    public void cargarDesdeCSV(String nombreArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                Estudiante est = Estudiante.fromCSV(linea);
                hashMap.usuarios.put(est.getID(), est);
                heap.insert(est);
            }
            System.out.println("Datos cargados desde: " + nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
