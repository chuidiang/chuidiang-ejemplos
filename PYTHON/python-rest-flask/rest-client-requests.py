# Chuidiang 22/01/2023
# Ejemplo de cliente que llama a web service REST. Para probar este programa
# debe estar arrancado rest-flask.py

import requests, json


# Saca por pantalla el listado de todos los usuarios
def do_get():
    print("--GET--")
    result = requests.get("http://127.0.0.1:5000/users")
    print(result.content)

    decoder = json.JSONDecoder()
    user = decoder.decode(result.content.decode())

    print(user)


# Saca por pantalla el usuario de índice 1 (Pedro)
def do_get_idx():
    print("--GET IDX--")
    result = requests.get("http://127.0.0.1:5000/users/1")
    print(result.status_code)

    decoder = json.JSONDecoder()
    user = decoder.decode(result.content.decode())

    print(user)


# Crea un usuario nuevo (Ana)
def do_post():
    print("-- POST --")
    new_user = '{"name": "Ana", "age": 44}'
    result = requests.post("http://127.0.0.1:5000/users", data=new_user)
    print (result.status_code)

    decoder = json.JSONDecoder()
    user = decoder.decode(result.content.decode())

    print(user)
    do_get()


# Modifica la edad del usuario 1 (Pedro)
def do_put():
    print("-- PUT --")
    new_user = '{"name": "Pedro", "age": 23}'
    result = requests.put("http://127.0.0.1:5000/users/1", data=new_user)
    print (result.status_code)

    decoder = json.JSONDecoder()
    user = decoder.decode(result.content.decode())

    print(user)
    do_get()


# Borra el usuario 3 (Ana)
def do_delete():
    print("-- DELETE --")
    result = requests.delete("http://127.0.0.1:5000/users/3")
    print(result.status_code)

    do_get()


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
