'''
Created on 20/02/2009
@author: Chuidiang
Ejemplo de cliente de socket.
Establece conexion con el servidor, envia "hola", recibe y escribe la
respuesta, espera 2 segundos, envia "adios", recibe y escribe la respuesta
y cierrra la conexion
'''
import socket
import time

if __name__ == '__main__':
    # Se establece la conexion
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect(("localhost", 8000))
    
    # Se envia "hola"
    s.send("hola".encode())
    
    # Se recibe la respuesta y se escribe en pantalla
    datos = s.recv(1000)
    print (datos.decode())
    
    # Espera de 2 segundos
    time.sleep(2)
    
    # Se envia "adios"
    s.send("adios".encode())
    
    # Se espera respuesta, se escribe en pantalla y se cierra la
    # conexion
    datos = s.recv(1000)
    print (datos.decode())
    s.close()
