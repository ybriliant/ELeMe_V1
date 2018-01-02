package com.a1141705068qq.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a1141705068qq.class_one.R;
import com.a1141705068qq.main.model.Dish;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Administrator on 2017/12/27.
 */

public class PayActivity extends Activity{

    private TextView send_address;
    private TextView name_restaurant;
    private TextView custom_name_phone;
    private TextView sum_price_canhe;
    private TextView sum_price_peisong;
    private TextView all_fee;
    private TextView arrive_time;
    private TextView all_fee_two;
    private Button payfinal;
    private LinearLayout pay_menu_object;
    private View view_add;
    public  int num_kind_food;
    private static String[] name___ = {"a1", "b1", "c1", "d1", "e1"};
    private static String[] account___ = {"a1", "b1", "c1", "d1", "e1"};
    private static String[] price___ = {"a1", "b1", "c1", "d1", "e1"};
    private Map<Dish,Integer>  single_goods;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        pay_menu_object = (LinearLayout) findViewById(R.id.pay_menu);
        name_restaurant = (TextView)findViewById(R.id.name_restaurant);//
        send_address = (TextView)findViewById(R.id.send_address);//
        custom_name_phone = (TextView)findViewById(R.id.custom_name_phone);//
        sum_price_canhe = (TextView)findViewById(R.id.sum_price_canhe);//
        sum_price_peisong = (TextView)findViewById(R.id.sum_price_peisong);//
        arrive_time = (TextView)findViewById(R.id.arrive_time);
        all_fee = (TextView)findViewById(R.id.all_fee);//
        all_fee_two = (TextView)findViewById(R.id.all_fee_two);//
        payfinal = (Button)findViewById(R.id.payfinal);

        Intent intent = getIntent();

        String data_name_restaurant = intent.getStringExtra("name_restaurant");
        String data_send_adress = intent.getStringExtra("send_adress");
        String data_custom_name_phone = intent.getStringExtra("custom_name_phone");
        Double data_sum_price_food = intent.getDoubleExtra("price_food",1.0);//黑栏目上的总价格
        num_kind_food = intent.getIntExtra("num_kind_of_food",1);

        int j = 0;
        single_goods = (Map<Dish,Integer>) intent.getSerializableExtra("singlemessage");

        for(Map.Entry<Dish,Integer> vo : single_goods.entrySet()){
            name___[j] = vo.getKey().getDishName();
            price___[j] = String.valueOf((vo.getKey().getDishAmount()-vo.getKey().getDishRemain())*vo.getKey().getDishPrice());
            account___[j] = String.valueOf((vo.getKey().getDishAmount()-vo.getKey().getDishRemain()));
            j=j+1;
        }

        name_restaurant.setText(data_name_restaurant);
        name_restaurant.setVisibility(View.VISIBLE);

        send_address.setText(data_send_adress);
        send_address.setVisibility(View.VISIBLE);

        custom_name_phone.setText(data_custom_name_phone);
        custom_name_phone.setVisibility(View.VISIBLE);

        long l = System.currentTimeMillis();
        Random rand = new Random();
        int randTime = rand.nextInt(12)+24;
        l +=randTime*60*1000;
        Date date = new Date(l);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        arrive_time.setText(dateFormat.format(date));
        arrive_time.setVisibility(View.VISIBLE);

        int a = random_canhefei();
        sum_price_canhe.setText(String.valueOf(a));
        sum_price_canhe.setVisibility(View.VISIBLE);

        int b = random_peisongfei();
        sum_price_peisong.setText(String.valueOf(b));
        sum_price_peisong.setVisibility(View.VISIBLE);


        double c = a+b+data_sum_price_food;
        all_fee.setText(String.valueOf(c));
        all_fee.setVisibility(View.VISIBLE);
        all_fee_two.setText(String.valueOf(c));
        all_fee_two.setVisibility(View.VISIBLE);

        addItemView();

        payfinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent payintent = new Intent(PayActivity.this,.class);
            }
        });
    }

    private void addItemView() {

        for(int i =0;i<num_kind_food;i++) {
            view_add = View.inflate(this, R.layout.item_for_food_in_activity_pay, null);

            TextView name = (TextView) view_add.findViewById(R.id.name_food);
            name.setText(name___[i]);
            //name.setText("1");
            name.setVisibility(View.VISIBLE);

            TextView num = (TextView) view_add.findViewById(R.id.num_of_food);
            num.setText(account___[i]);
            //num.setText("1");
            num.setVisibility(View.VISIBLE);

            TextView sum = (TextView) view_add.findViewById(R.id.sum_price_food);
            sum.setText(price___[i]);
            //sum.setText("1");
            sum.setVisibility(View.VISIBLE);

            pay_menu_object.addView(view_add);
        }
    }

    private int random_canhefei(){
        Random rand = new Random();
        int randNum = rand.nextInt(2);
        return randNum;
    }

    private int random_peisongfei(){
        Random rand = new Random();
        int randNum = rand.nextInt(3);
        return randNum;
    }
}
