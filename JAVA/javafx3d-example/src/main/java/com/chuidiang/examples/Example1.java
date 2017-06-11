package com.chuidiang.examples;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by chuidiang on 11/06/17.
 */
public class Example1 extends Application {
    public static Stage primaryStage;
    public static void main(String[] args)  {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage)  {
        primaryStage.setTitle("Hello World!!");
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        Button button = new Button("Click me!");
        gridPane.add(button,1,1);

        text = new TextField();
        gridPane.add(text, 2, 1);

        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                text.setText("Ouch!");
                if (null==secondWindow){
                    secondWindow=new Stage();
                    secondWindow.setTitle("Second Window");
                    secondWindow.initOwner(primaryStage);
                }
                secondWindow.setX(((Control)event.getSource()).getScene().getWindow().getX());
                secondWindow.setY(((Control)event.getSource()).getScene().getWindow().getY());
                secondWindow.show();

            }
        });
    }

    private Stage secondWindow=null;
    private TextField text;
}
