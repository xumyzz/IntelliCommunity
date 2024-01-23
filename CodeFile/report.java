package com.example.databasefinalhomework;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class report {
    private List<Announcement> announcements = new ArrayList<>();
    private ListView<String> listView = new ListView<>();
    private VBox layout = new VBox(10);
    private Scene scene = new Scene(layout, 300, 200);
    private String role;
    public void showWindow(String role,JDBCconnect jdbCconnect) {
        Stage stage = new Stage();
        try {
            ResultSet rs = jdbCconnect.getReports();
            while (rs.next()) {
                Announcement announcement = new Announcement(rs.getString("publisher"), rs.getString("title"), rs.getString("content"), rs.getTimestamp("publishTime").toLocalDateTime());
                announcements.add(announcement);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        updateListView();

        // 创建添加公告按钮
        Button addButton = new Button("");
        if(Objects.equals(role,"社区管理员")){
            addButton.setText("添加指示");
        }else{
            addButton.setText("添加汇报");
        }

            addButton.setOnAction(e -> {
            // 创建一个对话框来输入公告的标题
            TextInputDialog titleDialog = new TextInputDialog();
            if(Objects.equals(role,"社区管理员")){
                    titleDialog.setTitle("添加指示");
                    titleDialog.setHeaderText("请输入指示的标题：");
                }else{
                titleDialog.setTitle("添加汇报");
                titleDialog.setHeaderText("请输入汇报的标题：");
            }


            // 显示对话框并获取用户输入的标题
            String title = titleDialog.showAndWait().orElse("");
            if (!title.isEmpty()) {
                // 创建一个对话框来输入公告的内容
                TextInputDialog contentDialog = new TextInputDialog();

                if(Objects.equals(role,"社区管理员")){
                    contentDialog.setTitle("添加指示");
                    contentDialog.setHeaderText("请输入指示的内容：");
                }else{
                    contentDialog.setTitle("添加汇报");
                    contentDialog.setHeaderText("请输入汇报的内容：");
                }

                // 显示对话框并获取用户输入的内容
                String content = contentDialog.showAndWait().orElse("");
                if (!content.isEmpty()) {
                    // 在这里添加公告
                    Announcement announcement = new Announcement("社区管理员", title, content, LocalDateTime.now());
                    announcements.add(announcement);
                    try {
                        jdbCconnect.addReport(announcement);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    updateListView();
                }
            }
        });

        // 创建删除公告按钮
        Button deleteButton = new Button("");
        if(Objects.equals(role,"社区管理员")){
            deleteButton.setText("删除指示");
        }else{
            deleteButton.setText("删除汇报");
        }
        deleteButton.setOnAction(e -> {
            // 在这里删除公告
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                Announcement announcement = announcements.remove(selectedIndex);
                try {
                    jdbCconnect.deleteReport(announcement);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                updateListView();
            }
        });

        // 将按钮和列表视图添加到布局中

            layout.getChildren().addAll(addButton, deleteButton, listView);



        // 创建场景并显示
        stage.setScene(scene);
        stage.show();
    }

    private void updateListView() {
        listView.getItems().clear();
        for (Announcement announcement : announcements) {
            listView.getItems().add(announcement.toString());
        }
    }






    }