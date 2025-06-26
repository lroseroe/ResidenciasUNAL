package main;

import estructuras.MinHeap;

public class Main {
    public static void main(String[] args) {
        MinHeap<Integer> heap = new MinHeap<>(10);
        heap.insert(7);
        heap.insert(10);
        heap.insert(4);
        
        heap.print();
    }
}

