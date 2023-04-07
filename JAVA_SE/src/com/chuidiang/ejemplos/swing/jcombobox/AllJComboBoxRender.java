package com.chuidiang.ejemplos.swing.jcombobox;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;

/**
 * Ejemplo de cambio de colores de todos los JComboBox incluido el botón de despliegue del menú
 * @author Chuidiang
 * @date 18/02/2023
 */
public class AllJComboBoxRender {
    public static void main(String[] args) {
        final Enumeration<Object> keys = UIManager.getDefaults().keys();
        while(keys.hasMoreElements()){
            System.out.println(keys.nextElement());
        }
        UIManager.put("ComboBox.background",Color.RED);
        UIManager.put("ComboBox.foreground",Color.YELLOW);
        UIManager.put("ComboBox.disabledForeground", Color.YELLOW);
        UIManager.put("ComboBox.disabledBackground", Color.RED);
        UIManager.put("ComboBox.selectionBackground", Color.WHITE);
        UIManager.put("ComboBox.selectionForeground", Color.BLUE);
        UIManager.put("comboBox.buttonHighlight",Color.WHITE);
        UIManager.put("comboBox.buttonDarkShadow",Color.RED);
        UIManager.put("comboBox.buttonShadow",Color.ORANGE);
        UIManager.put("comboBox.buttonDarkShadow",Color.RED);
        UIManager.put("comboBox.buttonBackground",Color.RED);

        // Construyendo ventana
        JFrame window = new JFrame("Main Window");
        JCheckBox checkCombo = new JCheckBox("Disable Combo");
        JComboBox<String> combo = new JComboBox<>();
        combo.setBackground(Color.RED);
        combo.setForeground(Color.YELLOW);
        combo.addItem("Item 1");
        combo.addItem("Item 2");
        combo.addItem("Item 3");
        window.getContentPane().setLayout(new FlowLayout());
        window.getContentPane().add(checkCombo);
        window.getContentPane().add(combo);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // Cuando deshabilitemos el combo, debemos hacerlo editable para que coja los
        // colores del editor. Y cuando lo habilitemos, debemos recordar volver a ponerlo
        // no editable, para que el usuario no pueda modificar el valor escribiendo a mano.
        checkCombo.addActionListener(event->{
            if (checkCombo.isSelected()){
                combo.setEnabled(false);
            } else {
                combo.setEnabled(true);
            }
        });
    }
}
