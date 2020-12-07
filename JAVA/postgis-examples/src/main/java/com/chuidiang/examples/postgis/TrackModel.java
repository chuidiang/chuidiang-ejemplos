package com.chuidiang.examples.postgis;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fjabellan
 * @date 06/12/2020
 */
public class TrackModel {
    @Getter
    private Map<Long,Track> tracks = new HashMap<>();
}
