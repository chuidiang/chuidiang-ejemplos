# Chuidiang 21/01/2023
# El Hola Mundo de Flask. Levanta un servidor web y su URL se puede ver "Hello, World!".

from flask import Flask

app = Flask(__name__)


@app.route("/")
def hello_world():
    return "<p>Hello, World!</p>"
