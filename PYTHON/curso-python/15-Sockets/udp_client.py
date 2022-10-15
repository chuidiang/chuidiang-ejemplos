import socket

if __name__ == '__main__':
    # Direccion y puerto del servidor
    serverAddress = ('127.0.0.1', 8000)
    # Se abre el socket UDP (SOCK_DGRAM)
    with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as client:
        # Se envia "Hola".
        client.sendto("Hola".encode(), serverAddress)
        # Se espera respuesta
        message, server = client.recvfrom(1000)
        # Se imprime la respuesta
        print(str(server))
        print(message.decode())
