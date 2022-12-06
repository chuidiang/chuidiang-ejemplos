# Chuidiang 2022/12/06

if __name__ == '__main__':
    import json

    data = {"nombre": "juan", "apellido": "Perez", "fecha_nacimiento": {"año": 2002, "mes": 12, "dia": 1}}

    with open("json-data.txt", "w") as file:
        json.dump(data, file)

    with open("json-data.txt", "r") as file:
        for line in file:
            print(line)

    with open("json-data.txt", "r") as file:
        data_object = json.load(file)

    print(type(data_object))
    print(data_object)
