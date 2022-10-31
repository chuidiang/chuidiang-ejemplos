"""
Created on 20/02/2009
@author: Chuidiang

Ejemplo de socket no bloqueante en python.
El servidor acepta clientes y lee datos. Si está un tiempo sin recibir datos,
cierra el socket y espera al suguiente cliente.
"""

import socket

if __name__ == '__main__':
    # Se prepara el servidor
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server.bind(("", 8000))
    server.listen(1)
    print("Esperando clientes...")

    # bucle para atender clientes
    while True:

        # Se espera a un cliente
        socket_cliente, datos_cliente = server.accept()

        # Se escribe su informacion
        print("conectado " + str(datos_cliente))

        # Hacemos que el socket sea no bloqueante
        # socket_cliente.setblocking(False)
        socket_cliente.settimeout(1)

        # Bucle indefinido hasta que el cliente envie "adios"
        seguir = True
        while seguir:
            # Espera por datos
            try:
                peticion = socket_cliente.recv(1000)
            except TimeoutError:
                print ("Timeout. Cerramos socket")
                seguir = False
            else:
                print('Recibido ' + str(peticion))