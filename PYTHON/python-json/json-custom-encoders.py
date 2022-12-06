# Chuidiang 2022/12/06

import json


class MyEncoder(json.JSONEncoder):
    def default(self, o):
        if isinstance(o, complex):
            return {"complex": '%d+%dj' % (o.real, o.imag)}
        return json.JSONEncoder.default(self, o)


class MyDecoder(json.JSONDecoder):
    def __init__(self, *args, **kwargs):
        json.JSONDecoder.__init__(self, object_hook=self.object_hook, *args, **kwargs)

    def object_hook(self, dct):
        if "complex" in dct:
            print(dct["complex"])
            value = complex(dct["complex"])
            return value
        return dct


def con_encoders(param_dict_data):
    encoder = MyEncoder()
    json_data = encoder.encode(param_dict_data)
    print(type(json_data))
    print(json_data)

    decoder = MyDecoder()
    dict_data = decoder.decode(json_data)
    print(type(dict_data["fecha_nacimiento"]["año"]))
    print(dict_data)


if __name__ == '__main__':

    data = {"nombre": "juan", "apellido": "Perez", "fecha_nacimiento": {"año": 2+3j, "mes": 12, "dia": 1}}

    con_encoders(data)
