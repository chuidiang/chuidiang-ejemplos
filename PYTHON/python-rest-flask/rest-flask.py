# Chuidiang 21/01/2023

from flask import Flask, abort
import json

app = Flask(__name__)


class Data:
    def __init__(self, name, age):
        self.name = name
        self.age = age


users = [
    Data("Juan", 11),
    Data("Pedro", 22),
    Data("Maria", 33)
]


@app.get("/users")
def get_uses():
    result = []
    for user in users:
        result.append(user.__dict__)
    return result


@app.get("/users/<idx>")
def get_user(idx):
    try:
        user = users[int(idx)]
        return user.__dict__
    except Exception as error:
        print(error)
        abort(404)


@app.delete("/users/<idx>")
def delete_user(idx):
    try:
        del users[int(idx)]
        abort(204)
    except Exception as error:
        print(error)
        abort(500)

