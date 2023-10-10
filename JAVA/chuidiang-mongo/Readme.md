Este proyecto es el ejemplo completo de varios tutoriales en https://chuidiang.org

En el ejemplo se establece conexión con una base de datos MongoDB, se insertan, consultan, modifican y borran documentos de una colección.

**com.chuidiang.examples.mongo.MongoDBSampleMain.java** lo hace como documentos json directamente, usando bson Document. La explicación en  [Conectar Java con MongoDB](https://chuidiang.org/index.php?title=Conectar_Java_con_MongoDB_-_Ejemplo_CRUD)

**com.chuidiang.examples.mongo.MongoDBPojosSampleMain.java** lo hace configurando la conexión con un codec por defecto para POJOs. De esta forma, en vez de Document, podemos insertar, consultar, modificar o borrar directamente clases POJO de Java. La explicación en [MongoDB con POJOs Java](https://chuidiang.org/index.php?title=Java_POJOs_con_MongoDB)

**com.chuidiang.examples.mongo.query.QueryExample.java** tiene ejemplos más detallados de consultas, usando documentos bson. La explicación en [Query con MongoDB y Java](https://chuidiang.org/index.php?title=Query_con_MongoDB_y_Java) 