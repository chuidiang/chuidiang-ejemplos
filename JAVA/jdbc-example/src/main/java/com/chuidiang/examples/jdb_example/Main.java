package com.chuidiang.examples.jdb_example;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.*;
import java.sql.*;

/**
 * @author Chuidiang
 * @date 11/06/2022
 */
public class Main {

    private static final String SCHEMA_NAME = "jdbc_example";
    private static final String TABLE_NAME = "contacto";
    public static final String DB_CONNECTION_URL = "jdbc:postgresql://localhost:5432/chuidiang-examples?currentSchema=jdbc_example&escapeSyntaxCallMode=callIfNoReturn";

    public static void main(String[] args) throws SQLException, IOException {
        // Ambas llamadas hacen lo mismo, la unica diferencia entre ellas es
        // la forma de obtener la conexión de base de datos.
        driverManagerSamples();
        connectionPoolSamples();
    }

    private static void connectionPoolSamples() throws SQLException, IOException {
        try (BasicDataSource dataSource = new BasicDataSource()){
            dataSource.setUrl(DB_CONNECTION_URL);
            dataSource.setUsername("postgres");
            dataSource.setPassword("postgres");
            dataSource.setMaxTotal(10);
            dataSource.setInitialSize(2);
            dataSource.setMaxIdle(2);
            dataSource.setValidationQuery("select 1");

            try(Connection connection = dataSource.getConnection()){
                executeSamples(connection);
            }
        }
    }

    private static void driverManagerSamples() throws SQLException, IOException {
        try (Connection connection = DriverManager.getConnection(
                DB_CONNECTION_URL,
                "postgres",
                "postgres"))
        {
            executeSamples(connection);
        }
    }

    private static void executeSamples(Connection connection) throws SQLException, IOException {
        createTAble(connection);
        crudOperationsCreateStatement(connection);
        crudOperationsPrepareStatement(connection);
        createProcedures(connection);
        createFunctions(connection);
        binaryData(connection);
        showMetadata(connection);
        batchInsert(connection);
        scrollableAndUpdatable(connection);
    }

