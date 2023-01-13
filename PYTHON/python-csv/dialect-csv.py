# Chuidiang 13/01/2023

import csv

if __name__ == '__main__':
    with open("username.csv", "r") as csv_input_file:
        dialect = csv.Sniffer().sniff(csv_input_file.read(1024))
        print(dialect.delimiter)
        print(dialect.quotechar)

