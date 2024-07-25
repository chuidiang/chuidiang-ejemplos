Código de ejemplo de [Servicios REST en Angular](https://chuidiang.org/index.php?title=Servicios_REST_en_Angular)

Para este ejemplo levantamos un servidor REST sencillo, con las cuatro operaciones típicas GET, PUT, POST y DELETE. Para levantarlo, usamos el ejemplo que se hizo en [Servicio REST con Pyhton y Flask](https://chuidiang.org/index.php?title=Web_Services_REST_con_Python_y_Flask).

El ejemplo angular tiene dos clases:

* Un Componente que muestra una lista en pantalla y tiene botones para añadir, modificar y borrar elementos de la lista.
* Un servicio que es el que hace las llamadas REST al servidor usando HttpClient