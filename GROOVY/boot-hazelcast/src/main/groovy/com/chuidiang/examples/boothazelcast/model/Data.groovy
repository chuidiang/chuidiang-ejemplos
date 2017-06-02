package com.chuidiang.examples.boothazelcast.model

import groovy.transform.ToString

/**
 * Created by chuidiang on 2/06/17.
 */
@ToString
class Data implements Serializable{
    int id
    String name
    double randomValue
}
