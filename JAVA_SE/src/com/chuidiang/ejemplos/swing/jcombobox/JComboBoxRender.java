package com.chuidiang.ejemplos.swing.jcombobox;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

/**
 * Ejemplo de cambio de colores en un JComboBox incluido el botón de despliegue del menú
 * @author Chuidiang
 * @date 18/02/2023
 */
public class JComboBoxRender {
    public static void main(String[] args) {
        // Construyendo ventana
        JFrame window = new JFrame("Main Window");
        JComboBox<String> combo = new JComboBox<>();

        // Cambiamos la flecha
        combo.setUI(new MyArrow());

        // Cambiamos el render y los colores por defecto.
        combo.setRenderer(new MyComboBoxRender());
        combo.setBackground(Color.RED);
        combo.setForeground(Color.YELLOW);


        // Seguimos contruyendo ventana
        combo.addItem("Item 1");
        combo.addItem("Item 2");
        combo.addItem("Item 3");
        window.getContentPane().add(combo);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    /**
     * Render del Combo. Decide los colores a mostrar.
     */
    public static class MyComboBoxRender extends BasicComboBoxRenderer {
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            // La llamada a super hace que coja los colores defecto según esté seleccionado, tenga foco, etc.
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            // Cambiamos color de fondo y letra del item del combo desplegado.
            if (isSelected) {
                this.setForeground(Color.BLUE);
                this.setBackground(Color.WHITE);
            } else {
                this.setBackground(Color.YELLOW);
                this.setForeground(Color.RED);
            }

            // list dibuja el elemento visible del combo cuando no está desplegado.
            list.setSelectionBackground(Color.RED);
            list.setSelectionForeground(Color.YELLOW);

            return this;
        }
    }

    /**
     * Crea el botón para el combo.
     */
    public static class MyArrow extends BasicComboBoxUI {
        @Override
        protected JButton createArrowButton() {
            // Usamos BasicArrowButton que es el de defecto, poniéndole los colores que nos apetezcan.
            // Pero puedes crear aquí tu propio botón con el estilo que quieras.
            return new BasicArrowButton(SwingConstants.SOUTH, Color.WHITE, Color.RED, Color.BLACK, Color.LIGHT_GRAY);
        }
    }
}
