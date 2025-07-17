package estructuras;

import java.util.Arrays;

public class HeapSort {
    Estudiante[] estudiantes;

    public HeapSort(Estudiante[] estudiantes){
        if(estudiantes == null || estudiantes.length == 0){
            throw new IllegalArgumentException("No hay estudiantes registrados para ordenar.");
        }
        this.estudiantes = estudiantes;
    }

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

    //HeapSort pero reiniciando el estado del apoyo de los estudiantes
    public void heapSort2(){

        estudiantes = Arrays.stream(estudiantes)
                      .filter(e -> e != null && !e.isRemove())
                      .toArray(Estudiante[]::new);
        
        int n = estudiantes.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(n, i);
        }

        // Extraer elementos del heap uno por uno
        for (int i = n - 1; i > 0; i--) {
            
            estudiantes[i].setApoyo(false); //Reiniciar el apoyo de los estudiantes
            // Mover la raíz actual al final
            Estudiante temp = estudiantes[0];
            estudiantes[0] = estudiantes[i];
            estudiantes[i] = temp;

            // Llamar heapify en el heap reducido
            heapify(i, 0);
        }

        //asignarCupos(x);
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

    public void asignarCupos(int n){
        int cuposAsignados = 0;
        for(int i =0; cuposAsignados < n && i < estudiantes.length; i++){
            if(estudiantes[i] != null && !estudiantes[i].getApoyo()){
                estudiantes[i].setApoyo(true);
                cuposAsignados++;
            }
        }
    }
}
