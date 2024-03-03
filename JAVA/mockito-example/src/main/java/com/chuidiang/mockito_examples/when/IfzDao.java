package com.chuidiang.mockito_examples.when;

import java.util.List;

/**
 * Simula una clase con conexión a base de datos y que devuelve un
 * resultado al hacer una consula
 * @author Chuidiang
 * date 03/03/2024
 */
public interface IfzDao {
    Data findById (int id);
    List<Data> findByName(String name);

}
