# Analizador/Generador de Idioma

En un texto en un determinado idioma las probabilidades de que aparezca una letra detrás de otra pareja de letras
concreta es distinta. Por ejemplo, en inglés es muy probable que detrás de las letras "th" vaya una "e", mientras que
en español esa combinación de letras no es nada probable.

Teniendo muestras de textos en varios idiomas (directorio src/main/resources) en el que se incluyen como idiomas
un fichero java y un fichero xml, es posible analizar un texto dado para compararlo con los ficheros de muestra y
teniendo en cuanta la probabilidad de las combinaciones de letras, intentar determinar en qué idioma está ese texto.

Y yendo un poco más allá, es posible generar aleatoriamente, pero teniendo en cuenta las probabilidades, un texto
en un idioma determinado. Obtendremos un texto que no tendrá ningún sentido, ni siquiera las palabras existirán, pero
alguien que no conozca ese idioma, dirá que ese texto es de ese idioma. Por ejemplo, yo que no tengo ni idea de
Alemán, identificaré el texto como Alemán, aunque un Alemán seguro que no está de acuerdo.

Puedes arrancar el programa com.chuidiang.reconocedor_idioma.PruebaCorrelacionIdiomas y jugar con todo esto.