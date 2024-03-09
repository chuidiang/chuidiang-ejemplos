Ejemplos de código del tutorial [Ejemplo con Mockito](https://chuidiang.org/index.php?title=Ejemplo_sencillo_con_Mockito)

No hay ningún main(), solo una clase SomeComplexClass.java a la que queremos hacer el test
con mockito. Y para ver la potencia de mockito, hacemos que esta clase dependa de otras
clases difíciles de levantar en un test: Una que conecta con una base de datos y
otra que conecta un socket contra un servidor.

Hay otras clases adicionales para los siguientes tutoriales:

Los ejemplos de código del tutorial [Mockito when](https://chuidiang.org/index.php?title=Mockito_when) 
están en src/test/java, clase SomeWhenTest.java. 

Los ejemplos de código del tutorial [Mockito verify](https://chuidiang.org/index.php?title=Mockito_verify) están
en SomeVerifyTest.java y SomeJunit4VerifyTest.java