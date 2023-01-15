import csv

# Chuidiang. 13 Ene 2023
# Ejemplo lectura y escritura de fichero CSV.
# Lee un fichero csv con separdor ; y sin comillas en los elemtos y lo reescribe
# poniendo como separador una , y poniendo comillas simples en todos los elementos.
#
if __name__ == '__main__':
    with open("username.csv", newline='') as csv_input_file:
        with open("username-quoted.csv", "w", newline='') as csv_output_file:
            reader = csv.reader(csv_input_file, delimiter=";", quotechar=None)
            writer = csv.writer(csv_output_file, delimiter=",", quotechar="'", quoting=csv.QUOTE_ALL)
            for row in reader:
                print(row)
                writer.writerow(row)
