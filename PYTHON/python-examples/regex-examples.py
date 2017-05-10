import re

# re.match busca al principio de la cadena. Devuelve None
match= re.match(r'\d', 'a1234')
print(match)
if (match == None):
    print("No se encuentra número al principio de la cadena")

# re.search busca a lo largo de la cadena. el 1.
match= re.search(r'\d', 'a1234')
print(match)
if (match != None):
    print(f"encontrado {match.group()}")
    print(f"desde posición {match.pos} hasta {match.span()[1]}")

# re.findall devuelve una lista con todo lo que encuentra
matchList= re.findall('\d', '1234')
print("Se ha encontrado {} veces".format(len(matchList)))
for value in matchList:
    print(value)

# Se puede compilar una expresión regular para usarla varias veces.
pattern = re.compile(r"\d\d")
print (pattern.match("123"))
print (pattern.search("abc123"))
print (pattern.findall("12-34-567"))

print (re.search("hola","HOLA",re.IGNORECASE))

match = re.match(r"(\d+)/(\d+)/(\d+)", "11/12/2014")
print (match.group())
print (match.groups())
for value in match.groups():
    print(value)