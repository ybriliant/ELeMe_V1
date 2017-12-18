package com.a1141705068qq.main.util;

import android.text.TextUtils;

import com.a1141705068qq.main.gson.Dish;
import com.a1141705068qq.main.gson.Restaurant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by GGB on 2017/12/18.
 */

public class Utility {
    public static boolean handleRestaurantResponse(String response,Restaurant[] restaurant){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allRestaurants=new JSONArray(response);
                for(int i=0;i<allRestaurants.length();i++){
                    JSONObject restaurantObject=allRestaurants.getJSONObject(i);
                    restaurant[i].setRes_id(restaurantObject.getString("res_id"));
                    restaurant[i].setRes_name(restaurantObject.getString("res_name"));
                    restaurant[i].setRes_note(restaurantObject.getString("res_note"));
                    restaurant[i].setRes_mark(restaurantObject.getInt("res_mark"));
                    restaurant[i].setRes_picture(restaurantObject.getString("res_picture"));
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleDishResponse(String response, Dish[] dish) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allDishes = new JSONArray(response);
                for (int i = 0; i < allDishes.length(); i++) {
                    JSONObject dishObject = allDishes.getJSONObject(i);
                    dish[i].setDis_id(dishObject.getString("dis_id"));
                    dish[i].setRes_id(dishObject.getString("res_id"));
                    dish[i].setDis_name(dishObject.getString("dis_name"));
                    dish[i].setDis_price(dishObject.getDouble("dis_price"));
                    dish[i].setDis_picture(dishObject.getString("dis_picture"));
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
