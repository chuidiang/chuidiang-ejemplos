package org.postgresql.jdbc;

import java.sql.PreparedStatement;

/**
 * @author fjabellan
 * @date 21/06/2022
 */
public class Trampa {
    public static void print(PreparedStatement preparedStatement){
        System.out.println(((PgPreparedStatement)preparedStatement).preparedQuery.query);
        System.out.println(((PgPreparedStatement)preparedStatement).preparedQuery.query.getClass().getName() + "@" +
                Integer.toHexString(((PgPreparedStatement)preparedStatement).preparedQuery.query.hashCode()));
    }
}
