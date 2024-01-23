package com.example.databasefinalhomework;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

public class OldManApp {
    public void showWindow(JDBCconnect jdbCconnect,Olds LoggedinOlds) {
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        // 创建发布公告按钮
        Button announceButton = new Button("查看公告");
        announceButton.setOnAction(e -> {
            AnnouncementWindow aw = new AnnouncementWindow(jdbCconnect,LoggedinOlds);
            aw.showWindow(LoggedinOlds.getID());
            System.out.println("公告窗口打开");
        });

        // 创建查看汇报按钮
        Button reportButton = new Button("查看健康档案");
        reportButton.setOnAction(e -> {
            // 假设你有一个方法可以根据ID获取健康记录
            // 假设 "ID" 是用户登录时的ID
            HealthRecord record = null;
            try {
                record = jdbCconnect.getHealthRecordByID(LoggedinOlds.getID());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            if (record != null) {
                Stage recordStage = new Stage();
                VBox vbox = new VBox();
                vbox.setSpacing(20);
                Label IDLabel = new Label("ID: " + record.getID());
                Label HeightLabel = new Label("Height: " + record.getHeight());
                Label WeightLabel = new Label("Weight: " + record.getWeight());
                Label HighpressLabel = new Label("Highpress: " + record.getHighpress());
                Label LowpressLabel = new Label("Lowpress: " + record.getLowpress());
                Label BloodsugarLabel = new Label("Bloodsugar: " + record.getBloodsugar());
                Label TimeLabel = new Label("Time: " + record.getTime());
                Label DateLabel = new Label("Date: " + record.getDate());
                Label CareLabel = new Label("CareLevel:"+record.getCareLevel());
                IDLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20px;");
                HeightLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20px;");
                WeightLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20px;");
                HighpressLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20px;");
                LowpressLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20px;");
                BloodsugarLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20px;");
                TimeLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20px;");
                DateLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20px;");

                vbox.getChildren().addAll(IDLabel, HeightLabel, WeightLabel, HighpressLabel, LowpressLabel, BloodsugarLabel, TimeLabel, DateLabel,CareLabel);
                Scene scene = new Scene(vbox,300,200);

                recordStage.setScene(scene);
                recordStage.show();
            } else {
                System.out.println("没有找到健康记录");
            }
        });

        Button infoButton = new Button("个人信息");
        infoButton.setOnAction(e->{
            OldsInfoWindow ow = new OldsInfoWindow(jdbCconnect,LoggedinOlds.getID());
            ow.show();
        });
        // 将按钮添加到布局中
        layout.getChildren().addAll(announceButton, reportButton,infoButton);

        // 创建场景并显示
        Scene scene = new Scene(layout, 300, 200);
        stage.setTitle("老年人窗口");
        stage.setScene(scene);
        stage.show();
    }
}
