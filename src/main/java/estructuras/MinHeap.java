package estructuras;

//el llamado minHeap.insert(estudiante);

import java.util.HashMap;
import java.util.Map;
import residenciasunalhash.Estudiante;


public class MinHeap{
    Estudiante[] estudiantesArray;
    int maxSize;
    int size;
    private final Map<Estudiante, Integer> indexMap = new HashMap<>(); //Mapa auxiliar para los indices
    
    public MinHeap(int maxSize){
        this.maxSize = maxSize;
        this.estudiantesArray = new Estudiante[maxSize];
        size = 0;
        
    }

    public Estudiante[] getEstudiantesArray() { return estudiantesArray; }
    public int getSize(){ return size; }
    public int getPlaces(){ return maxSize; }
    public int parent(int i){ return (i-1)/2; }
    public int leftChild(int i){ return 2*i + 1; }
    public int rightChild(int i){ return 2*i + 2; }
    

    public void swap(int i, int j){
        Estudiante temp = estudiantesArray[i];
        estudiantesArray[i] = estudiantesArray[j];
        estudiantesArray[j] = temp;
        
        indexMap.put(estudiantesArray[i], i);
        indexMap.put(estudiantesArray[j], j);
    }
    
    public void siftUp(int i){
        while(i > 0 && estudiantesArray[parent(i)].compareTo(estudiantesArray[i]) > 0){ //Si el padre es mayor, hay que hacer swap
            swap(parent(i), i);
            i = parent(i);
        }
    }
    
    public void siftDown(int i){
        int minIndex = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if(left < size && estudiantesArray[left].compareTo(estudiantesArray[minIndex]) < 0){
            minIndex = left;
        }
    
        if(right < size && estudiantesArray[right].compareTo(estudiantesArray[minIndex]) < 0){
            minIndex = right;
        }
        
        if(i != minIndex){
            swap(i, minIndex);
            siftDown(minIndex);
        }
    }
    
    public void insert(Estudiante item){
        if(size == maxSize){
            return;
            //throw new RuntimeException("No existen más cupos disponibles."); //Cupos disponibles
        }
        estudiantesArray[size] = item;
        indexMap.put(item, size);
        siftUp(size);
        size++;
    }

    public Estudiante extractMin(){
        if(size == 0){
            return null;
        }
        
        if (size == 0) return null;
        Estudiante min = estudiantesArray[0];
        remove(min); 
        return min;
    }
    
    
    public void remove(Estudiante estudiante){
        int i = indexMap.get(estudiante);
        if(i < 0 || i > size - 1 || estudiantesArray[i] != estudiante){
            throw new RuntimeException("El estudiante no se encuentra registrado."); 
        }
        
        swap(i, size - 1);
        indexMap.remove(estudiantesArray[size - 1]);
        estudiantesArray[size - 1].setRemove();
        estudiantesArray[size - 1] = null;
        size--;

        if (i < size) {
            siftUp(i);
            siftDown(i);
        }
    }
    
    public void changePriority(Estudiante estudiante, int nuevoPuntaje){
        int index = indexMap.get(estudiante);

        if(index == -1){
            throw new RuntimeException("El estudiante no se encuentra en el sistema");
        }

        int viejoPuntaje = estudiante.getPuntaje();
        estudiante.setPuntaje(nuevoPuntaje);

        if(nuevoPuntaje<viejoPuntaje){
            siftUp(index);
        } else {
            siftDown(index);
        }
    }

    
    public void printHeap(){
        for (int i = 0; i < size; ++i) {
            System.out.println(estudiantesArray[i].getNombre() + "--" + estudiantesArray[i].getID() + "--" + estudiantesArray[i].getPuntaje() +
                    "--" + estudiantesArray[i].getApoyo());
        }
        System.out.println();
    }

}


