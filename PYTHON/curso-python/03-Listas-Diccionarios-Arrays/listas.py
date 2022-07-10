# Lista vacia
print('Lista vacia')
lista = []
print (lista)

# Lista con elementos
print('Lista con elementos')
lista = ['a',1,True]
print (lista)

# Acceder a elementos de la lista y modificarlo
print('Acceder a elementos de la lista')
print (lista[1])
lista[1] = 'modificado'
print (lista[1])


# Acceder a rangos de la lista
print('Acceder a rangos de la lista')
lista=[1,2,3,4]
print(lista[1:3])
lista[1:3] = ['modificado','alterado']
print(lista)
a,b=lista[1:3]
print(a)
print(b)

lista=[1,2,3,4]
lista[1:3]=[9,9,9,9,9]
print(lista)

# Anadir elemento al final
lista.append('Hola')
print (lista)

# Anadir elemento en una posicion intermedia
print('Anadir elemento en posicion intermedia')
lista=[1,2,4,5]
lista.insert(2,3) 
print(lista)

# Borrar elemento por valor
lista=['Hola','Adios','Buenas','Hasta la vista']
lista.remove('Hola')
print(lista)

# Borrar elemento por posicion
lista.pop(2)
print(lista)

# Concatenar Listas
lista1=[1,2,3]
lista2=['a','b','c']
print(lista1+lista2)

# Repetir la lista n veces
print(lista1 *3)

# Ordenar lista
lista=[3,3,2,1]
lista.sort()
print(lista)


