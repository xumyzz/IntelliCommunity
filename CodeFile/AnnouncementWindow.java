package com.example.databasefinalhomework;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AnnouncementWindow {
    private List<Announcement> announcements = new ArrayList<>();
    private ListView<String> listView = new ListView<>();
    private VBox layout = new VBox(10);
    private Scene scene = new Scene(layout, 300, 200);
    private String role;
    private JDBCconnect jdbCconnect;
    private Olds loggedInOld;

    public AnnouncementWindow(JDBCconnect jdbCconnect, Olds loggedInOld) {
        this.jdbCconnect = jdbCconnect;
        this.loggedInOld = loggedInOld;
    }
    public AnnouncementWindow(JDBCconnect jdbCconnect) {
        this(jdbCconnect, null);
    }

//    public AnnouncementWindow(JDBCconnect jdbCconnect) {
//        this.jdbCconnect = jdbCconnect;
//
//    }

    public void showWindow(String role) {
        Stage stage = new Stage();
        this.role = role;
        try {
            announcements = jdbCconnect.getAnnouncements();
            updateListView();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // 创建添加公告按钮
        Button addButton = new Button("添加公告");
        addButton.setOnAction(e -> {
            // 创建一个对话框来输入公告的标题
            TextInputDialog titleDialog = new TextInputDialog();
            titleDialog.setTitle("添加公告");
            titleDialog.setHeaderText("请输入公告的标题：");

            // 显示对话框并获取用户输入的标题
            String title = titleDialog.showAndWait().orElse("");
            if (!title.isEmpty()) {
                // 创建一个对话框来输入公告的内容
                TextInputDialog contentDialog = new TextInputDialog();
                contentDialog.setTitle("添加公告");
                contentDialog.setHeaderText("请输入公告的内容：");

                // 显示对话框并获取用户输入的内容
                String content = contentDialog.showAndWait().orElse("");

                TextInputDialog levelDialog = new TextInputDialog();
                contentDialog.setTitle("添加等级");
                contentDialog.setHeaderText("请输入公告等级：");

                // 显示对话框并获取用户输入的内容
                String level = contentDialog.showAndWait().orElse("");
                if (!content.isEmpty()) {
                    // 在这里添加公告
                    Announcement announcement = new Announcement(role, title, content, LocalDateTime.now(), level); // Assuming level A for now
                    announcements.add(announcement);
                    try {
                        jdbCconnect.addAnnouncement(announcement);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    updateListView();
                }
            }
        });

        // 创建删除公告按钮
        Button deleteButton = new Button("删除公告");
        deleteButton.setOnAction(e -> {
            // 在这里删除公告
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                Announcement announcement = announcements.get(selectedIndex);
                try {
                    jdbCconnect.deleteAnnouncement(announcement);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                announcements.remove(selectedIndex);
                updateListView();
            }
        });

        // 将按钮和列表视图添加到布局中
        if(Objects.equals(role, "社区管理员" )||Objects.equals(role, "健康记录员" )){
            layout.getChildren().addAll(addButton, deleteButton, listView);
        }else{
            layout.getChildren().add(listView);
        }

        // 创建场景并显示
        stage.setScene(scene);
        stage.show();
    }


        private void updateListView() {
            listView.getItems().clear();
            for (Announcement announcement : announcements) {
                if (loggedInOld == null || announcement.getLevel().equals("D")) {
                    listView.getItems().add(announcement.toString());
                }else if
                (announcement.getLevel().equals(loggedInOld.getCareLevel())) {
                    listView.getItems().add(announcement.toString());
                }
            }
        }

}

