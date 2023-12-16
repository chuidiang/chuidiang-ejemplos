# Chuidiang 25/11/2023
# Ejemplo sencillo de una ventana con un campo de texto y un boton
# Al pulsar el botón, se rellena el campo de texto.

import tkinter as tk


def set_text():
    campoTexto.delete(0, tk.END)
    campoTexto.insert(0, "Hola")
    # v.set("Hola")


def get_text():
    print(campoTexto.get())
    #print(v.get())


if __name__ == '__main__':
    rootWindow = tk.Tk()
    rootWindow.title("Main Window")

    v = tk.StringVar()
    campoTexto = tk.Entry(rootWindow)
    campoTexto.pack(side=tk.LEFT)

    buttonSet = tk.Button(rootWindow, text="hola", command=set_text)
    buttonSet.pack(side=tk.LEFT)

    buttonGet = tk.Button(rootWindow, text="Get", command=get_text)
    buttonGet.pack(side=tk.LEFT)

    rootWindow.mainloop()