package com.chuidiang.examples;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Sphere;

import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by JAVIER on 02/05/2016.
 */
public class JavaFX3DExample {

   private static JFrame mainFrame = new JFrame("Java FX 3D Example");
   private static JFXPanel fxPanel = new JFXPanel();
   private static Sphere sphere;

   public static void initFx(JFXPanel fxPanel) {

      sphere = new Sphere(100);
      sphere.setLayoutX(200);
      sphere.setLayoutY(200);
      Group root = new Group(sphere);

      Scene scene = new Scene(root, 900, 600);
      fxPanel.setScene(scene);
      fxPanel.setSize(900, 600);
   }

   public static void main(String[] args) {
      mainFrame.getContentPane().add(fxPanel);
      mainFrame.setSize(900,600);
      mainFrame.setVisible(true);
      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      Platform.runLater(new Runnable() {
         @Override
         public void run() {
            initFx(fxPanel);
         }
      });
      
      ExecutorService executor = Executors.newSingleThreadExecutor();
      executor.execute(new Runnable() {
         public void run() {
            int counter=0;
            while (true){
               try {
                  Thread.sleep(10);
               } catch (InterruptedException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }
               counter++;
               Platform.runLater(new Runnable() {
                  public void run(){
                     long value = System.currentTimeMillis()%10000;
                     sphere.setLayoutX(sphere.getLayoutX()+(value<5000?-1:1));
                  }
               });
            }
         }
      });
   }
}
