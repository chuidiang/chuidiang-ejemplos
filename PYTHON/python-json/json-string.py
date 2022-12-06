# Chuidiang 2022/12/06

import json


def con_funciones(param_dict_data):
    json_data = json.dumps(param_dict_data)
    print(type(json_data))
    print(json_data)

    dict_data = json.loads(json_data)
    print(type(dict_data))
    print(dict_data)


def con_encoders(param_dict_data):
    encoder = json.JSONEncoder()
    json_data = encoder.encode(param_dict_data)
    print(type(json_data))
    print(json_data)

    decoder = json.JSONDecoder()
    dict_data = decoder.decode(json_data)
    print(type(dict_data))
    print(dict_data)


if __name__ == '__main__':

    data = {"nombre": "juan", "apellido": "Perez", "fecha_nacimiento": {"año": 2002, "mes": 12, "dia": 1}}

    con_encoders(data)
    con_funciones(data)


