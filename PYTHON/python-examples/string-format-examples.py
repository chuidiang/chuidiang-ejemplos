import math

print ( "%d" % 11)

print ("Los valores son %d y %d" % (11,33))

print ("Entero con 5 cifras, espacios por delante %5d" % 11)
print ("Entero con 5 cifras, ceros por delante %05d" % 11)
print ("Flotante con 2 decimales %.2f" % 12.34567)
print ("Flotante con 2 decimales, diez cifras, espacios por delante %10.2f" % 12.34567)
print ("Flotante con 2 decimales, diez cifras, ceros por delante %010.2f" % 12.34567)


print ("El valor es {}".format(12))
print ("El valor es {}".format(12.3456))
print ("Los valores son {}, {} y {}".format(1,2,3))
print ("Los valores son {2}, {1} y {0}".format(1,2,3))
print ("{pepe} y {juan}".format(juan=1, pepe=2))

print ("Valor formateado {:10.2f}".format(12.34567))
print ("pi = {pi:6.4f} y e = {e:6.4f}".format(pi=math.pi, e=math.e))

theDict = {'one':1, 'two':2, 'three':3}
print ("el dict[two] es {[two]}".format(theDict))
print ("el dict[two] es {0[two]}".format(theDict))
print ("el dict[two] es {someName[two]}".format(someName=theDict))

valor=12.3456
print (f"El valor es {valor}")
print ( f"El valor es {valor:10.2f}")
print ( f"el dict[two] es {theDict['two']}")