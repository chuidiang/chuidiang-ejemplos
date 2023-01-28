Código de ejemplo del tutorial [Web Services con Python y Flask](https://chuwiki.chuidiang.org/index.php?title=Web_Services_REST_con_Python_y_Flask)

*hello-world-flask.py* contiene un pequeño Hola Mundo con *Flask* como ejemplo de aplicación mínima

*rest-flask.py* contiene un *Web Service REST JSON* con un ejemplo de *GET*, *POST*, *PUT* y *DELETE* sobre una lista de usuarios

También está aquí el código  del tutorial [Ciente Web Service con Python y requests](https://chuwiki.chuidiang.org/index.php?title=Cliente_Web_Service_REST_con_Python_y_requests). 
En él se explica como hacer peticiones *HTTP* con el módulo *requests* a un servidor *HTTP*.

En *basic-client.requests.py* hay un ejemplo básico de peticiones a un servidor *HTTP*. Para probarlo se debe levantar
el servidor *HTTP* del ejemplo *hello-world-flask.py*

En *rest-client-requests.py* hay un cliente que llama al web service levantado por *rest-flask.py*.