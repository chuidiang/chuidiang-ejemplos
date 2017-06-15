package com.chuidiang.examples;

import com.sun.javafx.sg.prism.NGNode;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
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

    public MeshView meshView;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        primaryStage.setScene(scene);

        TriangleMesh mesh = new TriangleMesh();
        for (int y=0;y<100;y++){
            for (int x = 0; x<100; x++){
                mesh.getPoints().addAll(x*10, y*10, (float)(Math.sin(x/5.0)*20.0+Math.sin(y/3.0)*25.0) );
            }
        }
        mesh.getTexCoords().addAll(0,0);

        for (int y=0;y<99;y++) {
            for (int x = 0; x < 99; x++) {
                mesh.getFaces().addAll(x+y*100, 0, x+y*100 + 100, 0, x+y*100 + 101, 0, x+y*100, 0, x+y*100 + 101, 0, x+y*100 + 1, 0);
            }
        }
        meshView = new MeshView(mesh);

        meshView.setDrawMode(DrawMode.LINE);
        meshView.setMaterial(new PhongMaterial());

//        meshView.setTranslateY(100);
//        meshView.setTranslateZ(200);
        double cc = Math.PI/4.0;
        matrixRotateNode(meshView, 0,cc*1.8, -0.2);
        root.getChildren().add(meshView);

        PerspectiveCamera camera = new PerspectiveCamera(false);
        camera.setTranslateY(100.0);
        scene.setCamera(camera);


        primaryStage.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);

                        float value = ((TriangleMesh)meshView.getMesh()).getPoints().get(332);
                        ((TriangleMesh)meshView.getMesh()).getPoints().set(332,value-(float)1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void matrixRotateNode(Node n, double alf, double bet, double gam){
        double A11=Math.cos(alf)*Math.cos(gam);
        double A12=Math.cos(bet)*Math.sin(alf)+Math.cos(alf)*Math.sin(bet)*Math.sin(gam);
        double A13=Math.sin(alf)*Math.sin(bet)-Math.cos(alf)*Math.cos(bet)*Math.sin(gam);
        double A21=-Math.cos(gam)*Math.sin(alf);
        double A22=Math.cos(alf)*Math.cos(bet)-Math.sin(alf)*Math.sin(bet)*Math.sin(gam);
        double A23=Math.cos(alf)*Math.sin(bet)+Math.cos(bet)*Math.sin(alf)*Math.sin(gam);
        double A31=Math.sin(gam);
        double A32=-Math.cos(gam)*Math.sin(bet);
        double A33=Math.cos(bet)*Math.cos(gam);

        double d = Math.acos((A11+A22+A33-1d)/2d);
        if(d!=0d){
            double den=2d*Math.sin(d);
            Point3D p= new Point3D((A32-A23)/den,(A13-A31)/den,(A21-A12)/den);
            n.setRotationAxis(p);
            n.setRotate(Math.toDegrees(d));
        }
    }
}
