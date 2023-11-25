# Chuidiang 25/11/2023
# Creación básica de una ventana en python

import tkinter as tk

if __name__ == '__main__':
    root = tk.Tk()
    root.title("Mi Primera Aplicación")

    label = tk.Label(root, text="¡Hola, Tkinter!")
    label.pack()

    root.mainloop()
