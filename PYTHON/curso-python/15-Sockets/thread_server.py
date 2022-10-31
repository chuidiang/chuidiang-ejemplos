"""
Created on 31/10/2022
@author: Chuidiang

Ejemplo de sockect servidor TCP/IP en python con un hilo para
atender clientes.

"""

import threading
import socket


# Clase con el hilo para atender a los clientes.
# En el constructor recibe el socket con el cliente y los datos del
# cliente para escribir por pantalla
class Client(threading.Thread):
    def __init__(self, socket_cliente, datos_cliente):
        # LLamada al constructor padre, para que se inicialice de forma
        # correcta la clase Thread.
        threading.Thread.__init__(self)
        # Guardamos los parametros recibidos.
        self.socket = socket_cliente
        self.datos = datos_cliente

    # Bucle para atender al cliente.
    def run(self):
        # Bucle indefinido hasta que el cliente envie "adios"
        # Lo hacemos con with socket para que se cierre automaticamente cuando terminemos
        with self.socket:
            seguir = True
            while seguir:
                # Espera por datos
                peticion = self.socket.recv(1000).decode()

                # Si recibimos cadena vacía, el socket ha sido cerrado por el cliente
                if not peticion:
                    seguir = False

                # Contestacion a "hola"
                if ("hola" == peticion):
                    print
                    str(self.datos) + " envia hola: contesto"
                    self.socket.send("pues hola".encode())

                # Contestacion y cierre a "adios"
                if ("adios" == peticion):
                    print
                    str(self.datos) + " envia adios: contesto y desconecto"
                    self.socket.send("pues adios".encode())
                    self.socket.close()
                    print
                    "desconectado " + str(self.datos)
                    seguir = False


# Main que utiliza el hilo para antender clientes.
if __name__ == '__main__':
    # Se prepara el servidor
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server:
        server.bind(("", 8000))
        server.listen(1)
        print("Esperando clientes...")

        # bucle para atender clientes
        while 1:

            # Se espera a un cliente
            socket_cliente, datos_cliente = server.accept()
            # Se escribe su informacion
            print("conectado " + str(datos_cliente))
            # Se instancia la clase hilo y se lanza
            hilo = Client(socket_cliente, datos_cliente)
            # y se arranca el hilo
            hilo.start()
