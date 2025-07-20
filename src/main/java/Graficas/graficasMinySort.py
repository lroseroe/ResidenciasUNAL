import matplotlib.pyplot as plt

# Tamaños de prueba
sizes = [10, 100, 1000, 10000, 100000,1000000]

# MinHeap: tiempos en milisegundos
minheap_insert = [0, 0, 0, 0, 5,15]
minheap_change_priority = [0, 0, 0, 1, 1,6]
minheap_remove = [0, 0, 0, 1, 1,27]

# HeapSort: tiempos en milisegundos
heapsort = [0, 1, 24, 570, 72082, 21721003]
asignar_cupos = [0, 1, 11, 54, 5892, 568242]

# === Gráfica 1: MinHeap ===
plt.figure(figsize=(10, 6))
plt.plot(sizes, minheap_insert, marker='o', label='Insert')
plt.plot(sizes, minheap_change_priority, marker='o', label='Change Priority')
plt.plot(sizes, minheap_remove, marker='o', label='Remove')

plt.title("Tiempos de ejecución MinHeap")
plt.xlabel("Tamaño de prueba (n)")
plt.ylabel("Tiempo (ms)")
plt.legend()
plt.grid(True)
plt.xscale("log")  # Escala logarítmica para ver mejor el crecimiento
plt.yscale("log")
plt.xticks(sizes, labels=sizes)
plt.tight_layout()
#plt.show()

# === Gráfica 2: HeapSort ===
plt.figure(figsize=(10, 6))
plt.plot(sizes, heapsort, marker='o', label='HeapSort')
plt.plot(sizes, asignar_cupos, marker='o', label='AsignarCupos')

plt.title("Tiempos de ejecución HeapSort")
plt.xlabel("Tamaño de prueba (n)")
plt.ylabel("Tiempo (ms)")
plt.legend()
plt.grid(True)
plt.xscale("log")
plt.yscale("log")
plt.xticks(sizes, labels=sizes)
plt.tight_layout()
plt.show()
