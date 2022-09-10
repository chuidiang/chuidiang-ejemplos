with open('fichero.txt') as origen:
    with open('copia.txt','w') as destino:
        linea = origen.readline()
        while linea:
            destino.write(linea)
            linea = origen.readline()

