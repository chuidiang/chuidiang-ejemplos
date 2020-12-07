package com.chuidiang.examples.postgis;

import org.postgis.Point;

import java.sql.SQLException;

/**
 * @author fjabellan
 * @date 07/12/2020
 */
public class MainCommons {
    TrackModel model;
    TrackDao dao;

    public MainCommons(){
        model = new TrackModel();
    }

    protected void updateModel() {
        model.getTracks().forEach((id,track)->{
            track.setLastPoint(track.getActualPoint());
            double longitude = track.getActualPoint().getX();
            double latitude = track.getActualPoint().getY();
            double radians = Math.toRadians(track.getCog());
            longitude = longitude+Math.cos(radians)*0.01;
            latitude = latitude+Math.sin(radians)*0.01;
            track.setCog(track.getCog()+Math.random()*10-4.5);
            track.setActualPoint(new Point(longitude,latitude));
        });
    }

    protected void fillModel() {
        for (int i=0;i<10000;i++){
            Track track = new Track();
            track.setId(i);
            track.setCog(Math.random()*360);
            track.setActualPoint(new Point(Math.random()*90-45,Math.random()*90-45));
            model.getTracks().put(Long.valueOf(i),track);
        }
    }

    protected void initDb() throws SQLException, ClassNotFoundException {
        dao = new TrackDao();
        dao.removeAll();
    }

    protected void start() throws SQLException, InterruptedException, ClassNotFoundException {
        fillModel();
        initDb();
    }
}
