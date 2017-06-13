package com.chuidiang.examples;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 * Created by chuidiang on 13/06/17.
 */
public class ExampleFxmlController {
    @FXML private Text actionTarget;
    public void handleSubmitButtonAction(ActionEvent actionEvent) {
        actionTarget.setText("Ouch!!");
    }
}
