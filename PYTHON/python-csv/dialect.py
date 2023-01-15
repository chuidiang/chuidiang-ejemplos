import csv

# Chuidiang 15/01/2023

if __name__ == '__main__':
    print(csv.list_dialects())
    for dialect_name in csv.list_dialects():
        dialect = csv.get_dialect(dialect_name)
        print(dialect_name+" "+dialect.delimiter+" "+dialect.quotechar+" "+str(dialect.quoting)+" ")

    print(csv.QUOTE_MINIMAL)
    print(csv.QUOTE_ALL)
    print(csv.QUOTE_NONNUMERIC)
    print(csv.QUOTE_NONE)

    csv.register_dialect("my_dialect", delimiter=",", quotechar="'", quoting=csv.QUOTE_ALL)
    my_dialect = csv.get_dialect("my_dialect")
    print("my_dialect " + my_dialect.delimiter + " " + my_dialect.quotechar + " " + str(my_dialect.quoting) + " ")


