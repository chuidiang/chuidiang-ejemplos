"""
Created on 20/02/2009
@author: Chuidiang
Cliente socket. Abre conexión pero y envia cinco mensajes, luego deja de hacerlo
Para prueba de noblocking_server.py
"""
import socket
import time

if __name__ == '__main__':
    # Se establece la conexion
    counter = 0
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.connect(("localhost", 8000))

        while counter<5:
            # Espera de 2 segundos
            time.sleep(0.5)
            s.send("Message".encode())
            counter += 1

        time.sleep(10)
