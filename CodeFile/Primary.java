package com.example.databasefinalhomework;

import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;

import static javafx.application.Application.launch;

public class Primary extends Application {
    static JDBCconnect jdbCconnect=new JDBCconnect();
    public static void main(String[] args) throws SQLException {
        jdbCconnect.DBconnect();
        launch();




    }

    @Override
    public void start(Stage stage) throws Exception {
        LoginApp loginApp = new LoginApp(jdbCconnect);
        loginApp.start(stage);
    }
}
