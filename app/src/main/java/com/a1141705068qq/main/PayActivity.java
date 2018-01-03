package com.a1141705068qq.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a1141705068qq.class_one.R;
import com.a1141705068qq.main.model.Dish;
import com.a1141705068qq.main.util.HttpUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/12/27.
 */

public class PayActivity extends Activity{

    private TextView send_address;
    private TextView name_restaurant;
    private TextView sum_price_canhe;
    private TextView sum_price_peisong;
    private TextView all_fee;
    private TextView arrive_time;
    private TextView all_fee_two;
    private Button payfinal;
    private ImageButton back;
    private LinearLayout pay_menu_object;
    private View view_add;
    private TextView custom_name_phone;
    private SharedPreferences upref;
    private String user_name;
    private String user_phone;
    private String user_id;
    public  int num_kind_food;
    private static String[] name___ = {"a1", "b1", "c1", "d1", "e1"};
    private static String[] account___ = {"a1", "b1", "c1", "d1", "e1"};
    private static String[] price___ = {"a1", "b1", "c1", "d1", "e1"};
    private Map<Dish,Integer>  single_goods;
    private String data_name_restaurant;
    private String sum;
    private StringBuffer dishes=new StringBuffer();
    private String user_loc;


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
        back = (ImageButton) findViewById(R.id.activity_pay_back);
        payfinal = (Button)findViewById(R.id.payfinal);
        upref=getSharedPreferences("user", Context.MODE_PRIVATE);
        user_name=upref.getString("user_name",null);
        user_phone=upref.getString("user_phone",null);
        user_id=upref.getString("user_id",null);
        user_loc=upref.getString("user_location",null);
        if(user_loc!=null) {
            send_address.setText(user_loc);
            send_address.setVisibility(View.VISIBLE);
        }
        Intent intent = getIntent();

        data_name_restaurant = intent.getStringExtra("name_restaurant");

        if(user_name!=null)
            custom_name_phone.setText(user_name+":"+user_phone);
        Double data_sum_price_food = intent.getDoubleExtra("price_food",1.0);//黑栏目上的总价格

        int j = 0;
        single_goods = (Map<Dish,Integer>) intent.getSerializableExtra("singlemessage");

        for(Map.Entry<Dish,Integer> vo : single_goods.entrySet()){
            name___[j] = vo.getKey().getDishName();
            price___[j] = String.valueOf((vo.getKey().getDishAmount()-vo.getKey().getDishRemain())*vo.getKey().getDishPrice());
            account___[j] = String.valueOf((vo.getKey().getDishAmount()-vo.getKey().getDishRemain()));
            j=j+1;
        }
        num_kind_food=j;

        name_restaurant.setText(data_name_restaurant);
        name_restaurant.setVisibility(View.VISIBLE);


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
        sum=String.valueOf(c);
        all_fee.setText(sum);
        all_fee.setVisibility(View.VISIBLE);
        all_fee_two.setText(String.valueOf(c));
        all_fee_two.setVisibility(View.VISIBLE);

        addItemView();

        payfinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequset();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void addItemView() {

        for(int i =0;i<num_kind_food;i++) {
            view_add = View.inflate(this, R.layout.item_for_food_in_activity_pay, null);

            TextView name = (TextView) view_add.findViewById(R.id.name_food);
            name.setText(name___[i]);
            name.setVisibility(View.VISIBLE);
            dishes.append("+"+name___[i]);

            TextView num = (TextView) view_add.findViewById(R.id.num_of_food);
            num.setText(account___[i]);
            dishes.append("x"+account___[i]);
            num.setVisibility(View.VISIBLE);

            TextView sum = (TextView) view_add.findViewById(R.id.sum_price_food);
            sum.setText(price___[i]);
            //sum.setText("1");
            sum.setVisibility(View.VISIBLE);

            pay_menu_object.addView(view_add);
        }
        if(dishes!=null)
            dishes.deleteCharAt(0);
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

    private void sendRequset(){
        String url="http://67.216.210.216/showorders.php";
        RequestBody requestBody=new FormBody.Builder()
                .add("userid",user_id)
                .add("resname",data_name_restaurant)
                .add("ordersum",sum)
                .add("disname",dishes.toString())
                .build();
        HttpUtil.postOkHttpRequest(url, requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(PayActivity.this,"点菜失败，请检查网络",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText=response.body().string();
                Log.i("PayActivity","response:"+responseText);
                if(responseText.equals("success!")){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(PayActivity.this,"支付成功",Toast.LENGTH_SHORT).show();
                        }
                    });
                    Intent intent=new Intent();
                    intent.putExtra("return_state",1);
                    setResult(RESULT_OK,intent);
                    finish();
                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(PayActivity.this,"点餐失败",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
