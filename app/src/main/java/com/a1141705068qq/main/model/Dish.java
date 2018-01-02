package com.a1141705068qq.main.model;

import java.io.Serializable;

/**
 * Created by cheng on 16-11-10.
 */
public class Dish implements Serializable {

    private String dishName;
    private double dishPrice;
    private int dishAmount;
    private int dishRemain;
    private String dishPic;
    //private String image_dish;

    public Dish(String dishName, double dishPrice, int dishAmount,String dishPic){
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.dishAmount = dishAmount;
        this.dishRemain = dishAmount;
        this.dishPic=dishPic;
    }

   /* public String getImage_dish() {
        return image_dish;
    }*/

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public double getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(double dishPrice) {
        this.dishPrice = dishPrice;
    }

    public int getDishAmount() {
        return dishAmount;
    }

    public void setDishAmount(int dishAmount) {
        this.dishAmount = dishAmount;
    }

    public int getDishRemain() {
        return dishRemain;
    }

    public void setDishRemain(int dishRemain) {
        this.dishRemain = dishRemain;
    }

    public void setDishPic(String dishPic){
        this.dishPic=dishPic;
    }

    public String getDishPic(){
        return dishPic;
    }

    public int hashCode(){
        int code = this.dishName.hashCode()+(int)this.dishPrice;
        return code;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this)return true;

        return obj instanceof Dish &&
                this.dishName.equals(((Dish)obj).dishName) &&
                this.dishPrice ==  ((Dish)obj).dishPrice &&
                this.dishAmount == ((Dish)obj).dishAmount &&
                this.dishRemain == ((Dish)obj).dishRemain&&
                this.dishPic==((Dish)obj).dishPic;
                //this.image_dish ==((Dish) obj).image_dish;
    }
}
