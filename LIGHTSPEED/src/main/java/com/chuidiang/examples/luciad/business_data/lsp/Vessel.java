package com.chuidiang.examples.luciad.business_data.lsp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of={"id"})
public class Vessel {
    private String name;
    private long id;
    private double longitude;
    private double latitude;
    private double heading;
}
