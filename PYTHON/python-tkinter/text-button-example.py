# Chuidiang 25/11/2023

import tkinter as tk


def funcion():
   v.set("Hola")


if __name__ == '__main__':
    frame = tk.Frame()

    v = tk.StringVar()
    campoTexto = tk.Entry(frame, textvariable=v)
    campoTexto.pack(side=tk.LEFT)

    boton = tk.Button(frame, text="hola", command=funcion)
    boton.pack(side=tk.LEFT)

    frame.pack()
    frame.mainloop()