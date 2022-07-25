def suma(a,b):
    return a+b

def resta(a,b):
    return a-b

def multiplica(a,b):
    return a*b

def divide(a,b):
    return a/b

def main():
    a = input ('Dime el primer sumando ')
    b = input ('Dime el segundo sumando ')
    print ('La suma es ', suma(int(a),int(b)))

if (__name__ == '__main__'):
    main()
