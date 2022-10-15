"""
Created on 15/10/2022
@author: Chuidiang
Cliente sockets para envio recepcion de lineas
"""
import socket
import time

if __name__ == '__main__':
    # Se establece la conexion
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.connect(("localhost", 8000))

        # Se encapsula en una variable de tipo fichero que
        # tiene metodos de read line y write line.
        s_file = s.makefile("rw")

        # Se envia "hola"
        s_file.write("hola\n")
        s_file.flush()

        # Se recibe la respuesta y se escribe en pantalla
        line = s_file.readline().rstrip('\n')
        print(line)

        # Espera de 2 segundos
        time.sleep(2)

        # Se envia "adios"
        s_file.write("adios\n")
        s_file.flush()

        # Se espera respuesta, se escribe en pantalla y se cierra la
        # conexion
        line = s_file.readline().rstrip('\n')
        print(line)
