package estructuras;

//Aqui debemos poner en la funcion de registarr estudiante el llamado
//el llamado minHeap.insert(estudiante);

public class MinHeap<T extends Comparable<T>>{
    T[] puntajes;
    int maxSize;
    int size;
    
    //Funcion cambiar cupos copiando los puntajes a un nuevo heap
    public void newPlaces(int cupos){
        MinHeap<T> nuevoHeap = new MinHeap<>(cupos);
        for(int i=0; i<size;i++){
            nuevoHeap.insert(puntajes[i]);
        }
    }

    public int getPlaces(){
        return maxSize;
    }

    //Funciones Internas
    public MinHeap(int maxSize){
        this.maxSize = maxSize;
        this.puntajes = (T[])new Comparable[maxSize];
        size = 0;
    }
    
    //Funciones de claves
    public int parent(int i){
        return (i-1)/2;
    }
    
    public int leftChild(int i){
        return 2*i + 1;
    }
    
    public int rightChild(int i){
        return 2*i + 2;
    }
    
    public void swap(int i, int j){
        T temp = puntajes[i];
        puntajes[i] = puntajes[j];
        puntajes[j] = temp;
    }
    
    public void siftUp(int i){
        while(i > 0 && puntajes[parent(i)].compareTo(puntajes[i])<0){ //Si el padre es mayor, hay que hacer swap
            swap(parent(i), i);
            i = parent(i);
        }
    }
    
    public void siftDown(int i){
        int minIndex = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if(left < size && puntajes[left].compareTo(puntajes[minIndex])<0){
            minIndex = left;
        }
    
        if(right < size && puntajes[right].compareTo(puntajes[minIndex])<0){
            minIndex = right;
        }
        
        if(i != minIndex){
            swap(i, minIndex);
            siftDown(minIndex);
        }
    }

    public T extractMin(){
        T result = puntajes[0];
        puntajes[0] = puntajes[--size];
        siftDown(0);
        return result;
    }
    
    //Funciones Publicas
    public void insert(T item){
        if(size == maxSize){
            throw new RuntimeException("No existen más cupos disponibles."); //Cupos disponibles
        }
        puntajes[size] = item;
        siftUp(size);
        size ++;
    }
    
    public void remove(Estudiante i){
        if(i.puntaje < 0 || i.puntaje > size - 1){
            throw new RuntimeException("El estudiante no se encuentra registrado."); //Problema deberia ser con el id 
        }
        puntajes[i.puntaje] = puntajes[0]; //Ponerlo de primero obligatoriamente
        siftUp(i.puntaje);
        extractMin();
    }

    public boolean find(Estudiante estudiante){
        for(int i=0; i<size; i++){
            if(puntajes[i].equals(estudiante.puntaje)){
                return true;
            }
        }
        
        return false;
    }
    
    public void changePriority(Estudiante estudiante, int nuevoPuntaje){
        int index = -1;
        for(int i=0; i<size; i++){
            if(puntajes[i].equals(estudiante.puntaje)){
                index = i;
                break;
            }
        }
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
}


