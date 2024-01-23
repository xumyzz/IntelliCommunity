package com.example.databasefinalhomework;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OldsInfoWindow {
    JDBCconnect jdbCconnect;
    TableView<Olds> tableView = new TableView<>();
    String loggedInOldId;
    public OldsInfoWindow(JDBCconnect jdbCconnect,String loggedInOldId) {
        this.jdbCconnect = jdbCconnect;
        this.loggedInOldId = loggedInOldId;
    }

    public void show() {
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        // 创建表格列
        TableColumn<Olds, String> IDColumn = new TableColumn<>("ID");
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<Olds, String> O_nameColumn = new TableColumn<>("O_name");
        O_nameColumn.setCellValueFactory(new PropertyValueFactory<>("O_name"));

        TableColumn<Olds, String> O_sexColumn = new TableColumn<>("O_sex");
        O_sexColumn.setCellValueFactory(new PropertyValueFactory<>("O_sex"));

        TableColumn<Olds, String> O_ageColumn = new TableColumn<>("O_age");
        O_ageColumn.setCellValueFactory(new PropertyValueFactory<>("O_age"));

        TableColumn<Olds, String> O_teleColumn = new TableColumn<>("O_tele");
        O_teleColumn.setCellValueFactory(new PropertyValueFactory<>("O_tele"));

        TableColumn<Olds, String> roomIDColumn = new TableColumn<>("roomID");
        roomIDColumn.setCellValueFactory(new PropertyValueFactory<>("roomID"));

        TableColumn<Olds, String> buildingColumn = new TableColumn<>("building");
        buildingColumn.setCellValueFactory(new PropertyValueFactory<>("building"));

        TableColumn<Olds, String> Rela_IDColumn = new TableColumn<>("Rela_ID");
        Rela_IDColumn.setCellValueFactory(new PropertyValueFactory<>("Rela_ID"));

        TableColumn<Olds, String> CareLevelColumn = new TableColumn<>("CareLevel");
        CareLevelColumn.setCellValueFactory(new PropertyValueFactory<>("CareLevel"));

        TableColumn<Olds, String> FamilyHistoryColumn = new TableColumn<>("FamilyHistory");
        FamilyHistoryColumn.setCellValueFactory(new PropertyValueFactory<>("FamilyHistory"));

        TableColumn<Olds, String> NameColumn = new TableColumn<>("Name");
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        TableColumn<Olds, String> PhonenumColumn = new TableColumn<>("Phonenum");
        PhonenumColumn.setCellValueFactory(new PropertyValueFactory<>("Phonenum"));

        tableView.getColumns().addAll(IDColumn, O_nameColumn, O_sexColumn, O_ageColumn, O_teleColumn, roomIDColumn, buildingColumn, Rela_IDColumn, CareLevelColumn, FamilyHistoryColumn, NameColumn, PhonenumColumn);

        // 加载数据
        try {
            ResultSet rs = jdbCconnect.getOldsInfo1(loggedInOldId);  // 修改这里，传入loggedInOldId作为参数
            while (rs.next()) {
                Olds olds = new Olds(rs.getString("ID"), rs.getString("O_name"), rs.getString("O_sex"), rs.getString("O_age"), rs.getString("O_tele"), rs.getString("roomID"), rs.getString("building"), rs.getString("Rela_ID"), rs.getString("CareLevel"), rs.getString("FamilyHistory"), rs.getString("Name"), rs.getString("Phonenum"));
                tableView.getItems().add(olds);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        // 将表格添加到布局中
        layout.getChildren().addAll(tableView);

        // 创建场景并显示
        Scene scene = new Scene(layout, 300, 200);
        stage.setTitle("老年人信息窗口");
        stage.setScene(scene);
        stage.show();
    }
}

