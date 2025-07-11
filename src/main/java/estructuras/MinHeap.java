package estructuras;

//el llamado minHeap.insert(estudiante);

public class MinHeap{
    Estudiante[] puntajes;
    int maxSize;
    int size;
    
    public MinHeap(int maxSize){
        this.maxSize = maxSize;
        this.puntajes = new Estudiante[maxSize];
        size = 0;
    }

    public int getSize(){
        return size;
    }
    public int getPlaces(){
        return maxSize;
    }
    
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
        Estudiante temp = puntajes[i];
        puntajes[i] = puntajes[j];
        puntajes[j] = temp;
    }
    
    public void siftUp(int i){
        while(i > 0 && puntajes[parent(i)].compareTo(puntajes[i]) > 0){ //Si el padre es mayor, hay que hacer swap
           
            swap(parent(i), i);
            i = parent(i);
        }
    }
    
    public void siftDown(int i){
        int minIndex = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if(left < size && puntajes[left].compareTo(puntajes[minIndex]) < 0){
            minIndex = left;
        }
    
        if(right < size && puntajes[right].compareTo(puntajes[minIndex]) < 0){
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
        puntajes[size] = item;
        size++;
        siftUp(size-1);
    }

    public Estudiante extractMin(){
        if(size == 0){
            return null;
        }
        
        Estudiante min = puntajes[0];
        puntajes[0] = puntajes[size-1];
        size--;
        siftDown(0);
        return min;
    }
    
    public void remove(Estudiante estudiante){
        int i = findIndex(estudiante);
        if(i < 0 || i > size - 1){
            throw new RuntimeException("El estudiante no se encuentra registrado."); 
        }

        puntajes[i] = puntajes[0]; //Ponerlo de primero obligatoriamente
        siftUp(i);
        extractMin();
    }

    public boolean find(Estudiante estudiante){
        for(int i=0; i<size; i++){
            if(puntajes[i].equals(estudiante)){
                return true;
            }
        }
        
        return false;
    }

    public int findIndex(Estudiante estudiante){
        for(int i=0; i<size; i++){
            if(puntajes[i].equals(estudiante)){
                return i;
            }
        }

        return -1;
    }
    
    public void changePriority(Estudiante estudiante, int nuevoPuntaje){
        int index = -1;
        for(int i=0; i<size; i++){
            if(puntajes[i].equals(estudiante)){
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

    public void print(){
        System.out.println("Estudiantes en el sistema: ");
        for(int i = 0;i<size;i++){
            System.out.println(puntajes[i].getNombre() + " " + puntajes[i].getID() + " " + puntajes[i].getPuntaje() + " " + puntajes[i].getApoyo());
        }

        System.out.println();
    }
}


