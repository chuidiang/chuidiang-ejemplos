package com.chuidiang.examples;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

/**
 * Created by chuidiang on 15/06/17.
 */
public class GraphicsExample extends Application{
    public static void main(String[] args) {
        launch(args);

    }

    private Group circles;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        primaryStage.setScene(scene);

        circles = new Group();
        for (int i = 0; i < 30; i++) {
            Circle circle = new Circle(150+10*i, Color.web("white", 0.05));
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.web("white", 0.16));
            circle.setStrokeWidth(4);
            circles.getChildren().add(circle);
        }
        root.getChildren().add(circles);


        primaryStage.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<100;i++) {
                    try {
                       Thread.sleep(100);
                       Circle circle = ((Circle) circles.getChildren().get(0));
                       circle.setRadius(150 + 150*Math.sin(i/5.0));
                    } catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
