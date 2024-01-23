package com.example.databasefinalhomework;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

public class HealthmanagerApp {
    public void showWindow(String role,JDBCconnect jdbCconnect) {
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        // 创建发布公告按钮
        Button announceButton = new Button("查看健康档案");
        announceButton.setOnAction(e -> {
            HealthRecordApp Hp = new HealthRecordApp(jdbCconnect);
            try {
                Hp.loadFromDatabase();
                for (HealthRecord record : Hp.getHealthRecords()) {
                    String healthLevel = Hp.calculateHealthLevel(record);
                    jdbCconnect.updateHealthLevel(record.getID(), healthLevel);
                }

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            Hp.show();

            System.out.println("健康档案窗口打开");
        });

        // 创建查看汇报按钮
        Button reportButton = new Button("查看汇报栏");
        reportButton.setOnAction(e -> {
            report r =new report();
            r.showWindow(role,jdbCconnect);
            System.out.println("查看汇报窗口打开");
        });

        Button announceButton1 = new Button("发布公告");
        announceButton1.setOnAction(e -> {
            AnnouncementWindow aw = new AnnouncementWindow(jdbCconnect);
            aw.showWindow(role);
            System.out.println("发布公告窗口打开");
        });
        // 将按钮添加到布局中
        layout.getChildren().addAll(announceButton, reportButton,announceButton1);

        // 创建场景并显示
        Scene scene = new Scene(layout, 300, 200);
        stage.setTitle("健康管理员窗口");
        stage.setScene(scene);
        stage.show();
    }
}
