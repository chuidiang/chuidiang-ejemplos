package com.chuidiang.lombok_example;

import lombok.*;

/**
 * @author fjabellan
 * @date 21/11/2020
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Data {
    private int anInteger;
    private String anString;
}
