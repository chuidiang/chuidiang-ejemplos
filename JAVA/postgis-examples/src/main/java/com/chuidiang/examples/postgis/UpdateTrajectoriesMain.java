package com.chuidiang.examples.postgis;

import java.sql.SQLException;

/**
 * @author fjabellan
 * @date 06/12/2020
 */
public class UpdateTrajectoriesMain extends MainCommons {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        new UpdateTrajectoriesMain().start();
    }

    protected void start() throws SQLException, InterruptedException, ClassNotFoundException {
        super.start();
        int endLoop=0;
        while (endLoop!=Integer.MAX_VALUE){
            updateTrajectories();
            updateModel();
            endLoop++;
            Thread.sleep(1000);
        }
    }

    private void updateTrajectories() throws SQLException {
        dao.updateTrajectories(model.getTracks());
    }

}
