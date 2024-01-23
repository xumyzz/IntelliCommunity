package com.example.databasefinalhomework;

public class User {
    private String U_type;
    private String U_num;
    private String U_psw;
    private String U_name;

    public User(String U_type, String U_num, String U_psw,String U_name) {
        this.U_type = U_type;
        this.U_num = U_num;
        this.U_psw = U_psw;
        this.U_name=U_name;
    }

    public String getU_type() {
        return U_type;
    }

    public void setU_type(String U_type) {
        this.U_type = U_type;
    }

    public String getU_num() {
        return U_num;
    }

    public void setU_num(String U_num) {
        this.U_num = U_num;
    }
    public String getU_name() {
        return U_name;
    }

    public void setU_name(String U_name) {
        this.U_num = U_name;
    }

    public String getU_psw() {
        return U_psw;
    }

    public void setU_psw(String U_psw) {
        this.U_psw = U_psw;
    }
}
