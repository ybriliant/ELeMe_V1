package com.a1141705068qq.main.gson;

/**
 * Created by GGB on 2018/1/2.
 */

public class Order {
    public String order_id;
    public String res_name;
    public String order_sum;
    public String user_id;
    public String dis_name;
    public String order_pic;

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setRes_name(String res_name) {
        this.res_name = res_name;
    }

    public String getRes_name() {
        return res_name;
    }

    public void setOrder_sum(String order_sum) {
        this.order_sum = order_sum;
    }

    public String getOrder_sum() {
        return order_sum;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setDis_name(String dis_name){
        this.dis_name=dis_name;
    }

    public String getDis_name() {
        return dis_name;
    }

    public void setOrder_pic(String order_pic) {
        this.order_pic = order_pic;
    }

    public String getOrder_pic() {
        return order_pic;
    }
}