    private static void batchInsert(Connection connection) throws SQLException {
        String[] names = {"Ana","Cristina","Maria"};
        String[] surnames = {"Gomez", "Perez", "Gonzalez"};
        String[] phoneNumbers = {"11111111","22222222","33333333"};

        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO contacto (nombre, apellidos, telefono) VALUES (?,?,?)")){
            for (int i = 0; i < names.length; i++) {
                ps.setString(1,names[i]);
                ps.setString(2,surnames[i]);
                ps.setString(3,phoneNumbers[i]);
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private static void scrollableAndUpdatable(Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM contacto",
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE)){
            try (ResultSet rs = ps.executeQuery()){

                // Buscamos a Cristina para cambiarle el número de telefono.
                while (rs.next()){
                    if ("Cristina".equals(rs.getString("nombre"))){
                        break;
                    }
                }
                rs.updateString("telefono","21212121");
                rs.updateRow();

                rs.beforeFirst();

                // Buscamos a Maria para borrarla
                while (rs.next()){
                    if ("Maria".equals(rs.getString("nombre"))){
                        rs.deleteRow();
                        break;
                    }
                }

                // Insertamos una persona nueva
                rs.moveToInsertRow();
                rs.updateString("nombre","Gloria");
                rs.updateString("apellidos","Ramirez");
                rs.updateString("telefono", "33334444");
                rs.insertRow();
            }

            // Nueva consulta para ver que efectivamente se ha cambiado todo en bd.
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()){
                    System.out.println("ID: " + rs.getObject(1));
                    System.out.println("NOMBRE " + rs.getObject(2));
                    System.out.println("APELLIDOS: " + rs.getObject(3));
                    System.out.println("TELEFOND: " + rs.getObject(4));

                }
            }
        }
    }

    private static void binaryData(Connection connection) throws SQLException, IOException {
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate("DROP TABLE IF EXISTS image");
            statement.executeUpdate("CREATE TABLE image " +
                    "(id SERIAL PRIMARY KEY, " +
                    "name text," +
                    "raster bytea)");
        }

        try(FileInputStream fis = new FileInputStream("src/main/files/jdbc-java.png")){
            try(PreparedStatement preparedStatement =connection.prepareStatement("INSERT INTO image (name, raster) VALUES (?,?)")) {
                preparedStatement.setString(1, "jdbc-java.png");
                preparedStatement.setBinaryStream(2, fis);
                final int inserted = preparedStatement.executeUpdate();
                System.out.println("Imagenes insertadas " + inserted);
            }
        }


        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM image WHERE name=?")) {
            preparedStatement.clearParameters();
            preparedStatement.setString(1, "jdbc-java.png");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println("Id : " + resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    System.out.println("Nombre : " + name);
                    try (FileOutputStream fos = new FileOutputStream("target/" + name)) {
                        try (InputStream raster = resultSet.getBinaryStream("raster")) {
                            raster.transferTo(fos);
                        }
                    }
                }
            }
        }
    }

    private static void createFunctions(Connection connection) throws SQLException {
        String sqlFunction = "CREATE OR REPLACE FUNCTION add(integer, integer) RETURNS integer" +
                "    AS 'select $1 + $2;'" +
                "    LANGUAGE SQL" +
                "    IMMUTABLE" +
                "    RETURNS NULL ON NULL INPUT";

        try (Statement st = connection.createStatement()){
            st.executeUpdate(sqlFunction);
        }

        try (CallableStatement callableStatement = connection.prepareCall("{? = call add(?,?)}")){
            callableStatement.registerOutParameter(1,Types.INTEGER);
            callableStatement.setInt(2,2);
            callableStatement.setInt(3,2);
            callableStatement.execute();
            System.out.println("La suma 2+2 es "+callableStatement.getInt(1));
        }
    }

    /**
     * Crea procedimientos y funciones en base de datos
     * para prueba.
     */
    private static void createProcedures(Connection connection) throws SQLException {
        System.out.println("Procedures");
        try(Statement st = connection.createStatement()){
            st.executeUpdate("DROP PROCEDURE IF EXISTS insert_data");
            st.executeUpdate("CREATE PROCEDURE insert_data(" +
                    "nombre varchar(20) , apellidos varchar(20), telefono varchar(20)) " +
                    "LANGUAGE SQL " +
                    "AS $$ " +
                    "  INSERT INTO contacto (nombre, apellidos, telefono) " +
                    "  VALUES (nombre, apellidos, telefono); " +
                    "$$");
        }

        try(CallableStatement callableStatement = connection.prepareCall("{call insert_data(?,?,?)}")){
            callableStatement.setString(1,"Pedro");
            callableStatement.setString(2,"Lopez");
            callableStatement.setString(3,"99883344");
            callableStatement.execute();
        }
    }


    /**
     * Elimina la tabla si existe y la crea de nuevas.
     * La eliminacion se hace para que si se arranca este programa de ejemplo varias veces, no de fallo porque
     * la tabla ya este creada.
     * En una aplicacion seria, no deberian borrarse las tablas y recrearlas cada vez que se arranca la aplicacion.
     * Deberia haber una logica algo mas compleja, como verificar si las tablas existen y son como esperamos que
     * sean.
     *
     * @param connection
     * @throws SQLException
     */
    private static void createTAble(Connection connection) throws SQLException {
        try(Statement st = connection.createStatement()) {
            st.executeUpdate(
                    "DROP TABLE IF EXISTS contacto");

            st.executeUpdate(
                    "CREATE TABLE contacto (id SERIAL PRIMARY KEY, " +
                            "nombre VARCHAR(20), " +
                            "apellidos VARCHAR(20), " +
                            "telefono VARCHAR(20))");
        }
    }

    /**
     * Operaciones tipicas CRUD: Create, Read, Update y Delete
     * @param connection
     */
    private static void crudOperationsCreateStatement(Connection connection) throws SQLException {
        try(Statement st = connection.createStatement()) {
            String name = "Juan";
            String surname = "Perez";
            String phoneNumber = "22334455";
            int inserted = st.executeUpdate(
                    String.format("INSERT INTO contacto (nombre, apellidos, telefono) VALUES ('%s','%s','%s')", name, surname, phoneNumber));
            System.out.println("Registros insertados: " + inserted);

            Integer contactId = null;

            try (ResultSet resultSet = st.executeQuery("SELECT * FROM contacto LIMIT 1")){
                while (resultSet.next()){
                    System.out.println("ID: " + resultSet.getObject(1));
                    contactId = resultSet.getInt(1);
                    System.out.println("NOMBRE " + resultSet.getObject(2));
                    System.out.println("APELLIDOS: " + resultSet.getObject(3));
                    System.out.println("TELEFOND: " + resultSet.getObject(4));
                }
            };

            if (null!=contactId) {
                try (ResultSet resultSet = st.executeQuery(String.format("SELECT * FROM contacto WHERE id=%d", contactId))) {
                    while (resultSet.next()) {
                        System.out.println("ID: " + resultSet.getInt("id"));
                        contactId = resultSet.getInt("id");
                        System.out.println("NOMBRE " + resultSet.getString("nombre"));
                        System.out.println("APELLIDOS: " + resultSet.getString("apellidos"));
                        System.out.println("TELEFOND: " + resultSet.getString("telefono"));
                    }
                }
            }


            String newPhoneNumber = "11223344";
            int updated = st.executeUpdate(
                    String.format("UPDATE contacto SET telefono='%s' WHERE id=%d",
                            newPhoneNumber, contactId));
            System.out.println("Registros modificados: " + updated);

            int deleted = st.executeUpdate(
                    String.format("DELETE FROM contacto WHERE id=%d",contactId));
            System.out.println("Registros borrados: " + deleted);

            System.out.println("--------------------------------");
        }

    }

    /**
     * Muestra las tablas de la base de datos y sus columnas
     *
     * @param connection
     * @throws SQLException
     */
    private static void showMetadata(Connection connection) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();

        try(ResultSet rs = metaData.getTables(null, SCHEMA_NAME, "%", null)){
            while(rs.next()){
                System.out.println("CATALOGO: " +rs.getObject(1));
                System.out.println("ESQUEMA: " + rs.getObject(2));
                System.out.println("NOMBRE: "+ rs.getObject(3));
                System.out.println("TIPO:" + rs.getObject(4));
                System.out.println("--------------------------------");
            }
        }

        try(ResultSet rs = metaData.getColumns( null, SCHEMA_NAME, TABLE_NAME, null)){
            while(rs.next()){
                System.out.println("CATALOGO: " +rs.getObject(1));
                System.out.println("ESQUEMA: " + rs.getObject(2));
                System.out.println("TABLA: "+ rs.getObject(3));
                System.out.println("COLUMNA:" + rs.getObject(4));
                System.out.println("TIPO:" + JDBCType.valueOf(rs.getInt(5)));
                System.out.println("--------------------------------");
            }
        }

    }

    /**
     * Operaciones CRUD usando preparedStatement.
     * @param connection
     */
    private static void crudOperationsPrepareStatement(Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO contacto (nombre, apellidos, telefono) " +
                "VALUES (?,?,?)")){
            preparedStatement.clearParameters();

            preparedStatement.setString(1,"Juan");
            preparedStatement.setString(2,"Perez");
            preparedStatement.setString(3,"12345678");

            int inserted = preparedStatement.executeUpdate();
            System.out.println("PreparedStatement insertados: "+ inserted);
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM contacto WHERE id=?")) {
            int contactId = 1;
            preparedStatement.setInt(1, contactId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println("ID: " + resultSet.getObject(1));
                    System.out.println("NOMBRE " + resultSet.getObject(2));
                    System.out.println("APELLIDOS: " + resultSet.getObject(3));
                    System.out.println("TELEFOND: " + resultSet.getObject(4));
                }
            }
        }
    }
}