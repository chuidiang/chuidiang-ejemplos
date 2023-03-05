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
        JFrame window = new JFrame("Main Window");
        JComboBox combo = new JComboBox();

        // Definimos como crear
//        combo.setUI(new MyArrow());
        combo.setForeground(Color.RED);
        combo.setBackground(Color.WHITE);
//        combo.setRenderer(new MyComboBoxRender());
        combo.addItem("Item 1");
        combo.addItem("Item 2");
        combo.addItem("Item 3");
        window.getContentPane().add(combo);


        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static class MyComboBoxRender extends BasicComboBoxRenderer.UIResource {
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            System.out.println(""+isSelected+" - "+cellHasFocus);
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            this.setOpaque(true);
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLUE);
            list.setSelectionBackground(Color.RED);
            return this;
        }
    }

    public static class MyArrow extends BasicComboBoxUI {
        @Override
        protected JButton createArrowButton() {
            return new BasicArrowButton(BasicArrowButton.SOUTH, Color.WHITE, Color.RED, Color.BLACK, Color.LIGHT_GRAY);
        }

//        @Override
//        protected ListCellRenderer<Object> createRenderer() {
//            return new MyComboBoxRender();
//        }
    }
}
