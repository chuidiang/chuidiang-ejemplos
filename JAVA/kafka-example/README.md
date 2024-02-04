Este es el código de [ejemplo de Java con Apache Kafka](https://chuidiang.org/index.php?title=Ejemplo_de_Apache_Kafka_con_Java)

En él se establece la conexión, se consulta si existe el topic 
"quickstart-events" para crearlo en caso negativo y luego se crea un 
productor y un consumidor.

En src/main/files/docker-compose-kafka-kraft.yml hay un fichero de
[docker compose para kafka](https://chuidiang.org/index.php?title=Apache_Kafka_con_Docker) 
por si quieres arrancar un nodo y probar el ejemplo.

KafkaSimpleExampleMain.java contiene el código explicado en el tutorial del 
enlace anterior.

KafkaComplexExampleMain.java contiene un ejemplo en el que se hacen dos 
particiones del topic y se ponen dos Consumer, de forma que a cada uno se le 
asigna una de las particiones.