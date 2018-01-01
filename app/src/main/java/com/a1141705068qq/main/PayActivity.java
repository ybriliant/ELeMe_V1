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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    private static String[] price___ = {"a1", "b1", "c1", "d1", "e1"};
    private static String[] account___ = {"a1", "b1", "c1", "d1", "e1"};

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

        Bundle bd=this.getIntent().getExtras();
        name___ = bd.getStringArray("pose_name");
        price___ = bd.getStringArray("pose_price");
        account___ = bd.getStringArray("pose_account");

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

    }

    private void addItemView() {
        for(int i =0;i<num_kind_food;i++) {
            view_add = View.inflate(this, R.layout.item_for_food_in_activity_pay, null);

            TextView name = (TextView) view_add.findViewById(R.id.name_food);
            name.setText(name___[i]);
            //name.setText("1");
            name.setVisibility(View.VISIBLE);

            TextView num = (TextView) view_add.findViewById(R.id.num_of_food);
            num.setText(price___[i]);
            //num.setText("1");
            num.setVisibility(View.VISIBLE);

            TextView sum = (TextView) view_add.findViewById(R.id.sum_price_food);
            sum.setText(account___[i]);
            //sum.setText("1");
            sum.setVisibility(View.VISIBLE);

            pay_menu_object.addView(view_add);
        }
    }

    //获取所有动态添加的DishItem，找到控件的id，获取数据
    private void initdata(){
        for (int i = 0; i < pay_menu_object.getChildCount(); i++) {
            View childAt = pay_menu_object.getChildAt(i);
            TextView name_food = (TextView) childAt.findViewById(R.id.name_food);
            TextView num_of_food = (TextView) childAt.findViewById(R.id.num_of_food);
            TextView sum_price_food = (TextView) childAt.findViewById(R.id.sum_price_food);

            name_food.setText("测试");
            num_of_food.setText("try");
            sum_price_food.setText("try");
        }
    }



    /*private void initPayView(){
        pay_main = (android.support.design.widget.CoordinatorLayout) findViewById(R.id.pay_main);
        pay_menu = (LinearLayout) findViewById(R.id.pay_menu);
        name_restaurant = (TextView)findViewById(R.id.name_restaurant);//
        send_address = (TextView)findViewById(R.id.send_address);//
        custom_name_phone = (TextView)findViewById(R.id.custom_name_phone);//
        sum_price_canhe = (TextView)findViewById(R.id.sum_price_canhe);//
        sum_price_peisong = (TextView)findViewById(R.id.sum_price_peisong);//
        all_fee = (TextView)findViewById(R.id.all_fee);//
        all_fee_two = (TextView)findViewById(R.id.all_fee_two);//
        payfinal = (Button)findViewById(R.id.payfinal);

        Intent intent = getIntent();

        String data_name_restaurant = intent.getStringExtra("name_restaurant");
        String data_send_adress = intent.getStringExtra("send_adress");
        String data_custom_name_phone = intent.getStringExtra("custom_name_phone");
        Integer data_num_of_food = intent.getIntExtra("num_of_food",1);//红圈上的数字
        Integer data_sum_price_food = intent.getIntExtra("sum_price_food",1);//黑栏目上的总价格

        name_restaurant.setText(data_name_restaurant);
        name_restaurant.setVisibility(View.VISIBLE);

        send_address.setText(data_send_adress);
        send_address.setVisibility(View.VISIBLE);

        custom_name_phone.setText(data_custom_name_phone);
        custom_name_phone.setVisibility(View.VISIBLE);

        int a = random_canhefei();
        sum_price_canhe.setText(a);
        sum_price_canhe.setVisibility(View.VISIBLE);

        int b = random_peisongfei();
        sum_price_peisong.setText(b);
        sum_price_peisong.setVisibility(View.VISIBLE);

        //动态添加一个控件
        for(int i = 0;i<data_num_of_food;i++){
            View dishView = View.inflate(this, R.layout.item_for_food_in_activity_pay, null);
            final View childAt = pay_menu.getChildAt(i);
            TextView name_food = (TextView) childAt.findViewById(R.id.name_food);
            TextView num_of_food = (TextView) childAt.findViewById(R.id.num_of_food);
            TextView sum_price_food = (TextView) childAt.findViewById(R.id.sum_price_food);

            //获取所有动态添加的Item，找到控件的id，获取数据
            name_food.setText("yanghan");
            num_of_food.setText("yanghan");
            sum_price_food.setText("yanghan");
        }

        int c = a+b+data_sum_price_food;
        all_fee.setText(c);
        all_fee.setVisibility(View.VISIBLE);
        all_fee_two.setText(c);
        all_fee_two.setVisibility(View.VISIBLE);
    }*/

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
