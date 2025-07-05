package estructuras;

public class MinHeap<T extends Comparable<T>>{
    T[] array;
    int maxSize;
    int size;
    
    public MinHeap(int maxSize){
        this. maxSize = maxSize;
        array = (T[]) new Comparable[maxSize];
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
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        
    }
    
    public void siftUp(int i){
        while(i > 0 && array[parent(i)].compareTo(array[i]) > 0){ //Si el padre es mayor, hay que hacer swap
            swap(parent(i), i);
            i = parent(i);
        }
    }
    
    public void siftDown(int i){
        int maxIndex = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if(left < size && array[left].compareTo(array[maxIndex]) < 0){
            maxIndex = left;
        }
    
        if(right < size && array[right].compareTo(array[maxIndex]) < 0){
            maxIndex = right;
        }
        
        if(i != maxIndex){
            swap(i, maxIndex);
            siftDown(maxIndex);
        }
    }
    
    public void insert(T item){
        if(size == maxSize){
            throw new RuntimeException("The heap is full."); //Cupos disponibles
        }
        array[size] = item;
        siftUp(size);
        size ++;
    }
    
    public T extractMin(){
        T result = array[0];
        array[0] = array[--size];
        siftDown(0);
        return result;
    }
    
    public void remove(int i){
        if(i < 0 || i > size - 1){
            throw new RuntimeException("Index not in Heap.");
        }
        array[i] = array[0]; //Ponerlo de primero obligatoriamente
        siftUp(i);
        extractMin();
    }
    
    public void changePriority(int i, T item){
        T oldItem = array[i];
        array[i] = item;
        if(item.compareTo(oldItem) > 0){
            siftUp(i);
        } else {
            siftDown(i);
        }
    }
    
    //Para testeos
    public void print(){
        for(int i = 0; i < size; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
