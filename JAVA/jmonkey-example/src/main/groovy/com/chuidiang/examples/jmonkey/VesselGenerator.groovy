package com.chuidiang.examples.jmonkey

class VesselGenerator {

    public static VesselData[] data = [
        new VesselData(
                name: "Federica",
                high: 1f,
                length: 10f,
                beam: 1f,
                heading: 90,
                speed: 1
        ),
        new VesselData(
                name: "Patricia",
                high: 2f,
                length: 12f,
                beam: 1.6f,
                heading: 110,
                speed: 2
        ),
        new VesselData(
                name: "Manolo",
                high: 0.5f,
                length: 6,
                beam: 0.5f,
                heading: 130,
                speed: 3
        ),
        new VesselData(
                name: "Josechu",
                high: 0.75f,
                length: 3,
                beam: 0.5f,
                heading: 150,
                speed: 4
        )
    ] as VesselData[];
}
