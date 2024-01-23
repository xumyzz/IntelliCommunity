package com.example.databasefinalhomework;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class LoginApp extends Application {
    JDBCconnect jdbCconnect;
    public LoginApp(JDBCconnect jdbCconnect){
        this.jdbCconnect=jdbCconnect;
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("老年社区管理系统");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(15, 15, 15, 15));
        grid.setVgap(25);
        grid.setHgap(25);

        Label nameLabel = new Label("用户名:");
        TextField nameInput = new TextField();
        nameInput.setPrefWidth(200);
        Label passLabel = new Label("密码:");
        PasswordField passInput = new PasswordField();

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("社区管理员", "健康记录员", "老年人", "家属");
        choiceBox.setValue("社区管理员");

        Button loginButton = new Button("登录");
        loginButton.setPrefWidth(100);
        loginButton.setOnAction(e -> {
            String U_type = choiceBox.getValue();
            String U_name = nameInput.getText();
            String U_psw = passInput.getText();
            // 检查用户名和密码
            if (jdbCconnect.checkCredentials(U_type, U_name, U_psw)) {
                // 如果用户名和密码正确，跳转到相应的用户界面
                // ...


                if(Objects.equals(U_type, "社区管理员")){
                    ManagerApp managerApp = new ManagerApp(jdbCconnect);
                    managerApp.showWindow(U_type);
                    System.out.println("登录成功！");
                    primaryStage.close();
                }
                if(Objects.equals(U_type, "健康记录员")){
                    HealthmanagerApp HealthmanagerApp = new HealthmanagerApp();
                    HealthmanagerApp.showWindow(U_type,jdbCconnect);
                    System.out.println("登录成功！");
                }

                if(Objects.equals(U_type,"老年人")){
                    OldManApp oldManApp = new OldManApp();
                    Olds loggedInOld = null;
                    try {
                        loggedInOld = jdbCconnect.getOldById(U_name);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    if (loggedInOld != null) {
                        oldManApp.showWindow(jdbCconnect,loggedInOld );
                    } else {
                        System.out.println("未找到用户");
                    }
                }


                if(Objects.equals(U_type,"家属")){
                    RelativesWindow rw =new RelativesWindow(jdbCconnect);
                    rw.show();
                }
            } else {
                // 如果用户名或密码不正确，提示用户重新输入
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("错误");
                alert.setHeaderText(null);
                alert.setContentText("用户名或密码错误，请重新输入！");
                alert.showAndWait();

                // 清空输入框
                nameInput.clear();
                passInput.clear();
            }


        });

        grid.add(nameLabel, 0, 0);
        grid.add(nameInput, 1, 0);
        grid.add(passLabel, 0, 1);
        grid.add(passInput, 1, 1);
        grid.add(choiceBox, 0, 2);
        grid.add(loginButton, 1, 2);

        Scene scene = new Scene(grid, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



}