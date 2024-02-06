En este proyecto hay un doble ejemplo. Por un lado, el uso de modulos de Java 9 y por otro, el uso de la clase ServiceLoader.

En el proyecto hay cuatro subproyectos:

* module-core tiene una interface que los demás módulos pueden implementar o usar
* module-impl1 y module-impl2 tienen implementaciones de dicha interface
* module-main tiene el main del proyecto

En los ficheros module-info.java de cada suproyecto puedes ver:

module-core exporta el paquete con la interface, siguiendo el estilo de módulos de java 9

module-impl1 y module-impl2 requieren la interface de module-core

module-main requiere la interface de module-core y usa los servicios que la implementen.

Si miras los ficheros en META-INF, verás que module-impl1 y module-impl2 avisan que implementan la interface de module-core. module-main, usando la clase ServiceLoader, obtiene las instancias de esos servicios.