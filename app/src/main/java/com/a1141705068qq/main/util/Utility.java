package com.a1141705068qq.main.util;

import android.text.TextUtils;

import com.a1141705068qq.main.gson.Dish;
import com.a1141705068qq.main.gson.Restaurant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GGB on 2017/12/18.
 */

public class Utility {
    public static List<Restaurant>handleRestaurantResponse(String jsonData){
        Gson gson=new Gson();
        List<Restaurant> restaurants=gson.fromJson(jsonData,new TypeToken<List<Restaurant>>(){
        }.getType());
        return restaurants;
    }

    public static List<Dish> handleDishResponse(String jsonData) {
        Gson gson = new Gson();
        List<Dish> dishes = gson.fromJson(jsonData, new TypeToken<List<Dish>>() {
        }.getType());
        return dishes;
    }
}
