package com.chuidiang.mockito_examples;

import java.sql.*;

/**
 * @author fjabellan
 * @date 15/11/2020
 */
public class DataBaseClass {
    public String getStringFromDataBase() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String line=null;
        try (Connection conexion = DriverManager.getConnection(
                "jdbc:postgresql://servidor:5432/database",
                "usuario",
                "password")
        )
        {
            final PreparedStatement preparedStatement = conexion.prepareStatement("select string from table_with_string limit 1");
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                line= resultSet.getString(1);
            }
        }
        return line;
    }
}
