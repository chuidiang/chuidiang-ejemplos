import socket
import struct

if __name__ == '__main__':
    multicast_group = '224.3.29.71'

    # Tarjeta de red y puerto por el que queremos escuchar.
    # '' indica todas las tarjatas de red disponibles.
    server_address = ('', 5557)

    # Se crea el socket Datagram. Multicast es UDP, es decir Datagram
    with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as sock:

        # Para escuchar debemos unirnos (bind) a una tarjeta de red y puerto.
        sock.bind(server_address)

        # Indicamos a que direcciones queremos escuchar.
        group = socket.inet_aton(multicast_group)
        mreq = struct.pack('4sL', group, socket.INADDR_ANY)
        sock.setsockopt(socket.IPPROTO_IP, socket.IP_ADD_MEMBERSHIP, mreq)

        # Bucle para recibir datos.
        while True:
            data, address = sock.recvfrom(1024)
            print(address, data.decode())
