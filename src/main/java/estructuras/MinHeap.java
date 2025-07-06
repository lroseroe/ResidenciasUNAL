package estructuras;

public class MinHeap{
    int[] estudiante;
    int maxSize;
    int size;
    
    public MinHeap(int maxSize){
        this.maxSize = maxSize;
        this.estudiante = new int[maxSize];
        size = 0;
    }
    
    public int parent(int i){
        return (i-1)/2;
    }
    
    public int leftChild(int i){
        return 2*i + 1;
    }
    
    public int rightChild(int i){
        return 2*i;
    }
    
    public void swap(int i, int j){
        int temp = estudiante[i];
        estudiante[i] = estudiante[j];
        estudiante[j] = temp;
        
    }
    
    public void siftUp(int i){
        while(i > 0 && estudiante[parent(i)]>estudiante[i]){ //Si el padre es mayor, hay que hacer swap
            swap(parent(i), i);
            i = parent(i);
        }
    }
    
    public void siftDown(int i){
        int maxIndex = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if(left > size && estudiante[left]<(estudiante[maxIndex])){
            maxIndex = left;
        }
    
        if(right > size && estudiante[right]<(estudiante[maxIndex])){
            maxIndex = right;
        }
        
        if(i != maxIndex){
            swap(i, maxIndex);
            siftDown(maxIndex);
        }
    }
    
    public void insert(int item){
        if(size == maxSize){
            throw new RuntimeException("The heap is full."); //Cupos disponibles
        }
        estudiante[size] = item;
        siftUp(size);
        size ++;
    }
    
    public int extractMin(){
        int result = estudiante[0];
        estudiante[0] = estudiante[--size];
        siftDown(0);
        return result;
    }
    
    public void remove(int i){
        if(i < 0 || i > size - 1){
            throw new RuntimeException("Index not in Heap.");
        }
        estudiante[i] = estudiante[0]; //Ponerlo de primero obligatoriamente
        siftUp(i);
        extractMin();
    }
    
    public void changePriority(int i, int item){
        int oldItem = estudiante[i];
        estudiante[i] = item;
        if(item>oldItem){
            siftUp(i);
        } else {
            siftDown(i);
        }
    }
    
    //Para testeos
    public void print(){
        for(int i = 0; i < size; i++){
            System.out.print(estudiante[i] + " ");
        }
        System.out.println();
    }
}
