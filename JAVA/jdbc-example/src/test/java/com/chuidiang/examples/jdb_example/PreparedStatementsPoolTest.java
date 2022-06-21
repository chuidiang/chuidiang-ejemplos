package com.chuidiang.examples.jdb_example;

import java.io.IOException;
import java.sql.*;

/**
 * Test para ver si el PreparedStatement se alamacena una sola vez en Base de Datos.
 * Y si va por conexion.
 * @author fjabellan
 * @date 21/06/2022
 */
public class PreparedStatementsPoolTest {
    public static void main(String[] args) throws SQLException, IOException {
        // Creacion de dos conexione.
        Connection connection = DriverManager.getConnection(Main.DB_CONNECTION_URL,"postgres","postgres");
        Connection connection2 = DriverManager.getConnection(Main.DB_CONNECTION_URL,"postgres","postgres");

        // PreparedStatement con la primera conexion.
        // Se ejecuta 10 veces porque el driver postgreSQL, por defecto, a la quinta vez lo manda precompilar al servidor
        // de base de datos.
        PreparedStatement contactQueryById = connection.prepareStatement("SELECT * FROM contacto WHERE id=?");
        for (int i=0;i<10;i++){
            contactQueryById.setInt(1,1);
            contactQueryById.executeQuery();
        }
        contactQueryById.close();

        // Idem con la primera conexion, misma SQL, pero pidiendo otra PreparedStatement.
        for (int i=0;i<10;i++){
            try(PreparedStatement test = connection.prepareStatement("SELECT * FROM contacto WHERE id=?")) {
                test.setInt(1, 1);
                test.executeQuery();
            }
        }

        // Consulta a BD cuantas prepared statement tiene, solo una.
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from pg_prepared_statements");
        System.out.println("-- Conexion 1");
        while(resultSet.next()){
            try {
                for (int i = 1; i < 10; i++) {
                    System.out.println(resultSet.getObject(i));
                }
            } catch(Exception e){
                System.out.println("---------------");
            }
        }

        // Si se hace la consulta con la segunda conexion, no sale nada.
        statement = connection2.createStatement();
        resultSet = statement.executeQuery("select * from pg_prepared_statements");
        System.out.println("-- Conexion 2");
        while(resultSet.next()){
            try {
                for (int i = 1; i < 10; i++) {
                    System.out.println(resultSet.getObject(i));
                }
            } catch(Exception e){
                System.out.println("---------------");
            }
        }

    }
}
