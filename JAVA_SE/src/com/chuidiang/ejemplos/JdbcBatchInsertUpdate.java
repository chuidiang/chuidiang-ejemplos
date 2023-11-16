package com.chuidiang.ejemplos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Ejemplo de Batch insert y update con JDBC.
 * 
 * Para ejecutar este programa necesitas un connector con la base de datos, como
 * mysql-connector-java-5.1.12.jar, segun el servidor de MySQL que tengas. U otro
 * connector de otra base de datos si tienes otro servidor de base de datos
 * 
 * @author Chuidiang
 */
public class JdbcBatchInsertUpdate {

   public static void main(String[] args) {
      try {
         // Los datos que vamos a introducir
         String[][] datos = { { "pedro", "gomez", "perez" },
               { "juan", "alvarez", "sanchez" },
               { "antonio", "rodriquez", "lopez" } };

         // La conexión a la base de datos
         try(Connection conexion = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/pruebas", "root", "")) {

            // El prepared statement para los insert
            try(PreparedStatement ps = conexion
                    .prepareStatement(
                            "insert into persona (nombre,apellido1,apellido2) values (?,?,?)",
                            Statement.RETURN_GENERATED_KEYS)) {

               // Vamos a�adiendo datos y a�adiendolos al batch.
               for (String[] dato : datos) {
                  ps.setString(1, dato[0]);
                  ps.setString(2, dato[1]);
                  ps.setString(3, dato[2]);

                  ps.addBatch();
               }

               // Ejecutamos el batch, devuelve un array de forma que cada posicion
               // contiene
               // el numero de filas afectadas por cada insert.
               int[] exitos = ps.executeBatch();

               for (int i = 0; i < datos.length; i++) {
                  System.out.println("La insercion de " + datos[i][0] + "da "
                          + exitos[i] + " inserciones");
               }

               // Claves que se han generado. Fijate al crear el PreparedStatement que
               // se ha puesto la opcion
               // Statement.RETURN_GENERATED_KEYS
               ResultSet rs = ps.getGeneratedKeys();
               int contador = 0;
               while (rs.next()) {
                  System.out.println(datos[contador][0] + " tiene clave " + rs.getInt(1));
                  contador++;
               }
            }
         }
         
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

}
