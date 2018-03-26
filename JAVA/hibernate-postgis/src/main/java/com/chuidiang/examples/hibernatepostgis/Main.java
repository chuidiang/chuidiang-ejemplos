package com.chuidiang.examples.hibernatepostgis;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();

        try {
            insertGeometry(sessionFactory);
            queryGeometry(sessionFactory);
            updateGeometry(sessionFactory);
            queryGeometry(sessionFactory);
            deleteGeometry(sessionFactory);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
           sessionFactory.close();
        }
    }

    private static void deleteGeometry(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        Query query = session.createQuery("delete from TheData where id=:id");
        query.setParameter("id",1L);
        query.executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    private static void updateGeometry(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        TheData theData = session.get(TheData.class, 1L);

        CoordinateSequence seq = new CoordinateArraySequence(new Coordinate[]{
                new Coordinate(5,6),
                new Coordinate(7,8),
                new Coordinate(9,10)});

        LineString geometry = new LineString(seq,new GeometryFactory());
        theData.setTheGeometry(geometry);

        session.saveOrUpdate(theData);
        session.getTransaction().commit();
        session.close();
    }

    private static void queryGeometry(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        List<TheData> list = session.createQuery("from TheData").list();

        if (list.size()==0){
            System.out.println("There are no entities");
        } else {
            for (TheData data : list) {
                System.out.println(data);
            }
        }

        session.getTransaction().commit();
        session.close();
    }

    private static void insertGeometry(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        TheData data = new TheData();
        data.setId(1L);
        CoordinateSequence seq = new CoordinateArraySequence(new Coordinate[]{new Coordinate(1,2), new Coordinate(3,4)});

        LineString geometry = new LineString(seq,new GeometryFactory());
        data.setTheGeometry(geometry);

        session.save(data);
        session.getTransaction().commit();
        session.close();
    }
}
