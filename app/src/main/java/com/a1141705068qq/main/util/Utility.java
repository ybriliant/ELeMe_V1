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
    public static boolean handleRestaurantResponse(String response, Restaurant[] restaurant) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allRestaurants = new JSONArray(response);
                for (int i = 0; i < allRestaurants.length(); i++) {
                    JSONObject restaurantObject = allRestaurants.getJSONObject(i);
                    restaurant[i].setRes_id(restaurantObject.getString("res_id"));
                    restaurant[i].setRes_name(restaurantObject.getString("res_name"));
                    restaurant[i].setRes_note(restaurantObject.getString("res_note"));
                    restaurant[i].setRes_mark(restaurantObject.getInt("res_mark"));
                    restaurant[i].setRes_picture(restaurantObject.getString("res_picture"));
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static List<Dish> handleDishResponse(String jsonData) {
        Gson gson = new Gson();
        List<Dish> dishes = gson.fromJson(jsonData, new TypeToken<List<Dish>>() {
        }.getType());
        return dishes;
    }
}
