package com.example.databasefinalhomework;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ManagerApp {
    public JDBCconnect jdbCconnect;
    public  ManagerApp(JDBCconnect jdbCconnect){
        this.jdbCconnect=jdbCconnect;
    }
    public void showWindow(String role) {
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        // 创建发布公告按钮
        Button announceButton = new Button("发布公告");
        announceButton.setOnAction(e -> {
            AnnouncementWindow aw = new AnnouncementWindow(jdbCconnect);
            aw.showWindow(role);
            System.out.println("发布公告窗口打开");
        });

        // 创建查看汇报按钮
        Button reportButton = new Button("查看汇报");
        reportButton.setOnAction(e -> {
            report r =new report();
            r.showWindow(role,jdbCconnect);
            System.out.println("查看汇报窗口打开");
        });

        // 创建管理人员按钮
        Button manageButton = new Button("管理人员");
        manageButton.setOnAction(e -> {
            ManagerWindow mw = new ManagerWindow(jdbCconnect);
            mw.show();
            System.out.println("管理人员窗口打开");
        });

        // 创建楼栋管理按钮
        Button buildingButton = new Button("楼栋管理");
        buildingButton.setOnAction(e -> {
            // 在这里实现楼栋管理窗口的打开和数据的显示
            ResultSet rs = null;
            try {
                rs = jdbCconnect.getBuildingInfo();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            // 创建一个新的窗口来显示查询结果
            Stage buildingStage = new Stage();
            VBox buildingLayout = new VBox(10);
            buildingLayout.setAlignment(Pos.CENTER);

            // 创建表格并填充查询结果
            TableView<ObservableList<String>> tableView = new TableView<>();
            try {
                // 获取列数
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = ((ResultSetMetaData) metaData).getColumnCount();

                // 创建列
                for (int i = 1; i <= columnCount; i++) {
                    final int j = i;
                    TableColumn<ObservableList<String>, String> column = new TableColumn<>(metaData.getColumnName(i));
                    column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(j - 1)));
                    tableView.getColumns().add(column);
                }

                // 填充数据
                while (rs.next()) {
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= columnCount; i++) {
                        row.add(rs.getString(i));
                    }
                    tableView.getItems().add(row);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            // 将表格添加到布局中
            buildingLayout.getChildren().add(tableView);

            // 创建场景并显示
            buildingStage.setScene(new Scene(buildingLayout, 500, 400));
            buildingStage.show();
            System.out.println("楼栋管理窗口打开");
        });

        // 将按钮添加到布局中
        layout.getChildren().addAll(announceButton, reportButton, manageButton, buildingButton);

        // 创建场景并显示
        Scene scene = new Scene(layout, 300, 200);
        stage.setTitle("社区管理者窗口");
        stage.setScene(scene);
        stage.show();
    }
}

