# Chuidiang 26/11/2023
# Ejemplo de ventana principal con un botón que abre una ventana secundaria.

import tkinter as tk


# Función para abrir la ventana secundaria
def open_secondary_window():
    global secondary_window
    secondary_window=tk.Toplevel(root)
    secondary_window.title("Ventana Secundaria")
    # Si queremos que sea modal
    # secondary_window.grab_set()

    # Una etiqueta por mostrar algo
    label = tk.Label(secondary_window, text="Esta es la ventana secundaria")
    label.pack(padx=20, pady=20)

    # centrado en pantalla
    # Obtener las dimensiones deseadas de la ventana secundaria
    ancho_ventana = secondary_window.winfo_reqwidth()
    alto_ventana = secondary_window.winfo_reqheight()

    # Calcular las coordenadas para centrar la ventana
    x = (root.winfo_screenwidth() - ancho_ventana) // 2
    y = (root.winfo_screenheight() - alto_ventana) // 2

    # Establecer la geometría de la ventana secundaria
    secondary_window.geometry(f"{ancho_ventana}x{alto_ventana}+{x}+{y}")


def oculta():
    secondary_window.withdraw()


def muestra():
    secondary_window.deiconify()


if __name__ == '__main__':
    # Ventana principal
    root = tk.Tk()
    root.title("Ventana Principal")

    # Botón para abrir la ventana secundaria
    button = tk.Button(root, text="Abrir Ventana Secundaria", command=open_secondary_window)
    button.pack(padx=20, pady=20)

    button = tk.Button(root, text="oculta", command=oculta)
    button.pack()

    button = tk.Button(root, text="muestra", command=muestra)
    button.pack()

    # Iniciar el bucle principal de la aplicación
    root.mainloop()

