package com.yanghan.fragment_tab.fragmentOneNeed;

/**
 * Created by Administrator on 2017/12/18.
 */

public class Shop {
    private String name_restaurant;
    private int image_restaurant;
    private int ratingbar1;
    private int start_send;
    private int send_fee;
    private int arrive_time_restaurant;
    private int distant_restaurant;
    private String discount_restaurant;

    public Shop(String name_restaurant, int image_restaurant, int star_num, int start_send, int send_fee,
                int arrive_time_restaurant, int distant_restaurant, String discount_restaurant){
        this.name_restaurant=name_restaurant;
        this.image_restaurant=image_restaurant;
        this.ratingbar1 = star_num;
        this.start_send = start_send;
        this.send_fee =send_fee;
        this.arrive_time_restaurant = arrive_time_restaurant;
        this.discount_restaurant= discount_restaurant;
        this.distant_restaurant= distant_restaurant;
    }

    public String getName_restaurant(){
        return name_restaurant;
    }

    public String getDiscount_restaurant(){
        return discount_restaurant;
    }

    public int getImage_restaurant(){
        return image_restaurant;
    }

    public int getRatingbar1() { return ratingbar1;}

    public int getStart_send(){
        return start_send;
    }

    public int getSend_fee(){
        return send_fee;
    }

    public int getArrive_time_restaurant(){
        return arrive_time_restaurant;
    }

    public int getDistant_restaurant(){
        return distant_restaurant;
    }
}
