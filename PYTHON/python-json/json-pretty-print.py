# Chuidiang 06/12/2022

import json

if __name__ == '__main__':
    data = {"nombre": "juan", "apellido": "Perez", "fecha_nacimiento": {"año": 2002, "mes": 12, "dia": 1}}

    string = json.dumps(data, indent=3)

    print(string)
