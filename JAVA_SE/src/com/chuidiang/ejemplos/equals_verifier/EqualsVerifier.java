package com.chuidiang.ejemplos.equals_verifier;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author fjabellan
 * @date 19/12/2021
 */
@Getter @Setter @EqualsAndHashCode
public final class EqualsVerifier {
    private int a;
    private String b;
    private Long c;
}
