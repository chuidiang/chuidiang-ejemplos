package com.chuidiang.examples.postgis;

import org.postgis.LineString;
import org.postgis.PGgeometry;
import org.postgis.Point;

import java.sql.*;
import java.util.Properties;

/**
 * @author fjabellan
 * @date 06/12/2020
 */
public class PostigsMain {

    private Connection conn;

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        new PostigsMain().start();

    }

    public void start() throws SQLException, ClassNotFoundException, InterruptedException {
        connect();
        delete();
        insertStart();
        updateStart();
        close();
    }


    private void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost/chuidiang-examples";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "postgres");
        conn = DriverManager.getConnection(url, props);
    }

    private void insertStart() throws SQLException {
        float longitude = 10;
        float latitude = 20;
        PreparedStatement ps = conn.prepareStatement("insert into geometry_tracks(id,timestamp,trajectory) values (?,?,?)");
        for (int i = 1; i < 10000; i++) {
            ps.setLong(1, i);
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            Point[] points = new Point[2];
            points[0]=new Point(longitude,latitude);
            points[1]=new Point(longitude,latitude);
            PGgeometry geometry = new PGgeometry(new LineString(points));
            ps.setObject(3, geometry);
            ps.executeUpdate();
            longitude++;
            latitude++;
        }
    }

    private void updateStart() throws InterruptedException, SQLException {
        int k=0;
        float longitude = 11.1f;
        float latitude = 21.1f;
        PreparedStatement ps = conn.prepareStatement("update geometry_tracks set timestamp=? ,trajectory=ST_AddPoint(trajectory,ST_MakePoint(?,?)) where id=?");
        while (true) {
            long started = System.currentTimeMillis();
            for (int i = 1; i < 10000; i++) {
                ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
                ps.setFloat(2,longitude);
                ps.setFloat(3,latitude);
                ps.setLong(4, i);
                ps.addBatch();
                longitude++;
                latitude++;
            }
            ps.executeLargeBatch();
            k++;
            if (k==Integer.MAX_VALUE){
                break;
            }
            System.out.println(System.currentTimeMillis()-started);
            Thread.sleep(3000);
        }
    }
    private void delete() throws SQLException {
        conn.prepareStatement("delete from geometry_tracks").executeUpdate();
    }


    private void close() {
        if (null != conn) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
