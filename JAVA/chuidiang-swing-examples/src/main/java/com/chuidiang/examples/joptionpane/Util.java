package com.chuidiang.examples.joptionpane;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

/**
 * @author fjabellan 16/10/2023
 */
public class Util {
    /**
     * Cambio de color de background recursivo desde el componente que se le pasa
     * hacia todos sus hijos, todos los niveles que haya.
     * @param component
     * @param color
     */
    public static void setBackgroundColor (Component component, Color color){
        component.setBackground(color);
        if (component instanceof Container){
            for (Component component1 : ((Container) component).getComponents()) {
                setBackgroundColor(component1, color);
            }
        }
    }

    /**
     * Convierte un array en un TableModel de una sola fila y N columnas
     * @param array
     * @return
     */
    public static TableModel getTableModel (Object [] array){
        DefaultTableModel tableModel = new DefaultTableModel(1, array.length);
        for (int i = 0; i < array.length; i++) {
            tableModel.setValueAt(array[i], 0, i);
        }
        return tableModel;
    }

    /**
     * Convierte un TableModel de una fila y N columnas en un array de N elementos.
     * @param tableModel
     * @return
     */
    public static Object[] getArray(TableModel tableModel){
        Object[] array = new Object[tableModel.getColumnCount()];
        for (int i = 0; i < array.length; i++) {
            array[i] = tableModel.getValueAt(0, i);
        }

        return array;
    }
}
