import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
import matplotlib.ticker as mtick


# Cargar el archivo CSV
df = pd.read_csv("resultados_hashmap.csv")  # Asegúrate de que el archivo esté en el mismo directorio o ajusta la ruta

# Configurar estilo de Seaborn
sns.set(style="whitegrid")

# Crear gráfico de líneas
plt.figure(figsize=(10, 6))
sns.lineplot(data=df, x="size", y="time_milliseconds", hue="operation", marker="o")

formatter = mtick.FuncFormatter(lambda x, _: f'{int(x):,}')
plt.gca().xaxis.set_major_formatter(formatter)
plt.gca().yaxis.set_major_formatter(formatter)

# Añadir etiquetas y título
plt.title("Tiempo de ejecución por tamaño y operación")
plt.xlabel("Tamaño (size)")
plt.ylabel("Tiempo (ms)")
plt.legend(title="Operación")

# Mostrar el gráfico
plt.tight_layout()
plt.show()
