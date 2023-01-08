# Chuidiang 10/12/2022
# Ejemplo de lectura de un fichero XML con python usando
# eventos de lectura en vez de leer todo el fichero de golpe.

import xml.etree.ElementTree as ET

if __name__ == '__main__':
    parser = ET.XMLPullParser(['end', 'comment', 'start-ns'])

    with open("sample-pull.xml") as data:
        for line in data:
            parser.feed(line)
            iterator = parser.read_events()
            for event, element in iterator:
                if 'start-ns' == event:
                    print(event, element)
                else:
                    print(event, element.tag, element.attrib, element.text)
