package com.chuidiang.examples.postgis;

import org.postgis.LineString;
import org.postgis.PGgeometry;
import org.postgis.Point;

import java.sql.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author fjabellan
 * @date 06/12/2020
 */
public class TrackDao {

    Connection conn;
    private PreparedStatement firstInsertPs;
    private PreparedStatement secondInsertPs;
    private PreparedStatement updateTrackstPs;
    private PreparedStatement insertTrackPointsPs;
    private PreparedStatement createTrajectoryFromPoints;
    private Set<Long> firstInserted = new HashSet<>();

    public TrackDao() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost/chuidiang-examples";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "postgres");
        conn = DriverManager.getConnection(url, props);
    }

    public void removeAll() throws SQLException {
        conn.prepareStatement("delete from geometry_tracks").executeUpdate();
        conn.prepareStatement("delete from point_tracks").executeUpdate();
    }

    public void updateTrajectories(Map<Long,Track> tracks) throws SQLException {
        long start = System.currentTimeMillis();
        firstTrajectoryInsert(tracks);
        secondTrajectoryInsert(tracks);
        followingTrajectoryUpdates(tracks);
        System.out.println(System.currentTimeMillis()-start);
    }

    public void firstTrajectoryInsert(Map<Long,Track> tracks) throws SQLException {
        if (null==firstInsertPs){
            firstInsertPs = conn.prepareStatement("insert into geometry_tracks(fusion_id,start_timestamp,stop_timestamp,trajectory) values (?,?,?,?)");
        }
        tracks.values().forEach(track -> {
            try {
                if (null!=track.getLastPoint()){
                    return;
                }
                Point trackLocation = track.getActualPoint();
                firstInsertPs.setLong(1, track.getId());
                firstInsertPs.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
                firstInsertPs.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                PGgeometry geometry = new PGgeometry(trackLocation);
                firstInsertPs.setObject(4, geometry);
                firstInsertPs.addBatch();
                firstInserted.add(track.getId());
            } catch (Exception e){
                e.printStackTrace();
            }
        });
        firstInsertPs.executeLargeBatch();
    }

    public void secondTrajectoryInsert(Map<Long,Track> tracks) throws SQLException {
        if (null==secondInsertPs){
            secondInsertPs = conn.prepareStatement("update geometry_tracks set stop_timestamp=?,trajectory=? where fusion_id=?");
        }
        tracks.values().forEach(track -> {
            try {
                if (null==track.getLastPoint() || !firstInserted.contains(track.getId())){
                    return;
                }
                secondInsertPs.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
                Point [] points = new Point[2];
                points[0] = track.getLastPoint();
                points[1] = track.getActualPoint();
                PGgeometry geometry = new PGgeometry(new LineString(points));
                secondInsertPs.setObject(2, geometry);
                secondInsertPs.setLong(3,track.getId());
                secondInsertPs.addBatch();
                firstInserted.remove(track.getId());
            } catch (Exception e){
                e.printStackTrace();
            }
        });
        secondInsertPs.executeLargeBatch();
    }

    public void followingTrajectoryUpdates(Map<Long,Track> tracks) throws SQLException {
        if (null==updateTrackstPs){
            updateTrackstPs = conn.prepareStatement("update geometry_tracks set stop_timestamp=? ,trajectory=ST_AddPoint(trajectory,?) where fusion_id=?");
        }
        tracks.values().forEach(track -> {
            try {
                if (null==track.getLastPoint() || firstInserted.contains(track.getId())){
                    return;
                }
                updateTrackstPs.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
                updateTrackstPs.setObject(2, new PGgeometry(track.getLastPoint()));
                updateTrackstPs.setLong(3,track.getId());
                updateTrackstPs.addBatch();
            } catch (Exception e){
                e.printStackTrace();
            }
        });
        updateTrackstPs.executeLargeBatch();
    }

    public void insertPoints(Map<Long,Track> tracks) throws SQLException {
        if (null== insertTrackPointsPs){
            insertTrackPointsPs = conn.prepareStatement("insert into point_tracks (fusion_id, timestamp, point) values(?,?,?)");
        }
        long startTime = System.currentTimeMillis();
        tracks.values().forEach(track -> {
            try {
                insertTrackPointsPs.setLong(1,track.getId());
                insertTrackPointsPs.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
                insertTrackPointsPs.setObject(3, new PGgeometry(track.getActualPoint()));
                insertTrackPointsPs.addBatch();
            } catch (Exception e){
                e.printStackTrace();
            }
        });
        insertTrackPointsPs.executeLargeBatch();
        System.out.println("insert points "+(System.currentTimeMillis()-startTime));
    }

    public void createTrajectoriesFromPoints(int minutes) throws SQLException {
        final long endTime = System.currentTimeMillis();
        final long startTime = endTime-minutes*60000;

        if (null==createTrajectoryFromPoints){
            createTrajectoryFromPoints = conn.prepareStatement(" insert into geometry_tracks (fusion_id, start_timestamp, stop_timestamp, trajectory)" +
                    " select traj.fusion_id, min(traj.timestamp), max(traj.timestamp), ST_MakeLine(traj.point) from (" +
                    "      select fusion_id,timestamp,point from point_tracks " +
                    "   where timestamp<? and timestamp>? order by fusion_id,timestamp asc" +
                    " ) traj group by fusion_id;");
        }

        createTrajectoryFromPoints.setTimestamp(1,new Timestamp(endTime));
        createTrajectoryFromPoints.setTimestamp(2, new Timestamp(startTime));

        createTrajectoryFromPoints.executeUpdate();
    }
}
