import socket
import time

if __name__ == '__main__':
    message = 'Hello World'
    multicast_group = ('224.3.29.71', 5557)

    # Crea socket Datagram. Los multicast son UDP, es decir, Datagram.
    with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as sock:

        # Un mensaje multicast se queda normalmente en la red local, no salta a otras
        # redes a traves de los routers. Cambiando esta opcion, TTL (Time To Live)
        # podemos indicar cuantos routers queremos que atraviese.

        # sock.setsockopt(socket.IPPROTO_IP, socket.IP_MULTICAST_TTL, 1)

        while True:
            sock.sendto(message.encode(), multicast_group)
            time.sleep(1)
