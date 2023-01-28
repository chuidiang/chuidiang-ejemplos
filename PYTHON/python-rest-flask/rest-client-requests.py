# Chuidiang 22/01/2023
# Ejemplo de cliente que llama a web service REST. Para probar este programa
# debe estar arrancado rest-flask.py

import json
import requests


# Saca por pantalla el listado de todos los usuarios
def do_get():
    print("--GET--")
    result = requests.get("http://127.0.0.1:5000/users")

    if 200 == result.status_code:
        print(result.status_code)
        print(result.content)
        user = result.json()
        print(user)
        print(user[1]['name'])
    else:
        print("Ha habido algun error en la llamada")
        print(result.status_code)


# Saca por pantalla el usuario de índice 1 (Pedro)
def do_get_idx():
    print("--GET IDX--")
    result = requests.get("http://127.0.0.1:5000/users/1")
    if 200 == result.status_code:
        user = result.json()
        print(user)
    else:
        print("Ha habido algun error en la llamada")
        print(result.status_code)


# Crea un usuario nuevo (Ana)
def do_post():
    print("-- POST --")
    new_user = '{"name": "Ana", "age": 44}'
    headers = {"Content-Type": "application/json"}
    result = requests.post("http://127.0.0.1:5000/users", data=new_user, headers=headers)

    if 201 == result.status_code:
        user = result.json()
        print(user)
        do_get()
    else:
        print("Ha habido algun error en la llamada")
        print(result.status_code)


# Modifica la edad del usuario 1 (Pedro)
def do_put():
    print("-- PUT --")
    new_user = '{"name": "Pedro", "age": 23}'
    headers = {"Content-Type": "application/json"}
    result = requests.put("http://127.0.0.1:5000/users/1", data=new_user, headers=headers)

    if 200 == result.status_code:
        user = result.json()
        print(user)
        do_get()
    else:
        print("Ha habido algun error en la llamada")
        print(result.status_code)


# Borra el usuario 3 (Ana)
def do_delete():
    print("-- DELETE --")
    result = requests.delete("http://127.0.0.1:5000/users/3")
    if 204 == result.status_code:
        print("Usuario borrado con exito")
        do_get()
    else:
        print("Ha habido algun error en la llamada")
        print(result.status_code)


# Main que llama a todas las funciones anteriores en orden.
if __name__ == '__main__':
    do_get()
    print("---------------")
    do_get_idx()
    print("---------------")
    do_post()
    print("---------------")
    do_put()
    print("---------------")
    do_delete()
