"""
Un mega comentario

- substring
- split
- funcion str - to string
- find - find substring
- replace
- length
- concatenar
- format float - format

- comparar cadenas
- recorrer cadena
 -contar caracteres
 - buscar/extraer subcadena
 - contar palabras
 - caracteres especiales
"""

# Declaraciones de cadena
cadena = "Hola Mundo"
cadena2 = 'Otro Hola Mundo '
cadena3 = """En un lugar
de la Mancha
de cuyo nombre
no quiero acordarme"""
print(cadena3)
print (len(cadena))

# raw string. No interpreta caracteres especiales.
cadena4 = r"dos\nlineas? ... No"
print(cadena4)

# Recorrer la cadena
cadena = "Hola Mundo"
for letra in cadena:
    print(letra)
print (cadena[3])
print (cadena[-1])


# Extraer substring
print ("Substrings")
print (cadena[1:3])
print (cadena[:3])
print (cadena[4:])
print (cadena[-4:-2])
print (cadena[-4:])
print (cadena[:-2])

# Buscar substring
print ("a" in cadena)
print ("undo" in cadena)
print ("ab" in cadena)

print(cadena.find("Hola"))
print(cadena.find("Mundo"))

print(cadena.find("Hola", 3))
print(cadena.find("Mundo", 3))

print(cadena.find("Hola", 0, 5))
print(cadena.find("Mundo", 0, 5))

cadena = "un uno, un dos, un tres"
print (cadena.find("un"))
print (cadena.rfind("un"))

print (cadena.replace("un", "XXX"))
print (cadena.replace("un", "XXX", 2))

print (cadena.count("un"))
print (cadena.count("un",10))
print (cadena.count("un",0,10))
