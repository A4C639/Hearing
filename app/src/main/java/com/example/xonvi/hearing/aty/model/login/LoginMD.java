package com.example.xonvi.hearing.aty.model.login;

/**
 * Created by xonvi on 2017/3/6.
 */

//登陆的数据模型
public class LoginMD {
    private int id;
    private String username;
    private String userpass;


    public LoginMD() {
    }

    public LoginMD(int id, String username, String userpass) {
        this.id = id;
        this.username = username;
        this.userpass = userpass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    @Override
    public String toString() {
        return "LoginMD{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userpass='" + userpass + '\'' +
                '}';
    }
}
