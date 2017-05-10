cabecera = ('texto izquierda','texto derecha','texto centrado','numero')

valores = []

for valor in range(10):
    valores.append((f'texto {valor}',f'texto {valor}',f'texto {valor}', 53/(valor+1)))

print (f'{cabecera[0]:<20} |',f'{cabecera[1]:>20} |',f'{cabecera[2]:^20} |',f'{cabecera[3]:>20}')

for valor in valores:
    print (f'{valor[0]:<20} |',f'{valor[1]:>20} |',f'{valor[2]:^20} |',f'{valor[3]:20.4f}')