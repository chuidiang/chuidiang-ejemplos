package com.chuidiang.examples.jmonkey;

import com.jme3.math.Vector3f;

public class Utils {
    /**
     *
     * @param headingDegrees  degrees
     * @return
     */
    static public Vector3f headingToVector3f (float headingDegrees){
        float radiansHeading = (float)Math.toRadians(headingDegrees);
        Vector3f vectorHeading = new Vector3f((float)Math.sin(radiansHeading), 0, (float)Math.cos(radiansHeading));
        return vectorHeading;
    };
}
