package estructuras;

public class HeapSort{

    //De subarbol a heap
    public static void heapify(int arr[], int n, int i) {
        int largest = i;        // Raiz
        int left = 2 * i + 1;   // left = 2*i + 1
        int right = 2 * i + 2;  // right = 2*i + 2

        // Si el hijo izquierdo es mayor que la raíz
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // Si el hijo derecho es mayor que largest hasta ahora
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // Si largest no es la raíz
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursivamente..
            heapify(arr, n, largest);
        }
    }

    public static void heapSort(int arr[]) {
        int n = arr.length;

        // Construir heap (reorganizar array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extraer elementos del heap uno por uno
        for (int i = n - 1; i > 0; i--) {
            // Mover la raíz actual al final
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Llamar heapify en el heap reducido
            heapify(arr, i, 0);
        }
    }

    // Función para imprimir el array
    public static void printArray(int arr[]) {
        for (int i = 0; i < arr.length; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}