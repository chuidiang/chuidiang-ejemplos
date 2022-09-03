# Lectura de fichero de texto, sin asegurarse del cierre del mismo en caso
# de error.
f = open ('fichero.txt')

linea = f.readline()

while linea != '':
    print (linea, end='')
    linea = f.readline()

f.close()


# Lectura de fichero de texto, asegurando el cierre del mismo en caso
# de error.
with open('fichero.txt') as f:
    linea = f.readline()
    while linea != '':
        print(linea, end='')
        linea = f.readline()




