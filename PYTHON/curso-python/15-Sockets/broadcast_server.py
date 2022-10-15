import socket

if __name__ == '__main__':
    # Se abre el socket servidor
    with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as server:
        # Se le pone a la escucha
        server.setsockopt(socket.SOL_SOCKET, socket.SO_BROADCAST, 1)
        server.bind(("", 8000))

        while True:
            # Se espera un mensaje de cliente
            message, client = server.recvfrom(1000)
            # Se imprime
            print(str(client))
            print(message.decode())
            print("-------")
            # Se le envía respuesta
            server.sendto("Mundo".encode(), client)
