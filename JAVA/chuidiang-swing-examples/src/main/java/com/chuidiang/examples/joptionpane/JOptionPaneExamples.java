package com.chuidiang.examples.joptionpane;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * @author fjabellan 16/10/2023
 */
public class JOptionPaneExamples {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JOptionPane Examples");
        frame.getContentPane().setLayout(new GridLayout(5,4));

        addBoolean(frame);
        addBooleanRadio(frame);
        addInteger(frame);
        addDouble(frame);
        addDoubleText(frame);
        addRadioButton(frame);
        addLogin(frame);
        addBackground(frame);
        addIcon(frame);
        addBreakLine(frame);
        addCustomButtonsAsText(frame);
        addArrayAsJtable(frame);
        addArrayAsString(frame);
        addTimeoutMessage(frame);
        addTimeoutInput(frame);

        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Se muestra un JOptionPane con mensaje que se cierra solo, pasados 5 segundos, si
     * el usuario no lo cierra antes.
     * Necesitamos el JDialog del JOptionPane para poder cerrarlo, por ello no podemos
     * llamar directamente a los métodos estáticos de JOptionPane. En su lugar, instanciamos
     * JOPtionPane y le pedimos que cree y nos devuelva el JDialog.
     * @param frame
     */
    private static void addTimeoutInput(JFrame frame) {
        JButton timeoutInputButton = new JButton("TimeInput");
        frame.getContentPane().add(timeoutInputButton);
        timeoutInputButton.addActionListener(event ->{

            // Se instancia y configura JOptionPane
            JOptionPane optionPane = new JOptionPane("5 segundos para meter un dato",
                    JOptionPane.QUESTION_MESSAGE);
            optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
            optionPane.setInitialValue("Soy el dato");
            optionPane.setWantsInput(true);

            // Se obtiene el JDialog
            JDialog dialog = optionPane.createDialog(timeoutInputButton, "Cierrame!");

            // Se lanza un hilo que cierre el JDialog pasados 5 segundos.
            // Es importante lanzarlo antes de hacer visible el dialogo, puesto que al ser un
            // dialogo modal, setVisible(true) bloquea el codigo hasta que se cierre el dialogo.
            new Thread( () -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dialog.setVisible(false);
            }).start();

            // Se visualiza el dialogo. Nos quedamos bloqueados en esta llamada hasta que alguien
            // cierre el dialogo.
            dialog.setVisible(true);

            // Cerrado el dialogo, liberamos memoria
            dialog.dispose();

            // Analizamos lo que ha pasado
            //
            // getValue() es el botón que ha pulsado el usuario. Si no ha tenido tiempo de pulsar ninguno
            // getValue() es UNINITIALIZED_VALUE, así que es el hilo el que ha cerrado el dialogo
            if (JOptionPane.UNINITIALIZED_VALUE.equals(optionPane.getValue())) {
                System.out.println("La ventana se ha cerrado por timeout");
                return;
            }

            // Si es el usuario el que ha cerrado el dialogo, el valor que ha introducido getInputValue()
            // vale UNINITIALIZED_VALUE si ha cancelado la operación, no ha introducido dato.
            if (JOptionPane.UNINITIALIZED_VALUE.equals(optionPane.getInputValue())){
                System.out.println("El usuario ha cancelado la operacion");
                return;
            }

            // Si no se cumple ninguna de las condiciones anteriores, es que ha pulsado OK/Aceptar, así
            // que getInputValue() devuelve lo que ha introducido.
            System.out.println("El usuario ha introducido " + optionPane.getInputValue());
        });

    }

    private static void addTimeoutMessage(JFrame frame) {
        JButton timeoutMessageButton = new JButton("TimeMessage");
        frame.getContentPane().add(timeoutMessageButton);
        timeoutMessageButton.addActionListener(event ->{

            JOptionPane optionPane = new JOptionPane("Si no me cierras,\n me cierro yo solo",
                    JOptionPane.OK_CANCEL_OPTION);

            JDialog dialog = optionPane.createDialog(timeoutMessageButton, "Cierrame!");
            new Thread( () -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dialog.setVisible(false);
            }).start();

            dialog.setVisible(true);
            dialog.dispose();
        });
    }

    private static void addDoubleText(JFrame frame) {
        JButton doubleTextButton = new JButton("DoubleText");
        frame.getContentPane().add(doubleTextButton);

        doubleTextButton.addActionListener(event -> {
            String enteredValue = JOptionPane.showInputDialog(doubleTextButton, "Introduce un Double", 32.4);
            if (null==enteredValue){
                System.out.println("El usuario ha cancelado la operacion");
            } else {
                System.out.println("El usuario ha introducido "+Double.parseDouble(enteredValue));
            }
        });
    }

    private static void addArrayAsString(JFrame frame) {
        JButton arrayButton = new JButton("ArrayString");
        frame.getContentPane().add(arrayButton);

        arrayButton.addActionListener(event -> {
            int [] array = {4,5,2,3,1};
            String arrayAsString= Integer.toString(array[0]);
            for (int i = 1; i < array.length; i++) {
                arrayAsString += ","+array[i];
            }
            final String returnedArray = JOptionPane.showInputDialog(arrayButton, "Introduce Array de int", arrayAsString);
            if (null==returnedArray){
                System.out.println("Usuario ha cancelado la entrada");
            } else {
                final String[] tokens = returnedArray.split(",");
                int [] newArray = new int[tokens.length];
                for (int i = 0; i < tokens.length; i++) {
                    newArray[i] = Integer.parseInt(tokens[i]);
                }
                System.out.println("El usuario ha introducido "+Arrays.toString(newArray));
            }
        });
    }

    /**
     * Mete un array de Object[] en un JOptionPane con un JTable editable.
     * Permite mostrar y modificar un array en un JOptionPane
     * @param frame
     */
    private static void addArrayAsJtable(JFrame frame) {
        JButton arrayButton = new JButton("ArrayJTable");
        frame.getContentPane().add(arrayButton);

        arrayButton.addActionListener(event -> {
            JTable table = new JTable(Util.getTableModel(new Integer[]{5,3,8,9}));
            int returnedValue = JOptionPane.showOptionDialog(arrayButton, table, "Array", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (JOptionPane.OK_OPTION==returnedValue){
                System.out.println(Arrays.toString(Util.getArray(table.getModel())));
            } else {
                System.out.println("cancelado");
            }
        });
    }

    /**
     * JOptionPane con botones totalmente definidos por código. No usa los botones de defecto de OK, Cancel, etc.
     * @param frame
     */
    private static void addCustomButtonsAsText(JFrame frame) {
        JButton customButtonsButton = new JButton("CustomButtons");
        frame.getContentPane().add(customButtonsButton);

        customButtonsButton.addActionListener(action -> {

            int buttonPressed = JOptionPane.showOptionDialog(customButtonsButton, new JTextField(30), "The title",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    new String[]{"Button 0", "Button 1", "Button 2", "Button 3", "Button 4"}, "Button 3");

            System.out.println("Boton Pulsado "+buttonPressed);
        });
    }

    /**
     * Tres formas de dar formato (estilo) al texto mostrado en el JOptionPane
     * @param frame
     */
    private static void addBreakLine(JFrame frame) {
        JButton breakButton = new JButton("Break");
        frame.getContentPane().add(breakButton);
        breakButton.addActionListener(action -> {
            JOptionPane.showMessageDialog(breakButton, "Esta linea\nesta partida\nen tres");

            JOptionPane.showMessageDialog(breakButton, "<html>Todo esta en <b>negrita</b><br>Pero podemos poner <i>cursiva</i></html>");

            final JLabel label = new JLabel("<html>Esta tiene <b>negrita</b><br>y <i>cursiva</i><br>y hemos personalizado la fuente</html>");
            label.setFont(new Font("Arial", Font.PLAIN, 12));
            label.setOpaque(true);
            label.setForeground(Color.RED);
            JOptionPane.showMessageDialog(breakButton, label);
        });

    }

    /**
     * Cambio de color de fondo de JOptionPane.
     * @param frame
     */
    private static void addBackground(JFrame frame) {
        JButton backgroundButton = new JButton("Background");
        frame.getContentPane().add(backgroundButton);
        backgroundButton.addActionListener(action -> {

            JOptionPane optionPane = new JOptionPane("¿Quieres guardar el fichero?",
                    JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
            JDialog dialog = optionPane.createDialog(backgroundButton, "Salvar fichero");

            Util.setBackgroundColor(dialog, Color.LIGHT_GRAY);

            dialog.setVisible(true);
            if (null != optionPane.getValue() && JOptionPane.OK_OPTION == (Integer) optionPane.getValue()) {
                System.out.println("El usuario quiere salvar fichero");
            } else {
                System.out.println("El usuario ha cancelado");
            }
            dialog.dispose();
        });
    }

    /**
     * Poner un icono propio en JOptionPane
     * @param frame
     */
    private static void addIcon(JFrame frame) {
        JButton backgroundButton = new JButton("Background");
        frame.getContentPane().add(backgroundButton);
        backgroundButton.addActionListener(action ->
            JOptionPane.showConfirmDialog(backgroundButton, "¿Quieres guardar el fichero?", "Salvar Fichero",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                    new ImageIcon("src/main/files/Actions-document-save-icon.png"))
        );
    }

    /**
     * JOptionPane con un formulario de Login
     * @param frame
     */
    private static void addLogin(JFrame frame) {
        JButton loginButton = new JButton("Login");
        frame.getContentPane().add(loginButton);

        loginButton.addActionListener(action -> {
            LoginForm loginForm = new LoginForm();

            int returnedValue = JOptionPane.showOptionDialog(loginButton, loginForm, "Login",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (JOptionPane.OK_OPTION == returnedValue) {
                System.out.println("El usuario ha introducido usuario = " + loginForm.getUserName() +
                        "  password = " + Arrays.toString(loginForm.getPassword()));
            } else {
                System.out.println("El usuario ha cancelado");
            }
        });
    }

    /**
     * JOptionPane con tres radio button.
     * @param frame
     */
    private static void addRadioButton(JFrame frame) {
        JButton radioButton = new JButton("Radio");
        frame.getContentPane().add(radioButton);

        radioButton.addActionListener(action -> {
            RadioButtonPanel radioButtonPanel = new RadioButtonPanel();

            int returnedValue = JOptionPane.showOptionDialog(radioButton, radioButtonPanel, "Elige Color",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (JOptionPane.OK_OPTION == returnedValue) {
                System.out.println("El usuario ha introducido " + radioButtonPanel.getSelectedColor());
            } else {
                System.out.println("El usuario ha cancelado");
            }
        });
    }

    private static void addBooleanRadio(JFrame frame) {
        JButton booleanRadioButton = new JButton("BooleanRadio");
        frame.getContentPane().add(booleanRadioButton);

        booleanRadioButton.addActionListener(action -> {
            BooleanRadioButtonPanel booleanRadioButtonPanel = new BooleanRadioButtonPanel();

            int returnedValue = JOptionPane.showOptionDialog(booleanRadioButton, booleanRadioButtonPanel, "Elige Boolean",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (JOptionPane.OK_OPTION == returnedValue) {
                System.out.println("El usuario ha introducido " + booleanRadioButtonPanel.getBooleanValue());
            } else {
                System.out.println("El usuario ha cancelado");
            }
        });
    }

    /**
     * JOptionPane para pedir un entero
     * @param frame
     */
    private static void addInteger(JFrame frame) {
        JButton numberButton = new JButton("Integer");
        frame.getContentPane().add(numberButton);
        numberButton.addActionListener(action -> {
            NumberJTextField textField = new NumberJTextField(OnlyNumbersDocumentFilter.REGEX_INT);

            int returnedVale = JOptionPane.showOptionDialog(numberButton, textField, "Introduce un Entero",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (JOptionPane.OK_OPTION == returnedVale){
                System.out.println("El usuario ha introducido " + textField.getValue());
            } else {
                System.out.println("El usuario ha cancelado");
            }
        });
    }

    /**
     * JOptionPane para pedir un double
     * @param frame
     */
    private static void addDouble(JFrame frame) {
        JButton numberButton = new JButton("Double");
        frame.getContentPane().add(numberButton);
        numberButton.addActionListener(action -> {
            NumberJTextField textField = new NumberJTextField(OnlyNumbersDocumentFilter.REGEX_DOUBLE);

            int returnedVale = JOptionPane.showOptionDialog(numberButton, textField, "Introduce un Double",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (JOptionPane.OK_OPTION == returnedVale){
                System.out.println("El usuario ha introducido " + textField.getValue());
            } else {
                System.out.println("El usuario ha cancelado");
            }
        });
    }

    /**
     * JOptionPane para seleccionar un boolean (true o false)
     * @param frame
     */
    private static void addBoolean(JFrame frame) {
        JButton booleanButton = new JButton("Boolean");
        frame.getContentPane().add(booleanButton);
        booleanButton.addActionListener(action -> {
            Boolean selectedValue = (Boolean) JOptionPane.showInputDialog(booleanButton, "Boolean", "Selecciona Boolean",
                    JOptionPane.QUESTION_MESSAGE, null, new Boolean[]{Boolean.TRUE, Boolean.FALSE},
                    Boolean.FALSE);
            if (null == selectedValue) {
                System.out.println("Se ha cancelado");
            } else {
                System.out.println("Valor seleccionado " + selectedValue);
            }
        });
    }
}
