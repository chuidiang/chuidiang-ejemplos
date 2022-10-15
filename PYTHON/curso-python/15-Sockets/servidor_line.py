'''
Created on 15/10/2022
@author: Chuidiang

Socket servidor python con envio/lectura de lineas de texto.
'''

import socket


def do_hola():
    print(str(datos_cliente) + " envia hola: contesto")
    socket_file.write("pues hola\n")
    socket_file.flush()


def do_adios():
    global seguir
    print(str(datos_cliente) + " envia adios: contesto y desconecto")
    socket_file.write("pues adios\n")
    socket_file.flush()
    socket_cliente.close()
    print("desconectado " + str(datos_cliente))
    seguir = False


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
            with socket_cliente:
                # Se escribe su informacion
                print("conectado " + str(datos_cliente))

                # Bucle indefinido hasta que el cliente envie "adios"
                seguir = True
                # Se encapsula dentro de una variable de tipo fichero, que tiene
                # metodos de readLine()
                socket_file = socket_cliente.makefile("rw")
                while seguir:
                    # Espera por datos
                    line = socket_file.readline().rstrip('\n')
                    print('Recibido ' + line)

                    # Contestacion a "hola"
                    if ("hola" == line):
                        do_hola()

                    # Contestacion y cierre a "adios"
                    if ("adios" == line):
                        do_adios()
