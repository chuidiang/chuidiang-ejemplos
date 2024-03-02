package com.chuidiang.ejemplos.misc;

/**
 * @author Chuidiang
 * date 02/03/2024
 */
public class SleepInterrupt {
    public static void main(String[] args) {
        Thread theThread = new Thread(() -> {
            try {
                System.out.println("Thread started");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        theThread.start();
        theThread.interrupt();
    }
}
