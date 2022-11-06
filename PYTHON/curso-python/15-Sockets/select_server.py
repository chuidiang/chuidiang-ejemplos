"""
chuidiang 05 Nov 2022

Servidor socket usando select.
Para problarlo y conectar clientes, arrancar cliente.py.
"""


import socket
import select

if __name__ == '__main__':
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server.setblocking(False)
    server.bind(("", 8000))
    server.listen(1)

    inputs = [server]
    outputs = []
    output_messages = {}

    while True:
        readables, writables, exceptions = select.select(inputs, outputs, inputs)

        for socket in readables:
            if socket is server:
                print("Aceptado cliente")
                client, client_data = server.accept()
                client.setblocking(False)
                inputs.append(client)
            else:
                message = socket.recv(1024).decode()
                if "hola" == message:
                    output_messages[socket] = "Pues Hola"
                    outputs.append(socket)
                if "adios" == message:
                    output_messages[socket] = "Pues Adios"
                    outputs.append(socket)
                if "" == message:
                    inputs.remove(socket)
                    socket.close()

        for socket in writables:
            socket.send(output_messages[socket].encode())
            outputs.remove(socket)
