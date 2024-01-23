package com.example.databasefinalhomework;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Button;
public class HealthRecordApp {
    private Stage stage;
    JDBCconnect jdbCconnect;
    TableView<HealthRecord> tableView = new TableView<>();
    public HealthRecordApp(JDBCconnect jdbCconnect) {
        this.stage = new Stage();
        this.jdbCconnect=jdbCconnect;
    }

    public void show() {
        Button addButton = new Button("添加记录");
        addButton.setOnAction(e -> {
            Stage addStage = new Stage();
            VBox vbox = new VBox();

            TextField IDField = new TextField("");
            TextField HeightField = new TextField("");
            TextField WeightField = new TextField("");
            TextField HighpressField = new TextField("");
            TextField LowpressField = new TextField("");
            TextField BloodsugarField = new TextField("");
            TextField TimeField = new TextField("");
            TextField DateField = new TextField("");
            TextField CareField = new TextField("");

            IDField.setPromptText("ID:");
            HeightField.setPromptText("Height:");
            WeightField.setPromptText("Weight:");
            HighpressField.setPromptText("Highpress:");
            LowpressField.setPromptText("Lowpress:");
            BloodsugarField.setPromptText("Bloodsugar:");
            TimeField.setPromptText("Time:");



            Button submitButton = new Button("提交");
            submitButton.setOnAction(ev -> {
                HealthRecord record = new HealthRecord(
                        IDField.getText(),
                        HeightField.getText(),
                        WeightField.getText(),
                        HighpressField.getText(),
                        LowpressField.getText(),
                        BloodsugarField.getText(),
                        TimeField.getText(),
                        DateField.getText()
                );
                try {
                    addRecord(record);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                addStage.close();
            });

            vbox.getChildren().addAll(IDField, HeightField, WeightField, HighpressField, LowpressField, BloodsugarField, TimeField, DateField, submitButton);
            Scene scene = new Scene(vbox);
            addStage.setScene(scene);
            addStage.show();
        });

        Button deleteButton = new Button("删除记录");
        deleteButton.setOnAction(e -> {
            HealthRecord record = tableView.getSelectionModel().getSelectedItem();
            if(record!=null){
                try {
                    deleteRecord(record);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        Button updateButton = new Button("更新记录");
        updateButton.setOnAction(e -> {
            HealthRecord record = tableView.getSelectionModel().getSelectedItem();
            if (record != null) {
                Stage updateStage = new Stage();
                HBox hbox = new HBox();

                TextField IDField = new TextField(record.getID());
                TextField HeightField = new TextField(record.getHeight());
                TextField WeightField = new TextField(record.getWeight());
                TextField HighpressField = new TextField(record.getHighpress());
                TextField LowpressField = new TextField(record.getLowpress());
                TextField BloodsugarField = new TextField(record.getBloodsugar());
                TextField TimeField = new TextField(record.getTime());
                TextField DateField = new TextField(record.getDate());

                IDField.setPromptText("ID:");
                HeightField.setPromptText("Height:");
                WeightField.setPromptText("Weight:");
                HighpressField.setPromptText("Highpress:");
                LowpressField.setPromptText("Lowpress:");
                BloodsugarField.setPromptText("Bloodsugar:");
                TimeField.setPromptText("Time:");
                DateField.setPromptText("Date:");

                Button submitButton = new Button("提交");
                submitButton.setOnAction(ev -> {
                    record.setID(IDField.getText());
                    record.setHeight(HeightField.getText());
                    record.setWeight(WeightField.getText());
                    record.setHighpress(HighpressField.getText());
                    record.setLowpress(LowpressField.getText());
                    record.setBloodsugar(BloodsugarField.getText());
                    record.setTime(TimeField.getText());
                    record.setDate(DateField.getText());
                    try {
                        updateRecord(record);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    updateStage.close();
                });

                hbox.getChildren().addAll(IDField, HeightField, WeightField, HighpressField, LowpressField, BloodsugarField, TimeField, DateField, submitButton);
                Scene scene = new Scene(hbox);
                updateStage.setScene(scene);
                updateStage.show();
            }
        });



    Button refreshButton = new Button("刷新");
        refreshButton.setOnAction(e -> {
            try {
                refresh();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });




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
        HBox hbox = new HBox(addButton, deleteButton, updateButton, refreshButton);
        hbox.setSpacing(10);  // 设置按钮之间的间距为10像素

        VBox vbox = new VBox(hbox, tableView);
        Scene scene = new Scene(vbox, 800, 420);
        stage.setScene(scene);
        stage.show();
    }

    public void loadFromDatabase() throws SQLException {
        ResultSet rs=jdbCconnect.HealthRecord_loadFromDatabase();
        while (rs.next()) {
            HealthRecord record = new HealthRecord(rs);
            // 添加到TableView中
            tableView.getItems().add(record);

        }
    }


    public void addRecord(HealthRecord record) throws SQLException {
        jdbCconnect.addRecord(record);
        tableView.getItems().add(record);
    }

    public void deleteRecord(HealthRecord record) throws SQLException {
        jdbCconnect.deleteRecord(record);
        tableView.getItems().remove(record);
    }

    public void updateRecord(HealthRecord record) throws SQLException {
        jdbCconnect.updateRecord(record);
        int index = tableView.getItems().indexOf(record);
        tableView.getItems().set(index, record);
    }

    public void refresh() throws SQLException {
        tableView.getItems().clear();
        loadFromDatabase();
    }
    public String calculateHealthLevel(HealthRecord record) {
        double highPress = Double.parseDouble(record.getHighpress());
        double lowPress = Double.parseDouble(record.getLowpress());
        double bloodSugar = Double.parseDouble(record.getBloodsugar());

        int abnormalCount = 0;
        if (highPress > 140) abnormalCount++;
        if (lowPress > 90) abnormalCount++;
        if (bloodSugar > 7.0||bloodSugar<4.4) abnormalCount++;

        switch (abnormalCount) {
            case 0: return "D";
            case 1: return "C";
            case 2: return "B";
            case 3: return "A";
            default: return "Unknown";
        }
    }
    public ObservableList<HealthRecord> getHealthRecords() {
        return tableView.getItems();
    }

}

