package com.example.databasefinalhomework;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerWindow {
    JDBCconnect jdbCconnect;
    TableView<User> tableView = new TableView<>();

    public ManagerWindow(JDBCconnect jdbCconnect) {
        this.jdbCconnect = jdbCconnect;
    }

    public void show() {
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        // 创建添加人员按钮
        Button addButton = new Button("添加人员");
        addButton.setOnAction(e -> {
            Stage addStage = new Stage();
            VBox vbox = new VBox();

            TextField U_typeField = new TextField("");
            TextField U_numField = new TextField("");
            TextField U_pswField = new TextField("");
            TextField U_nameField = new TextField("");

            U_typeField.setPromptText("U_type:");
            U_numField.setPromptText("U_num:");
            U_pswField.setPromptText("U_psw:");
            U_nameField.setPromptText("U_name:");

            Button submitButton = new Button("提交");
            submitButton.setOnAction(ev -> {
                User user = new User(
                        U_typeField.getText(),
                        U_numField.getText(),
                        U_pswField.getText(),
                        U_nameField.getText()
                );
                try {
                    jdbCconnect.addUser(user);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                addStage.close();
            });

            vbox.getChildren().addAll(U_typeField, U_numField, U_pswField, U_nameField, submitButton);
            Scene scene = new Scene(vbox);
            addStage.setScene(scene);
            addStage.show();
        });

        // 创建删除人员按钮
        // 创建删除人员按钮
        Button deleteButton = new Button("删除人员");
        deleteButton.setOnAction(e -> {
            Stage deleteStage = new Stage();
            VBox vbox = new VBox();

            TextField U_numField = new TextField("");
            U_numField.setPromptText("U_num:");

            Button submitButton = new Button("提交");
            submitButton.setOnAction(ev -> {
                User user = new User("", U_numField.getText(), "","");
                try {
                    jdbCconnect.deleteUser(user);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                deleteStage.close();
            });

            vbox.getChildren().addAll(U_numField, submitButton);
            Scene scene = new Scene(vbox);
            deleteStage.setScene(scene);
            deleteStage.show();
        });


        // 创建表格列
        TableColumn<User, String> U_typeColumn = new TableColumn<>("U_type");
        U_typeColumn.setCellValueFactory(new PropertyValueFactory<>("U_type"));

        TableColumn<User, String> U_numColumn = new TableColumn<>("U_num");
        U_numColumn.setCellValueFactory(new PropertyValueFactory<>("U_num"));

        TableColumn<User, String> U_pswColumn = new TableColumn<>("U_psw");
        U_pswColumn.setCellValueFactory(new PropertyValueFactory<>("U_psw"));

        TableColumn<User, String> U_nameColumn = new TableColumn<>("U_name");
        U_nameColumn.setCellValueFactory(new PropertyValueFactory<>("U_name"));

        tableView.getColumns().addAll(U_typeColumn, U_numColumn, U_pswColumn, U_nameColumn);

        // 加载数据
        try {
            ResultSet rs = jdbCconnect.getUsers();
            while (rs.next()) {
                User user = new User(rs.getString("U_type"), rs.getString("U_num"), rs.getString("U_psw"), rs.getString("U_name"));
                tableView.getItems().add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // 创建查找人员按钮
// 创建查找人员按钮
        Button findButton = new Button("查找人员");
        findButton.setOnAction(e -> {
            Stage findStage = new Stage();
            VBox vbox = new VBox();

            TextField U_typeField = new TextField("");
            TextField U_nameField = new TextField("");

            U_typeField.setPromptText("U_type:");
            U_nameField.setPromptText("U_name:");

            Button submitButton = new Button("提交");
            submitButton.setOnAction(ev -> {
                try {
                    User user = jdbCconnect.findUser(U_typeField.getText(), U_nameField.getText());
                    if (user != null) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("查找结果");
                        alert.setHeaderText(null);
                        alert.setContentText("U_type: " + user.getU_type() + "\n" +
                                "U_num: " + user.getU_num() + "\n" +
                                "U_psw: " + user.getU_psw() + "\n" +
                                "U_name: " + user.getU_name());
                        alert.showAndWait();
                    } else {
                        System.out.println("未找到用户");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                findStage.close();
            });

            vbox.getChildren().addAll(U_typeField, U_nameField, submitButton);
            Scene scene = new Scene(vbox);
            findStage.setScene(scene);
            findStage.show();
        });

// 创建修改人员信息按钮
        Button updateButton = new Button("修改人员信息");
        updateButton.setOnAction(e -> {
            Stage updateStage = new Stage();
            VBox vbox = new VBox();

            TextField U_typeField = new TextField("");
            TextField U_numField = new TextField("");
            TextField U_pswField = new TextField("");
            TextField U_nameField = new TextField("");

            U_typeField.setPromptText("U_type:");
            U_numField.setPromptText("U_num:");
            U_pswField.setPromptText("U_psw:");
            U_nameField.setPromptText("U_name:");

            Button submitButton = new Button("提交");
            submitButton.setOnAction(ev -> {
                User user = new User(
                        U_typeField.getText(),
                        U_numField.getText(),
                        U_pswField.getText(),
                        U_nameField.getText()
                );
                try {
                    jdbCconnect.updateUser(user);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                updateStage.close();
            });

            vbox.getChildren().addAll(U_typeField, U_numField, U_pswField, U_nameField, submitButton);
            Scene scene = new Scene(vbox);
            updateStage.setScene(scene);
            updateStage.show();
        });

// 将新的按钮添加到布局中
        layout.getChildren().addAll(addButton, deleteButton, findButton, updateButton, tableView);








        // 创建场景并显示
        Scene scene = new Scene(layout, 300, 200);
        stage.setTitle("管理人员窗口");
        stage.setScene(scene);
        stage.show();}}



