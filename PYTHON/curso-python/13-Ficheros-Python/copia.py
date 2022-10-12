with open('fichero.txt') as origen:
    with open('copia.txt','w') as destino:
        linea = origen.readline()
        while linea:
            destino.write(linea)
            linea = origen.readline()


with open('fichero.txt','rb') as origen:
    with open('copia2.txt','wb') as destino:
        for byte in origen: 
            destino.write(byte)
