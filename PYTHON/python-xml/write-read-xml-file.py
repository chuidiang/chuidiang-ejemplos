# Chuidiang 07/12/2022

import xml.etree.ElementTree as ET


def write_xml():
    a = ET.Element('A')
    a.set("name", "abuelo")
    b = ET.SubElement(a, 'B', {"name": "padre"})
    c = ET.SubElement(b, 'C', {"name": "niño1"})
    b.text = "inner1"
    b.tail = "inner2"
    # d = ET.SubElement(c, 'D')
    comment = ET.Comment("Comentario")
    c.append(comment)
    ET.indent(a)
    et = ET.ElementTree(a)
    et.write("fichero.xml", xml_declaration=True)


def read_xml():
    root = ET.parse("sample.xml")

    root_node = root.getroot()
    print(root_node.tag, root_node.attrib, root_node.text)

    print("Leyendo hijos del root_node con un bucle iterador")
    for child in root_node:
        print("Hijo de root_node: ", child.tag, child.attrib, child.text, child.tail)

    print("Leyendo hijos del root_node con indice")
    number_of_childs = len(root_node)
    for index in range(number_of_childs):
        child = root_node[index]
        print("Hijo de root_node: ", child.tag, child.attrib, child.text, child.tail)

    print("Buscando tags country")
    for country_node in root_node.iter('country'):
        print("iter: ", country_node.tag, country_node.attrib, country_node.text, country_node.tail)


if __name__ == '__main__':
    # write_xml()
    read_xml()



