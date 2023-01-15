import csv

# Chuidiang. 13 Ene 2023
# Ejemplo lectura y escritura de fichero CSV.
# Lee un fichero csv con separdor ; y sin comillas en los elemtos y lo reescribe
# poniendo como separador una , y poniendo comillas simples en todos los elementos.
#
if __name__ == '__main__':
    # Lectura de fichero y mostrarlo en pantalla
    with open("username.csv", newline='') as csv_input_file:
        reader = csv.reader(csv_input_file, delimiter=";", quotechar=None)
        for row in reader:
            print(row)

    # Escritura de fichero
    with open("output.csv", "w", newline='') as csv_output_file:
        writer = csv.writer(csv_output_file, delimiter=",", quotechar="'", quoting=csv.QUOTE_ALL)
        writer.writerow(["header1", "header2", "header3"])
        writer.writerow(["data_1_1", "data_1_2", "data_1_3"])
        writer.writerow(["data_2_1", "data_2_2", "data_2_3"])
        writer.writerow(["data_3_1", "data_3_2", "data_3_3"])


    # Leemos el fichero y lo reescribirmos cambiando separaores y comillas.
    with open("username.csv", newline='') as csv_input_file:
        with open("username-quoted.csv", "w", newline='') as csv_output_file:
            reader = csv.reader(csv_input_file, delimiter=";", quotechar=None)
            writer = csv.writer(csv_output_file, delimiter=",", quotechar="'", quoting=csv.QUOTE_ALL)
            for row in reader:
                print(row)
                writer.writerow(row)
