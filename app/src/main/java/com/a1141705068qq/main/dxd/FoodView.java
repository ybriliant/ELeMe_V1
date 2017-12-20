package com.a1141705068qq.main.dxd;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a1141705068qq.class_one.R;

/**
 * Created by Shinobu on 2017/12/20.
 */

public class FoodView extends LinearLayout {
    public ImageView image;
    public TextView name;
    private TextView price;

    public FoodView(Context context, AttributeSet attrs){
        super(context,attrs);
        View view=LayoutInflater.from(context).inflate(R.layout.image_button,this);
        image=(ImageView)view.findViewById(R.id.food_image);
        name=(TextView)view.findViewById(R.id.name_text);
        price=(TextView)view.findViewById(R.id.cost_text);
     //   setMassage(R.drawable.hbg,"zhizunqixiabao","12");
     //   name.setText("zhizunqixiabao");
     //   image.setImageResource(R.drawable.hbg);
     //   image=(ImageView)findViewById(R.id.food_image);
     //   name=(TextView)findViewById(R.id.name_text);
     //   price=(TextView)findViewById(R.id.cost_text);
    }

    public void setMassage(int image_id,String food_name,String food_price){
        image.setImageResource(image_id);
        name.setText(food_name);
        price.setText(food_price);
    }
}
