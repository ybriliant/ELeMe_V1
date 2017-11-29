package com.example.ggb.eleme.xd;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ggb.eleme.R;

/**
 * Created by Shinobu on 2017/11/29.
 */

public class ImageButton extends LinearLayout {
    private ImageView foodImage;
    private TextView nameText;
    private TextView costText;
    public ImageButton(Context context,AttributeSet attrs){
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.image_button,this);
        this.setClickable(true);
        this.setFocusable(true);
        this.foodImage=(ImageView)findViewById(R.id.food_image);
        this.nameText=(TextView)findViewById(R.id.name_text);
        this.costText=(TextView)findViewById(R.id.cost_text);
    }
    public void setMessage(int imageId,String name,int cost){
        String cos="$"+cost;
        foodImage.setImageResource(imageId);
        nameText.setText(name);
        costText.setText(cos);
    }
}
