package estructuras;

//el llamado minHeap.insert(estudiante);

public class MinHeap{
    Estudiante[] estudiantesArray;
    int maxSize;
    int size;
    
    public MinHeap(int maxSize){
        this.maxSize = maxSize;
        this.estudiantesArray = new Estudiante[maxSize];
        size = 0;
    }

    public int getSize(){ return size; }
    public int getPlaces(){ return maxSize; }
    public int parent(int i){ return (i-1)/2; }
    public int leftChild(int i){ return 2*i + 1; }
    public int rightChild(int i){ return 2*i + 2; }
    

    public void swap(int i, int j){
        Estudiante temp = estudiantesArray[i];
        estudiantesArray[i] = estudiantesArray[j];
        estudiantesArray[j] = temp;
        
        estudiantesArray[i].setHeapIndex(i);
        estudiantesArray[j].setHeapIndex(j);
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
        estudiantesArray[size].setHeapIndex(size);
        siftUp(size);
        size++;
    }

    public Estudiante extractMin(){
        if(size == 0){
            return null;
        }
        
        while(size >0){
            Estudiante min = estudiantesArray[0];
            if(!min.isRemove()){
                swap(0, size-1);
                size--;
                siftDown(0);
                return min;
            }else{
                swap(0, size-1);
                size--;
                siftDown(0);
            }
        }

        return null;
    }
    
    public void remove(Estudiante estudiante){
        int i = estudiante.getHeapIndex();
        if(i < 0 || i > size - 1){
            throw new RuntimeException("El estudiante no se encuentra registrado."); 
        }

        estudiantesArray[i].setRemove();
    }
    
    /*
    public int findIndex(Estudiante estudiante){
        for(int i=0; i<size; i++){
            if(estudiantesArray[i].equals(estudiante)){
                return i;
            }
        }
        return -1;
    }
    */
    
    public void changePriority(Estudiante estudiante, int nuevoPuntaje){
        int index = estudiante.getHeapIndex();

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

    public Estudiante[] array(){
        Estudiante[] result = new Estudiante[size];
        System.arraycopy(estudiantesArray, 0, result, 0, size);
        return result;
    }
    
    public void printHeap(){
        for (int i = 0; i < size; ++i) {
            System.out.println(estudiantesArray[i].getNombre() + "--" + estudiantesArray[i].getID() + "--" + estudiantesArray[i].getPuntaje() +
                    "--" + estudiantesArray[i].getApoyo());
        }
        System.out.println();
    }

}


