package com.example.databasefinalhomework;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
public class RelativesWindow {
    JDBCconnect jdbCconnect;
    TableView<HealthRecord> tableView = new TableView<>();

    public RelativesWindow(JDBCconnect jdbCconnect) {
        this.jdbCconnect = jdbCconnect;
    }

    public void show() {
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        // 创建查看照看老年人健康档案按钮
        Button viewHealthRecordsButton = new Button("查看照看老年人健康档案");
        viewHealthRecordsButton.setOnAction(e -> {
            Stage healthRecordsStage = new Stage();
            VBox vbox = new VBox();

            // 创建表格列
            TableColumn<HealthRecord, String> IDColumn = new TableColumn<>("ID");
            IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

            TableColumn<HealthRecord, String> HeightColumn = new TableColumn<>("Height");
            HeightColumn.setCellValueFactory(new PropertyValueFactory<>("Height"));

            TableColumn<HealthRecord, String> WeightColumn = new TableColumn<>("Weight");
            WeightColumn.setCellValueFactory(new PropertyValueFactory<>("Weight"));

            TableColumn<HealthRecord, String> HighpressColumn = new TableColumn<>("Highpress");
            HighpressColumn.setCellValueFactory(new PropertyValueFactory<>("Highpress"));

            TableColumn<HealthRecord, String> LowpressColumn = new TableColumn<>("Lowpress");
            LowpressColumn.setCellValueFactory(new PropertyValueFactory<>("Lowpress"));

            TableColumn<HealthRecord, String> BloodsugarColumn = new TableColumn<>("Bloodsugar");
            BloodsugarColumn.setCellValueFactory(new PropertyValueFactory<>("Bloodsugar"));

            TableColumn<HealthRecord, String> TimeColumn = new TableColumn<>("Time");
            TimeColumn.setCellValueFactory(new PropertyValueFactory<>("Time"));

            TableColumn<HealthRecord, String> DateColumn = new TableColumn<>("Date");
            DateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));

            TableColumn<HealthRecord, String> CareLevelColumn = new TableColumn<>("CareLevel");
            CareLevelColumn.setCellValueFactory(new PropertyValueFactory<>("CareLevel"));
            tableView.getColumns().addAll(IDColumn, HeightColumn, WeightColumn, HighpressColumn, LowpressColumn, BloodsugarColumn, TimeColumn, DateColumn,CareLevelColumn);

            try {
                ResultSet rs = jdbCconnect.getHealthRecordsInfo();
                while (rs.next()) {
                    HealthRecord healthRecord = new HealthRecord(rs.getString("ID"), rs.getString("Height"), rs.getString("Weight"), rs.getString("Highpress"), rs.getString("Lowpress"), rs.getString("Bloodsugar"), rs.getString("Time"), rs.getString("Date"), rs.getString("CareLevel"));
                    tableView.getItems().add(healthRecord);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            vbox.getChildren().add(tableView);
            Scene scene = new Scene(vbox);
            healthRecordsStage.setScene(scene);
            healthRecordsStage.show();
        });

        // 创建获取健康检测员电话按钮
        Button getTeleButton = new Button("获取健康检测员电话");
        getTeleButton.setOnAction(e -> {
            try {
                String tele = jdbCconnect.getHealthcareWorkerTele();
                Stage teleStage = new Stage();
                VBox vbox = new VBox();
                Label teleLabel = new Label("健康检测员电话: " + tele);
                vbox.getChildren().add(teleLabel);
                Scene scene = new Scene(vbox);
                teleStage.setScene(scene);
                teleStage.show();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        // 将按钮添加到布局中
        layout.getChildren().addAll(viewHealthRecordsButton, getTeleButton);

        // 创建场景并显示
        Scene scene = new Scene(layout, 300, 200);
        stage.setTitle("家属窗口");
        stage.setScene(scene);
        stage.show();
    }
}

