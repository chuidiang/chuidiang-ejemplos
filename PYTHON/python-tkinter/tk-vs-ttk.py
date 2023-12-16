# Chuidiang 26/11/2023

import tkinter as tk
from tkinter import ttk

# Crear la ventana principal
root = tk.Tk()
root.title("Ejemplo de Estilos en ttk.Label")

# Definir un estilo para ttk.Label
style = ttk.Style()
style.configure("EstiloLabel.TLabel", font=("Helvetica", 14), foreground="blue", background="lightgray", padding=(10, 5))

# Crear una etiqueta ttk.Label y aplicar el estilo definido
etiqueta_ttk = ttk.Label(root, text="Etiqueta con Estilo", style="EstiloLabel.TLabel")
etiqueta_ttk.pack(padx=20, pady=20)

# Crear una etiqueta tk.Label sin el estilo personalizado
etiqueta_tk = tk.Label(root, text="Etiqueta sin Estilo", bg="lightgray", fg="blue", font=("Helvetica", 14), padx=10,
                       pady=5)
etiqueta_tk.pack(padx=20, pady=20)

# Iniciar el bucle principal de la aplicación
root.mainloop()
