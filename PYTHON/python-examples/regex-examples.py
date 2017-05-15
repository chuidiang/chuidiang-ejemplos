import re

# re.match busca al principio de la cadena. Devuelve None
match= re.match(r'\d', 'a1234')
print(match)
if (match == None):
    print("No se encuentra número al principio de la cadena")

# re.search busca al principio de la cadena. Encuentra el 1.
match= re.match('\\d', '1234')
print(match)
if (match != None):
    print(f"encontrado {match.group()}")
    print(f"desde posición {match.start()} hasta {match.end()}")


# re.search busca a lo largo de la cadena. el 1.
match= re.search(r'\d', 'a1234')
print(match)
if (match != None):
    print(f"encontrado {match.group()}")
    print(f"desde posición {match.start()} hasta {match.end()}")

# re.findall devuelve una lista con todo lo que encuentra
matchList= re.findall(r'\d', '1234')
print(matchList)
print("Se ha encontrado {} veces".format(len(matchList)))
for value in matchList:
    print(value)

# r.findall con grupos
match = re.findall(r'(\d+)/(\d+)/(\d+)','11/12/2017 5/3/2014 9/1/1994')
print(match)
for value in match:
    print(f'dia={value[0]}, mes={value[1]}, año={value[2]}')

# Se puede compilar una expresión regular para usarla varias veces.
pattern = re.compile(r"\d\d")
match = pattern.match("123")
print(f'pattern.match() = {match.group()}')
match = pattern.search("abc123")
print(f'pattern.search() = {match.group()}')
matchList = pattern.findall("12-34-567")
for value in matchList:
    print(f'pattern.findall() = {value}')

# Ignore Case
print (re.search("hola","HOLA",re.IGNORECASE))

match = re.match(r"(\d+)/(\d+)/(\d+)", "11/12/2014")
print (match.group())
print (match.groups())
for value in match.groups():
    print(value)