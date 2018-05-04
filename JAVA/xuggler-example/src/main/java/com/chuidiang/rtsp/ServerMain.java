package com.chuidiang.rtsp;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain extends JFrame {
    public static void main(String[] args) throws IOException {
        ServerMain server = new ServerMain();
        server.start();
    }

    JLabel label;
    int RTSPport=10000;

    public ServerMain(){
        super("RTSP com.chuidiang.rtsp.Server");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label = new JLabel("Send frame #        ", JLabel.CENTER);
        getContentPane().add(label, BorderLayout.CENTER);

        pack();
        setVisible(true);

        //Handler to close the main window
//        addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//                //stop the timer and exit
//                timer.stop();
//                rtcpReceiver.stopRcv();
//                System.exit(0);
//            }});

    }

    public void start() throws IOException {
        final ServerSocket listenSocket = new ServerSocket(RTSPport);
                while (true) {
                    try {
                            Socket client = listenSocket.accept();
                            System.out.println("Otra conexion!!");
                            new Server(client, RTSPport);
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }


    }
}
