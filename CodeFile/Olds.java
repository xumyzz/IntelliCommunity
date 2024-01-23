package com.example.databasefinalhomework;


public class Olds {
    private String ID;
    private String O_name;
    private String O_sex;
    private String O_age;
    private String O_tele;
    private String roomID;
    private String building;
    private String Rela_ID;
    private String CareLevel;
    private String FamilyHistory;
    private String Name;
    private String Phonenum;

    public Olds(String ID, String O_name, String O_sex, String O_age, String O_tele, String roomID, String building, String Rela_ID, String CareLevel, String FamilyHistory, String Name, String Phonenum) {
        this.ID = ID;
        this.O_name = O_name;
        this.O_sex = O_sex;
        this.O_age = O_age;
        this.O_tele = O_tele;
        this.roomID = roomID;
        this.building = building;
        this.Rela_ID = Rela_ID;
        this.CareLevel = CareLevel;
        this.FamilyHistory = FamilyHistory;
        this.Name = Name;
        this.Phonenum = Phonenum;
    }

    // Getter and Setter methods for each field...

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getO_name() {
        return O_name;
    }

    public void setO_name(String O_name) {
        this.O_name = O_name;
    }

    public String getO_sex() {
        return O_sex;
    }

    public void setO_sex(String O_sex) {
        this.O_sex = O_sex;
    }

    public String getO_age() {
        return O_age;
    }

    public void setO_age(String O_age) {
        this.O_age = O_age;
    }

    public String getO_tele() {
        return O_tele;
    }

    public void setO_tele(String O_tele) {
        this.O_tele = O_tele;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRela_ID() {
        return Rela_ID;
    }

    public void setRela_ID(String Rela_ID) {
        this.Rela_ID = Rela_ID;
    }

    public String getCareLevel() {
        return CareLevel;
    }

    public void setCareLevel(String CareLevel) {
        this.CareLevel = CareLevel;
    }

    public String getFamilyHistory() {
        return FamilyHistory;
    }

    public void setFamilyHistory(String FamilyHistory) {
        this.FamilyHistory = FamilyHistory;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPhonenum() {
        return Phonenum;
    }

    public void setPhonenum(String Phonenum) {
        this.Phonenum = Phonenum;
    }
}

