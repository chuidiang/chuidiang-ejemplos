print("hola")

# list
for a in [1,2,3]:
    print(a)

# range
print ("range")
for a in range(3):
    print(a)

# more elaborated range
print ("range with start, end and step")
for a in range(4,10,2):
    print(a)

# tupla
for a in (1,2,3):
    print(a)

# string
for a in "hola":
    print(a)

# dict. It prints de keys
for a in {"one":1, "two":2, "three":3}:
    print(a)

# dict. It prints key and value
for a,b in {"one":1, "two":2, "three":3}.items():
    print("key={}, value={}".format(a,b))

# file
file = open("for-examples.py","r")

for line in file:
    print (line, end="")

file.close()

for a in (1,3,5,3,5,7,6,4,2):
    if (a%2==0):
        break
    print(a)

print ("Even")
for a in (1,3,5,3,5,7,6,4,2):
    if (a%2==0):
        continue
    print(a)