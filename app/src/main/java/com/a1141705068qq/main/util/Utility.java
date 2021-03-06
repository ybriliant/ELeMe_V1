package com.a1141705068qq.main.util;

import android.text.TextUtils;

import com.a1141705068qq.main.gson.Dish;
import com.a1141705068qq.main.gson.Order;
import com.a1141705068qq.main.gson.Restaurant;
import com.a1141705068qq.main.gson.User;
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
    /*public static Restaurant handleRestaurantResponse(String jsonData){
        Gson gson=new Gson();
        Restaurant res=gson.fromJson(jsonData,Restaurant.class);
        return res;
    }*/
    public static List<Restaurant> handleRestaurantsResponse(String jsonData){
        if(!TextUtils.isEmpty(jsonData)){
            try{
                JSONArray jsonArray=new JSONArray(jsonData);
                List<Restaurant> restaurants=new ArrayList<>();
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    Restaurant res=new Restaurant();
                    res.setRes_id(jsonObject.getString("res_id"));
                    res.setRes_name(jsonObject.getString("res_name"));
                    res.setRes_note(jsonObject.getString("res_note"));
                    res.setRes_mark(jsonObject.getInt("res_mark"));
                    res.setRes_picture(jsonObject.getString("res_picture"));
                    restaurants.add(res);
                }
                return restaurants;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Restaurant handleRestaurantResponse(String jsonData){
        if(!TextUtils.isEmpty(jsonData)){
            try{
                JSONArray jsonArray = new JSONArray(jsonData);
                Restaurant res=new Restaurant();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    res.setRes_id(jsonObject.getString("res_id"));
                    res.setRes_name(jsonObject.getString("res_name"));
                    res.setRes_note(jsonObject.getString("res_note"));
                    res.setRes_mark(jsonObject.getInt("res_mark"));
                    res.setRes_picture(jsonObject.getString("res_picture"));
                    return res;
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public static List<Dish> handleDishResponse(String jsonData) {
        Gson gson = new Gson();
        List<Dish> dishes = gson.fromJson(jsonData, new TypeToken<List<Dish>>() {
        }.getType());
        return dishes;
    }

    public static User handleUserResponse(String jsonData){
        try {
            Gson gson = new Gson();
            List<User> users=gson.fromJson(jsonData,new TypeToken<List<User>>(){}.getType());
            User user=users.get(0);
            return user;
        }catch (Exception e){
             e.printStackTrace();
        }
        return null;
    }

    public static List<Order> handleOrderResponse(String jsonData){
        try {
            Gson gson=new Gson();
            List<Order> orders=gson.fromJson(jsonData,new TypeToken<List<Order>>(){}.getType());
            return orders;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
