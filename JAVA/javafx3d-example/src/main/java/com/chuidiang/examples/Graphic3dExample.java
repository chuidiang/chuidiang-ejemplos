package com.chuidiang.examples;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Mesh;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;

/**
 * Created by chuidiang on 15/06/17.
 */
public class Graphic3dExample extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        primaryStage.setScene(scene);

        TriangleMesh mesh = new TriangleMesh();
        for (int y=0;y<2;y++){
            for (int x = 0; x<100; x++){
                mesh.getPoints().addAll(x*10, y*10, (float)(Math.sin(x/10.0)*20.0) );
            }
        }


        MeshView meshView = new MeshView(mesh);
        root.getChildren().add(meshView);


        primaryStage.show();
    }
}
