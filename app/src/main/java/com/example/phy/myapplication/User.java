package com.example.phy.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class User {

    public final static String NAME="name";
    public final static String PWD="pwd";
    public final static String MOBLIE="mobile";
    public final static String DANWEI="danwei";
    public final static String QQ="qq";
    public final static String ADDRESS="address";

    private String myPhone;  //手机号
    private String name;     //姓名
    private String pwd;      //密码
    private String moblie;   //手机号码
    private String danwei;   //单位
    private String qq;       //QQ
    private String address;  //地址

    public String getMyPhone() { return myPhone; }
    public String getMoblie() {
        return moblie;
    }
    public String getPwd() {
        return pwd;
    }
    public String getName() {
        return name;
    }
    public String getDanwei() {
        return danwei;
    }
    public String getQq() {
        return qq;
    }
    public String getAddress() {
        return address;
    }

    public void setName(String name) { this.name = name; }
    public void setMyPhone(String myPhone) {
        this.myPhone = myPhone;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public void setMoblie(String moblie) {
        this.moblie = moblie;
    }
    public void setDanwei(String danwei) {
        this.danwei = danwei;
    }
    public void setQq(String qq) {
        this.qq = qq;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
