Este proyecto sirve de apoyo al tutorial [Java 9 modules](https://chuidiang.org/index.php?title=Java_9_modules)

En el proyecto hay tres subproyectos:

* module-core ofrece una interface que los demás módulos pueden implementar o usar. Tiene una implementación privada que otros módulos puede obtener con ServiceLoader
* module-impl1 tiene también otra implementación de dicha interface
* module-main tiene el main del proyecto y coge las dos implementaciones anteriores.

En los ficheros module-info.java de cada subproyecto puedes ver:

module-core exporta el paquete con la interface y proporciona una implementación para ella, siguiendo el estilo de módulos de java 9

module-impl requiere la interface de module-core

module-main requiere la interface de module-core y usa los servicios que la implementen. Para obtener dichos servicios, debe usar la clase ServiceLoader.
