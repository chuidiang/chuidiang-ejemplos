package com.chuidiang.examples.graphic3d;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.stage.Stage;

/**
 * Created by chuidiang on 15/06/17.
 */
public class Graphic3dExample extends Application{

    public static final int MAX = 50;
    public static final int MIN = -MAX;

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
        for (int y = MIN; y< MAX; y++){
            for (int x = MIN; x< MAX; x++){
                mesh.getPoints().addAll(x, y, function(x,y));
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
        RotationMatrix.matrixRotateNode(meshView, 0,80, -20);
        root.getChildren().add(meshView);

//        Box xAxis = new Box(100,1,1);
//        xAxis.setMaterial(new PhongMaterial(Color.RED));
//        matrixRotateNode(xAxis, 0,80, -20);
//        root.getChildren().add(xAxis);
//
//        Box yAxis = new Box(1,100,1);
//        yAxis.setMaterial(new PhongMaterial());
//        matrixRotateNode(yAxis, 0,80, -20);
//        root.getChildren().add(yAxis);

        ParallelCamera camera = new ParallelCamera();
//        camera.setFieldOfView(30);
        camera.setTranslateZ(-100.0);
        camera.setScaleX(1/7f);
        camera.setScaleY(1/7f);

        camera.setTranslateY(-50.0);
        camera.setTranslateX(-50.0);
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
                        Thread.sleep(200);
                        for (int y = MIN; y<49; y++) {
                            for (int x = MIN; x< MAX; x++) {
                                float value = ((TriangleMesh) meshView.getMesh()).getPoints().get(getZindex(x,y+1));
                                ((TriangleMesh) meshView.getMesh()).getPoints().set(getZindex(x,y), value);
                            }
                        }
                        for (int x = MIN; x< MAX; x++){
                            float value= function(x/5.0, yCalculus/3.0);
                            ((TriangleMesh) meshView.getMesh()).getPoints().set(getZindex(x,49), 1);
                        }
                        yCalculus+=1f;

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });//.start();
    }

    public static float function(double x, double y) {
        return (float) (Math.sin(x) * 2.0 + Math.sin(y) * 3.0);
    }

    private int getXindex (float x, float y){
        x+= MAX;
        y+= MAX;
        return (int)(x*3 + y*300);
    }
    private int getYindex (float x, float y){
        return getXindex(x,y)+1;
    }
    private int getZindex (float x, float y){
        return getXindex(x,y)+2;
    }

}
