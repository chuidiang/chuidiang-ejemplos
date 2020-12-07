package com.chuidiang.examples.postgis;

import lombok.Getter;
import lombok.Setter;
import org.postgis.Point;

/**
 * @author fjabellan
 * @date 06/12/2020
 */
@Getter @Setter
public class Track {
    Point actualPoint;
    Point LastPoint;
    double cog;
    long id;
    long timestamp;
}
