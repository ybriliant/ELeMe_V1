package com.a1141705068qq.main.model;

import android.widget.TextView;

/**
 * Created by Administrator on 2017/12/31.
 */

public class DishItem {
       private String name_food;
        public int num_of_food;
        public Double sum_price_food;

    public DishItem(String name_food, int num_of_food, double sum_price_food){
        this.name_food = name_food;
        this.num_of_food = num_of_food;
        this.sum_price_food = sum_price_food;
    }
}
