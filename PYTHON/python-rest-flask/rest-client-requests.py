# Chuidiang 22/01/2023

import requests, json


def do_get():
    print("--GET--")
    result = requests.get("http://127.0.0.1:5000/users")
    print(result.content)

    decoder = json.JSONDecoder()
    user = decoder.decode(result.content.decode())

    print(user)


def do_get_idx():
    print("--GET IDX--")
    result = requests.get("http://127.0.0.1:5000/users/1")
    print(result.status_code)

    decoder = json.JSONDecoder()
    user = decoder.decode(result.content.decode())

    print(user)


def do_post():
    print("-- POST --")
    new_user = '{"name": "Ana", "age": 44}'
    result = requests.post("http://127.0.0.1:5000/users", data=new_user)
    print (result.status_code)

    decoder = json.JSONDecoder()
    user = decoder.decode(result.content.decode())

    print(user)
    do_get()


def do_put():
    print("-- POST --")
    new_user = '{"name": "Pedro", "age": 23}'
    result = requests.put("http://127.0.0.1:5000/users/1", data=new_user)
    print (result.status_code)

    decoder = json.JSONDecoder()
    user = decoder.decode(result.content.decode())

    print(user)
    do_get()


if __name__ == '__main__':
    do_get()
    do_get_idx()
    do_post()
    do_put()
