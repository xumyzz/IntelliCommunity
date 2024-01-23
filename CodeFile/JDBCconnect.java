package com.example.databasefinalhomework;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCconnect extends Exception {
    static Connection conn;
    public static void DBconnect(){
        String driver = "com.mysql.cj.jdbc.Driver";
        String URL = "jdbc:mysql://localhost:3306/community2?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false";
        conn = null;
        try
        {
            Class.forName(driver);
        }
        catch(java.lang.ClassNotFoundException e)
        {
            System.out.println("无法加载驱动.");
        }
        try
        {
            conn=DriverManager.getConnection(URL,"root","021129");//这里输入你自己安装MySQL时候设置的用户名和密码，用户名默认为root
            System.out.println("连接成功.");

        }
        catch(Exception e)
        {
            System.out.println("连接失败:" + e.getMessage());
        }
    }

    public static void selectTable(String sql) throws SQLException {
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);//创建数据对象

            while (rs.next()){
                System.out.print(rs.getInt(1) + "\t");
                System.out.print(rs.getString(2) + "\t");
                System.out.print(rs.getInt(3) + "\t");
                System.out.println();
            }
            rs.close();
            stmt.close();
            conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }




    }


    public boolean checkCredentials(String U_type, String U_num, String U_psw) {
        try {
            String query = "SELECT * FROM users WHERE U_type = '" + U_type + "' AND U_num = '" + U_num + "' AND U_psw = '" + U_psw + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

//    public ResultSet HealthRecord_loadFromDatabase() {
//        try {
//            String query = "SELECT * FROM health_records";
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(query);
//            return rs;
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//        }
//
//        return null;
//    }

    public void addRecord(HealthRecord record) throws SQLException {
        String sql = "INSERT INTO health_records (ID, Height, Weight, Highpress, Lowpress, Bloodsugar, Time, Date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, record.getID());
        pstmt.setString(2, record.getHeight());
        pstmt.setString(3, record.getWeight());
        pstmt.setString(4, record.getHighpress());
        pstmt.setString(5, record.getLowpress());
        pstmt.setString(6, record.getBloodsugar());
        pstmt.setString(7, record.getTime());
        pstmt.setString(8, record.getDate());
        pstmt.executeUpdate();
    }

    public void deleteRecord(HealthRecord record) throws SQLException {
        String sql = "DELETE FROM health_records WHERE ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, record.getID());
        pstmt.executeUpdate();
    }

    public void updateRecord(HealthRecord record) throws SQLException {
        String sql = "UPDATE health_records SET Height = ?, Weight = ?, Highpress = ?, Lowpress = ?, Bloodsugar = ?, Time = ?, Date = ? WHERE ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, record.getHeight());
        pstmt.setString(2, record.getWeight());
        pstmt.setString(3, record.getHighpress());
        pstmt.setString(4, record.getLowpress());
        pstmt.setString(5, record.getBloodsugar());
        pstmt.setString(6, record.getTime());
        pstmt.setString(7, record.getDate());
        pstmt.setString(8, record.getID());
        pstmt.executeUpdate();
    }

    public HealthRecord getHealthRecordByID(String ID) throws SQLException {
        String query = "SELECT * FROM health_records LEFT JOIN olds ON health_records.ID=olds.ID WHERE health_records.ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, ID);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return new HealthRecord(rs);
        } else {
            return null;
        }
    }


    public ResultSet getUsers() throws SQLException {
        String query = "SELECT * FROM users";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }

    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (U_type, U_num, U_psw) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getU_type());
        pstmt.setString(2, user.getU_num());
        pstmt.setString(3, user.getU_psw());
        pstmt.executeUpdate();
    }

    public void deleteUser(User user) throws SQLException {
        String sql = "DELETE FROM users WHERE U_num = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getU_num());
        pstmt.executeUpdate();
    }

    public ResultSet getOldsInfo() throws SQLException {
        String query = "SELECT olds.*, relatives.Name, relatives.Phonenum " +
                "FROM olds " +
                "LEFT JOIN relatives ON olds.Rela_ID = relatives.ID";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }
    public ResultSet getOldsInfo1(String loggedInOldId) throws SQLException {
        String sql = "SELECT * FROM olds LEFT JOIN relatives ON olds.ID=relatives.CareOldID WHERE Olds.ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, loggedInOldId);
        return pstmt.executeQuery();
    }

    public String getHealthcareWorkerTele() throws SQLException {
        String query = "SELECT healthcareworker.Tele " +
                "FROM relatives " +
                "LEFT JOIN olds ON relatives.CareOldID = olds.ID " +
                "LEFT JOIN building ON olds.building = building.BID " +
                "LEFT JOIN healthcareworker ON building.ResworkerID = healthcareworker.ID";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()) {
            return rs.getString("Tele");
        } else {
            return null;
        }
    }

    public ResultSet getHealthRecordsInfo() throws SQLException {
        String sql = "SELECT health_records.*, olds.CareLevel FROM health_records INNER JOIN olds ON health_records.ID = olds.ID";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        return pstmt.executeQuery();
    }

    public ResultSet getBuildingInfo() throws SQLException {
        // 在这里实现联合查询并返回结果
        String query = "SELECT building.*, healthcareworker.Name FROM building LEFT JOIN healthcareworker ON building.ResWorkerID = healthcareworker.ID";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }
    public User findUser(String U_type, String U_name) throws SQLException {
        String query = "SELECT * FROM users WHERE U_type = ? AND U_name = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, U_type);
        pstmt.setString(2, U_name);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return new User(rs.getString("U_type"), rs.getString("U_num"), rs.getString("U_psw"), rs.getString("U_name"));
        } else {
            return null;
        }
    }

    public void updateUser(User user) throws SQLException {
        String query = "UPDATE users SET U_type = ?, U_psw = ?, U_name = ? WHERE U_num = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, user.getU_type());
        pstmt.setString(2, user.getU_psw());
        pstmt.setString(3, user.getU_name());
        pstmt.setString(4, user.getU_num());
        pstmt.executeUpdate();
    }


    public void addAnnouncement(Announcement announcement) throws SQLException {
        String query = "INSERT INTO cannounce (Title, Time, Content, AnounncedByid, level) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, announcement.getTitle());
        pstmt.setTimestamp(2, Timestamp.valueOf(announcement.getPublishTime()));
        pstmt.setString(3, announcement.getContent());
        pstmt.setString(4, announcement.getPublisher());
        pstmt.setString(5, announcement.getLevel());
        pstmt.executeUpdate();
    }

    public void deleteAnnouncement(Announcement announcement) throws SQLException {
        String query = "DELETE FROM cannounce WHERE Title = ? AND Time = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, announcement.getTitle());
        pstmt.setTimestamp(2, Timestamp.valueOf(announcement.getPublishTime()));
        pstmt.executeUpdate();
    }

    public Olds getOldById(String id) throws SQLException {
        String query = "SELECT * FROM olds LEFT JOIN relatives ON olds.ID=relatives.CareOldID WHERE olds.ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return new Olds(rs.getString("ID"), rs.getString("O_name"), rs.getString("O_sex"), rs.getString("O_age"), rs.getString("O_tele"), rs.getString("roomID"), rs.getString("building"), rs.getString("Rela_ID"), rs.getString("CareLevel"), rs.getString("FamilyHistory"), rs.getString("Name"), rs.getString("Phonenum"));
        } else {
            return null;
        }
    }

    public List<Announcement> getAnnouncements() throws SQLException {
        List<Announcement> announcements = new ArrayList<>();
        String query = "SELECT * FROM cannounce";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            announcements.add(new Announcement(rs.getString("AnounncedByid"), rs.getString("Title"), rs.getString("Content"), rs.getTimestamp("Time").toLocalDateTime(), rs.getString("level")));
        }
        return announcements;
    }

    public void updateHealthLevel(String ID, String healthLevel) throws SQLException {
        String sql = "UPDATE olds SET CareLevel = ? WHERE ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, healthLevel);
        pstmt.setString(2, ID);
        pstmt.executeUpdate();
    }

    public ResultSet HealthRecord_loadFromDatabase() throws SQLException {
        String sql = "SELECT health_records.*, olds.CareLevel FROM health_records INNER JOIN olds ON health_records.ID = olds.ID";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        return pstmt.executeQuery();
    }

    public void addReport(Announcement announcement) throws SQLException {
        String sql = "INSERT INTO reports (publisher, title, content, publishTime) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, announcement.getPublisher());
        pstmt.setString(2, announcement.getTitle());
        pstmt.setString(3, announcement.getContent());
        pstmt.setTimestamp(4, Timestamp.valueOf(announcement.getPublishTime()));
        pstmt.executeUpdate();
    }

    public void deleteReport(Announcement announcement) throws SQLException {
        String sql = "DELETE FROM reports WHERE publisher = ? AND title = ? AND content = ? AND publishTime = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, announcement.getPublisher());
        pstmt.setString(2, announcement.getTitle());
        pstmt.setString(3, announcement.getContent());
        pstmt.setTimestamp(4, Timestamp.valueOf(announcement.getPublishTime()));
        pstmt.executeUpdate();
    }

    public ResultSet getReports() throws SQLException {
        String sql = "SELECT * FROM reports";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        return pstmt.executeQuery();
    }



}




