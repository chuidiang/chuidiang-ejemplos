package com.chuidiang.lombok_example;

import lombok.*;

/**
 * @author fjabellan
 * @date 21/11/2020
 */
@Getter
@Setter
@ToString
//@ToString(of={"anInteger","anString"})
@EqualsAndHashCode
//@EqualsAndHashCode(of={"anInteger"})
@AllArgsConstructor
@NoArgsConstructor
// @RequiredArgsConstructor  // Añade constructor con el atributo que está anotado @NonNull
// @lombok.Data
public class Data {
    private int anInteger;
    // @Getter(value=AccessLevel.NONE)
    // @NonNull
    private String anString;
}
