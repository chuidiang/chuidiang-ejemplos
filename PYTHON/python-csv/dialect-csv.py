# Chuidiang 13/01/2023
# Ejemplo de csv.Siniffer para analizar los separadores del fichero CSV.
# Para el ejemplo debes ejecutar primero read-write-csv.py, de forma que ese
# script genere el fichero usnername-quoted.csv que usa este ejemplo.

import csv

if __name__ == '__main__':
    with open("username-quoted.csv", "r") as csv_input_file:
        dialect = csv.Sniffer().sniff(csv_input_file.read(1024))
        print(dialect.delimiter)
        print(dialect.quotechar)

