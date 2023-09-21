Este proyecto es el ejemplo completo de varios tutoriales en https://chuidiang.org

En el ejemplo se establece conexión con una base de datos MongoDB, se insertan, consultan, modifican y borran documentos de una colección.

MongoDBSampleMain.java lo hace como documentos json directamente, usando bson Document. La explicación en  https://chuidiang.org/index.php?title=Conectar_Java_con_MongoDB_-_Ejemplo_CRUD

MongoDBPojosSampleMain.java lo hace configurando la conexión con un codec por defecto para POJOs. De esta forma, en vez de Document, podemos insertar, consultar, modificar o borrar directamente clases POJO de Java.