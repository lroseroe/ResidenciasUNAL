package estructuras;

import java.util.Arrays;
import main.MainPanel;
import residenciasunalhash.Estudiante;

public class HeapSort {
    Estudiante[] estudiantes;
    MainPanel panel;

    public HeapSort(Estudiante[] estudiantes, MainPanel panel){
        this.panel = panel;
        if(estudiantes == null || estudiantes.length == 0){
            throw new IllegalArgumentException("No hay estudiantes registrados para ordenar.");
        }
        this.estudiantes = estudiantes;
    }

    public Estudiante[] getEstudiantes() { return estudiantes; }
    public void setEstudiantes(Estudiante[] estudiantes) { this.estudiantes = estudiantes; }
    
    

    public void heapSort(){
        estudiantes = Arrays.stream(estudiantes)
                      .filter(e -> e != null && !e.isRemove())
                      .toArray(Estudiante[]::new);
        
        int n = estudiantes.length;
        
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(n, i); 
        }


        // Extraer elementos del heap uno por uno
        for (int i = n - 1; i > 0; i--) {
            // Mover la raíz actual al final
            Estudiante temp = estudiantes[0];
            estudiantes[0] = estudiantes[i];
            estudiantes[i] = temp;

            // Llamar heapify en el heap reducido
            heapify(i, 0);
        }
    }

    public void heapify(int n, int i) {
        int largest = i;        //largest como raiz
        int left = 2 * i + 1;   
        int right = 2 * i + 2;  

        // Si el hijo izquierdo es mayor que la raíz
        if (left < n && estudiantes[left].getPuntaje() > estudiantes[largest].getPuntaje()) {
            largest = left;
        }

        // Si el hijo derecho es mayor que largest hasta ahora
        if (right < n && estudiantes[right].getPuntaje() > estudiantes[largest].getPuntaje()) {
            largest = right;
        }

        // Si largest no es la raíz
        if (largest != i) {
            Estudiante swap = estudiantes[i];
            estudiantes[i] = estudiantes[largest];
            estudiantes[largest] = swap;

            heapify(n, largest);
        }
    }

    public void printEstudiantes() {
        for (int i = 0; i < estudiantes.length; ++i) {
            System.out.println(estudiantes[i].getNombre() + "--" + estudiantes[i].getID() + "--" + estudiantes[i].getPuntaje() + "--" + estudiantes[i].getApoyo());
        }
        System.out.println();
    }

    /*
    Sobre la asignación. Los cupos una vez se asignan son inmutables, si se agrega
    un nuevo estudiante, sin importar su puntaje debe esperar hasta que se asignen más cupos; sin embargo, 
    si por algún motivo se reduce la cantidad de cupos, se empiezan a "quitar" los cupos de 
    acuerdo a la prioridad
    */
    
    public void asignarCupos(int n){ //n es el total de residencias
        int actualTakenResidences = 0;
        for (Estudiante est : estudiantes) {
            if (est != null && !est.isRemove() && est.getApoyo()) {
                actualTakenResidences++;
            }
        }
        
        panel.takenResidences = actualTakenResidences; //Para evitar errores
        
        if(n > actualTakenResidences){ 
            int cuposPorAsignar = n - actualTakenResidences; //Cuando hay cupos disponibles
            for(int i = 0; cuposPorAsignar > 0 && i < estudiantes.length; i++){
                if(estudiantes[i] != null && !estudiantes[i].isRemove() && !estudiantes[i].getApoyo()){
                    estudiantes[i].setApoyo(true);
                    cuposPorAsignar --;
                    panel.takenResidences ++;
                }
            }
        } else if(n < actualTakenResidences){ 
            int cuposPorQuitar = actualTakenResidences - n; //Cuando se reduce el número de cupos
            for(int i = estudiantes.length - 1; cuposPorQuitar > 0 && i >= 0; i--){
                if(estudiantes[i] != null && !estudiantes[i].isRemove() && estudiantes[i].getApoyo()){
                    estudiantes[i].setApoyo(false);
                    cuposPorQuitar --;
                    panel.takenResidences --;
                }
            }
        }
        
        //Si el total es igual a actualTakenResidences, no se hace nada
        panel.availableResidences = panel.totalResidences - panel.takenResidences;
        
    }
}
