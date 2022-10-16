import socket

if __name__ == '__main__':
    # Se abre el socket UDP (SOCK_DGRAM)
    with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as client:
        client.bind(("", 8000))
        while True:
            message, server = client.recvfrom(1000)
            # Se imprime la respuesta
            print(str(server))
            print(message.decode())
