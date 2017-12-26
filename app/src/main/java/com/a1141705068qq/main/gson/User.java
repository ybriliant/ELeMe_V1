package com.a1141705068qq.main.gson;

import java.io.Serializable;

/**
 * Created by GGB on 2017/12/25.
 */

public class User{
    public String user_id;
    public String user_name;
    public String user_phone;
    public String user_picture;
    public String user_location;

    public void setUser_id(String user_id){
        this.user_id=user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_picture(String user_picture) {
        this.user_picture = user_picture;
    }

    public String getUser_picture() {
        return user_picture;
    }

    public void setUser_location(String user_location) {
        this.user_location = user_location;
    }

    public String getUser_location() {
        return user_location;
    }
}
