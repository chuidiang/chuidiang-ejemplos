package com.chuidiang.examples.hibernatepostgis;

import com.vividsolutions.jts.geom.Geometry;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TheData {
    @Id
    private long id;
    private Geometry theGeometry;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Geometry getTheGeometry() {
        return theGeometry;
    }

    public void setTheGeometry(Geometry theGeometry) {
        this.theGeometry = theGeometry;
    }

    @Override
    public String toString() {
        return "TheData{" +
                "id=" + id +
                ", theGeometry=" + theGeometry +
                '}';
    }
}
