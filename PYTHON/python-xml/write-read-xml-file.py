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

    print("Buscando tags rank")
    for rank_node in root_node.iter('rank'):
        print("iter: ", rank_node.tag, rank_node.attrib, rank_node.text, rank_node.tail)

    print("find() y findall()")

    print("find(country): ", root_node.find("country").attrib)
    countries = root_node.findall("country")
    for country in countries:
        print("findall(neighbor): ", country.attrib)

    # del nodo actual hacia abajo, seleccionar un country que tenga neighbor y elegir uno de esos neighbor
    # Solo encuentra neighbor que sean hijos de cuntry que a su vez deben ser hijos del nodo actual
    print(root_node.find("country/neighbor").attrib)

    # del nodo actual hacia abajo, buscar cualquier tag que tenga un neighbor y devolver el neigbhor.
    # Solo encuentra neighbor que sean hijos de un hijo cualquiera del nodo actual.
    print(root_node.find("*/neighbor").attrib)

    # Del nodo actual hacia abajo, busca un neighbor a cualquier nivel de profuncidad.
    print(root_node.find(".//neighbor").attrib)

    # Del nodo actual hacia abajo, busca el padre de un neighbor que esté en cualquier nivel de profundidad.
    print(root_node.find(".//neighbor/..").attrib)

    # Del nodo actual hacia abajo busca cualquier nodo que tenga un atributo name que sea Austria
    print(root_node.find(".//*[@name='Austria']").attrib)

    # Busqueda por texto.
    xml_string = "<a>texto de a<b>texto de b<c>texto de c</c></b></a>"
    string_root_node = ET.fromstring(xml_string)
    print(string_root_node.find(".//*[.='texto de c']"))
    print(string_root_node.find(".//*[.='texto de btexto de c']"))
    print(string_root_node.find(".[.='texto de atexto de btexto de c']"))

    panama = root_node.find(".//*[@name='Panama']")
    ET.dump(panama)
    panama.clear()
    ET.dump(panama)

    panama.set("name", "Panama")
    panama.attrib["key"] = "value"
    panama.text = "The text"
    ET.dump(panama)
    panama.attrib.pop("key")
    ET.dump(panama)

    ET.dump(root_node)
    for country in root_node.findall("country[@name='Panama']"):
        root_node.remove(country)
    ET.dump(root_node)


if __name__ == '__main__':
    # write_xml()
    read_xml()



