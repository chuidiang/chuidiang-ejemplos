# Chuidiang 10/12/2022
import xml.etree.ElementTree as ET

if __name__ == '__main__':
    parser = ET.XMLPullParser()
    parser.feed("sample.xml")

    iterator = parser.read_events()
    print(iterator)
