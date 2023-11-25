# Chuidiang 25/11/2023

import tkinter as tk


def on_button_click():
    label.config(text="¡Haz clic en el botón!")


if __name__ == '__main__':
    root = tk.Tk()
    root.title("Botón Tkinter")

    label = tk.Label(root, text="Presiona el botón")
    label.pack()

    button = tk.Button(root, text="Haz clic", command=on_button_click)
    button.pack()

    root.mainloop()
