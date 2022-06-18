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

    public static void main(String[] args) throws ClassNotFoundException {

        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/chuidiang-examples?currentSchema=jdbc_example",
                "postgres",
                "postgres"))
        {
            createTAble(connection);
            crudOperationsCreateStatement(connection);
            crudOperationsPrepareStatement(connection);
            showMetadata(connection);

        } catch (SQLException e) {
            System.err.println("Error en la ejecucion " + e.getMessage());
        }

        try (BasicDataSource dataSource = new BasicDataSource()){

            dataSource.setUrl("jdbc:postgresql://localhost/chuidiang-examples?currentSchema=jdbc_example");
            dataSource.setUsername("postgres");
            dataSource.setPassword("postgres");
            dataSource.setMaxTotal(10);
            dataSource.setInitialSize(2);
            dataSource.setMaxIdle(2);
            dataSource.setValidationQuery("select 1");

            try(Connection connection = dataSource.getConnection()){
                createTAble(connection);
                crudOperations(connection);
                showMetadata(connection);
            }
        } catch (SQLException e){
            System.err.println("Error en la ejecucion: "+e.getMessage());
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
            int inserted = st.executeUpdate(
                    String.format("INSERT INTO contacto (nombre, apellidos, telefono) " +
                            "VALUES ('%s','%s','%s')","Juan","Perez","22334455"));
            System.out.println("Registros insertados: " + inserted);

            int contactId = 1;

            try (ResultSet resultSet = st.executeQuery(String.format("SELECT * FROM contacto WHERE id=%d",contactId))){
                while (resultSet.next()){
                    System.out.println("ID: " + resultSet.getObject(1));
                    System.out.println("NOMBRE " + resultSet.getObject(2));
                    System.out.println("APELLIDOS: " + resultSet.getObject(3));
                    System.out.println("TELEFOND: " + resultSet.getObject(4));
                }
            };

            int updated = st.executeUpdate("UPDATE contacto SET telefono='11223344' WHERE id=1");
            System.out.println("Registros modificados: " + updated);

            int deleted = st.executeUpdate(String.format("DELETE FROM contacto WHERE id=%d",contactId));
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
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO contacto (nombre, apellidos, telefono) " +
                "VALUES (?,?,?)")){
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
