package com.chuidiang.examples.jdb_example;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

/**
 * @author Chuidiang
 * @date 11/06/2022
 */
public class Main {

    private static final String SCHEMA_NAME = "jdbc_example";
    private static final String TABLE_NAME = "contacto";

    public static void main(String[] args) {
        // Ambas llamadas hacen lo mismo, la unica diferencia entre ellas es
        // la forma de obtener la conexión de base de datos.
        driverManagerSamples();
        connectionPoolSamples();
    }

    private static void connectionPoolSamples() {
        try (BasicDataSource dataSource = new BasicDataSource()){
            dataSource.setUrl("jdbc:postgresql://localhost/chuidiang-examples?currentSchema=jdbc_example");
            dataSource.setUsername("postgres");
            dataSource.setPassword("postgres");
            dataSource.setMaxTotal(10);
            dataSource.setInitialSize(2);
            dataSource.setMaxIdle(2);
            dataSource.setValidationQuery("select 1");

            try(Connection connection = dataSource.getConnection()){
                executeSamples(connection);
            } catch (SQLException e){
                System.err.println("Error en la ejecucion: "+e.getMessage());
            }
        } catch (SQLException e){
            System.err.println("Error en la ejecucion: "+e.getMessage());
        }
    }

    private static void driverManagerSamples() {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/chuidiang-examples?currentSchema=jdbc_example",
                "postgres",
                "postgres"))
        {
            executeSamples(connection);

        } catch (SQLException e) {
            System.err.println("Error en la ejecucion " + e.getMessage());
        }
    }

    private static void executeSamples(Connection connection) throws SQLException {
        createTAble(connection);
        crudOperationsCreateStatement(connection);
        crudOperationsPrepareStatement(connection);
        showMetadata(connection);
        createProcedures(connection);
        createFunctions(connection);
    }

    private static void createFunctions(Connection connection) {
        String sqlFunction = "CREATE OR REPLACE FUNCTION add(integer, integer) RETURNS integer" +
                "    AS 'select $1 + $2;'" +
                "    LANGUAGE SQL" +
                "    IMMUTABLE" +
                "    RETURNS NULL ON NULL INPUT";

        try (Statement st = connection.createStatement()){
            st.executeUpdate(sqlFunction);
        } catch (SQLException e) {
            System.err.println("Errro creando funcion "+e.getMessage());
        }

        try (CallableStatement callableStatement = connection.prepareCall("{? = call add(?,?)}")){
            callableStatement.registerOutParameter(1,Types.INTEGER);
            callableStatement.setInt(2,2);
            callableStatement.setInt(3,2);
            callableStatement.execute();
            System.out.println("La suma 2+2 es "+callableStatement.getInt(1));
        } catch (SQLException e) {
            System.err.println("Errro llamando funcion "+e.getMessage());
        }
    }

    /**
     * Crea procedimientos y funciones en base de datos
     * para prueba.
     */
    private static void createProcedures(Connection connection) {
        try(Statement st = connection.createStatement()){
            st.executeUpdate("DROP PROCEDURE IF EXISTS insert_data");
            st.executeUpdate("CREATE PROCEDURE insert_data(" +
                    "nombre varchar(20) , apellidos varchar(20), telefono varchar(20)) " +
                    "LANGUAGE SQL " +
                    "AS $$ " +
                    "  INSERT INTO contacto (nombre, apellidos, telefono) " +
                    "  VALUES (nombre, apellidos, telefono); " +
                    "$$");
        } catch (SQLException e){
            System.err.println("Error creando procedure " + e.getMessage());

        }

        try(CallableStatement cst = connection.prepareCall("{call insert_data(?,?,?)}")){
            cst.setString(1,"Pedro");
            cst.setString(2,"Lopez");
            cst.setString(3,"99883344");
            cst.execute();
        } catch (SQLException e) {
            System.err.println("Error creando procedure " + e.getMessage());
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
    private static void crudOperationsPrepareStatement(Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO contacto (nombre, apellidos, telefono) " +
                "VALUES (?,?,?)")){
            preparedStatement.clearParameters();

            preparedStatement.setString(1,"Juan");
            preparedStatement.setString(2,"Perez");
            preparedStatement.setString(3,"12345678");

            int inserted = preparedStatement.executeUpdate();
            System.out.println("PreparedStatement insertados: "+ inserted);
        } catch (Exception e){
            System.err.println("Error con prepared statement "+e.getMessage());
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT FROM * contacto WHERE id=?"))
        {
            int contactId=1;
            preparedStatement.setInt(1,contactId);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    System.out.println("ID: " + resultSet.getObject(1));
                    System.out.println("NOMBRE " + resultSet.getObject(2));
                    System.out.println("APELLIDOS: " + resultSet.getObject(3));
                    System.out.println("TELEFOND: " + resultSet.getObject(4));
                }
            } catch (Exception e) {
                System.out.println("Error query: " +e.getMessage());
            }

        } catch (Exception e){
            System.err.println("Error con prepared statement "+e.getMessage());
        }
    }
}
