import socket
import time

if __name__ == '__main__':
    # Se abre el socket servidor
    with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as server:
        # Se le pone a la escucha
        server.setsockopt(socket.SOL_SOCKET, socket.SO_BROADCAST, 1)

        while True:
            # Envio de una info por broadcast
            server.sendto("Hola".encode(), ('<broadcast>', 8000))
            server.sendto("Hola".encode(), ('255.255.255.255', 8000))
            server.sendto("Hola".encode(), ('127.0.0.255', 8000))
            server.sendto("Hola".encode(), ('192.168.0.255', 8000))
            time.sleep(2)

