package com.chuidiang.examples.postgis;

import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author fjabellan
 * @date 07/12/2020
 */
public class InsertPointsThenCreateTrajectoriesMain extends MainCommons {
    public static void main(String[] args) throws InterruptedException, SQLException, ClassNotFoundException {
        new InsertPointsThenCreateTrajectoriesMain().start();
    }

    protected void start() throws SQLException, InterruptedException, ClassNotFoundException {
        super.start();
        int endLoop=0;


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    dao.createTrajectoriesFromPoints(1);
                    System.out.println("Creadas trayectorias");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        },60000,60000);
        while (endLoop!=Integer.MAX_VALUE){
            insertPoints();
            updateModel();
            endLoop++;
            Thread.sleep(1000);
        }
    }

    private void insertPoints() throws SQLException {
        dao.insertPoints(model.getTracks());
    }

}
