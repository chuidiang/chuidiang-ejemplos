package com.chuidiang.ejemplos.threads;

/**
 * @author Chuidiang
 * date 10/02/2024
 *
 * Ejemplo sencillo de wait() y notify()
 */
public class WaitNotifyExample {
    /** Para usar como objeto de sincronización, llamando a su wait() y notify() */
    static Object syncro = new Object();

    /** Dato que genera uno de los hilos y que espera el otro hilo */
    static String data=null;

    public static void main(String[] args) {
        // Se lanza el hilo que espera por el dato
        new Thread(() -> {
            synchronized (syncro){
                try {
                    // Se queda bloqueado hasta que alguien llame a notify()
                    syncro.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Supuestamente el dato debería estar disponible
                System.out.printf("Data = %s\n", data);
            }
        }).start();

        // Se lanza el hilo que genera el dato
        new Thread(() -> {
            try {
                // Ponemos una espera para simular que el dato tarda en generarse
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // dato generado
            data = "Hello World!";
            synchronized (syncro){
                // se avisa a los que estén esperando
                syncro.notify();
            }
        }).start();

    }
}






