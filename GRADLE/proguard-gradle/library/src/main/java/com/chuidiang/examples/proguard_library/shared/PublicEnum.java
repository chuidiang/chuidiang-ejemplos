package com.chuidiang.examples.proguard_library.shared;

import java.util.Random;

/**
 * @author fjabellan
 * @date 01/11/2020
 */
public enum PublicEnum {
    UNO,
    DOS,
    TRES;

    private int value;

    PublicEnum(){
        value= new Random().nextInt();
    }
}
