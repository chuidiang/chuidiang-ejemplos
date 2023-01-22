# Chuidiang 21/01/2023
# El Hola Mundo de Flask. Levanta un servidor web y su URL se puede ver "Hello, World!".

from flask import Flask, request

app = Flask(__name__)


@app.route("/")
def hello_world():
    return "<p>Hello, World!</p>"


@app.route("/<path>", methods=['GET', 'POST'])
def show_path(path):

    content = f"<p>{path}</p>"

    if request.args:
        content += "<ul>"
        for key in request.args.keys():
            content += f"<li>{key} = {request.args[key]}</li>"
        content += "</ul>"

    if request.data:
        content += f"<p>data = {request.data}</p>"

    return content
