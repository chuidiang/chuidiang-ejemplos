package com.chuidiang.examples.jdb_example;

import java.sql.*;

/**
 * @author Chuidiang
 * @date 11/06/2022
 */
public class Main {

    private static final String SCHEMA_NAME = "jdbc_example";
    private static final String TABLE_NAME = "contacto";

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost/chuidiang-examples?currentSchema=jdbc_example",
                "postgres",
                "postgres"))
        {
            createTAble(connection);
            crudOperations(connection);
            showMetadata(connection);

        } catch (SQLException e) {
            System.err.println("Error en la ejecucion " + e.getMessage());
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
    private static void crudOperations(Connection connection) throws SQLException {
        try(Statement st = connection.createStatement()) {
            int inserted = st.executeUpdate(
                    "INSERT INTO contacto (nombre, apellidos, telefono) " +
                            "VALUES ('juan','perez','22334455')");
            System.out.println("Registros insertados: " + inserted);

            try (ResultSet resultSet = st.executeQuery("SELECT * FROM contacto")){
                while (resultSet.next()){
                    System.out.println("ID: " + resultSet.getObject(1));
                    System.out.println("NOMBRE " + resultSet.getObject(2));
                    System.out.println("APELLIDOS: " + resultSet.getObject(3));
                    System.out.println("TELEFOND: " + resultSet.getObject(4));
                }
            };

            int updated = st.executeUpdate("UPDATE contacto SET telefono='11223344' WHERE id=1");
            System.out.println("Registros modificados: " + updated);

            int deleted = st.executeUpdate("DELETE FROM contacto WHERE id=1");
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



}
