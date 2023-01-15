import csv

# Chuidiang 15/01/2023

if __name__ == '__main__':
    with open("username.csv", newline='') as csv_file:
        dict_reader = csv.DictReader(csv_file, delimiter=";")
        for row in dict_reader:
           print(row)

    with open("output.csv", "w", newline='') as csv_outuput_file:
        header = ["column1", "column2", "column3"]
        dict_writer = csv.DictWriter(csv_outuput_file, header, delimiter=",", quotechar="'", quoting=csv.QUOTE_ALL)
        dict_writer.writeheader()
        dict_writer.writerow({"column1": "data_1_1"})
        dict_writer.writerow({"column1": "data_2_1", "column2": "data_2_2", "column3": "data_2_3"})
