package com.example.databasefinalhomework;
import javafx.beans.property.SimpleStringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HealthRecord {
    private SimpleStringProperty ID;
    private SimpleStringProperty Height;
    private SimpleStringProperty Weight;
    private SimpleStringProperty Highpress;
    private SimpleStringProperty Lowpress;
    private SimpleStringProperty Bloodsugar;
    private SimpleStringProperty Time;
    private SimpleStringProperty Date;

    private SimpleStringProperty CareLevel;

    public HealthRecord(String ID, String Height, String Weight, String Highpress, String Lowpress, String Bloodsugar, String Time, String Date,String CareLevel) {
        this.ID = new SimpleStringProperty(ID);
        this.Height = new SimpleStringProperty(Height);
        this.Weight = new SimpleStringProperty(Weight);
        this.Highpress = new SimpleStringProperty(Highpress);
        this.Lowpress = new SimpleStringProperty(Lowpress);
        this.Bloodsugar = new SimpleStringProperty(Bloodsugar);
        this.Time = new SimpleStringProperty(Time);
        this.Date = new SimpleStringProperty(Date);
        this.CareLevel = new SimpleStringProperty(CareLevel);
    }
    public HealthRecord(String ID, String Height, String Weight, String Highpress, String Lowpress, String Bloodsugar, String Time, String Date) {
        this.ID = new SimpleStringProperty(ID);
        this.Height = new SimpleStringProperty(Height);
        this.Weight = new SimpleStringProperty(Weight);
        this.Highpress = new SimpleStringProperty(Highpress);
        this.Lowpress = new SimpleStringProperty(Lowpress);
        this.Bloodsugar = new SimpleStringProperty(Bloodsugar);
        this.Time = new SimpleStringProperty(Time);
        this.Date = new SimpleStringProperty(Date);
        this.CareLevel = new SimpleStringProperty("");
    }


    public String getID() {
        return ID.get();
    }

    public void setID(String ID) {
        this.ID.set(ID);
    }

    public String getHeight() {
        return Height.get();
    }

    public void setHeight(String Height) {
        this.Height.set(Height);
    }

    public String getWeight() {
        return Weight.get();
    }

    public void setWeight(String Weight) {
        this.Weight.set(Weight);
    }

    public String getHighpress() {
        return Highpress.get();
    }

    public void setHighpress(String Highpress) {
        this.Highpress.set(Highpress);
    }

    public String getLowpress() {
        return Lowpress.get();
    }

    public void setLowpress(String Lowpress) {
        this.Lowpress.set(Lowpress);
    }

    public String getBloodsugar() {
        return Bloodsugar.get();
    }

    public void setBloodsugar(String Bloodsugar) {
        this.Bloodsugar.set(Bloodsugar);
    }

    public String getTime() {
        return Time.get();
    }

    public void setTime(String Time) {
        this.Time.set(Time);
    }

    public String getDate() {
        return Date.get();
    }

    public void setDate(String Date) {
        this.Date.set(Date);
    }
    public String getCareLevel() {
        return CareLevel.get();
    }

    public void setCareLevel(String CareLevel) {
        this.CareLevel.set(CareLevel);
    }

    public HealthRecord(ResultSet rs) throws SQLException {
        this.ID = new SimpleStringProperty(rs.getString("ID"));
        this.Height = new SimpleStringProperty(rs.getString("Height"));
        this.Weight = new SimpleStringProperty(rs.getString("Weight"));
        this.Highpress = new SimpleStringProperty(rs.getString("Highpress"));
        this.Lowpress = new SimpleStringProperty(rs.getString("Lowpress"));
        this.Bloodsugar = new SimpleStringProperty(rs.getString("Bloodsugar"));
        this.Time = new SimpleStringProperty(rs.getString("Time"));
        this.Date = new SimpleStringProperty(rs.getString("Date"));
        this.CareLevel = new SimpleStringProperty(rs.getString("CareLevel"));
    }

}
