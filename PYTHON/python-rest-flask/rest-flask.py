# Chuidiang 21/01/2023
# Ejemplo de web service con python Flask

from flask import Flask, abort, request
import traceback
import json

app = Flask(__name__)


# Datos de usuario
class UserData:
    def __init__(self, name, age):
        self.name = name
        self.age = age


# Lista de usuarios
users = [
    UserData("Juan", 11),
    UserData("Pedro", 22),
    UserData("Maria", 33)
]


# Metodo GET para obtener la lista de todos los usuarios.
@app.get("/users")
def get_users():
    result = []
    for user in users:
        result.append(user.__dict__)
    return result


# Metodo GET para obtener un usuario concreto por indice
@app.get("/users/<idx>")
def get_user(idx):
    try:
        user = users[int(idx)]
        return user.__dict__
    except IndexError:
        traceback.print_exc()
        abort(404)
    except ValueError:
        traceback.print_exc()
        abort(400)
    except Exception:
        traceback.print_exc()
        raise


# Metodo DELETE para borrar un usuario concreto por indice
@app.delete("/users/<idx>")
def delete_user(idx):
    try:
        del users[int(idx)]
        return "", 204
    except (IndexError, ValueError):
        traceback.print_exc()
        abort(400)
    except Exception:
        traceback.print_exc()
        raise


# Peticion POST para añadir un nuevo usuario
@app.post("/users")
def add_user():
    try:
        new_user_dict = request.get_json()
        new_user = UserData(new_user_dict["name"], int(new_user_dict["age"]))
        users.append(new_user)
        return new_user.__dict__, 201
    except json.JSONDecodeError:
        traceback.print_exc()
        abort(400)
    except Exception:
        traceback.print_exc()
        raise


# Peticion PUT para modificar un usuario por indice.
@app.put("/users/<idx>")
def put_user(idx):
    try:
        new_user_dict = request.get_json()
        new_user = UserData(new_user_dict["name"], int(new_user_dict["age"]))
        users[int(idx)] = new_user
        return new_user.__dict__, 200
    except (IndexError, ValueError, json.JSONDecodeError):
        traceback.print_exc()
        abort(400)
    except Exception:
        traceback.print_exc()
        raise

