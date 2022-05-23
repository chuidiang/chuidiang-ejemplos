Ejemplo usando springdoc-openapi-ui para generar documentación de web service rest de 
forma automática.

Una vez arrancada la aplicación, la documentación swagger está disponible en

http://localhost:8080/swagger-ui/index.html

mientras que la openapi está en 

http://localhost:8080/v3/api-docs

Las posibles anotaciones swagger para el código están en

https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations

Para generar codigo de cliente

java -jar openapi-generator-cli-5.4.0.jar generate -i http://localhost:8080/v3/api-docs -g java