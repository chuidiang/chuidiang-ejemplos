package com.chuidiang.examples;

import com.sun.javafx.geom.transform.Affine3D;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.sg.prism.NGNode;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
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
    public void stop(){
        System.exit(0);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        primaryStage.setScene(scene);

        TriangleMesh mesh = new TriangleMesh();
        for (int y=-50;y<50;y++){
            for (int x = -50; x<50; x++){
                mesh.getPoints().addAll(x, y, (float)(Math.sin(x/5.0)*2.0+Math.sin(y/3.0)*3.0) );
            }
        }
        mesh.getTexCoords().addAll(0,0);

        for (int y=0;y<99;y++) {
            for (int x = 0; x < 99; x++) {
                mesh.getFaces().addAll(x+y*100, 0, x+y*100 + 100, 0, x+y*100 + 101, 0, x+y*100, 0, x+y*100 + 101, 0, x+y*100 + 1, 0);
            }
        }
        meshView = new MeshView(mesh);


        meshView.setDrawMode(DrawMode.FILL);
        meshView.setMaterial(new PhongMaterial(Color.AQUAMARINE));
        //meshView.setRotationAxis(new Point3D(0, -5, 0));
        //meshView.setRotate(10);
//        meshView.setTranslateY(100);
//        meshView.setTranslateZ(200);
        double cc = 45;
//        meshView.getTransforms().add(new Rotate(0,0,0));
        matrixRotateNode(meshView, 0,80, -20);
        root.getChildren().add(meshView);

        Box xAxis = new Box(100,1,1);
        xAxis.setMaterial(new PhongMaterial(Color.RED));
        matrixRotateNode(xAxis, 0,80, -20);
        root.getChildren().add(xAxis);

        Box yAxis = new Box(1,100,1);
        yAxis.setMaterial(new PhongMaterial());
        matrixRotateNode(yAxis, 0,80, -20);
        root.getChildren().add(yAxis);

        PerspectiveCamera camera = new PerspectiveCamera(true);
//        camera.setFieldOfView(30);
        camera.setTranslateZ(-200.0);

//        camera.setTranslateY(500.0);
//        camera.setTranslateX(200.0);
        camera.setNearClip(0.1);
        camera.setFarClip(400.0);

        scene.setCamera(camera);


        primaryStage.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                float yCalculus = 50f;
                while (true) {

                    try {
                        Thread.sleep(50);
                        for (int y=0;y<99;y++) {
                            for (int x=0;x<100;x++) {
                                float value = ((TriangleMesh) meshView.getMesh()).getPoints().get(getZindex(x,y+1));
                                ((TriangleMesh) meshView.getMesh()).getPoints().set(getZindex(x,y), value);
                            }
                        }
                        for (int x = -50; x<50; x++){
                            float value= (float)(Math.sin(x/5.0)*2.0+Math.sin(yCalculus/3.0)*3.0) ;
                            ((TriangleMesh) meshView.getMesh()).getPoints().set(getZindex(x+50,99), value);
                        }
                        yCalculus+=1f;

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private int getXindex (float x, float y){
        return (int)(x*3 + y*300);
    }
    private int getYindex (float x, float y){
        return (int)(x*3 + y*300+1);
    }
    private int getZindex (float x, float y){
        return (int)(x*3 + y*300+2);
    }

    private void matrixRotateNode(Node n, double alf, double bet, double gam){
        alf = alf*Math.PI/180.0;
        bet = bet*Math.PI/180.0;
        gam = gam*Math.PI/180.0;
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
