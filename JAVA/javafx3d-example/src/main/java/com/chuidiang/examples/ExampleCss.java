package com.chuidiang.examples;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by chuidiang on 11/06/17.
 */
public class ExampleCss extends Application {
    public static void main(String[] args)  {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)  {
        buildAndShowMainWindow(primaryStage);
        addButtonAction(primaryStage);
        startClockTimer();
    }

    @Override
    public void stop(){
        clockTimer.cancel();
    }

    private void buildAndShowMainWindow(Stage primaryStage) {
        primaryStage.setTitle("Hello World!!");
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        button = new Button("Click me!");
        button.setId("TheButton");
        gridPane.add(button,1,1);

        text = new TextField();
        gridPane.add(text, 2, 1);

        clockLabel = new Label();
        gridPane.add(clockLabel, 1,2, 2, 1);

        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/form.css").toExternalForm());
        primaryStage.show();
    }

    private void addButtonAction(Stage primaryStage) {
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

    private void startClockTimer() {
        clockTimer = new Timer();
        clockTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        clockLabel.setText(new Date().toString());
                    }
                });

            }
        }, 0, 1000);
    }

    private Stage secondWindow=null;
    private TextField text;
    private Label clockLabel;
    private Button button;
    private Timer clockTimer;
}
