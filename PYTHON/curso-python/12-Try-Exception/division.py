import traceback

class ApplicationError(Exception):
    pass

def divide(dividendo,divisor):
    try:
        cociente = dividendo/divisor
    except ZeroDivisionError as error:
        raise ApplicationError('funcion divide()') from error
    else:
        return cociente

try:
    resultado = divide(1,0)
except Exception as error:
    traceback.print_exc()
else:
    print ('1/0 es ', resultado)
