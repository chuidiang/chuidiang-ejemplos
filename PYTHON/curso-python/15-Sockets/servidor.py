"""
Created on 20/02/2009
@author: Chuidiang


Ejemplo de socket en python. Un servidor que acepta clientes.
Si el cliente envia "hola", el servidor contesta "pues hola".
Si el cliente envia "adios", el servidor contesta "pues adios" y
cierra la conexion.
El servidor no acepta multiples clientes simultaneamente.
"""

import socket

if __name__ == '__main__':
   # Se prepara el servidor
   server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
   server.bind(("", 8000))
   server.listen(1)
   print ("Esperando clientes...")
   
   # bucle para atender clientes
   while 1:
       
      # Se espera a un cliente
      socket_cliente, datos_cliente = server.accept()
      
      # Se escribe su informacion
      print ("conectado "+str(datos_cliente))
      
      # Bucle indefinido hasta que el cliente envie "adios"
      seguir = True
      while seguir:
         # Espera por datos
         peticion = socket_cliente.recv(1000)
         print ('Recibido '+str(peticion))
         
         # Contestacion a "hola"
         if ("hola"==peticion.decode()):
             print (str(datos_cliente)+ " envia hola: contesto")
             socket_cliente.send("pues hola".encode())
             
         # Contestacion y cierre a "adios"
         if ("adios"==peticion.decode()):
             print (str(datos_cliente)+ " envia adios: contesto y desconecto")
             socket_cliente.send("pues adios".encode())
             socket_cliente.close()
             print ("desconectado "+str(datos_cliente))
             seguir = False
