# Chuidiang 28/01/2023

# Ejemplo de llamadas a servidor HTTP usando el módulo requests.
# Haca una llamada pasando parámetros en la URL y contenido en el cuerpo de la petición

import requests


if __name__ == '__main__':
    headers = {"Content-Type": "application/json"}
    data = '{"key1": "value1", "key2": "value2"}'
    params = {"par1": "value1", "par2": "value2"}
    response = requests.post("http://127.0.0.1:5000/sample", headers=headers, data=data, params=params)
    print(response.content)
    print(response.status_code)