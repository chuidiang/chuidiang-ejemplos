En este directorio tienes los ejemplos básicos de código del [Curso de Spring Framework](https://chuidiang.org/index.php?title=Categor%C3%ADa:Curso_Spring_Framework)

En concreto

* [PruebaBeanContainer.java](https://github.com/chuidiang/chuidiang-ejemplos/blob/master/JAVA/SPRING-FRAMEWORK/spring-core/src/main/java/com/chuidiang/pruebas/spring/PruebaBeanContainer.java) 
contiene el main del ejemplo básico en el que se instancian varias clases java usando un fichero de configuración 
XML de Spring
* [AgendaMain.java](https://github.com/chuidiang/chuidiang-ejemplos/blob/master/JAVA/SPRING-FRAMEWORK/spring-core/src/main/java/com/chuidiang/pruebas/spring/AgendaMain.java)
contiene el main del ejemplo básico en el que se instancian varias clases java y se inyectan unas a otras de forma
que puedan llamarse entre ellas. También usando un fichero de configuración XML de Spring
* [PostConstructMain.java](src%2Fmain%2Fjava%2Fcom%2Fchuidiang%2Fpruebas%2Fpostconstruct_scheduled%2FPostConstructMain.java) repite el ejemplo anterior, pero esta vez usando una mezcla de anotaciones de Spring sobre
las clases y el fichero XML de configuración de Spring
* [PostConstructMain.java](src%2Fmain%2Fjava%2Fcom%2Fchuidiang%2Fpruebas%2Fpostconstruct_scheduled%2FPostConstructMain.java) tiene un ejemplo de uso de init-method (fichero Spring XML) o @PostConstruct (anotaciones) 
* [PostConstructMain.java](src%2Fmain%2Fjava%2Fcom%2Fchuidiang%2Fpruebas%2Fpostconstruct_scheduled%2FPostConstructMain.java) tiene un ejemplo de tarea periódica con Spring usando task:scheduled (fichero Spring XML) o @Scheduled (anotacion)